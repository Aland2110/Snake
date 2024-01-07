import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
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
        MouseHandler.changeSet(false);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        g.setColor(Color.GREEN);
        // titel på spil
        drawText("Snake game", 60, 100, g);

        // Tegner linje
        g.setColor(Color.WHITE);
        g2.setStroke(new BasicStroke(3));
        g.drawLine(300, 115, SCREENWIDTH - 300, 115);

        // start-knap
        g.setColor(Color.GREEN);
        drawText("Play game", 50, 175, g);

        drawText("Settings", 50, 275, g);
    }

    public void drawText(String text, int size, int y, Graphics g) {
        g.setFont(new Font("Algerian", Font.BOLD, size));
        FontMetrics metrics = getFontMetrics(g.getFont());
        g.drawString(text, (SCREENWIDTH - metrics.stringWidth(text)) / 2, y);
    }
}