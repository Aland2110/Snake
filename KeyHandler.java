import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    private boolean leftPressed, rightPressed, upPressed, downPressed;

    @Override
    public void keyTyped(KeyEvent e) {

    }

    // registrerer når en knap trykkes på
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        switch (code) {
            case KeyEvent.VK_W:
                upPressed = true;
                break;
            case KeyEvent.VK_A:
                leftPressed = true;
                break;
            case KeyEvent.VK_S:
                downPressed = true;
                break;
            case KeyEvent.VK_D:
                rightPressed = true;
                break;
        }
    }

    // registrerer når knappen slippes
    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        switch (code) {
            case KeyEvent.VK_W:
                upPressed = false;
                break;
            case KeyEvent.VK_A:
                leftPressed = false;
                break;
            case KeyEvent.VK_S:
                downPressed = false;
                break;
            case KeyEvent.VK_D:
                rightPressed = false;
                break;
        }
    }

    public boolean getLeft() {
        return leftPressed;
    }

    public boolean getRight() {
        return rightPressed;
    }

    public boolean getUp() {
        return upPressed;
    }

    public boolean getDown() {
        return downPressed;
    }
}
