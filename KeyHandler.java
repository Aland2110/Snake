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

        // behandler menurelateret kontrol
        if (gp.getGameState() != gp.playState) {
            switch (code) {
                case KeyEvent.VK_W:
                    gp.UI.commandNum--;
                    if (gp.UI.commandNum < 0) {
                        gp.UI.commandNum = 2;
                    }
                    break;
                case KeyEvent.VK_S:
                    gp.UI.commandNum++;
                    if (gp.UI.commandNum > 2) {
                        gp.UI.commandNum = 0;
                    }
                    break;
                case KeyEvent.VK_ENTER:
                    if (gp.getGameState() == gp.titleState) {
                        if (gp.UI.commandNum == 0) {
                            gp.setGameState(gp.playState);
                            gp.startGame();
                            gp.playMusic(0);
                        } else if (gp.UI.commandNum == 1) {
                            // Later
                        } else if (gp.UI.commandNum == 2) {
                            System.exit(0);
                        }
                    } else if (gp.getGameState() == gp.pauseState) {
                        if (gp.UI.commandNum == 0) {
                            gp.setGameState(gp.playState);
                            gp.playMusic(0);
                        } else if (gp.UI.commandNum == 1) {
                            gp.setGameState(gp.titleState);
                        } else if (gp.UI.commandNum == 2) {
                            System.exit(0);
                        }
                    } else if (gp.getGameState() == gp.gameOverState) {
                        if (gp.UI.commandNum == 0) {
                            gp.setGameState(gp.playState);
                            gp.startGame();
                            gp.playMusic(0);
                        } else if (gp.UI.commandNum == 1) {
                            gp.setGameState(gp.titleState);
                        } else if (gp.UI.commandNum == 2) {
                            System.exit(0);
                        }
                    }
                    break;
            }
        }

        // behandler spilrelateret kontrol
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
                        gp.stopMusic(0);
                        gp.setGameState(gp.pauseState);
                        break;
                    } else if (gp.getGameState() == gp.pauseState) {
                        gp.playMusic(0);
                        gp.setGameState(gp.playState);
                        break;
                    }

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