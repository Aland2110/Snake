import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class Sound {

        Clip[] clip = new Clip[30];
        URL soundURL[] = new URL[30];
        FloatControl control;

        public Sound() {
                soundURL[0] = getClass().getResource("res\\musik\\mario_musik.wav");
                soundURL[1] = getClass().getResource("res\\musik\\eatingSE.wav");
        }

        public void setFile(int i) {
                try {
                        AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
                        clip[i] = AudioSystem.getClip();
                        clip[i].open(ais);
                        volume(-30, i);
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
                control = (FloatControl) clip[i].getControl(FloatControl.Type.MASTER_GAIN);
                control.setValue(v);
        }
}
