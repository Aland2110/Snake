import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable {
    final int ORIGINALSIZE = 16; // 16x16 tiles
    final int SCALE = 3;

    final int TILESIZE = ORIGINALSIZE * SCALE; // 48x48 tiles
    final int SCREENCOL = 16;
    final int SCREENROW = 12;
    final int SCREENWIDTH = TILESIZE * SCREENCOL; // 768 pixels
    final int SCREENHEIGHT = TILESIZE * SCREENROW; // 576 pixels

    final int FPS = 60;

    KeyHandler keyH = new KeyHandler();
    Thread gameThread;

    // set player variables
    int playerX = SCREENWIDTH / 2;
    int playerY = SCREENHEIGHT / 2;
    int playerSpeed = 4;

    public GamePanel() {
        this.setPreferredSize(new Dimension(SCREENWIDTH, SCREENHEIGHT)); // vinduets størrelse defineres
        this.setBackground(Color.GREEN);
        this.setDoubleBuffered(true); // performance (loader i baggrunden)
        this.addKeyListener(keyH);
        this.setFocusable(true); // gamePanel fokuserer på at modtage tastatur-input
    }

    // spillets "tid" startes
    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
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
        if (keyH.getUp()) {
            playerY -= playerSpeed;
        } else if (keyH.getDown()) {
            playerY += playerSpeed;
        } else if (keyH.getLeft()) {
            playerX -= playerSpeed;
        } else if (keyH.getRight()) {
            playerX += playerSpeed;
        }
    }

    // paintComponent er en indbygget metode i JPanel
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        // firkant tegnes
        g2.setColor(Color.BLACK);
        g2.fillRect(playerX, playerY, TILESIZE, TILESIZE);
        g2.dispose();
    }
}
