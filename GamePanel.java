import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Random;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable {
    // Screen size & resolution
    public int TILESIZE = 100;
    public int GAMEWIDTH;
    public int GAMEHEIGHT;
    public int n;
    public int m;

    // Game running variables
    private int FPS;
    private boolean gameRunning;
    private double drawInterval;
    private double nextDrawTime;

    // Game State
    private int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;

    // Musikken
    Sound sound = new Sound();

    // Objects needed
    Random random;
    KeyHandler keyH = new KeyHandler(this);
    Thread gameThread;
    Entity entity = new Entity(this, keyH);
    UI UI = new UI(this, entity);

    public GamePanel(int n, int m, int s) {
        this.random = new Random();
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true); // performance (loader i baggrunden)
        this.addKeyListener(keyH);
        this.setFocusable(true); // gamePanel fokuserer på at modtage tastatur-input
        this.gameState = s;
        settings(n, m);
    }

    public void settings(int n, int m) {
        this.n = n < 5 || n > 100 ? 20 : n;
            this.m = m < 5 || m > 100 ? 20 : m;
            this.GAMEWIDTH = TILESIZE * n;
            this.GAMEHEIGHT = TILESIZE * m;
    
            while (GAMEWIDTH > 1400 || GAMEHEIGHT > 700) {
                TILESIZE -= 1;
                this.GAMEWIDTH = TILESIZE * n;
                this.GAMEHEIGHT = TILESIZE * m;
            }
    
            this.setPreferredSize(new Dimension(GAMEWIDTH, GAMEHEIGHT)); // spilets størrelse defineres
            this.setMaximumSize(new Dimension(GAMEWIDTH, GAMEHEIGHT));
            this.setMinimumSize(new Dimension(GAMEWIDTH, GAMEHEIGHT));
            this.FPS = 5;
        }

    public void setUpGame() {
        entity.snakePos();
        // PlayMusic(0);
    }

    // spillet startes
    public void startGame() {
        gameRunning = true;
        settings(n, m);
        this.drawInterval = 1000000000 / FPS;
        setUpGame();
        entity.getImage();
        gameThread = new Thread(this);
        gameThread.start();
        entity.createApple();
    }

    @Override
    public void run() {
        this.drawInterval = 1000000000 / FPS;
        this.nextDrawTime = System.nanoTime() + drawInterval;

        // mens spillet kører, opdateres og tegnes vinduet
        while (gameThread != null) {
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

    // paintComponent er en indbygget metode i JPanel
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (gameRunning) {
            if (gameState != titleState) {
                entity.draw(g); // Slangen og æblet tegnes
                UI.drawPlay(g);
            } else {
                UI.drawTitleScreen(g);
            }
        } else {
            UI.drawGameOver(g);
        }
    }

    public void checkCollision() {
        int[] x = entity.getXarr();
        int[] y = entity.getYarr();

        // Tjekker collision med sig selv
        for (int i = entity.bodyParts; i > 0; i--) {
            if (y[0] == y[i] && x[0] == x[i]) {
                gameRunning = false;
            }
        }

        // Tjekker om slangens hovedet er inden for banen.
        if (x[0] < 0 || x[0] >= GAMEWIDTH || y[0] < 0 || y[0] >= GAMEHEIGHT) {
            gameRunning = false;
        }
    }

    public int getGameState() {
        return gameState;
    }

    public void setGameState(int n) {
        gameState = n;
    }

    public void PlayMusic(int i) {

        sound.setFile(i);
        sound.play();
        sound.loop();
    }

    public void stopMusic() {
        sound.stop();
    }

    public void PlaySE(int i) {
        sound.setFile(i);
        sound.play();
    }

    public void screenFit() {
        while (GAMEWIDTH > 1200 && GAMEHEIGHT > 500) {
            TILESIZE -= 1;
        }
    }
}