import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;

public class Main {
    public static final int SCREENWIDTH = 1600;
    public static final int SCREENHEIGHT = 1000;

    public static void main(String[] args) {
        game(40, 20);
    }

    public static void game(int n, int m) {
        JFrame gameWindow = new JFrame();
        GamePanel gamePanel = new GamePanel(n, m, 0);

        gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameWindow.setResizable(false);
        gameWindow.setTitle("Snake game");
        gameWindow.setSize(SCREENWIDTH, SCREENHEIGHT);

        Box box = new Box(BoxLayout.Y_AXIS);
        box.add(Box.createVerticalGlue());
        box.add(gamePanel);
        box.add(Box.createVerticalGlue());

        gameWindow.add(box);
        gameWindow.setLocationRelativeTo(null);
        gameWindow.setVisible(true);
    
        gamePanel.startGame();
    }
}