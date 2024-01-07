import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    private char direction = 'D';
    GamePanel gp;

    public KeyHandler(GamePanel gp) {
        this.gp = gp;
    }

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
            case KeyEvent.VK_ESCAPE:
                if (gp.getGameState() == gp.playState) {
                    gp.setGameState(gp.pauseState);
                    break;
                } else if (gp.getGameState() == gp.pauseState) {
                    gp.setGameState(gp.playState);
                    break;
                }
                
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public char getDir() {
        return direction;
    }

}