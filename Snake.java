import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Snake {
    private int[] x;
    private int[] y;
    public int bodyParts = 2;
    public int appleEaten;
    private char lastDir;
    private int spriteCounter = 0;
    private int spriteNum = 1;
    private BufferedImage Hoved1, Hoved1H, Hoved1N, Hoved1V, Hoved2, Hoved2H, Hoved2N, Hoved2V, Hoved3, Hoved3H,
            Hoved3N, Hoved3V, Hoved4, Hoved4H, Hoved4N, Hoved4V, Hale, HaleH, HaleN, HaleV, Krop, KropH;

    GamePanel gp;
    KeyHandler keyH;

    public Snake(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;
    }

    public void snakePos() {
        this.x = new int[(gp.SCREENWIDTH * gp.SCREENHEIGHT) / gp.TILESIZE];
        this.y = new int[(gp.SCREENWIDTH * gp.SCREENHEIGHT) / gp.TILESIZE];
        this.lastDir = 'D';

        for (int i = 0; i < bodyParts; i++) {
            x[i] = (gp.n / 2 - i) * gp.TILESIZE;
            y[i] = gp.m / 2 * gp.TILESIZE;
        }
    }

    public int[] getXarr() {
        return x;
    }

    public int[] getYarr() {
        return y;
    }

    public void getPlayerImage() {
        try {
            Hoved1 = ImageIO.read(getClass().getResourceAsStream("/Player/Hoved 1.png"));
            Hoved1H = ImageIO.read(getClass().getResourceAsStream("/Player/Hoved 1 (H).png"));
            Hoved1N = ImageIO.read(getClass().getResourceAsStream("/Player/Hoved 1 (N).png"));
            Hoved1V = ImageIO.read(getClass().getResourceAsStream("/Player/Hoved 1 (V).png"));
            Hoved2 = ImageIO.read(getClass().getResourceAsStream("/Player/Hoved 2.png"));
            Hoved2H = ImageIO.read(getClass().getResourceAsStream("/Player/Hoved 2 (H).png"));
            Hoved2N = ImageIO.read(getClass().getResourceAsStream("/Player/Hoved 2 (N).png"));
            Hoved2V = ImageIO.read(getClass().getResourceAsStream("/Player/Hoved 2 (V).png"));
            Hoved3 = ImageIO.read(getClass().getResourceAsStream("/Player/Hoved 3.png"));
            Hoved3H = ImageIO.read(getClass().getResourceAsStream("/Player/Hoved 3 (H).png"));
            Hoved3N = ImageIO.read(getClass().getResourceAsStream("/Player/Hoved 3 (N).png"));
            Hoved3V = ImageIO.read(getClass().getResourceAsStream("/Player/Hoved 3 (V).png"));
            Hoved4 = ImageIO.read(getClass().getResourceAsStream("/Player/Hoved 4.png"));
            Hoved4H = ImageIO.read(getClass().getResourceAsStream("/Player/Hoved 4 (H).png"));
            Hoved4N = ImageIO.read(getClass().getResourceAsStream("/Player/Hoved 4 (N).png"));
            Hoved4V = ImageIO.read(getClass().getResourceAsStream("/Player/Hoved 4 (V).png"));
            Hale = ImageIO.read(getClass().getResourceAsStream("/Player/Hale.png"));
            HaleH = ImageIO.read(getClass().getResourceAsStream("/Player/Hale (H).png"));
            HaleN = ImageIO.read(getClass().getResourceAsStream("/Player/Hale (N).png"));
            HaleV = ImageIO.read(getClass().getResourceAsStream("/Player/Hale (V).png"));
            Krop = ImageIO.read(getClass().getResourceAsStream("/Player/Krop.png"));
            KropH = ImageIO.read(getClass().getResourceAsStream("/Player/Krop (H).png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        for (int i = bodyParts; i > 0; i--) {
            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }
        char dir = keyH.getDir();
        if (dir == 'W') {
            if (lastDir != 'S') {
                this.lastDir = dir;
                y[0] -= gp.TILESIZE;
            } else {
                y[0] += gp.TILESIZE;
            }
        } else if (dir == 'S') {
            if (lastDir != 'W') {
                this.lastDir = dir;
                y[0] += gp.TILESIZE;
            } else {
                y[0] -= gp.TILESIZE;
            }
        } else if (dir == 'A') {
            if (lastDir != 'D') {
                this.lastDir = dir;
                x[0] -= gp.TILESIZE;
            } else {
                x[0] += gp.TILESIZE;
            }
        } else if (dir == 'D') {
            if (lastDir != 'A') {
                this.lastDir = dir;
                x[0] += gp.TILESIZE;
            } else {
                x[0] -= gp.TILESIZE;
            }
        }

        spriteCounter++;
        if (spriteCounter > 0) {
            spriteNum++;
            if (spriteNum > 4) {
                spriteNum = 1;
            }
            spriteCounter = 0;
        }
    }

    public void draw(Graphics g) {
        BufferedImage image = null;

        switch (lastDir) {
            case 'W':
                if (spriteNum == 1) {
                    image = Hoved1;
                }
                if (spriteNum == 2) {
                    image = Hoved2;
                }
                if (spriteNum == 3) {
                    image = Hoved3;
                }
                if (spriteNum == 4) {
                    image = Hoved4;
                }
                break;
            case 'D':
                if (spriteNum == 1) {
                    image = Hoved1H;
                }
                if (spriteNum == 2) {
                    image = Hoved2H;
                }
                if (spriteNum == 3) {
                    image = Hoved3H;
                }
                if (spriteNum == 4) {
                    image = Hoved4H;
                }
                break;
            case 'S':
                if (spriteNum == 1) {
                    image = Hoved1N;
                }
                if (spriteNum == 2) {
                    image = Hoved2N;
                }
                if (spriteNum == 3) {
                    image = Hoved3N;
                }
                if (spriteNum == 4) {
                    image = Hoved4N;
                }
                break;
            case 'A':
                if (spriteNum == 1) {
                    image = Hoved1V;
                }
                if (spriteNum == 2) {
                    image = Hoved2V;
                }
                if (spriteNum == 3) {
                    image = Hoved3V;
                }
                if (spriteNum == 4) {
                    image = Hoved4V;
                }
                break;
        }
        // tegner slangens hovede
        g.drawImage(image, x[0], y[0], gp.TILESIZE, gp.TILESIZE, null);

        // tegner slangens krop vandret
        if (keyH.getDir() == 'D' || keyH.getDir() == 'A') {
            for (int i = 1; i < bodyParts - 1; i++) {
                g.drawImage(KropH, x[i], y[i], gp.TILESIZE, gp.TILESIZE, null);
            }
            // tegner slangens krop lodret
        } else if (keyH.getDir() == 'W' || keyH.getDir() == 'S') {
            for (int i = 1; i < bodyParts - 1; i++) {
                g.drawImage(Krop, x[i], y[i], gp.TILESIZE, gp.TILESIZE, null);
            }
        }
        BufferedImage tailImage = null;
        // Vælger det korrekte hale billede baseret på retningen.
        switch (keyH.getDir()) {
            case 'W':
                tailImage = Hale;
                break;
            case 'S':
                tailImage = HaleN;
                break;
            case 'D':
                tailImage = HaleH;
                break;
            case 'A':
                tailImage = HaleV;
                break;
        }

        // Slangens hale tegnes
        g.drawImage(tailImage, x[bodyParts - 1], y[bodyParts - 1], gp.TILESIZE, gp.TILESIZE, null);
    }

    public void appleEat() {
        if (y[0] == gp.appleY && x[0] == gp.appleX) {
            bodyParts++;
            appleEaten++;
            gp.createApple();
        }
    }
}