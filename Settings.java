/*********************************************************
*  Name: Siddharth Pai, Nik Valcic, Ben Stafl            *
*  Course: ICS 4U1 - 01  Pd. 4                           *
*  Final Assignment (Summative): Flappy World            *
*  Class Purpose: To make a class that deals with the    *
*  settigs screen                                        *
*  Due Date: May 27, 2014                                *
*********************************************************/
import javax.swing.*;
import java.awt.event.*; 
import java.awt.*; 

class Settings
{
   int state;
   int clickedX, clickedY;
   ImageIcon image;
   Button back;
   Button on1;
   Button off1;
   Button on2;
   Button off2;
   Button easy;
   Button hard;
   
   boolean backPressed;
   
   /*** Settings *****************************************
   * Purpose: (Constructor) initializes instances        *
   * Parameters: state                                   *
   * Returns: none                                       *
   ******************************************************/
   public Settings (int s)
   {
      state = s;
      
      image = new ImageIcon ("Images/Settings/"+state+"/Settings.png");
      back = new Button (385, 580, 5);
      on1 = new Button (400, 210,6);
      off1 = new Button (590, 210,7);
      on2 = new Button (400, 330,6);
      off2 = new Button (590, 330,7);
      easy = new Button (8);
      hard = new Button (9);
      
      on1.pressed();
      on2.pressed();
      easy.pressed();
      backPressed = false;
   }
   
   /*** displaySettings *********************
   * Purpose: displays the settings screen  *
   * Parameters: Graphics object            *
   * Returns: none                          *
   *****************************************/
   public void displaySettings (Graphics g1)
   {
      g1.drawImage (image.getImage(), 0, 0, null);
      back.displayButton (g1);
      on1.displayButton (g1);
      off1.displayButton (g1);
      on2.displayButton (g1);
      off2.displayButton (g1);
      easy.displayButton (g1);
      hard.displayButton (g1);
   }
   
   /*** updateSettings1 ************************************************************
   * Purpose: updates the buttons on screen                                        *
   * Parameters: MouseEvent, music (on or off), sounds (on or off), easy (or hard) *                                    *
   * Returns: none                                                                 *
   ********************************************************************************/
   public boolean[] updateSettings1 (MouseEvent e, boolean m, boolean s, boolean f)
   {
      boolean [] result = new boolean [3];
      clickedX = e.getX();
      clickedY = e.getY();
      
      if ((e.getX()>= on1.x && e.getX() <= on1.x+on1.width) && (e.getY() >= on1.y && e.getY() <= on1.y + on1.height))
      {
         on1.pressed();
         off1.released();
         m = true;
      }
      
      else if ((e.getX()>= off1.x && e.getX() <= off1.x+off1.width) && (e.getY() >= off1.y && e.getY() <= off1.y + off1.height))
      {
         on1.released();
         off1.pressed();
         m = false;
      }
      
      else if ((e.getX()>= on2.x && e.getX() <= on2.x+on2.width) && (e.getY() >= on2.y && e.getY() <= on2.y + on2.height))
      {
         on2.pressed();
         off2.released();
         s = true;
      }
      
      else if ((e.getX()>= off2.x && e.getX() <= off2.x+off2.width) && (e.getY() >= off2.y && e.getY() <= off2.y + off2.height))
      {
         on2.released();
         off2.pressed();
         s = false;
      }
      
      else if ((e.getX()>= easy.x && e.getX() <= easy.x+easy.width) && (e.getY() >= easy.y && e.getY() <= easy.y + easy.height))
      {
         easy.pressed();
         hard.released();
         f = true;
      }
      
      else if ((e.getX()>= hard.x && e.getX() <= hard.x+ hard.width) && (e.getY() >= hard.y && e.getY() <= hard.y + hard.height))
      {
         easy.released();
         hard.pressed();
         f = false;
      }
      
      else if ((e.getX()>= back.x && e.getX() <= back.x+ back.width) && (e.getY() >= back.y && e.getY() <= back.y + back.height))
      {
         back.pressed();
      }
      
      result[0] = m;
      result[1] = s;
      result [2] = f;
      return result;
   }
   
   /*** updateSettings2 *********************
   * Purpose: updates the buttons on screen *
   * Parameters: MouseEvent                 *
   * Returns: none                          *
   *****************************************/
   public void updateSettings2(MouseEvent e)
   {
      if ((e.getX()>= back.x && e.getX() <= back.x+ back.width) && (e.getY() >= back.y && e.getY() <= back.y + back.height))
      {
         if ((clickedX>= back.x && clickedX <= back.x+ back.width) && (clickedY >= back.y && clickedY <= back.y + back.height))
            backPressed = true;
      }
      
      back.released();  
   }
   
   /*** updateSettings3 ***********
   * Purpose: updates the buttons *
   * Parameters: settings info    *
   * Returns: none                *
   *******************************/
   public void updateSettings3(boolean[] settingInfo)
   {
      if (settingInfo[0])
      {
         on1.pressed();
         off1.released();
      }
      else 
      {
         off1.pressed();
         on1.released();
      }
      
      if (settingInfo[1])
      {
         on2.pressed();
         off2.released();
      }
      else
      {
         on2.released();
         off2.pressed();
      }
      
      if (settingInfo[2])
      {
         easy.pressed();
         hard.released();
      }
      else
      {
         easy.released();
         hard.pressed();
      }
   }

}