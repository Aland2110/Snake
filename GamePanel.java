import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.util.Random;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable {
    // Screen size & resolution
    public int TILESIZE = 100;
    public int GAMEWIDTH;
    public int GAMEHEIGHT;
    public int n = 20;
    public int m = 20;
    public int gameWindowXStart;
    public int gameWindowYStart;

    // Game running variables
    private int FPS = 60;
    private double drawInterval;
    private double nextDrawTime;

    // Game States
    private int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int gameOverState = 3;
    public final int settingState = 4;

    // Musikken
    Sound sound = new Sound();

    // Objects needed
    Random random;
    KeyHandler keyH = new KeyHandler(this);
    Thread gameThread;
    Entity entity = new Entity(this, keyH);
    UI UI = new UI(this);
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    public GamePanel(int n, int m, int s) {
        this.random = new Random();
        this.setPreferredSize(screenSize); // spilets størrelse defineres
        this.setBackground(Color.PINK);
        this.setDoubleBuffered(true); // performance (loader i baggrunden)
        this.addKeyListener(keyH);
        this.setFocusable(true); // gamePanel fokuserer på at modtage tastatur-input
        this.gameState = s;
    }

    public void settings() {
        this.GAMEWIDTH = TILESIZE * n;
        this.GAMEHEIGHT = TILESIZE * m;
        this.gameWindowXStart = ((int) screenSize.getWidth() - GAMEWIDTH) / 2;
        this.gameWindowYStart = ((int) screenSize.getHeight() - GAMEHEIGHT) / 2;

        while (GAMEWIDTH > (int) screenSize.getWidth() - TILESIZE * 4
                || GAMEHEIGHT > (int) screenSize.getHeight() - TILESIZE * 20) {
            TILESIZE -= 1;
            this.GAMEWIDTH = TILESIZE * n;
            this.GAMEHEIGHT = TILESIZE * m;
            this.gameWindowXStart = ((int) screenSize.getWidth() - GAMEWIDTH) / 2;
            this.gameWindowYStart = ((int) screenSize.getHeight() - GAMEHEIGHT) / 2;
        }
        while (gameWindowXStart % TILESIZE != 0 || gameWindowYStart % TILESIZE != 0) {
            if (gameWindowXStart % TILESIZE != 0) {
                gameWindowXStart++;
            }
            if (gameWindowYStart % TILESIZE != 0) {
                gameWindowYStart++;
            }
        }
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    // spillet startes
    public void startGame() {
        settings();
        entity.applesEaten = 0;
        entity.snakePos();
        entity.getImage();
        entity.createApple();
    }

    @Override
    public void run() {

        // mens spillet kører, opdateres og tegnes vinduet
        while (gameThread != null) {
            updateFPS();
            this.drawInterval = 1000000000 / FPS;
            this.nextDrawTime = System.nanoTime() + drawInterval;

            update();
            repaint(); // kalder metoden paintComponent

            // Sleep method
            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime /= 1000000;
                remainingTime = remainingTime < 0 ? 0 : remainingTime;

                Thread.sleep((long) remainingTime);

                nextDrawTime += drawInterval;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // Optimer senere
    public void update() {
        // Spillet bliver kørt
        if (gameState == playState) {
            entity.update();
            checkCollision();
        } else if (gameState == pauseState) {
            // Hvis spillet bliver pauset, sker der ingenting
        }
    }

    public void updateFPS() {
        if (gameState != playState) {
            FPS = 60;
        } else {
            FPS = 5;
        }
    }

    // paintComponent er en indbygget metode i JPanel
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (gameState == playState || gameState == pauseState) {
            UI.drawLevel(g);
            UI.drawScore(g);
            entity.draw(g); // Slangen og æblet tegnes
            UI.drawPause(g);
        }
        if (gameState == titleState) {
            UI.drawTitleScreen(g);
        }
        if (gameState == gameOverState) {
            stopMusic(0);
            UI.drawGameOver(g);
        }
        if (gameState == settingState) {
            UI.drawSettingScreen(g);
        }
    }

    public void checkCollision() {
        int[] x = entity.getXarr();
        int[] y = entity.getYarr();

        // Tjekker collision med sig selv
        for (int i = entity.bodyParts; i > 0; i--) {
            if (y[0] == y[i] && x[0] == x[i]) {
                gameState = gameOverState;
                entity.snakePos();
            }
        }

        // Tjekker om slangens hovedet er inden for banen.
        if (x[0] < gameWindowXStart || x[0] >= gameWindowXStart + GAMEWIDTH || y[0] < gameWindowYStart
                || y[0] >= gameWindowYStart + GAMEHEIGHT) {
            gameState = gameOverState;
            entity.snakePos();
        }
    }

    public int getGameState() {
        return gameState;
    }

    public void setGameState(int n) {
        gameState = n;
    }

    public void setGameSize(boolean b, int settingCommandNum) {
        if (settingCommandNum == 0) {
            if (b) {
                n += 2;
            } else {
                n -= 2;
            }
        } else if (settingCommandNum == 1) {
            if (b) {
                m += 2;
            } else {
                m -= 2;
            }
        }
    }

    public void playMusic(int i) {
        sound.setFile(i, UI.volume - 80);
        sound.play(i);
        sound.loop(i);
    }

    public void stopMusic(int i) {
        sound.stop(i);
    }

    public void PlaySE(int i) {
        sound.setFile(i, UI.volume - 80);
        sound.play(i);
    }

    public void changeVolume(boolean b) {
        if (b) {
            UI.volume += 2;
        } else {
            UI.volume -= 2;
        }
    }
}