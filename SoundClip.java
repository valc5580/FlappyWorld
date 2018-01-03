/*********************************************************
*  Name: Siddharth Pai, Nik Valcic, Ben Stafl            *
*  Course: ICS 4U1 - 01  Pd. 4                           *
*  Final Assignment (Summative): Flappy World            *
*  Class Purpose: To make a class that deals with the    *
*  sounds in the game                                    *
*  Due Date: May 27, 2014                                *
*********************************************************/
import java.io.*;
import java.net.URL;
import javax.sound.sampled.*;
import javax.swing.*;

public class SoundClip
{
   Clip clip;
   
   /*** SoundClip ************************************
   * Purpose: (Constructor) to initialize instances  *
   * Parameters: file name                           *
   * Returns: none                                   *
   **************************************************/
   public SoundClip (String fileName) 
   {  
      try 
      {
         URL url = this.getClass().getClassLoader().getResource(fileName);
         AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
         clip = AudioSystem.getClip();
         clip.open(audioIn);
      } 
      catch (UnsupportedAudioFileException e) {
         e.printStackTrace();
      } 
      catch (IOException e) {
         e.printStackTrace();
      } 
      catch (LineUnavailableException e) {
         e.printStackTrace();
      }
   }
   
   /*** start *******************************
   * Purpose: starts playing the sound file *
   * Parameters: none                       *
   * Returns: none                          *
   *****************************************/
   public void start()
   {
      clip.start();
   }
}