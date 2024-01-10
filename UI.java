import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

public class UI {
    GamePanel gp;
    public int titleCommandNum = 0;
    public int gameOverCommandNum = 0;
    public int pauseCommandNum = 0;
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
        if (gameOverCommandNum == 0) {
            g.drawString(">", gp.GAMEWIDTH / 2 + tile * 15, 16 * tile);
        }

        // tilbage til menuen
        drawText(g, Color.WHITE, 45, "Back to menu", 18 * tile);
        if (gameOverCommandNum == 1) {
            g.drawString(">", gp.GAMEWIDTH / 2 + tile * 14, 18 * tile);
        }
        
        // afslut spil
        drawText(g, Color.WHITE, 45, "Quit", 20 * tile);
        if (gameOverCommandNum == 2) {
            g.drawString(">", gp.GAMEWIDTH / 2 + tile * 16, 20 * tile);
        }
    }

    public void drawPauseScreen(Graphics g) {
        // pause
        drawText(g, Color.WHITE, 75, "PAUSED", tile * 10);

        // fortsæt med at spille
        drawText(g, Color.WHITE, 45, "Continue", tile * 13);
        if (pauseCommandNum == 0) {
            g.drawString(">", gp.GAMEWIDTH / 2 + tile * 15, 13 * tile);
        }

        // tilbage til menuen
        drawText(g, Color.WHITE, 45, "Back to menu", 15 * tile); 
        if (pauseCommandNum == 1) {
            g.drawString(">", gp.GAMEWIDTH / 2 + tile * 13, 15 * tile);
        }

        // luk spil
        drawText(g, Color.WHITE, 45, "Quit", 17 * tile); 
        if (pauseCommandNum == 2) {
            g.drawString(">", gp.GAMEWIDTH / 2 + tile * 16, 17 * tile);
        }
    }

    public void drawTitleScreen(Graphics g) {
        // spillets titel
        drawText(g, Color.WHITE, 110, "Snake", 10 * tile);

        // start spil
        drawText(g, Color.WHITE, 45, "Start game", 15 * tile);
        if (titleCommandNum == 0) {
            g.drawString(">", gp.GAMEWIDTH / 2 + tile * 14, 15 * tile);
        }

        // indstillinger
        drawText(g, Color.WHITE, 45, "Settings", 17 * tile);
        if (titleCommandNum == 1) {
            g.drawString(">", gp.GAMEWIDTH / 2 + tile * 15, 17 * tile);
        }

        // afslut spil
        drawText(g, Color.WHITE, 45, "Quit", 19 * tile);
        if (titleCommandNum == 2) {
            g.drawString(">", gp.GAMEWIDTH / 2 + tile * 16, 19 * tile);
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