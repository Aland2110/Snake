import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JFrame.*;

public class Main {
    private static JFrame menuWindow = new JFrame();
    private static JFrame settingWindow = new JFrame();
    public static void main(String[] args) {
        menu();
    }

    public static void menu() {
        settingWindow.dispose();
        menuWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menuWindow.setResizable(false);
        menuWindow.setTitle("Snake game");
        
        MenuPanel menuPanel = new MenuPanel();

        menuWindow.add(menuPanel);
        menuWindow.pack();
        menuWindow.setLocationRelativeTo(null);
        menuWindow.setVisible(true);
    }

    public static void settings() {
        menuWindow.dispose();
        settingWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        settingWindow.setResizable(false);
        settingWindow.setTitle("Snake game");

        SettingPanel settingPanel = new SettingPanel();

        settingWindow.add(settingPanel);
        settingWindow.pack();
        settingWindow.setLocationRelativeTo(null);
        settingWindow.setVisible(true);
    }

    public static void game(int n, int m) {
        menuWindow.dispose();
        JFrame gameWindow = new JFrame();
        GamePanel gamePanel = new GamePanel(n, m);
        gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameWindow.setResizable(false);
        gameWindow.setTitle("Snake game");
        gameWindow.add(gamePanel);
        gameWindow.pack();
        gameWindow.setLocationRelativeTo(null);
        gameWindow.setVisible(true);
        gamePanel.startGame();
    }
}