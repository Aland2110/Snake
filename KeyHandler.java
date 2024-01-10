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

        if (gp.getGameState() == gp.titleState) {
            switch (code) {
                case KeyEvent.VK_W:
                    gp.UI.titleCommandNum--;
                    if (gp.UI.titleCommandNum < 0) {
                        gp.UI.titleCommandNum = 2;
                    }
                    break;
                case KeyEvent.VK_S:
                    gp.UI.titleCommandNum++;
                    if (gp.UI.titleCommandNum > 2) {
                        gp.UI.titleCommandNum = 0;
                    }
                    break;
                case KeyEvent.VK_ENTER:
                    if (gp.UI.titleCommandNum == 0) {
                        gp.setGameState(gp.playState);
                        gp.startGame();
                    } else if (gp.UI.titleCommandNum == 1) {
                        // Later
                    } else if (gp.UI.titleCommandNum == 2) {
                        System.exit(0);
                    }
                    break;
            }
        }

        if (gp.getGameState() == gp.playState || gp.getGameState() == gp.pauseState) {
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

        if (gp.getGameState() == gp.pauseState) {
            switch (code) {
                case KeyEvent.VK_W:
                    gp.UI.pauseCommandNum--;
                    if (gp.UI.pauseCommandNum < 0) {
                        gp.UI.pauseCommandNum = 2;
                    }
                    break;
                case KeyEvent.VK_S:
                    gp.UI.pauseCommandNum++;
                    if (gp.UI.pauseCommandNum > 2) {
                        gp.UI.pauseCommandNum = 0;
                    }
                    break;
                case KeyEvent.VK_ENTER:
                    if (gp.UI.pauseCommandNum == 0) {
                        gp.setGameState(gp.playState);
                    } else if (gp.UI.pauseCommandNum == 1) {
                        gp.setGameState(gp.titleState);
                    } else if (gp.UI.pauseCommandNum == 2) {
                        System.exit(0);
                    }
                    break;
            }
        }

        if (gp.getGameState() == gp.gameOverState) {
            switch (code) {
                case KeyEvent.VK_W:
                    gp.UI.gameOverCommandNum--;
                    if (gp.UI.gameOverCommandNum < 0) {
                        gp.UI.gameOverCommandNum = 2;
                    }
                    break;
                case KeyEvent.VK_S:
                    gp.UI.gameOverCommandNum++;
                    if (gp.UI.gameOverCommandNum > 2) {
                        gp.UI.gameOverCommandNum = 0;
                    }
                    break;
                case KeyEvent.VK_ENTER:
                    if (gp.UI.gameOverCommandNum == 0) {
                        gp.setGameState(gp.playState);
                        gp.startGame();
                    } else if (gp.UI.gameOverCommandNum == 1) {
                        gp.setGameState(gp.titleState);
                    } else if (gp.UI.gameOverCommandNum == 2) {
                        System.exit(0);
                    }
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