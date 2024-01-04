import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Random;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable {
    private int ORIGINALSIZE = 25; // 16x16 tiles
    private int SCALE = 2;
    private int SCREENCOL;
    private int SCREENROW;
    private int TILESIZE = ORIGINALSIZE * SCALE; // 50x50 tiles
    private int SCREENWIDTH;
    private int SCREENHEIGHT;
    private int[] x; // slangens x-koordinater
    private int[] y; // slangens y-koordinater
    private int FPS = 1;
    private int appleX;
    private int appleY;
    private int bodyParts = 2;
    private int appleEaten = 0;

    Random random;
    KeyHandler keyH = new KeyHandler();
    Thread gameThread;

    public GamePanel(int n, int m) {
        this.SCREENCOL = n < 5 || n > 100 ? 20 : n;
        this.SCREENROW = m < 5 || m > 100 ? 20 : m;
        this.SCREENWIDTH = TILESIZE * SCREENCOL;
        this.SCREENHEIGHT = TILESIZE * SCREENROW;
        this.x = new int[(SCREENWIDTH * SCREENHEIGHT) / TILESIZE];
        this.y = new int[(SCREENWIDTH * SCREENHEIGHT) / TILESIZE];
        this.setPreferredSize(new Dimension(n * TILESIZE, m * TILESIZE)); // vinduets størrelse defineres
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true); // performance (loader i baggrunden)
        this.addKeyListener(keyH);
        this.setFocusable(true); // gamePanel fokuserer på at modtage tastatur-input
        this.random = new Random();
        // slangen placeres i midten af banen
        for (int i = 0; i < bodyParts; i++) {
            x[i] = (n / 2 - i) * TILESIZE;
            y[i] = m / 2 * TILESIZE;
        }
    }

    // spillet startes
    public void startGame() {
        gameThread = new Thread(this);
        gameThread.start();
        createApple();
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

    // registrerer spillerens bevægelse
    public void update() {
        for (int i = bodyParts; i > 0; i--) {
            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }
        // Sørger for at kun en retning kan vælges.
        synchronized (keyH) {
            if (keyH.getDir() == 'W') {
                y[0] -= TILESIZE;
            } else if (keyH.getDir() == 'S') {
                y[0] += TILESIZE;
            } else if (keyH.getDir() == 'A') {
                x[0] -= TILESIZE;
            } else if (keyH.getDir() == 'D') {
                x[0] += TILESIZE;
            }
        }
        appleEat();
        checkCollision();
    }

    // paintComponent er en indbygget metode i JPanel
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        if (gameThread != null) {
            // griddet tegnes
            g2.setColor(Color.WHITE);
            for (int i = 0; i < SCREENROW; i++) {
                g2.drawLine(0, i * TILESIZE, SCREENWIDTH, i * TILESIZE);
            }
            for (int j = 0; j < SCREENCOL; j++) {
                g2.drawLine(j * TILESIZE, 0, j * TILESIZE, SCREENHEIGHT);

            }

            // æble tegnes
            g2.setColor(Color.RED);
            g2.fillOval(appleX, appleY, TILESIZE, TILESIZE);

            // slange tegnes
            for (int i = 0; i < bodyParts; i++) {
                if (i == 0) {
                    g2.setColor(Color.PINK);
                    g2.fillRect(x[i], y[i], TILESIZE, TILESIZE);
                } else {
                    g2.setColor(Color.GREEN);
                    g2.fillRect(x[i], y[i], TILESIZE, TILESIZE);
                }
                g2.setColor(Color.RED);
                g2.setFont(new Font("Ink Free", Font.BOLD, 50));
                FontMetrics metrics = getFontMetrics(g2.getFont());
                g2.drawString("Score: " + appleEaten, (SCREENWIDTH - metrics.stringWidth("Score: " + appleEaten)) / 2,
                        TILESIZE);
            }
        } else {
            gameOver(g2);
        }

    }

    // Finder random koordinater til æblet
    public void createApple() {
        int tempX = random.nextInt(SCREENCOL) * TILESIZE;
        int tempY = random.nextInt(SCREENROW) * TILESIZE;

        // tjekker om koordinaterne til æblet er i slangen
        for (int i = 0; i < bodyParts; i++) {
            while (x[i] == tempX && y[i] == tempY) {
                tempX = random.nextInt(SCREENCOL) * TILESIZE;
                tempY = random.nextInt(SCREENROW) * TILESIZE;
            }
        }

        appleX = tempX;
        appleY = tempY;
    }

    // Tjekker om hoved er ved æble
    public void appleEat() {
        if (y[0] == appleY && x[0] == appleX) {
            bodyParts++;
            appleEaten++;
            createApple();
        }
    }

    // Tjekker collision med sig selv
    public void checkCollision() {
        for (int i = 1; i < bodyParts; i++) {
            if (y[0] == y[i] && x[0] == x[i]) {
                gameThread = null;
            }
        }
        // Tjekker om slangens hovedet er inden for banen.
        if (x[0] < 0 || x[0] >= SCREENWIDTH || y[0] < 0 || y[0] >= SCREENHEIGHT) {
            gameThread = null;
        }
    }

    // Funktion der tegner game over stats og tekst
    public void gameOver(Graphics2D g2) {
        g2.setColor(Color.RED);
        g2.setFont(new Font("Ink Free", Font.BOLD, 75));
        FontMetrics metrics = getFontMetrics(g2.getFont());
        g2.drawString("Game Over", (SCREENWIDTH - metrics.stringWidth("Game Over")) / 2, SCREENHEIGHT / 2);
        g2.drawString("Point: " + appleEaten, (SCREENWIDTH - metrics.stringWidth("Point: " + appleEaten)) / 2,
                SCREENHEIGHT / 4);
    }
}