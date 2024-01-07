import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseHandler implements MouseListener {
    private double x;
    private double y;
    private static boolean set;

    @Override
    public void mouseClicked(MouseEvent e) {
        this.x = e.getX();
        this.y = e.getY();

        if (x >= 485 && x <= 785 && y >= 150 && y <= 200 && set == false) {
            Main.game(20, 20);
            System.out.println(set);
        } else if (x >= 500 && x <= 750 && y >= 225 && y <= 275) {
            if (set == false) {
                Main.settings();
            } else {
                Main.menu();
            }
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

    public static void changeSet(boolean t) {
        set = t;
    }
}