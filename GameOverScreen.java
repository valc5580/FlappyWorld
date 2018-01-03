/*********************************************************
*  Name: Siddharth Pai, Nik Valcic, Ben Stafl            *
*  Course: ICS 4U1 - 01  Pd. 4                           *
*  Final Assignment (Summative): Flappy World            *
*  Class Purpose: To make a class that deals with the    *
*  Game Over screen (for 1P and 2P)                      *
*  Due Date: May 27, 2014                                *
*********************************************************/
import java.awt.event.*;
import javax.swing.*; 
import java.awt.*;

class GameOverScreen
{
   ImageIcon screen = new ImageIcon ("Images/GameOverScreen/screen.png");
   Button restart;
   Button quit;
   
   int clickedX,clickedY;
   
   boolean restartPressed = false;
   boolean quitPressed = false;
   
   boolean restartIt = false;
   boolean quitIt = false;
   
   int x, y, width, height, finalY; 
   
   /*** GameOverScreen ***********************************
   * Purpose: (Constructor) to initialize the instances  * 
   * of the class                                        *
   * Parameters: none                                    *
   * Returns: none                                       *
   ******************************************************/
   public GameOverScreen()
   {
      width = 545;
      height = 385;
      x = (900/2)- (width/2);
      finalY = 170;
      y = 700;
      restart = new Button ((2*x+width)/2 - 100, y+115, 13); 
      quit = new Button ((2*x+width)/2 - 100, y+215, 12);
   }
   
   /*** GameOverScreen ***********************************
   * Purpose: (Constructor) to initialize the instances  *  
   * of the class (for the 2Player mode)                 *
   * Parameters: state                                   *
   * Returns: none                                       *
   ******************************************************/
   public GameOverScreen(int s)
   {
      if (s == 1)
         screen = new ImageIcon ("Images/GameOverScreen/P1.png");
      else if (s == 2)
         screen = new ImageIcon ("Images/GameOverScreen/P2.png");
         
      width = 545;
      height = 385;
      x = (900/2)- (width/2);
      finalY = 170;
      y = 700;
      restart = new Button ((2*x+width)/2 - 100, y+115, 13); 
      quit = new Button ((2*x+width)/2 - 100, y+215, 12);
   }
   
   /*** displayGameOverScreen ****************************
   * Purpose: Displays the game over screen              *
   * Parameters: Graphics Object                         *
   * Returns: none                                       *
   ******************************************************/
   public void displayGameOverScreen(Graphics g)
   {
         g.drawImage (screen.getImage(), x, y, null);
         restart.displayButton(g);
         quit.displayButton (g);
   }
   
   /*** updateCord ***************************************
   * Purpose: updates the coordinates                    *
   * Parameters: none                                    *
   * Returns: none                                       *
   ******************************************************/
   public void updateCord()
   {
      if (y >finalY)
         y-=10;
      restart = new Button ((2*x+width)/2 - 100, y+115, 13); 
      quit = new Button ((2*x+width)/2 - 100, y+215, 12);
   }
   
   /*** updateScreen *************************************
   * Purpose: updates the screen                         *
   * Parameters: MouseEvent                              *
   * Returns: none                                       *
   ******************************************************/
   public void updateScreen(MouseEvent e)
   {
      clickedX = e.getX();
      clickedY = e.getY();
      
      if ((e.getX() >= restart.x && e.getX() <= restart.x + restart.width) && (e.getY() >= restart.y && e.getY() <= restart.y+restart.height))
      {
         restart.pressed();
         restartPressed = true;
      }
      
      else if ((e.getX() >= quit.x && e.getX() <= quit.x + quit.width) && (e.getY() >= quit.y && e.getY() <= quit.y+quit.height))
      {
         quit.pressed();
         quitPressed = true;
      }
   }
   
   /*** updateScreen2 ************************************
   * Purpose: updates the screen                         *
   * Parameters: MouseEvent                              *
   * Returns: none                                       *
   ******************************************************/
   public void updateScreen2(MouseEvent e)
   {
      if ((e.getX() >= restart.x && e.getX() <= restart.x + restart.width) && (e.getY() >= restart.y && e.getY() <= restart.y+restart.height))
      {
         if ((clickedX >= restart.x && clickedX <= restart.x + restart.width) && (clickedY >= restart.y && clickedY <= restart.y+restart.height))
         {
            restartPressed = false;
            restartIt = true;
            quitIt = false;
         }
      }
      
      else if ((e.getX() >= quit.x && e.getX() <= quit.x + quit.width) && (e.getY() >= quit.y && e.getY() <= quit.y+quit.height))
      {
         if ((clickedX >= quit.x && clickedX <= quit.x + quit.width) && (clickedY >= quit.y && clickedY <= quit.y+quit.height))
         {
            quitPressed = false;
            restartIt = false;
            quitIt = true;
         }
      }
      
      restart.released();
      quit.released();
   }
}