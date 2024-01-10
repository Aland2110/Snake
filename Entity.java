import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.Random;

public class Entity {
    private int[] x;
    private int[] y;
    public int bodyParts = 2;
    public int appleEaten;
    private char lastDir;
    private int spriteCounter = 0;
    private int spriteNum = 1;
    private BufferedImage Hoved1, Hoved1H, Hoved1N, Hoved1V, Hoved2, Hoved2H, Hoved2N, Hoved2V, Hoved3, Hoved3H,
            Hoved3N, Hoved3V, Hoved4, Hoved4H, Hoved4N, Hoved4V, Hale, HaleH, HaleN, HaleV, Krop, KropH, Æble,
            Hjørnestykke, HjørnestykkeV, HjørnestykkeH, HjørnestykkeN;
    private int appleX;
    private int appleY;

    GamePanel gp;
    KeyHandler keyH;
    Random random;

    public Entity(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;
        this.random = new Random();
    }

    public void snakePos() {
        this.x = new int[(gp.GAMEWIDTH * gp.GAMEHEIGHT) / gp.TILESIZE];
        this.y = new int[(gp.GAMEWIDTH * gp.GAMEHEIGHT) / gp.TILESIZE];
        this.lastDir = 'D';

        for (int i = 0; i < bodyParts; i++) {
            x[i] = (gp.n / 2 - i) * gp.TILESIZE + gp.gameWindowXStart;
            y[i] = gp.m / 2 * gp.TILESIZE + gp.gameWindowYStart;
        }
    }

    public int[] getXarr() {
        return x;
    }

    public int[] getYarr() {
        return y;
    }

    public void getImage() {
        try {
            Hoved1 = ImageIO.read(getClass().getResourceAsStream("/res/Hoved 1.png"));
            Hoved1H = ImageIO.read(getClass().getResourceAsStream("/res/Hoved 1 (H).png"));
            Hoved1N = ImageIO.read(getClass().getResourceAsStream("/res/Hoved 1 (N).png"));
            Hoved1V = ImageIO.read(getClass().getResourceAsStream("/res/Hoved 1 (V).png"));
            Hoved2 = ImageIO.read(getClass().getResourceAsStream("/res/Hoved 2.png"));
            Hoved2H = ImageIO.read(getClass().getResourceAsStream("/res/Hoved 2 (H).png"));
            Hoved2N = ImageIO.read(getClass().getResourceAsStream("/res/Hoved 2 (N).png"));
            Hoved2V = ImageIO.read(getClass().getResourceAsStream("/res/Hoved 2 (V).png"));
            Hoved3 = ImageIO.read(getClass().getResourceAsStream("/res/Hoved 3.png"));
            Hoved3H = ImageIO.read(getClass().getResourceAsStream("/res/Hoved 3 (H).png"));
            Hoved3N = ImageIO.read(getClass().getResourceAsStream("/res/Hoved 3 (N).png"));
            Hoved3V = ImageIO.read(getClass().getResourceAsStream("/res/Hoved 3 (V).png"));
            Hoved4 = ImageIO.read(getClass().getResourceAsStream("/res/Hoved 4.png"));
            Hoved4H = ImageIO.read(getClass().getResourceAsStream("/res/Hoved 4 (H).png"));
            Hoved4N = ImageIO.read(getClass().getResourceAsStream("/res/Hoved 4 (N).png"));
            Hoved4V = ImageIO.read(getClass().getResourceAsStream("/res/Hoved 4 (V).png"));
            Hale = ImageIO.read(getClass().getResourceAsStream("/res/Hale.png"));
            HaleH = ImageIO.read(getClass().getResourceAsStream("/res/Hale (H).png"));
            HaleN = ImageIO.read(getClass().getResourceAsStream("/res/Hale (N).png"));
            HaleV = ImageIO.read(getClass().getResourceAsStream("/res/Hale (V).png"));
            Krop = ImageIO.read(getClass().getResourceAsStream("/res/Krop.png"));
            KropH = ImageIO.read(getClass().getResourceAsStream("/res/Krop (H).png"));
            Æble = ImageIO.read(getClass().getResourceAsStream("/res/Æble.png"));
            Hjørnestykke = ImageIO.read(getClass().getResourceAsStream("/res/Hjørnestykke.png"));
            HjørnestykkeV = ImageIO.read(getClass().getResourceAsStream("/res/HjørnestykkeV.png"));
            HjørnestykkeN = ImageIO.read(getClass().getResourceAsStream("/res/HjørnestykkeN.png"));
            HjørnestykkeH = ImageIO.read(getClass().getResourceAsStream("/res/HjørnestykkeH.png"));
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

        appleEat();
    }

    public void draw(Graphics g) {
        BufferedImage image = null;

        g.drawImage(Æble, appleX, appleY, gp.TILESIZE, gp.TILESIZE, null);

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

        // itererer gennem slangens krop hvor hovedet og halen er eksluderet
        for (int i = 1; i < bodyParts - 1; i++) {
            BufferedImage bodyImage = null;
            // Kommer fra venstre side og går op
            if (x[i] > x[i + 1] && y[i] > y[i - 1]) {
                bodyImage = HjørnestykkeV;
            } // Kommer fra venstre side og går ned
            else if (x[i] > x[i + 1] && y[i] < y[i - 1]) {
                bodyImage = Hjørnestykke; 
            }  // Kommer fra venstre side og går op
            else if (x[i] > x[i - 1] && y[i] > y[i + 1]) {
                bodyImage = HjørnestykkeV;
            } // Kommer fra højre side og går ned
            else if (x[i] < x[i + 1] && y[i] < y[i - 1]) {
                bodyImage = HjørnestykkeN;
            } // Kommer fra højre side og går op
            else if (x[i] < x[i + 1] && y[i] > y[i - 1]) {
                bodyImage = HjørnestykkeH;
            } // Kommer oppe fra og går til højre
            else if (x[i] < x[i - 1] && y[i] > y[i + 1]) {
                bodyImage = HjørnestykkeH;
            } // Kommer nede fra og går til højre
            else if (x[i] < x[i - 1] && y[i] < y[i + 1]) {
                bodyImage = HjørnestykkeN;
            } // Kommer nede fra og går til venstre 
            else if (x[i] > x[i - 1] && y[i] < y[i + 1]) {
                bodyImage = Hjørnestykke;
            } // Kroppen vandret
            else if (x[i] < x[i - 1] || x[i] > x[i - 1]) {
                bodyImage = KropH;
            } // Kroppen lodret
            else if (y[i] < y[i - 1] || y[i] > y[i - 1]) {
                bodyImage = Krop;
            }
            g.drawImage(bodyImage, x[i], y[i], gp.TILESIZE, gp.TILESIZE, null);
        }

        BufferedImage tailImage = null;

        int tailX = x[bodyParts - 1] - x[bodyParts - 2];
        int tailY = y[bodyParts - 1] - y[bodyParts - 2];

        if (tailX < 0) {
            // Tjekker om halen er til venstre for den forrige kropsdel
            tailImage = HaleH;
        } else if (tailX > 0) {
            // Tjekker om halen er til højre for den forrige kropsdel
            tailImage = HaleV;
        } else if (tailY < 0) {
            // Tjekker om halen er over den forrige kropsdel
            tailImage = HaleN;
        } else if (tailY > 0) {
            // Tjekker om halen er under den forrige kropsdel
            tailImage = Hale;
        }

        // Slangens hale tegnes
        g.drawImage(tailImage, x[bodyParts - 1], y[bodyParts - 1], gp.TILESIZE, gp.TILESIZE, null);
    }

    public void appleEat() {
        if (y[0] == appleY && x[0] == appleX) {
            bodyParts++;
            appleEaten++;
            createApple();
        }
    }

    public void createApple() {
        int tempX = random.nextInt(gp.n) * gp.TILESIZE + gp.gameWindowXStart;
        int tempY = random.nextInt(gp.m) * gp.TILESIZE + gp.gameWindowYStart;

        // tjekker om koordinaterne til æblet er i slangen
        for (int i = 0; i < bodyParts; i++) {
            while (x[i] == tempX && y[i] == tempY) {
                tempX = random.nextInt(gp.n) * gp.TILESIZE + gp.gameWindowXStart;
                tempY = random.nextInt(gp.m) * gp.TILESIZE + gp.gameWindowYStart;
            }
        }

        appleX = tempX;
        appleY = tempY;
    }
}