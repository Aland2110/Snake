import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

public class UI {
    GamePanel gp;
    Entity snake;
    public int commandNum = 0;
    private int menuBut = 4;

    public UI(GamePanel gp, Entity snake) {
        this.gp = gp;
        this.snake = snake;
    }

    public void drawPlay(Graphics g) {
        if (gp.getGameState() == gp.pauseState) {
            drawPauseScreen(g);
        }
    }

    public void drawScore(Graphics g) {
        g.setColor(Color.RED);
        g.setFont(new Font("Arial", Font.BOLD, 50));
        FontMetrics metrics = g.getFontMetrics(g.getFont());
        g.drawString("Score: " + snake.appleEaten,
                (gp.GAMEWIDTH - metrics.stringWidth("Score: " + snake.appleEaten)) / 2,
                gp.TILESIZE);
    }

    public void drawGameOver(Graphics g) {
        g.setColor(Color.RED);
        g.setFont(new Font("Arial", Font.BOLD, 75));
        FontMetrics metrics = g.getFontMetrics(g.getFont());
        g.drawString("Game Over", ((int)gp.screenSize.getWidth() - metrics.stringWidth("Game Over")) / 2, (int)gp.screenSize.getHeight() / 2);
        g.drawString("Score: " + snake.appleEaten,
                ((int)gp.screenSize.getWidth() - metrics.stringWidth("Score: " + snake.appleEaten)) / 2,
                (int)gp.screenSize.getHeight() / 4);
    }

    public void drawPauseScreen(Graphics g) {
        g.setColor(Color.RED);
        g.setFont(new Font("Arial", Font.BOLD, 75));
        FontMetrics metrics = g.getFontMetrics(g.getFont());
        g.drawString("PAUSED", ((int)gp.screenSize.getWidth() - metrics.stringWidth("PAUSED")) / 2, (int)gp.screenSize.getHeight() / 2);
    }

    public void drawTitleScreen(Graphics g) {
        // start spil
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 45));
        FontMetrics metrics = g.getFontMetrics(g.getFont());
        g.drawString("Start game", (gp.GAMEWIDTH - metrics.stringWidth("Start game")) / 2, gp.GAMEHEIGHT / 2 - menuBut * 2 * gp.TILESIZE);
        if (commandNum == 0) {
            g.drawString(">", (gp.GAMEWIDTH - metrics.stringWidth(">")) / 2 - gp.TILESIZE * 5, gp.GAMEHEIGHT / 2 - menuBut * 2 * gp.TILESIZE);
        }

        // indstillinger
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 45));
        metrics = g.getFontMetrics(g.getFont());
        g.drawString("Settings", (gp.GAMEWIDTH - metrics.stringWidth("Settings")) / 2, gp.GAMEHEIGHT / 2 - menuBut * gp.TILESIZE);
        if (commandNum == 1) {
            g.drawString(">", (gp.GAMEWIDTH - metrics.stringWidth(">")) / 2 - gp.TILESIZE * 5, gp.GAMEHEIGHT / 2 - menuBut*gp.TILESIZE);
        }

        // afslut spil
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 45));
        metrics = g.getFontMetrics(g.getFont());
        g.drawString("Quit", (gp.GAMEWIDTH - metrics.stringWidth("Quit")) / 2, gp.GAMEHEIGHT / 2);
        if (commandNum == 2) {
            g.drawString(">", (gp.GAMEWIDTH - metrics.stringWidth(">")) / 2 - gp.TILESIZE * 5, gp.GAMEHEIGHT / 2);
        }
    }

    public void drawLevel(Graphics g) {
        g.setColor(Color.WHITE);
        g.drawRect(gp.gameWindowXStart, gp.gameWindowYStart, gp.GAMEWIDTH, gp.GAMEHEIGHT);
    }
}