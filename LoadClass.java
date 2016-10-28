package minesweeper;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.Clip;
import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 *
 * @author User
 */
public class LoadClass {
    
    Clip clip;
    Icon blank = new ImageIcon(getClass().getResource("Default.png"));
    Icon empty = new ImageIcon(getClass().getResource("Empty.png"));
    Icon one = new ImageIcon(getClass().getResource("One.png"));
    Icon two = new ImageIcon(getClass().getResource("Two.png"));
    Icon three = new ImageIcon(getClass().getResource("Three.png"));
    Icon four = new ImageIcon(getClass().getResource("Four.png"));
    Icon five = new ImageIcon(getClass().getResource("Five.png"));
    Icon six = new ImageIcon(getClass().getResource("Six.png"));
    Icon seven = new ImageIcon(getClass().getResource("Seven.png"));
    Icon eight = new ImageIcon(getClass().getResource("Eight.png"));
    Icon flagIcon = new ImageIcon(getClass().getResource("Flag.png"));
    Icon nuclear = new ImageIcon(getClass().getResource("Nuclear.png"));
    
    public LoadClass(){
        
    }
    
    public void playMusic(){
            
            try {
                AudioInputStream var;
                var = AudioSystem.getAudioInputStream(getClass().getResource("Boom.wav"));
                clip = AudioSystem.getClip();
                clip.open(var);
                FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                gainControl.setValue(-25.0f);
                clip.start();
            } catch (Exception e){}
            
    }
    
    public void stopMusic(){
        clip.stop();
    }
}
