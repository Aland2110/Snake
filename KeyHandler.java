import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    private char actualDirection = 'D';
    private char nextDirection;

    @Override
    public void keyTyped(KeyEvent e) {

    }

    // registrerer når en knap trykkes på
    @Override
    public synchronized void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W) {
            if (actualDirection != 'S') {
                nextDirection = 'W';
            }
        } else if (code == KeyEvent.VK_S) {
            if (actualDirection != 'W') {
                nextDirection = 'S';
            }
        } else if (code == KeyEvent.VK_D) {
            if (actualDirection != 'A') {
                nextDirection = 'D';
            }
        } else if (code == KeyEvent.VK_A) {
            if (actualDirection != 'D') {
                nextDirection = 'A';
            }
        }
    }

    // registrerer når knappen slippes
    @Override
    public void keyReleased(KeyEvent e) {

    }

    public char getDir() {
        return actualDirection;
    }

    public void updateDir() {
        actualDirection = nextDirection;
    }

}