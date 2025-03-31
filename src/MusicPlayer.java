import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class MusicPlayer {
    private Clip clip;
    private FloatControl gainControl;
    private boolean isPlaying = false;
    private float volume = 0.5f;
    private boolean looping = false;

    public MusicPlayer(String filePath) throws UnsupportedAudioFileException,
            IOException,
            LineUnavailableException {
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(new File(filePath));
        clip = AudioSystem.getClip();
        clip.open(audioStream);
        gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        setVolume(volume);
    }

    // Play with optional looping
    public void play(boolean loop) {
        if (clip != null) {
            this.looping = loop;
            clip.setFramePosition(0);
            if (loop) {
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            } else {
                clip.start();
            }
            isPlaying = true;
        }
    }

    // Original play method (no loop)
    public void play() {
        play(false);
    }

    public void pause() {
        if (clip != null && isPlaying) {
            clip.stop();
            isPlaying = false;
        }
    }

    public void stop() {
        if (clip != null) {
            clip.stop();
            clip.setFramePosition(0);
            isPlaying = false;
        }
    }

    // Properly close the audio resources
    public void close() {
        if (clip != null) {
            stop();
            clip.close();
        }
    }

    public void setVolume(float volume) {
        this.volume = Math.max(0.0f, Math.min(1.0f, volume)); // Clamp between 0 and 1
        if (gainControl != null) {
            float dB = (float) (Math.log(volume) / Math.log(10.0) * 20.0);
            gainControl.setValue(dB);
        }
    }

    public float getVolume() {
        return volume;
    }

    public boolean isPlaying() {
        return isPlaying;
    }

    public boolean isLooping() {
        return looping;
    }
}