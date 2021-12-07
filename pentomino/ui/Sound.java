package pentomino.ui;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {

	public static void clear_sound() {
		File sound_clear = new File("assets/clear.wav");
		try {
			AudioInputStream audio1 = AudioSystem.getAudioInputStream(sound_clear);
			Clip clip1 = AudioSystem.getClip();
			clip1.open(audio1);
			clip1.start();
			// clip1.drain();
		} catch (Exception e) {
		}
	}

	public static void fall_sound() {
		try {
			File sound_fall = new File("assets/fall.wav");
			AudioInputStream audio1 = AudioSystem.getAudioInputStream(sound_fall);
			Clip clip1 = AudioSystem.getClip();
			clip1.open(audio1);
			clip1.start();
			// clip1.drain();
		} catch (Exception e) {
		}
	}

	public static void game_over_sound() {
		try {
			File sound_fall = new File("assets/gameover.wav");
			AudioInputStream audio1 = AudioSystem.getAudioInputStream(sound_fall);
			Clip clip1 = AudioSystem.getClip();
			clip1.open(audio1);
			clip1.start();
			// clip1.drain();
		} catch (Exception e) {
		}
	}

	public static void rotate_sound() {
		try {
			File sound_fall = new File("assets/rotate.wav");
			AudioInputStream audio1 = AudioSystem.getAudioInputStream(sound_fall);
			Clip clip1 = AudioSystem.getClip();
			clip1.open(audio1);
			clip1.start();
			// clip1.drain();
		} catch (Exception e) {
		}
	}
}