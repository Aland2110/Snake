import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable {
    private int ORIGINALSIZE = 25;
    private int SCALE = 2;
    public int TILESIZE = ORIGINALSIZE * SCALE;
    public int SCREENWIDTH;
    public int SCREENHEIGHT;
    private int FPS = 5;
    public int appleX;
    public int appleY;
    private BufferedImage Æble;
    public int n;
    public int m;

    Random random;
    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    Snake snake = new Snake(this, keyH);

    public GamePanel(int n, int m) {
        this.n = n < 5 || n > 100 ? 20 : n;
        this.m = m < 5 || m > 100 ? 20 : m;
        this.SCREENWIDTH = TILESIZE * n;
        this.SCREENHEIGHT = TILESIZE * m;
        this.random = new Random();
        snake.snakePos();
        this.setPreferredSize(new Dimension(n * TILESIZE, m * TILESIZE)); // vinduets størrelse defineres
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true); // performance (loader i baggrunden)
        this.addKeyListener(keyH);
        this.setFocusable(true); // gamePanel fokuserer på at modtage tastatur-input
    }

    // spillet startes
    public void startGame() {
        getImage();
        snake.getPlayerImage();
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

    // metoden bruger "ImageIO.read" til at læse filerne fra classpath og de er gemt
    // i BufferedImage variablerne
    public void getImage() {
        try {
            Æble = ImageIO.read(getClass().getResourceAsStream("/Player/Æble.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // registrerer spillerens bevægelse
    // Optimer senere
    public void update() {
        snake.update();
        snake.appleEat();
        checkCollision();
    }

    // paintComponent er en indbygget metode i JPanel
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (gameThread != null) {
            // griddet tegnes
            g.setColor(Color.WHITE);
            for (int i = 0; i < m; i++) {
                g.drawLine(0, i * TILESIZE, SCREENWIDTH, i * TILESIZE);
            }
            for (int j = 0; j < n; j++) {
                g.drawLine(j * TILESIZE, 0, j * TILESIZE, SCREENHEIGHT);

            }
            snake.draw(g);
            // æble tegnes med vores egen tegning
            g.drawImage(Æble, appleX, appleY, TILESIZE, TILESIZE, null);

            g.setColor(Color.RED);
            g.setFont(new Font("Ink Free", Font.BOLD, 50));
            FontMetrics metrics = getFontMetrics(g.getFont());
            g.drawString("Score: " + snake.appleEaten,
                    (SCREENWIDTH - metrics.stringWidth("Score: " + snake.appleEaten)) / 2,
                    TILESIZE);
        } else {
            gameOver(g);
        }

    }

    // Finder random koordinater til æblet
    public void createApple() {
        int tempX = random.nextInt(n) * TILESIZE;
        int tempY = random.nextInt(m) * TILESIZE;
        int[] x = snake.getXarr();
        int[] y = snake.getYarr();

        // tjekker om koordinaterne til æblet er i slangen
        for (int i = 0; i < snake.bodyParts; i++) {
            while (x[i] == tempX && y[i] == tempY) {
                tempX = random.nextInt(n) * TILESIZE;
                tempY = random.nextInt(m) * TILESIZE;
            }
        }

        appleX = tempX;
        appleY = tempY;
    }

    // Tjekker collision med sig selv
    public void checkCollision() {
        int[] x = snake.getXarr();
        int[] y = snake.getYarr();

        for (int i = snake.bodyParts; i > 0; i--) {
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
    public void gameOver(Graphics g) {
        g.setColor(Color.RED);
        g.setFont(new Font("Ink Free", Font.BOLD, 75));
        FontMetrics metrics = getFontMetrics(g.getFont());
        g.drawString("Game Over", (SCREENWIDTH - metrics.stringWidth("Game Over")) / 2, SCREENHEIGHT / 2);
        g.drawString("Point: " + snake.appleEaten,
                (SCREENWIDTH - metrics.stringWidth("Point: " + snake.appleEaten)) / 2,
                SCREENHEIGHT / 4);
    }
}