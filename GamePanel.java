import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public class GamePanel extends JPanel {
    final int ORIGINALSIZE = 16; // 16x16 tiles
    final int SCALE = 3;

    final int TILESIZE = ORIGINALSIZE * SCALE; // 48x48 tiles
    final int SCREENCOL = 16;
    final int SCREENROW = 12;
    final int SCREENWIDTH = TILESIZE * SCREENCOL; // 768 pixels
    final int SCREENHEIGHT = TILESIZE * SCREENROW; // 576 pixels

    public GamePanel() {
        this.setPreferredSize(new Dimension(SCREENWIDTH, SCREENHEIGHT)); // vinduets størrelse defineres
        this.setBackground(Color.GREEN);
        this.setDoubleBuffered(true); // performance (loader i baggrunden)
    }
}
