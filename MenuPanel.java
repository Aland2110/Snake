import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.FontMetrics;
import javax.swing.JPanel;

public class MenuPanel extends JPanel {
    private final int SCREENWIDTH = 1280;
    private final int SCREENHEIGHT = 720;

    MouseHandler mouseH = new MouseHandler();

    public MenuPanel() {
        this.setPreferredSize(new Dimension(SCREENWIDTH, SCREENHEIGHT));
        this.setBackground(Color.DARK_GRAY);
        this.setDoubleBuffered(true);
        this.addMouseListener(mouseH);
        this.setFocusable(true);
        repaint();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // titel på spil
        g.setColor(Color.GREEN);
        g.setFont(new Font("Algerian", Font.BOLD, 60));
        FontMetrics metrics = getFontMetrics(g.getFont());
        g.drawString("Snake Game", (SCREENWIDTH - metrics.stringWidth("Snake Game")) / 2, 100);

        // start-knap
        g.setFont(new Font("Algerian", Font.BOLD, 50));
        metrics = getFontMetrics(g.getFont());
        g.drawString("Play game", (SCREENWIDTH - metrics.stringWidth("Play game")) / 2, 200);
    }
}