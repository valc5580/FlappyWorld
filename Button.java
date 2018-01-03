/*********************************************************
*  Name: Siddharth Pai, Nik Valcic, Ben Stafl            *
*  Course: ICS 4U1 - 01  Pd. 4                           *
*  Final Assignment (Summative): Flappy World            *
*  Class Purpose: To make a class that deals with the    *
*  buttons throughout the game                           *
*  Due Date: May 27, 2014                                *
*********************************************************/

import javax.swing.*; 
import java.awt.*;
import java.awt.event.*;

class Button
{  
   int x, y, width, height, state; 
   ImageIcon button;  
   
   /*** Button *******************************************
   * Purpose: (Constructor) To initialize the instances  *
   * of the class                                        *
   * Parameters: int value for the state of the button   *
   * Returns: none                                       *
   ******************************************************/
   public Button (int s)
   { 
      state = s;
      
      if (state == 1)
      {
         button = new ImageIcon ("Images/Buttons/1/Normal/play.png");
         width = 310; 
         height = 130;
         x = 310;
         y = 270; 
      }
      
      else if (state == 18)
      {
         button = new ImageIcon ("Images/Buttons/1/Normal/play2.png");
         width = 310; 
         height = 130;
         x = 310;
         y = 270;
      }
      
      else if (state == 2)
      {
         button = new ImageIcon ("Images/Buttons/1/Normal/instructions.png");
         width = 185; 
         height = 78; 
         x = 650;
         y = 445;
      }
      
      else if (state == 3)
      {
         button = new ImageIcon ("Images/Buttons/1/Normal/settings.png");
         width = 185; 
         height = 78; 
         x = 370;
         y = 445;
      }
      
      else if (state == 4)
      {
         button = new ImageIcon ("Images/Buttons/1/Normal/highScore.png");
         width = 185; 
         height = 78; 
         x = 65;
         y = 445;
      }
      
      else if (state == 8)
      {
         button = new ImageIcon ("Images/Settings/1/Level Options/easy.png");
         width = 115; 
         height = 71; 
         x = 400;
         y = 445;
      }
      
      else if (state == 9)
      {
         button = new ImageIcon ("Images/Settings/1/Level Options/hard.png");
         width = 115; 
         height = 71; 
         x = 590;
         y = 445;
      }
   }
   
   /*** Button *********************************************
   * Purpose: To initialize the instances of the class     *
   * (given certain parameters)                            *
   * Parameters: x coordinate, y coordinate, state         *
   * Returns: none                                         *
   ********************************************************/
   public Button (int xCord, int yCord, int s)
   {
      state = s;
      x = xCord;
      y = yCord;
      
      if (state == 5)
      {
         button = new ImageIcon ("Images/Buttons/1/Normal/back.png");
         width = 130;
         height = 71;
         x = xCord;
         y = yCord;
      }
      
      else if (state == 21)
      {
         button = new ImageIcon ("Images/Buttons/1/Normal/done.png");
         width = 104;
         height = 57;
         x = xCord;
         y = yCord;
      }

      
      else if (state == 6)
      {
         button = new ImageIcon ("Images/Settings/1/OnOff/on.png");
         width = 115;
         height = 71;
         x = xCord;
         y = yCord;
      }
      
      else if (state == 7)
      {
         button = new ImageIcon ("Images/Settings/1/OnOff/off.png");
         width = 115;
         height = 71;
         x = xCord;
         y = yCord;
      }
      
      else if (state == 10)
      {
         button = new ImageIcon ("Images/Buttons/1/Normal/pause2.png");
         width= 90;
         height= 68;
         x = xCord;
         y = yCord;
      }
      
      else if (state == 11)
      {
         button = new ImageIcon ("Images/Buttons/PauseButtons/Normal/resume.png");
         width= 200;
         height= 80;
         x = xCord;
         y = yCord;
      }
      
      else if (state == 12)
      {
         button = new ImageIcon ("Images/Buttons/PauseButtons/Normal/quit.png");
         width= 200;
         height= 80;
         x = xCord;
         y = yCord;
      }
      
      else if (state == 13)
      {
         button = new ImageIcon ("Images/Buttons/Restart/restart.png");
         width = 200;
         height = 80;
         x = xCord;
         y = yCord;
      }
      
      else if (state == 14)
      {
         button = new ImageIcon ("Images/Buttons/1/Normal/submit.png");
         width = 200;
         height = 80;
         x = xCord;
         y = yCord; 
      }
      
      else if (state == 15)
      {
         button = new ImageIcon ("Images/Buttons/1/Normal/left.png");
         width = 100;
         height = 100;
         x = xCord;
         y = yCord;
      }
      
      else if (state == 16)
      {
         button = new ImageIcon ("Images/Buttons/1/Normal/right.png");
         width = 100;
         height = 100;
         x = xCord;
         y = yCord;
      }
      
      else if (state == 17)
      {
         button = new ImageIcon ("Images/Buttons/1/Normal/select.png");
         width = 243;
         height = 93;
         x = xCord;
         y = yCord;
      }
      
      else if (state == 19)
      {
         button = new ImageIcon ("Images/Buttons/1/Normal/select2.png");
         width = 243;
         height = 93;
         x = xCord;
         y = yCord;
      }
      
      else if (state == 20)
      {
         button = new ImageIcon ("Images/Buttons/1/Normal/select3.png");
         width = 243;
         height = 92;
         x = xCord;
         y = yCord;
      }
   }
   
   /*** displayButton ************************************
   * Purpose: To display the buttons                     *
   * Parameters: Graphcs Object                          *
   * Returns: none                                       *
   ******************************************************/
   public void displayButton (Graphics g1)
   {
      g1.drawImage (button.getImage(), x, y, null);
   }
   
   /*** displayButton *****************************************
   * Purpose: To display the button, given certain parameters *
   * Parameters: Graphics object, width, and height           *
   * Returns: none                                            *
   ***********************************************************/
   public void displayButton (Graphics g1, int w, int h)
   {
      g1.drawImage (button.getImage(), x, y, w, h, null);
   }
   
   /*** pressed ******************************************
   * Purpose: To make the button "pressed"               *
   * Parameters: none                                    *
   * Returns: none                                       *
   ******************************************************/
   public void pressed ()
   {
      if (state == 1)
         button = new ImageIcon ("Images/Buttons/1/Pressed/play.png");
      else if (state == 2)
         button = new ImageIcon ("Images/Buttons/1/Pressed/instructions.png");
      else if (state == 3)
         button = new ImageIcon ("Images/Buttons/1/Pressed/settings.png");
      else if (state == 4)
         button = new ImageIcon ("Images/Buttons/1/Pressed/highScore.png");
      else if (state == 5)
         button = new ImageIcon ("Images/Buttons/1/Pressed/back.png");
      else if (state == 6)
         button = new ImageIcon ("Images/Settings/1/OnOff/on2.png");
      else if (state == 7)
         button = new ImageIcon ("Images/Settings/1/OnOff/off2.png");
      else if (state == 8)
         button = new ImageIcon ("Images/Settings/1/Level Options/easy2.png");
      else if (state == 9)
         button = new ImageIcon ("Images/Settings/1/Level Options/hard2.png");
      else if (state == 10)
         button = new ImageIcon ("Images/Buttons/1/Pressed/pause2.png");
      else if (state == 11)
         button = new ImageIcon ("Images/Buttons/PauseButtons/Pressed/resume.png");
      else if (state == 12)
         button = new ImageIcon ("Images/Buttons/PauseButtons/Pressed/quit.png");
      else if (state == 13)
         button = new ImageIcon ("Images/Buttons/Restart/restartpressed.png");
      else if (state == 14)
         button = new ImageIcon ("Images/Buttons/1/Pressed/submit.png");
      else if (state == 15)
         button = new ImageIcon ("Images/Buttons/1/Pressed/left.png");
      else if (state == 16)
         button = new ImageIcon ("Images/Buttons/1/Pressed/right.png");
      else if (state == 17)
         button = new ImageIcon ("Images/Buttons/1/Pressed/select.png");
      else if (state == 18)
         button = new ImageIcon ("Images/Buttons/1/Pressed/play2.png");
      else if (state == 19)
         button = new ImageIcon ("Images/Buttons/1/Pressed/select2.png");
      else if (state == 20)
         button = new ImageIcon ("Images/Buttons/1/Pressed/select3.png");
      else if (state == 21)
         button = new ImageIcon ("Images/Buttons/1/Pressed/done.png");
   }
   
   /*** released ***************************
   * Purpose: To "release" the button      *
   * Parameters: none                      *
   * Returns: none                         *
   ****************************************/
   public void released()
   {
      if (state == 1)
         button = new ImageIcon ("Images/Buttons/1/Normal/play.png");
      else if (state == 2)
         button = new ImageIcon ("Images/Buttons/1/Normal/instructions.png");
      else if (state == 3)
         button = new ImageIcon ("Images/Buttons/1/Normal/settings.png");
      else if (state == 4)
         button = new ImageIcon ("Images/Buttons/1/Normal/highScore.png");
      else if (state == 5)
         button = new ImageIcon ("Images/Buttons/1/Normal/back.png");
      else if (state == 6)
         button = new ImageIcon ("Images/Settings/1/OnOff/on.png");
      else if (state == 7)
         button = new ImageIcon ("Images/Settings/1/OnOff/off.png");
      else if (state == 8)
         button = new ImageIcon ("Images/Settings/1/Level Options/easy.png");
      else if (state == 9)
         button = new ImageIcon ("Images/Settings/1/Level Options/hard.png");  
      else if (state == 10)
         button = new ImageIcon ("Images/Buttons/1/Normal/pause2.png");
      else if (state == 11)
         button = new ImageIcon ("Images/Buttons/PauseButtons/Normal/resume.png");
      else if (state == 12)
         button = new ImageIcon ("Images/Buttons/PauseButtons/Normal/quit.png"); 
      else if (state == 13)
         button = new ImageIcon ("Images/Buttons/Restart/restart.png");
      else if (state == 14)
         button = new ImageIcon ("Images/Buttons/1/Normal/submit.png");
      else if (state == 15)
         button = new ImageIcon ("Images/Buttons/1/Normal/left.png");
      else if (state == 16)
         button = new ImageIcon ("Images/Buttons/1/Normal/right.png");
      else if (state == 17)
         button = new ImageIcon ("Images/Buttons/1/Normal/select.png");
      else if (state == 18)
         button = new ImageIcon ("Images/Buttons/1/Normal/play2.png");
      else if (state == 19)
         button = new ImageIcon ("Images/Buttons/1/Normal/select2.png");
      else if (state == 20)
         button = new ImageIcon ("Images/Buttons/1/Normal/select3.png");
      else if (state == 21)
         button = new ImageIcon ("Images/Buttons/1/Normal/done.png");
   }
   
   /*** setTransparent ***********************************
   * Purpose: To make the buttons "translucent"          *
   * Parameters: none                                    *
   * Returns: none                                       *
   ******************************************************/
   public void setTransparent ()
   {
      if (state == 15)
         button = new ImageIcon ("Images/Buttons/1/Normal/leftT.png");
      else if (state == 16)
         button = new ImageIcon ("Images/Buttons/1/Normal/rightT.png");
      else if (state == 17)
         button = new ImageIcon ("Images/Buttons/1/Normal/select1T.png");
      else if (state == 19)
         button = new ImageIcon ("Images/Buttons/1/Normal/select2T.png");
   }
}