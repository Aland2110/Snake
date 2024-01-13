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
                case KeyEvent.VK_UP:
                    gp.UI.commandNum--;
                    if (gp.UI.commandNum < 0) {
                        gp.UI.commandNum = 2;
                    }
                    break;
                case KeyEvent.VK_DOWN:
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
                            gp.UI.commandNum = 0;
                        } else if (gp.UI.commandNum == 1) {
                            gp.setGameState(gp.settingState);
                            gp.UI.commandNum = 0;
                        } else if (gp.UI.commandNum == 2) {
                            System.exit(0);
                            gp.UI.commandNum = 0;
                        }
                    } else if (gp.getGameState() == gp.pauseState) {
                        if (gp.UI.commandNum == 0) {
                            gp.setGameState(gp.playState);
                            gp.playMusic(0);
                            gp.UI.commandNum = 0;
                        } else if (gp.UI.commandNum == 1) {
                            gp.setGameState(gp.titleState);
                            gp.UI.commandNum = 0;
                        } else if (gp.UI.commandNum == 2) {
                            System.exit(0);
                            gp.UI.commandNum = 0;
                        }
                    } else if (gp.getGameState() == gp.gameOverState) {
                        if (gp.UI.commandNum == 0) {
                            gp.setGameState(gp.playState);
                            gp.startGame();
                            gp.playMusic(0);
                            gp.UI.commandNum = 0;
                        } else if (gp.UI.commandNum == 1) {
                            gp.setGameState(gp.titleState);
                            gp.UI.commandNum = 0;
                        } else if (gp.UI.commandNum == 2) {
                            System.exit(0);
                            gp.UI.commandNum = 0;
                        }
                    }
                    break;
            }
        }

        // behandler spilrelateret kontrol
        if (gp.getGameState() == gp.playState || gp.getGameState() == gp.pauseState) {
            switch (code) {
                case KeyEvent.VK_UP:
                    direction = 'W';
                    break;
                case KeyEvent.VK_LEFT:
                    direction = 'A';
                    break;
                case KeyEvent.VK_DOWN:
                    direction = 'S';
                    break;
                case KeyEvent.VK_RIGHT:
                    direction = 'D';
                    break;
                case KeyEvent.VK_ESCAPE:
                    if (gp.getGameState() == gp.playState) {
                        gp.stopMusic(0);
                        gp.setGameState(gp.pauseState);
                    } else if (gp.getGameState() == gp.pauseState) {
                        gp.playMusic(0);
                        gp.setGameState(gp.playState);
                    }
                    break;

            }
        }

        // behandler kontrol i indstillinger
        if (gp.getGameState() == gp.settingState) {
            switch (code) {
                case KeyEvent.VK_UP:
                    gp.UI.settingCommandNum--;
                    if (gp.UI.settingCommandNum < 0) {
                        gp.UI.settingCommandNum = 4;
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    gp.UI.settingCommandNum++;
                    if (gp.UI.settingCommandNum > 4) {
                        gp.UI.settingCommandNum = 0;
                    }
                    break;
                case KeyEvent.VK_LEFT:
                    if (gp.UI.settingCommandNum == 0 && gp.n > 6) {
                        gp.setGameSize(false, gp.UI.settingCommandNum);
                    } else if (gp.UI.settingCommandNum == 1 && gp.m > 6) {
                        gp.setGameSize(false, gp.UI.settingCommandNum);
                    } else if (gp.UI.settingCommandNum == 2 && gp.UI.volume > 0) {
                        gp.changeVolume(false);
                    } else if (gp.UI.settingCommandNum == 3) {
                        gp.UI.difficulty--;
                        if (gp.UI.difficulty < 0) {
                            gp.UI.difficulty = 2;
                        }
                        gp.changeDifficulty();
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if (gp.UI.settingCommandNum == 0 && gp.n < 100) {
                        gp.setGameSize(true, gp.UI.settingCommandNum);
                    } else if (gp.UI.settingCommandNum == 1 && gp.m < 100) {
                        gp.setGameSize(true, gp.UI.settingCommandNum);
                    } else if (gp.UI.settingCommandNum == 2 && gp.UI.volume < 100) {
                        gp.changeVolume(true);
                    } else if (gp.UI.settingCommandNum == 3 && gp.tempFPS < 100) {
                        gp.UI.difficulty++;
                        if (gp.UI.difficulty > 2) {
                            gp.UI.difficulty = 0;
                        }
                        gp.changeDifficulty();
                    }
                    break;
                case KeyEvent.VK_ENTER:
                    if (gp.UI.settingCommandNum == 4) {
                        gp.setGameState(gp.titleState);
                        gp.UI.settingCommandNum = 0;
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

    public void setDir() {
        direction = 'D';
    }
}