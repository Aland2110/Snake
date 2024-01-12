import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class UI {
    GamePanel gp;
    BufferedImage grass;
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    public int commandNum = 0;
    public int settingCommandNum = 0;
    public int volume = 50;
    private int tile = (int) screenSize.getWidth() / 48;

    public UI(GamePanel gp) {
        this.gp = gp;
    }

    public void drawPause(Graphics g) {
        if (gp.getGameState() == gp.pauseState) {
            drawPauseScreen(g);
        }
    }

    public void drawScore(Graphics g) {
        drawText(g, Color.decode("#C6372A"), 50, "Score: " + gp.entity.applesEaten, 2 * tile);
    }

    public void drawGameOver(Graphics g) {
        // game over
        drawText(g, Color.WHITE, 75, "Game Over", tile * 10);

        // scoren vises
        drawText(g, Color.WHITE, 60, "Score: " + gp.entity.applesEaten, tile * 12);

        // prøv igen
        if (commandNum == 0) {
            drawText(g, Color.decode("#C6372A"), 45, "Try again", tile * 16);
        } else {
            drawText(g, Color.WHITE, 45, "Try again", tile * 16);
        }

        // tilbage til menuen
        if (commandNum == 1) {
            drawText(g, Color.decode("#C6372A"), 45, "Back to menu", 18 * tile);
        } else {
            drawText(g, Color.WHITE, 45, "Back to menu", 18 * tile);
        }

        // afslut spil
        if (commandNum == 2) {
            drawText(g, Color.decode("#C6372A"), 45, "Quit", 20 * tile);
        } else {
            drawText(g, Color.WHITE, 45, "Quit", 20 * tile);
        }
    }

    public void drawPauseScreen(Graphics g) {
        // pause
        drawText(g, Color.WHITE, 75, "PAUSED", tile * 10);

        // fortsæt med at spille
        if (commandNum == 0) {
            drawText(g, Color.decode("#C6372A"), 45, "Continue", tile * 13);
        } else {
            drawText(g, Color.WHITE, 45, "Continue", tile * 13);
        }

        // tilbage til menuen
        if (commandNum == 1) {
            drawText(g, Color.decode("#C6372A"), 45, "Back to menu", 15 * tile);
        } else {
            drawText(g, Color.WHITE, 45, "Back to menu", 15 * tile);
        }

        // luk spil
        if (commandNum == 2) {
            drawText(g, Color.decode("#C6372A"), 45, "Quit", 17 * tile);
        } else {
            drawText(g, Color.WHITE, 45, "Quit", 17 * tile);
        }
    }

    public void drawTitleScreen(Graphics g) {
        // spillets titel
        drawText(g, Color.decode("#6CC82A"), 110, "Snake", 10 * tile);

        // start spil
        if (commandNum == 0) {
            drawText(g, Color.decode("#C6372A"), 45, "Start game", 15 * tile);
        } else {
            drawText(g, Color.WHITE, 45, "Start game", 15 * tile);
        }

        // indstillinger
        if (commandNum == 1) {
            drawText(g, Color.decode("#C6372A"), 45, "Settings", 17 * tile);
        } else {
            drawText(g, Color.WHITE, 45, "Settings", 17 * tile);
        }

        // afslut spil
        if (commandNum == 2) {
            drawText(g, Color.decode("#C6372A"), 45, "Quit", 19 * tile);
        } else {
            drawText(g, Color.WHITE, 45, "Quit", 19 * tile);
        }
    }

    public void drawSettingScreen(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        // titel på indstillinger
        drawText(g, Color.WHITE, 110, "Settings", 10 * tile);

        // bane indstillinger
        g2.setStroke(new BasicStroke(5));

        // ændre n's størrelse
        g.setColor(Color.GREEN);
        g.fillRect((int) screenSize.getWidth() / 3, 12 * tile, (int)((screenSize.getWidth() / 3 / 100) * gp.n), tile);
        if (settingCommandNum == 0) {
            g.setColor(Color.decode("#C6372A"));
            drawText(g, Color.decode("#C6372A"), 45, "The x-axis of the level", 14 * tile);
            drawText(g, Color.decode("#C6372A"), tile, "" + gp.n, 13 * tile);
        } else {
            g.setColor(Color.WHITE);
            drawText(g, Color.WHITE, 45, "The x-axis of the level", 14 * tile);
            drawText(g, Color.WHITE, tile, "" + gp.n, 13 * tile);
        }
        g.drawRect((int) screenSize.getWidth() / 3, 12 * tile, (int) screenSize.getWidth() / 3, tile);

        // ændre m's størrelse
        g.setColor(Color.GREEN);
        g.fillRect((int) screenSize.getWidth() / 3, 15 * tile, (int)((screenSize.getWidth() / 3 / 100) * gp.m), tile);
        if (settingCommandNum == 1) {
            g.setColor(Color.decode("#C6372A"));
            drawText(g, Color.decode("#C6372A"), 45, "The y-axis of the level", 17 * tile);
            drawText(g, Color.decode("#C6372A"), tile, "" + gp.m, 16 * tile);
        } else {
            g.setColor(Color.WHITE);
            drawText(g, Color.WHITE, 45, "The y-axis of the level", 17 * tile);
            drawText(g, Color.WHITE, tile, "" + gp.m, 16 * tile);
        }
        g.drawRect((int) screenSize.getWidth() / 3, 15 * tile, (int) screenSize.getWidth() / 3, tile);

        // ændre volumen
        g.setColor(Color.GREEN);
        g.fillRect((int) screenSize.getWidth() / 3, 18 * tile, (int)((screenSize.getWidth() / 3 / 100) * volume), tile);
        if (settingCommandNum == 2) {
            g.setColor(Color.decode("#C6372A"));
            drawText(g, Color.decode("#C6372A"), 45, "Change volume", 20 * tile);
            drawText(g, Color.decode("#C6372A"), tile, "" + volume, 19 * tile);
        } else {
            g.setColor(Color.WHITE);
            drawText(g, Color.WHITE, 45, "Change volume", 20 * tile);
            drawText(g, Color.WHITE, tile, "" + volume, 19 * tile);
        }
        g.drawRect((int) screenSize.getWidth() / 3, 18 * tile, (int) screenSize.getWidth() / 3, tile);

        // ændre sværhedsgrad
        g.setColor(Color.GREEN);
        g.fillRect((int) screenSize.getWidth() / 3, 21 * tile, (int)((screenSize.getWidth() / 3 / 100) * gp.tempFPS), tile);
        if (settingCommandNum == 3) {
            g.setColor(Color.decode("#C6372A"));
            drawText(g, Color.decode("#C6372A"), 45, "Change difficulty", 23 * tile);
            drawText(g, Color.decode("#C6372A"), tile, "" + gp.tempFPS, 22 * tile);
        } else {
            g.setColor(Color.WHITE);
            drawText(g, Color.WHITE, 45, "Change difficulty", 23 * tile);
            drawText(g, Color.WHITE, tile, "" + gp.tempFPS, 22 * tile);
        }
        g.drawRect((int) screenSize.getWidth() / 3, 21 * tile, (int) screenSize.getWidth() / 3, tile);

        // gå tilbage til menuen
        if (settingCommandNum == 4) {
            drawText(g, Color.decode("#C6372A"), 80, "Back", 26 * tile);
        } else {
            drawText(g, Color.WHITE, 80, "Back", 26 * tile);
        }
    }

    public void drawLevel(Graphics g) {
        g.setColor(Color.WHITE);

        getImage();

        int scaleX = gp.n;
        int scaleY = gp.m;
        int numX = gp.n / scaleX;
        int numY = gp.m / scaleY;

        while (numX * scaleX != gp.n || scaleX > 20) {
            scaleX--;
            numX = gp.n / scaleX;
        }

        while (numY * scaleY != gp.m || scaleY > 20) {
            scaleY--;
            numY = gp.m / scaleY;
        }

        int x = gp.gameWindowXStart;
        int y = gp.gameWindowYStart;

        for (int i = 0; i < numY; i++) {
            for (int j = 0; j < numX; j++) {
                g.drawImage(grass, x, y, gp.TILESIZE * scaleX, gp.TILESIZE * scaleY, null);
                x += gp.TILESIZE * scaleX;
            }
            y += gp.TILESIZE * scaleY;
            x = gp.gameWindowXStart;
        }

        g.setColor(Color.BLACK);
        g.drawRect(gp.gameWindowXStart, gp.gameWindowYStart, gp.GAMEWIDTH, gp.GAMEHEIGHT);
    }

    public void getImage() {
        try {
            grass = ImageIO.read(getClass().getResourceAsStream("/res/grass.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void drawText(Graphics g, Color c, int fontSize, String text, int y) {
        g.setColor(c);
        g.setFont(new Font("Algerian", Font.BOLD, fontSize));
        FontMetrics metrics = g.getFontMetrics(g.getFont());
        g.drawString(text, ((int) screenSize.getWidth() - metrics.stringWidth(text)) / 2, y);
    }
}