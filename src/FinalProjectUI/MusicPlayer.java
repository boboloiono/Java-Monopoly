package FinalProjectUI;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;

public class MusicPlayer {
	public static Clip clip;
	public static AudioInputStream audioInputStream;
	public static FloatControl volumeControl;

	public MusicPlayer() {
		
	}
	
    public static void playBackgroundMusic(String filePath) {
        try {
            File musicFile = new File(filePath);
            audioInputStream = AudioSystem.getAudioInputStream(musicFile);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY); // 循環播放
            clip.start();

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
    
    public static void playSFX(String filePath) {
	    try {
	    	File musicFile = new File(filePath);
	    	audioInputStream = AudioSystem.getAudioInputStream(musicFile);
            clip = AudioSystem.getClip();
	    	clip.open(audioInputStream);
	        clip.start();
	    } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
	    	e.printStackTrace();
	    }		

    }
    
    public static void closeSFX() {
    	clip.stop();
    	clip.close();
    	try {
			audioInputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

    }
    
    public static void controlVolume(double volume) {
    	// 獲取音量控制
        volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);

        // 設置音量（範圍：0.0到1.0）
        float linearVolume = (float) (Math.log(volume) / Math.log(10.0) * 20.0);
        volumeControl.setValue(linearVolume);
    }
}
