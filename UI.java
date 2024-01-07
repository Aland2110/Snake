import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

public class UI {
    GamePanel gp;
    Entity snake;

    public UI(GamePanel gp, Entity snake) {
        this.gp = gp;
        this.snake = snake;
    }

    public void draw(Graphics g) {
        if (gp.getGameState() == gp.playState) {
            drawScore(g);
        } else if (gp.getGameState() == gp.pauseState) {
            drawPauseScreen(g);
        }
    }

    public void drawScore(Graphics g) {
        g.setColor(Color.RED);
        g.setFont(new Font("Ink Free", Font.BOLD, 50));
        FontMetrics metrics = g.getFontMetrics(g.getFont());
        g.drawString("Score: " + snake.appleEaten,
                (gp.SCREENWIDTH - metrics.stringWidth("Score: " + snake.appleEaten)) / 2,
                gp.TILESIZE);
    }

    public void drawGameOver(Graphics g) {
        g.setColor(Color.RED);
        g.setFont(new Font("Ink Free", Font.BOLD, 75));
        FontMetrics metrics = g.getFontMetrics(g.getFont());
        g.drawString("Game Over", (gp.SCREENWIDTH - metrics.stringWidth("Game Over")) / 2, gp.SCREENHEIGHT / 2);
        g.drawString("Score: " + snake.appleEaten,
                (gp.SCREENWIDTH - metrics.stringWidth("Score: " + snake.appleEaten)) / 2,
                gp.SCREENHEIGHT / 4);
    }

    public void drawPauseScreen(Graphics g) {
        g.setColor(Color.RED);
        g.setFont(new Font("Ink Free", Font.BOLD, 75));
        FontMetrics metrics = g.getFontMetrics(g.getFont());
        g.drawString("PAUSED", (gp.SCREENWIDTH - metrics.stringWidth("PAUSED")) / 2, gp.SCREENHEIGHT / 2);
    }
}
