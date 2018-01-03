/*********************************************************
*  Name: Siddharth Pai, Nik Valcic, Ben Stafl            *
*  Course: ICS 4U1 - 01  Pd. 4                           *
*  Final Assignment (Summative): Flappy World            *
*  Class Purpose: To make a class that deals with the    *
*  power ups in the game                                 *
*  Due Date: May 27, 2014                                *
*********************************************************/
import javax.swing.*; 
import java.awt.event.*;
import java.awt.*;

class PowerUp
{
   int state; 
   ImageIcon powerUp;
   int x, y, finalY, c;
   static int width = 52;
   static int height = 52;
   boolean moveUp = false;
   boolean visible = true;
   
   /*** PowerUp ************************************
   * Purpose: (Constructor) initializes instances  *
   * Parameters: state, x coordinate, y coordinate *
   * Returns: none                                 *
   ************************************************/
   public PowerUp (int s, int xCord, int yCord)
   {
      state = s;
      x = xCord;
      finalY = yCord;
      y= yCord;
      c = 10;
      
      if (state == 1)
         powerUp = new ImageIcon("Images/PowerUps/1.png");
         
      else if (state == 2)
         powerUp = new ImageIcon("Images/PowerUps/2.png");
         
      else if (state == 3)
         powerUp = new ImageIcon("Images/PowerUps/3.png");
         
      else if (state == 4)
         powerUp = new ImageIcon("Images/PowerUps/4.png"); 
      
      else if (state == 5)
         powerUp = new ImageIcon("Images/PowerUps/5.png");
         
      else if (state == 6)
         powerUp = new ImageIcon("Images/PowerUps/6.png");
   } 
   
   /*** displayPowerUp ***************
   * Purpose: displays the power up  *
   * Parameters: Graphics object     *
   * Returns: none                   *
   **********************************/
   public void displayPowerUp (Graphics g)
   {
      if (visible)
         g.drawImage (powerUp.getImage(), x, y, null);
   }
   
   /*** updateCord *************************************
   * Purpose: updates the coordinates of the power up  *
   * Parameters: none                                  *
   * Returns: none                                     *
   ****************************************************/
   public void updateCord()
   {
      if (y <= finalY-c)
         moveUp = false;
      else if (y >= finalY + c)
         moveUp = true;
      
      if (moveUp)
         y--;
      else 
         y++;
   }
   
   /*** move *******************************************
   * Purpose: moves the power up (with the pipes)      *
   * Parameters: none                                  *
   * Returns: none                                     *
   ****************************************************/
   public void move()
   {
      x-=3;
   }
   
   /*** setVisible ******************************************
   * Purpose: allows to toggle between displaying the       * 
   * power up or not                                        *
   * Parameters: boolean - true will display and vice versa *
   * Returns: none                                          *
   *********************************************************/
   public void setVisible (boolean b)
   {
      visible = b;
   }
}