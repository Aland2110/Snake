import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {
        menu();
    }

    public static void menu() {
        JFrame menuWindow = new JFrame();
        menuWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menuWindow.setResizable(false);
        menuWindow.setTitle("Snake game");
        
        MenuPanel menuPanel = new MenuPanel();
        
        menuWindow.add(menuPanel);
        menuWindow.pack();
        menuWindow.setLocationRelativeTo(null);
        menuWindow.setVisible(true);
    }

    public static void game(int n, int m) {
        JFrame gameWindow = new JFrame();
        gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameWindow.setResizable(false);
        gameWindow.setTitle("Snake game");
        
        GamePanel gamePanel = new GamePanel(n, m);
        
        gameWindow.add(gamePanel);
        gameWindow.pack();
        gameWindow.setLocationRelativeTo(null);
        gameWindow.setVisible(true);
        
        gamePanel.startGame();
    }
}