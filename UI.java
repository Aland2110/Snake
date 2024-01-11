import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class UI {
    GamePanel gp;
    BufferedImage grass;
    public int commandNum = 0;
    private int tile = 40;

    public UI(GamePanel gp) {
        this.gp = gp;
    }

    public void drawPause(Graphics g) {
        if (gp.getGameState() == gp.pauseState) {
            drawPauseScreen(g);
        }
    }

    public void drawScore(Graphics g) {
        drawText(g, Color.RED, 50, "Score: " + gp.entity.applesEaten, 2 * tile);
    }

    public void drawGameOver(Graphics g) {
        // game over
        drawText(g, Color.WHITE, 75, "Game Over", tile * 10);

        // scoren vises
        drawText(g, Color.WHITE, 60, "Score: " + gp.entity.applesEaten, tile * 12);

        // prøv igen
        drawText(g, Color.WHITE, 45, "Try again", tile * 16);
        if (commandNum == 0) {
            g.drawString(">",(int) gp.screenSize.getWidth() / 2 - 4 * tile, 16 * tile);
        }

        // tilbage til menuen
        drawText(g, Color.WHITE, 45, "Back to menu", 18 * tile);
        if (commandNum == 1) {
            g.drawString(">",(int) gp.screenSize.getWidth() / 2 - 5 * tile, 18 * tile);
        }

        // afslut spil
        drawText(g, Color.WHITE, 45, "Quit", 20 * tile);
        if (commandNum == 2) {
            g.drawString(">",(int) gp.screenSize.getWidth() / 2 - 2 * tile, 20 * tile);
        }
    }

    public void drawPauseScreen(Graphics g) {
        // pause
        drawText(g, Color.WHITE, 75, "PAUSED", tile * 10);

        // fortsæt med at spille
        drawText(g, Color.WHITE, 45, "Continue", tile * 13);
        if (commandNum == 0) {
            g.drawString(">",(int) gp.screenSize.getWidth() / 2 - 4 * tile, 13 * tile);
        }

        // tilbage til menuen
        drawText(g, Color.WHITE, 45, "Back to menu", 15 * tile);
        if (commandNum == 1) {
            g.drawString(">",(int) gp.screenSize.getWidth() / 2 - 5 * tile, 15 * tile);
        }

        // luk spil
        drawText(g, Color.WHITE, 45, "Quit", 17 * tile);
        if (commandNum == 2) {
            g.drawString(">",(int) gp.screenSize.getWidth() / 2 - 2 * tile, 17 * tile);
        }
    }

    public void drawTitleScreen(Graphics g) {
        // spillets titel
        drawText(g, Color.WHITE, 110, "Snake", 10 * tile);

        // start spil
        drawText(g, Color.WHITE, 45, "Start game", 15 * tile);
        if (commandNum == 0) {
            g.drawString(">",(int) gp.screenSize.getWidth() / 2 - 5 * tile, 15 * tile);
        }

        // indstillinger
        drawText(g, Color.WHITE, 45, "Settings", 17 * tile);
        if (commandNum == 1) {
            g.drawString(">",(int) gp.screenSize.getWidth() / 2 - 4 * tile, 17 * tile);
        }

        // afslut spil
        drawText(g, Color.WHITE, 45, "Quit", 19 * tile);
        if (commandNum == 2) {
            g.drawString(">",(int) gp.screenSize.getWidth() / 2 - 2 * tile, 19 * tile);
        }
    }

    public void drawLevel(Graphics g) {
        g.setColor(Color.WHITE);
        
        getImage();

        int scaleX = gp.n;
        int scaleY = gp.m;
        int numX = gp.n / scaleX;
        int numY = gp.m / scaleY;

        while (numX*scaleX != gp.n || scaleX > 20) {
            scaleX--;
            numX = gp.n / scaleX;
        }

        while (numY*scaleY != gp.m || scaleY > 20) {
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
        g.drawString(text, ((int) gp.screenSize.getWidth() - metrics.stringWidth(text)) / 2, y);
    }
}