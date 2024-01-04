import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.FontMetrics;
import javax.swing.JPanel;

public class MenuPanel extends JPanel {
    private final int SCREENWIDTH = 1280;
    private final int SCREENHEIGHT = 720; 

    public MenuPanel() {
        this.setPreferredSize(new Dimension(SCREENWIDTH, SCREENHEIGHT));
        this.setBackground(Color.GRAY);
        this.setDoubleBuffered(true);
        this.setFocusable(true);
        repaint();
    }
    
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.WHITE);
        g.setFont(new Font("Ink Free", Font.BOLD, 60));
        FontMetrics metrics = getFontMetrics(g.getFont());
        g.drawString("Snake Game", (SCREENWIDTH - metrics.stringWidth("Snake Game")) / 2, 100);
    }
}