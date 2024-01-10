import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {
        game(40, 20);
    }

    public static void game(int n, int m) {
        JFrame gameWindow = new JFrame();
        GamePanel gamePanel = new GamePanel(n, m, 0);

        gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameWindow.setResizable(false);
        gameWindow.setTitle("Snake game");
        gameWindow.setExtendedState(JFrame.MAXIMIZED_BOTH);
        gameWindow.setUndecorated(true);
        gameWindow.add(gamePanel);
        gameWindow.setLocationRelativeTo(null);
        gameWindow.setVisible(true);
    
        gamePanel.startGame();
    }
}