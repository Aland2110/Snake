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

    Thread gameThread;

    public GamePanel() {
        this.setPreferredSize(new Dimension(SCREENWIDTH, SCREENHEIGHT)); // vinduets størrelse defineres
        this.setBackground(Color.GREEN);
        this.setDoubleBuffered(true); // performance (loader i baggrunden)
    }

    // spillets "tid" startes
    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }   

    @Override
    public void run() {
        // mens spillet kører, opdateres og tegnes vinduet
        while (gameThread != null) {
            update();
            repaint(); // kalder metoden paintComponent 
        }
    }

    public void update() {

    }

    // paintComponent er en indbygget metode i JPanel
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        // firkant tegnes
        g2.setColor(Color.BLACK);
        g2.fillRect(400, 300, TILESIZE, TILESIZE);
        g2.dispose();
    }
}
