import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Random;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable {
    static final int ORIGINALSIZE = 25; // 16x16 tiles
    static final int SCALE = 2;

    static final int TILESIZE = ORIGINALSIZE * SCALE; // 50x50 tiles
    static final int SCREENCOL = 20;
    static final int SCREENROW = 20;
    static final int SCREENWIDTH = TILESIZE * SCREENCOL; // 500 pixels
    static final int SCREENHEIGHT = TILESIZE * SCREENROW; // 500 pixels
    static final int[] x = new int[(SCREENWIDTH * SCREENHEIGHT) / TILESIZE]; // slangens x-koordinater
    static final int[] y = new int[(SCREENWIDTH * SCREENHEIGHT) / TILESIZE]; // slangens y-koordinater

    final int FPS = 60;

    int appleX;
    int appleY;

    Random random;

    KeyHandler keyH = new KeyHandler();
    Thread gameThread;

    public GamePanel() {
        this.setPreferredSize(new Dimension(SCREENWIDTH, SCREENHEIGHT)); // vinduets størrelse defineres
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true); // performance (loader i baggrunden)
        this.addKeyListener(keyH);
        this.setFocusable(true); // gamePanel fokuserer på at modtage tastatur-input
        this.random = new Random();
    }

    // spillets "tid" startes
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
    
    }

    // paintComponent er en indbygget metode i JPanel
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        // firkant tegnes
        g2.setColor(Color.WHITE);

        // griddet tegnes
        for (int i = 0; i < SCREENCOL; i++) {
            g2.drawLine(i * TILESIZE, 0, i * TILESIZE, SCREENHEIGHT);
            g2.drawLine(0, i * TILESIZE, SCREENWIDTH, i * TILESIZE);
        }
        
        // Æble tegnes
        g2.setColor(Color.RED);
        g2.fillOval(appleX, appleY, TILESIZE, TILESIZE);
    }
    
    public void createApple() {
        appleX = random.nextInt(SCREENCOL) * TILESIZE;
        appleY = random.nextInt(SCREENROW) * TILESIZE;
    }
}