import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame(); 
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // tilføjer funktionen til at kunne lukke vinduet
        window.setResizable(false); // vinduet kan ikke ændres i størrelse

        GamePanel gamePanel = new GamePanel(); 
        window.add(gamePanel); // gamepanelet bliver tilføjet til vinduet

        window.pack(); // al pakkes ind så det passer

        window.setLocationRelativeTo(null); // vinduet starter i midten af skærmen½
        window.setVisible(true);

        gamePanel.startGame(); // spillets tid startes
    }
}