import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class Sound {

        Clip clip[] = new Clip[3];
        URL soundURL[] = new URL[3];
        FloatControl control[] = new FloatControl[3];

        public Sound() {
                soundURL[0] = getClass().getResource("res/Music/spil_musik.wav");
                soundURL[1] = getClass().getResource("res/Music/eatingSE.wav");
                soundURL[2] = getClass().getResource("res/Music/death_sound.wav");
        }

        public void setFile(int i, int v) {
                try {
                        AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
                        clip[i] = AudioSystem.getClip();
                        clip[i].open(ais);
                        volume(v, i);
                } catch (Exception e) {
                        e.printStackTrace();
                }
        }

        public void play(int i) {
                clip[i].start();
        }

        public void loop(int i) {
                clip[i].loop(Clip.LOOP_CONTINUOUSLY);
        }

        public void stop(int i) {
                clip[i].stop();
        }

        public void volume(float v, int i) {
                control[i] = (FloatControl) clip[i].getControl(FloatControl.Type.MASTER_GAIN);
                control[i].setValue(v);
        }
}
