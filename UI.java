import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

public class UI {
    GamePanel gp;
    public int commandNum = 0;

    public UI(GamePanel gp) {
        this.gp = gp;
    }

    public void drawPlay(Graphics g) {
        if (gp.getGameState() == gp.pauseState) {
            drawPauseScreen(g);
        }
    }

    public void drawScore(Graphics g) {
        drawText(g, Color.RED, 50, "Score: " + gp.entity.appleEaten, 2 * gp.TILESIZE);
    }

    public void drawGameOver(Graphics g) {
        drawText(g, Color.RED, 75, "Game Over", (int) gp.screenSize.getHeight() / 2);
        drawText(g, Color.RED, 75, "Score: " + gp.entity.appleEaten, gp.TILESIZE * 4);
    }

    public void drawPauseScreen(Graphics g) {
        drawText(g, Color.RED, 75, "PAUSED", (int) gp.screenSize.getHeight() / 2);
    }

    public void drawTitleScreen(Graphics g) {
        // start spil
        drawText(g, Color.WHITE, 45, "Start game", 2 * gp.TILESIZE);
        if (commandNum == 0) {
            g.drawString(">", gp.GAMEWIDTH / 2 - gp.TILESIZE * 5, 2 * gp.TILESIZE);
        }

        // indstillinger
        drawText(g, Color.WHITE, 45, "Settings", 4 * gp.TILESIZE);
        if (commandNum == 1) {
            g.drawString(">", gp.GAMEWIDTH / 2 - gp.TILESIZE * 5, 4 * gp.TILESIZE);
        }

        // afslut spil
        drawText(g, Color.WHITE, 45, "Quit", 6 * gp.TILESIZE);
        if (commandNum == 2) {
            g.drawString(">", gp.GAMEWIDTH / 2 - gp.TILESIZE * 5, 6 * gp.TILESIZE);
        }
    }

    public void drawLevel(Graphics g) {
        g.setColor(Color.WHITE);
        g.drawRect(gp.gameWindowXStart, gp.gameWindowYStart, gp.GAMEWIDTH, gp.GAMEHEIGHT);
    }

    public void drawText(Graphics g, Color c, int fontSize, String text, int y) {
        g.setColor(c);
        g.setFont(new Font("Arial", Font.BOLD, fontSize));
        FontMetrics metrics = g.getFontMetrics(g.getFont());
        g.drawString(text, ((int) gp.screenSize.getWidth() - metrics.stringWidth(text)) / 2, y);
    }
}