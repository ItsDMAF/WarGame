package GameLogic;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;

public class AudioPlayer {
    private Clip clip;

    // To play the music
    public void playSound(String soundFilePath) {
        try {
            File soundFile = new File(soundFilePath);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    // Make the music to play as loop
    public void loopSound(String soundFilePath) {
        try {
            File soundFile = new File(soundFilePath);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    // Stop the music
    public void stopSound() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
    }

    // Getter of music
    public Clip getClip() {
        return clip;
    }

    // Setter of music
    public void setClip(Clip clip) {
        this.clip = clip;
    }

    @Override
    public String toString() {
        return "AudioPlayer"
                + "\nclip=" + clip;
    }

}
