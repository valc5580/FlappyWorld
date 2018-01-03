/*********************************************************
*  Name: Siddharth Pai, Nik Valcic, Ben Stafl            *
*  Course: ICS 4U1 - 01  Pd. 4                           *
*  Final Assignment (Summative): Flappy World            *
*  Class Purpose: To make a class that deals with the    *
*  pause screen in 1 player                              *
*  Due Date: May 27, 2014                                *
*********************************************************/
import java.awt.event.*; 
import java.awt.*; 
import javax.swing.*;

class PauseScreen
{
   int x, y, finalY, width, height;
   ImageIcon screen;
   Button resume; 
   Button quit; 
   
   /*** PauseScreen **************************************
   * Purpose: (Constructor) initializes the instances    *
   * Parameters: none                                    *
   * Returns: none                                       *
   ******************************************************/
   public PauseScreen()
   {
      screen = new ImageIcon ("Images/PauseScreen/pauseScreen.png");
      width = 545;
      height = 385;
      x = (900/2)- (width/2);
      finalY = 170;
      y = 700;
      resume = new Button ((2*x+width)/2 - 100, y+115, 11); 
      quit = new Button ((2*x+width)/2 - 100, y+215, 12);
   }
   
   /*** displayPauseScreen ***************
   * Purpose: displays the pause screen  *
   * Parameters: Graphics object         *
   * Returns: none                       *
   **************************************/
   public void displayPauseScreen(Graphics g)
   {
      g.drawImage (screen.getImage(), x, y, null);
      resume.displayButton(g);
      quit.displayButton (g);
   }
   
   /*** updateCord ***********************************
   * Purpose: updates the coordinates of the screen  *
   * Parameters: none                                *
   * Returns: none                                   *
   **************************************************/
   public void updateCord ()
   {
      if (y> finalY)
      {
         y-=6;
         resume = new Button ((2*x+width)/2 - 100, y+115, 11); 
         quit = new Button ((2*x+width)/2 - 100, y+215, 12);
      }
   }
}