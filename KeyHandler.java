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
                if (direction != 'S') {
                    direction = 'W';
                }
                break;
            case KeyEvent.VK_A:
                if (direction != 'D') {
                    direction = 'A';
                }
                break;
            case KeyEvent.VK_S:
                if (direction != 'W') {
                    direction = 'S';
                }
                break;
            case KeyEvent.VK_D:
                if (direction != 'A') {
                    direction = 'D';
                }
                break;
        }
    }

    // registrerer når knappen slippes
    @Override
    public void keyReleased(KeyEvent e) {

    }

    public char getDir() {
        return direction;
    }

}
