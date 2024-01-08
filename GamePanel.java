import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Random;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable {
    // Screen size & resolution
    private int ORIGINALSIZE = 25;
    private int SCALE = 2;
    public int TILESIZE = ORIGINALSIZE * SCALE;
    public int SCREENWIDTH;
    public int SCREENHEIGHT;
    public int n;
    public int m;

    // Game running variables
    private int FPS = 5;
    private boolean gameRunning;

    // Game State
    private int gameState;
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

    public GamePanel(int n, int m) {
        this.n = n < 5 || n > 100 ? 20 : n;
        this.m = m < 5 || m > 100 ? 20 : m;
        this.SCREENWIDTH = TILESIZE * n;
        this.SCREENHEIGHT = TILESIZE * m;
        this.random = new Random();
        entity.snakePos();
        this.setPreferredSize(new Dimension(n * TILESIZE, m * TILESIZE)); // vinduets størrelse defineres
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true); // performance (loader i baggrunden)
        this.addKeyListener(keyH);
        this.setFocusable(true); // gamePanel fokuserer på at modtage tastatur-input
    }

    public void setUpGame() {
        gameState = playState;

        PlayMusic(0);
    }

    // spillet startes
    public void startGame() {
        gameRunning = true;
        setUpGame();
        entity.getImage();
        gameThread = new Thread(this);
        gameThread.start();
        entity.createApple();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000 / FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;

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
        // Spillet bliver kører
        if (gameState == playState) {
            entity.update();
            checkCollision();
        } else {
            // Hvis spillet bliver pauset, sker der ingenting
        }
    }

    // paintComponent er en indbygget metode i JPanel
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (gameRunning) {
            // griddet tegnes
            g.setColor(Color.WHITE);
            for (int i = 0; i < m; i++) {
                g.drawLine(0, i * TILESIZE, SCREENWIDTH, i * TILESIZE);
            }
            for (int j = 0; j < n; j++) {
                g.drawLine(j * TILESIZE, 0, j * TILESIZE, SCREENHEIGHT);

            }
            entity.draw(g); // Slangen og æblet tegnes
            UI.draw(g);
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
        if (x[0] < 0 || x[0] >= SCREENWIDTH || y[0] < 0 || y[0] >= SCREENHEIGHT) {
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
}