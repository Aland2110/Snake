import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseHandler implements MouseListener {
    private double x;
    private double y;

    @Override
    public void mouseClicked(MouseEvent e) {
        this.x = e.getX();
        this.y = e.getY();

        if (x >= 500 && x <= 750 && y >= 150 && y <= 400) {
            System.out.println("Test");
            Main.game(20, 20);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}