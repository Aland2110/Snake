import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.Font;
import java.awt.FontMetrics;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class SettingPanel extends JPanel {
    private final int SCREENWIDTH = 1280;
    private final int SCREENHEIGHT = 720;
    private final int IMAGEPOSWIDTH = 100;
    private final int IMAGEPOSHEIGHT = 200; 

    private BufferedImage arrowUp;
    private BufferedImage arrowDown;
    

    MouseHandler mouseH = new MouseHandler();

    public SettingPanel() {
        this.setPreferredSize(new Dimension(SCREENWIDTH, SCREENHEIGHT));
        this.setBackground(Color.DARK_GRAY);
        this.setDoubleBuffered(true);
        this.addMouseListener(mouseH);
        this.setFocusable(true);
        try {
            arrowUp = ImageIO.read(new File("./res/arrowUp.png"));
            arrowDown = ImageIO.read(new File("./res/arrowDown.png"));
        } catch(IOException e) {e.printStackTrace();}
        repaint();
        MouseHandler.changeSet(true);
    }
  
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        
        g.setColor(Color.GREEN);
        // titel på spil
        drawText("Settings", 60, 100, g);

        //Tegner linje
        g.setColor(Color.WHITE);
        g2.setStroke(new BasicStroke(3));
        g.drawLine(300, 115, SCREENWIDTH-300, 115);

        // start-knap
        g.setColor(Color.GREEN);

        g.drawImage(arrowUp, 400, 150, IMAGEPOSWIDTH, 100, null, null);
        g.drawImage(arrowDown, 400, 250, IMAGEPOSWIDTH, 100, null, null);

        drawText("Back", 50, 275, g);
        
        
    }

    public void drawText(String text, int size, int y, Graphics g) {
        g.setFont(new Font("Algerian", Font.BOLD, size));
        FontMetrics metrics = getFontMetrics(g.getFont());
        g.drawString(text, (SCREENWIDTH - metrics.stringWidth(text)) / 2, y);
    }
}
