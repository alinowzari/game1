import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class MusicPlayer {

    private Clip clip;
    private boolean isPlaying = false;
    private FloatControl volumeControl;

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

            // Get the volume control
            volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);

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

    // ✅ Check if the music is playing
    public boolean isPlaying() {
        return isPlaying;
    }

    // ✅ Method to set the volume (range 0.0f to 1.0f)
    public void setVolume(float volume) {
        if (volumeControl != null) {
            float min = volumeControl.getMinimum();
            float max = volumeControl.getMaximum();
            float range = max - min;
            float volumeValue = min + (range * volume);  // Scale volume
            volumeControl.setValue(volumeValue);  // Set the volume
        }
    }
}
