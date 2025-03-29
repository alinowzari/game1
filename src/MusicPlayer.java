import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class MusicPlayer {

    private Clip clip;
    private boolean isPlaying = false;
    private float volume = 0.5f;  // ✅ Store current volume

    public MusicPlayer(String filePath) {
        try {
            File audioFile = new File(filePath);
            if (!audioFile.exists()) {
                System.out.println("Audio file not found: " + filePath);
                return;
            }

            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
            setVolume(volume);  // ✅ Apply default volume on startup

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    // 🎵 Play the music
    public void play() {
        if (clip != null && !isPlaying) {
            clip.setFramePosition(0);    // Start from the beginning
            clip.start();
            isPlaying = true;
        }
    }

    // 🔥 Pause the music
    public void pause() {
        if (clip != null && isPlaying) {
            clip.stop();
            isPlaying = false;
        }
    }

    // 🔥 Stop the music
    public void stop() {
        if (clip != null) {
            clip.stop();
            clip.setFramePosition(0);
            isPlaying = false;
        }
    }

    // ✅ Set volume method
    public void setVolume(float volume) {
        this.volume = volume;  // Store the current volume

        if (clip != null) {
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);

            // Convert volume to decibels (range -80 to 6 dB)
            float dB = (float) (Math.log(volume) / Math.log(10.0) * 20.0);
            gainControl.setValue(Math.max(gainControl.getMinimum(), Math.min(dB, gainControl.getMaximum())));
        }
    }

    // ✅ Get current volume
    public float getVolume() {
        return volume;  // Return the stored volume
    }

    // ✅ Check if the music is playing
    public boolean isPlaying() {
        return isPlaying;
    }
}
