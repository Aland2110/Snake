import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {
        game();
    }

    public static void game() {
        JFrame gameWindow = new JFrame();
        GamePanel gamePanel = new GamePanel();

        gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameWindow.setResizable(false);
        gameWindow.setTitle("Snake game");
        gameWindow.setExtendedState(JFrame.MAXIMIZED_BOTH);
        gameWindow.setUndecorated(true);
        gameWindow.add(gamePanel);
        gameWindow.setLocationRelativeTo(null);
        gameWindow.setVisible(true);

        gamePanel.startGameThread();
        gamePanel.startGame();
    }
}