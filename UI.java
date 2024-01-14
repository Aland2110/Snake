import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class UI {
    GamePanel gp;
    BufferedImage grass;
    Font pixeBoyFont;

    public int commandNum = 0;
    public int settingCommandNum = 0;
    public int volume = 50;
    private int tile = 40;
    public int difficulty = 0;
    public final double SCREENWIDTH = 1280;
    public final double SCREENHEIGHT = 720;
    
    public UI(GamePanel gp) {
        this.gp = gp;

        // fonten hentes fra rescource-mappen
        try {
            InputStream font = getClass().getResourceAsStream("res/Fonts/Pixeboy-z8XGD.ttf");
            pixeBoyFont = Font.createFont(Font.TRUETYPE_FONT, font);
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // tegn pause skærmen hvis der er pause
    public void drawPause(Graphics g) {
        if (gp.getGameState() == gp.pauseState) {
            drawPauseScreen(g);
        }
    }

    // tegn scoren
    public void drawScore(Graphics g) {
        g.drawImage(gp.entity.Æble, tile, tile, null);
        drawText(g, Color.WHITE, 70, "x " + gp.entity.applesEaten, 3 * tile, 2 * tile + 20);
    }

    // tegn game over skærmen
    public void drawGameOver(Graphics g) {
        // game over
        drawText(g, Color.WHITE, 75, "Game Over", tile * 4);

        // scoren vises
        drawText(g, Color.WHITE, 60, "Score: " + gp.entity.applesEaten, tile * 6);

        // prøv igen
        if (commandNum == 0) {
            drawText(g, Color.decode("#C6372A"), 60, "Try again", tile * 10);
        } else {
            drawText(g, Color.WHITE, 60, "Try again", tile * 10);
        }

        // tilbage til menuen
        if (commandNum == 1) {
            drawText(g, Color.decode("#C6372A"), 60, "Back to menu", 12 * tile);
        } else {
            drawText(g, Color.WHITE, 60, "Back to menu", 12 * tile);
        }

        // afslut spil
        if (commandNum == 2) {
            drawText(g, Color.decode("#C6372A"), 60, "Quit", 14 * tile);
        } else {
            drawText(g, Color.WHITE, 60, "Quit", 14 * tile);
        }
    }

    // tegn pause skærmen
    public void drawPauseScreen(Graphics g) {
        // pause
        drawText(g, Color.WHITE, 75, "PAUSED", 6 * tile);

        // fortsæt med at spille
        if (commandNum == 0) {
            drawText(g, Color.decode("#C6372A"), 60, "Continue", tile * 9);
        } else {
            drawText(g, Color.WHITE, 60, "Continue", tile * 9);
        }

        // tilbage til menuen
        if (commandNum == 1) {
            drawText(g, Color.decode("#C6372A"), 60, "Back to menu", 11 * tile);
        } else {
            drawText(g, Color.WHITE, 60, "Back to menu", 11 * tile);
        }

        // luk spil
        if (commandNum == 2) {
            drawText(g, Color.decode("#C6372A"), 60, "Quit", 13 * tile);
        } else {
            drawText(g, Color.WHITE, 60, "Quit", 13 * tile);
        }
    }

    // tegn menuskærmen
    public void drawTitleScreen(Graphics g) {
        // spillets titel
        drawText(g, Color.decode("#6CC82A"), 110, "Snake", 3 * tile);

        // start spil
        if (commandNum == 0) {
            drawText(g, Color.decode("#C6372A"), 60, "Start game", 7 * tile);
        } else {
            drawText(g, Color.WHITE, 60, "Start game", 7 * tile);
        }

        // indstillinger
        if (commandNum == 1) {
            drawText(g, Color.decode("#C6372A"), 60, "Settings", 9 * tile);
        } else {
            drawText(g, Color.WHITE, 60, "Settings", 9 * tile);
        }

        // afslut spil
        if (commandNum == 2) {
            drawText(g, Color.decode("#C6372A"), 60, "Quit", 11 * tile);
        } else {
            drawText(g, Color.WHITE, 60, "Quit", 11 * tile);
        }
    }

    // tegn indstilling skærmen
    public void drawSettingScreen(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        // titel på indstillinger
        drawText(g, Color.WHITE, 140, "Settings", 3 * tile);

        // bane indstillinger
        g2.setStroke(new BasicStroke(5));

        // ændre n's størrelse
        g.setColor(Color.GREEN);
        g.fillRect((int) SCREENWIDTH / 3, 5 * tile, (int) (SCREENWIDTH / 3 / 100 * gp.n), tile);
        if (settingCommandNum == 0) {
            g.setColor(Color.decode("#C6372A"));
            drawText(g, Color.decode("#C6372A"), 60, "The x-axis of the level", 7 * tile);
            drawText(g, Color.decode("#C6372A"), tile, "" + gp.n, 6 * tile);
        } else {
            g.setColor(Color.WHITE);
            drawText(g, Color.WHITE, 60, "The x-axis of the level", 7 * tile);
            drawText(g, Color.WHITE, tile, "" + gp.n, 6 * tile);
        }
        g.drawRect((int) SCREENWIDTH / 3, 5 * tile, (int) SCREENWIDTH / 3, tile);

        // ændre m's størrelse
        g.setColor(Color.GREEN);
        g.fillRect((int) SCREENWIDTH / 3, 8 * tile, (int) (SCREENWIDTH / 3 / 100 * gp.m), tile);
        if (settingCommandNum == 1) {
            g.setColor(Color.decode("#C6372A"));
            drawText(g, Color.decode("#C6372A"), 60, "The y-axis of the level", 10 * tile);
            drawText(g, Color.decode("#C6372A"), tile, "" + gp.m, 9 * tile);
        } else {
            g.setColor(Color.WHITE);
            drawText(g, Color.WHITE, 60, "The y-axis of the level", 10 * tile);
            drawText(g, Color.WHITE, tile, "" + gp.m, 9 * tile);
        }
        g.drawRect((int) SCREENWIDTH / 3, 8 * tile, (int) SCREENWIDTH / 3, tile);

        // ændre volumen
        g.setColor(Color.GREEN);
        g.fillRect((int) SCREENWIDTH / 3, 11 * tile, (int) (SCREENWIDTH / 3 / 100 * volume),
                tile);
        if (settingCommandNum == 2) {
            g.setColor(Color.decode("#C6372A"));
            drawText(g, Color.decode("#C6372A"), 60, "Change volume", 13 * tile);
            drawText(g, Color.decode("#C6372A"), tile, "" + volume, 12 * tile);
        } else {
            g.setColor(Color.WHITE);
            drawText(g, Color.WHITE, 60, "Change volume", 13 * tile);
            drawText(g, Color.WHITE, tile, "" + volume, 12 * tile);
        }
        g.drawRect((int) SCREENWIDTH / 3, 11 * tile, (int) SCREENWIDTH / 3, tile);

        // ændre sværhedsgrad
        g.setColor(Color.GREEN);
        g.fillRect((int) SCREENWIDTH / 3, 14 * tile, (int) ((SCREENWIDTH / 9) * (difficulty + 1)),
                tile);
        if (settingCommandNum == 3) {
            g.setColor(Color.decode("#C6372A"));
            drawText(g, Color.decode("#C6372A"), 60, "Change difficulty", 16 * tile);
            switch (difficulty) {
                case 0:
                    drawText(g, Color.decode("#C6372A"), tile, "Easy", 15 * tile);
                    break;
                case 1:
                    drawText(g, Color.decode("#C6372A"), tile, "Medium", 15 * tile);
                    break;
                case 2:
                    drawText(g, Color.decode("#C6372A"), tile, "Hard", 15 * tile);
                    break;
            }
        } else {
            g.setColor(Color.WHITE);
            drawText(g, Color.WHITE, 60, "Change difficulty", 16 * tile);
            switch (difficulty) {
                case 0:
                    drawText(g, Color.WHITE, tile, "Easy", 15 * tile);
                    break;
                case 1:
                    drawText(g, Color.WHITE, tile, "Medium", 15 * tile);
                    break;
                case 2:
                    drawText(g, Color.WHITE, tile, "Hard", 15 * tile);
                    break;
            }
        }
        g.drawRect((int) SCREENWIDTH / 3, 14 * tile, (int) SCREENWIDTH / 3, tile);

        // gå tilbage til menuen
        if (settingCommandNum == 4) {
            drawText(g, Color.decode("#C6372A"), 80, "Back", tile, 16 * tile);
        } else {
            drawText(g, Color.WHITE, 80, "Back", tile, 16 * tile);
        }
    }

    // tegner banen
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

    // henter sprites til banen
    public void getImage() {
        try {
            grass = ImageIO.read(getClass().getResourceAsStream("/res/Sprites/grass.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // tegn tekst centreret på skærmen, men man kan ændre på y-koordinatet
    public void drawText(Graphics g, Color c, int fontSize, String text, int y) {
        g.setColor(c);
        g.setFont(pixeBoyFont);
        g.setFont(g.getFont().deriveFont(Font.BOLD, fontSize));
        FontMetrics metrics = g.getFontMetrics(g.getFont());
        g.drawString(text, ((int) SCREENWIDTH - metrics.stringWidth(text)) / 2, y);
    }

    // tegn tekst, men vælg x-koordinat og y-koordinat
    public void drawText(Graphics g, Color c, int fontSize, String text, int x, int y) {
        g.setColor(c);
        g.setFont(pixeBoyFont);
        g.setFont(g.getFont().deriveFont(Font.BOLD, fontSize));
        g.drawString(text, x, y);
    }
}
