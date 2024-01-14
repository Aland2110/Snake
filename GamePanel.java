import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Random;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable {
    // skærmstørrelse og opløsning
    public int TILESIZE = 100;
    public int GAMEWIDTH;
    public int GAMEHEIGHT;
    public int n = 20;
    public int m = 20;
    public int gameWindowXStart;
    public int gameWindowYStart;

    // spilkørende variabler
    public int tempFPS = 5;
    private int FPS = 60;
    private double drawInterval;
    private double nextDrawTime;

    // game States
    private int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int gameOverState = 3;
    public final int settingState = 4;

    // nødvendige objekter
    Random random;
    KeyHandler keyH = new KeyHandler(this);
    Thread gameThread;
    Entity entity = new Entity(this, keyH);
    UI UI = new UI(this);
    Sound sound = new Sound();

    public GamePanel() {
        this.random = new Random();
        this.setPreferredSize(new Dimension((int) UI.SCREENWIDTH, (int) UI.SCREENHEIGHT)); // vinduets størrelse
        this.setDoubleBuffered(true); // performance (loader i baggrunden)
        this.addKeyListener(keyH);
        this.setFocusable(true); // gamePanel fokuserer på at modtage tastatur-input
        this.gameState = titleState;
    }

    // størrelsen af banen tilpasses til vinduet
    public void settings() {
        this.GAMEWIDTH = TILESIZE * n;
        this.GAMEHEIGHT = TILESIZE * m;
        this.gameWindowXStart = (int) (UI.SCREENWIDTH - GAMEWIDTH) / 2;
        this.gameWindowYStart = (int) (UI.SCREENHEIGHT - GAMEHEIGHT) / 2;

        while (GAMEWIDTH > UI.SCREENWIDTH - TILESIZE * 4
                || GAMEHEIGHT > UI.SCREENHEIGHT - TILESIZE * 20) {
            TILESIZE -= 1;
            this.GAMEWIDTH = TILESIZE * n;
            this.GAMEHEIGHT = TILESIZE * m;
            this.gameWindowXStart = (int) (UI.SCREENWIDTH - GAMEWIDTH) / 2;
            this.gameWindowYStart = (int) (UI.SCREENHEIGHT - GAMEHEIGHT) / 2;
        }
        while (GAMEWIDTH < TILESIZE * 6 || GAMEHEIGHT < TILESIZE * 6) {
            TILESIZE += 1;
            this.GAMEWIDTH = TILESIZE * n;
            this.GAMEHEIGHT = TILESIZE * m;
            this.gameWindowXStart = (int) (UI.SCREENWIDTH - GAMEWIDTH) / 2;
            this.gameWindowYStart = (int) (UI.SCREENHEIGHT - GAMEHEIGHT) / 2;
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

    // spillets tråd startes
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

    // spillet kører
    @Override
    public void run() {
        while (gameThread != null) {
            updateFPS(tempFPS);
            this.drawInterval = 1000000000 / FPS;
            this.nextDrawTime = System.nanoTime() + drawInterval;

            update(); // mens spillet kører, opdateres og tegnes vinduet
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

    public void update() {
        // Spillet bliver spillet
        if (gameState == playState) {
            entity.update();
            checkCollision();
        } else if (gameState == pauseState) {
            // Hvis spillet bliver pauset, sker der ingenting
        }
    }

    // spillets FPS ændres afhængigt af om man er i spil eller ej
    public void updateFPS(int n) {
        if (gameState != playState) {
            FPS = 60;
        } else {
            FPS = n;
        }
    }

    // paintComponent er en indbygget metode i JPanel, der bruges til at tegne
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // alt spilrelateret tegnes
        if (gameState == playState || gameState == pauseState) {
            this.setBackground(Color.decode("#2AB9C6"));
            UI.drawLevel(g);
            UI.drawScore(g);
            entity.draw(g);
            UI.drawPause(g);
        }
        // menuskærmen tegnes
        if (gameState == titleState) {
            this.setBackground(Color.decode("#852AC6"));
            UI.drawTitleScreen(g);
        }
        // gameoverskærmen tegnes
        if (gameState == gameOverState) {
            this.setBackground(Color.decode("#852AC6"));
            stopMusic(0);
            UI.drawGameOver(g);
        }
        // indstillingskærmen tegnes
        if (gameState == settingState) {
            UI.drawSettingScreen(g);
        }
    }

    // tjekker om spilleren kolliderer med noget
    public void checkCollision() {
        int[] x = entity.getXarr();
        int[] y = entity.getYarr();

        // Tjekker collision med sig selv
        for (int i = entity.bodyParts; i > 0; i--) {
            if (y[0] == y[i] && x[0] == x[i]) {
                playSE(2);
                gameState = gameOverState;
                entity.snakePos();
            }
        }

        // der slangen kan gp "igennem" væggene på easy mode ellers dør den
        if (UI.difficulty == 0) {
            if (x[0] < gameWindowXStart) {
                x[0] = gameWindowXStart + GAMEWIDTH - TILESIZE;
            }
            if (x[0] >= gameWindowXStart + GAMEWIDTH) {
                x[0] = gameWindowXStart;
            }
            if (y[0] < gameWindowYStart) {
                y[0] = gameWindowYStart + GAMEHEIGHT - TILESIZE;
            }
            if (y[0] >= gameWindowYStart + GAMEHEIGHT) {
                y[0] = gameWindowYStart;
            }
        } else {
            if (x[0] < gameWindowXStart || x[0] >= gameWindowXStart + GAMEWIDTH || y[0] < gameWindowYStart
                    || y[0] >= gameWindowYStart + GAMEHEIGHT) {
                playSE(2);
                gameState = gameOverState;
                entity.snakePos();
            }
        }
    }

    // metode til at returnere game state
    public int getGameState() {
        return gameState;
    }

    // metode til at ændre game state
    public void setGameState(int n) {
        gameState = n;
    }

    // ændr på banens størrelse i spillet
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

    // ændr på sværhedsgraden af spillet i indstillinger
    public void changeDifficulty() {
        tempFPS = (n + m) / 6;
        if (UI.difficulty == 0) {
            tempFPS -= tempFPS / 2;
        } else if (UI.difficulty == 1) {
            tempFPS = (n + m) / 6;
        } else if (UI.difficulty == 2) {
            tempFPS += tempFPS / 2;
        }
    }

    // adspil musik i loop fra array af musik
    public void playMusic(int i) {
        int v = UI.volume - 80;
        if (v > 6) {
            v = 6;
        }
        sound.setFile(i, v);
        sound.play(i);
        sound.loop(i);
    }

    // stop en specifik musik
    public void stopMusic(int i) {
        sound.stop(i);
    }

    // afspil lydeffekt
    public void playSE(int i) {
        int v = UI.volume - 80;
        if (v > 6) {
            v = 6;
        }
        sound.setFile(i, v);
        sound.play(i);
    }

    // ændr på lyd i indstillinger
    public void changeVolume(boolean b) {
        if (b) {
            UI.volume += 2;
        } else {
            UI.volume -= 2;
        }
    }
}
