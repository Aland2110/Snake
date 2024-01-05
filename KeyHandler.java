import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    private char direction = 'D';

    @Override
    public void keyTyped(KeyEvent e) {

    }

    // registrerer når en knap trykkes på
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        switch (code) {
            case KeyEvent.VK_W:
                direction = 'W';
                break;
            case KeyEvent.VK_A:
                direction = 'A';
                break;
            case KeyEvent.VK_S:
                direction = 'S';
                break;
            case KeyEvent.VK_D:
                direction = 'D';
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public char getDir() {
        return direction;
    }

}