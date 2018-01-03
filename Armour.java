/*********************************************************
*  Name: Siddharth Pai, Nik Valcic, Ben Stafl            *
*  Course: ICS 4U1 - 01  Pd. 4                           *
*  Final Assignment (Summative): Flappy World            *
*  Class Purpose: To make a class that deals with the    *
*  armour in the 2 player mode of the game               *
*  Due Date: May 27, 2014                                *
*********************************************************/

import java.awt.*; 
import java.awt.event.*; 
import javax.swing.*; 

class Armour
{
   ImageIcon gun = new ImageIcon ("Images/Armour/gun.png");
   Bullet [] bullets = new Bullet [3];
   
   int x, y, finalY, bulletsLeft;
   
   boolean done = false;
   
   /*** Armour *******************************************
   * Purpose: (Constructor) to initialize certain values * 
   * for the instances of this class                     *
   * Parameters: x and y coordinates of the armour (gun) *
   * Returns: none                                       *
   ******************************************************/
   public Armour(int x, int y)
   {
      this.x = x;
      this.y = -40;
      finalY = y -60;
      
      for (int i = 0; i < bullets.length; i++)
         bullets [i] = new Bullet (x + 30,y + 5);
      
      bulletsLeft = 3;
   }
   
   /*** displayArmour ************************************
   * Purpose: To display the armour (guns and bullets)   *
   * Parameters: Graphics object                         *
   * Returns: none                                       *
   ******************************************************/
   public void displayArmour(Graphics g)
   {
      for (int i = 0; i < bullets.length; i++)
         bullets[i].displayBullet (g);
         
      g.drawImage (gun.getImage(), x, y, null);
   } 
   
   /*** updateCord ***************************************
   * Purpose: To update the coordinates of the gun and   * 
   * bullet (dropping down effect in game)               *
   * Parameters: x and y coordinates                     *
   * Returns: none                                       *
   ******************************************************/
   public void updateCord(int n, int n2)
   {
      if (y < n2-60 && !done)
      {
         y+=6;
      }
      
      else
      {
         done = true;
         y = n2 - 60;
      }
         
      x = n;
      
      for (int i = 0 ; i < bulletsLeft; i++)
      {
         bullets[i].x = x + 30;
         bullets[i].y = y + 5;
      }
   }
   
   /*** updateArmour *************************************
   * Purpose: To shoot the bullets when the player       *   
   * presses the correct key                             *
   * Parameters: KeyEvent , int variable (1 - P1, 2 - P2)*
   * Returns: none                                       *
   ******************************************************/
   public void updateArmour(KeyEvent e,  int n)
   {
      if (n == 1)
      {
         if (e.getKeyCode() == 39 || e.getKeyCode() == 37)
         {
            if (bulletsLeft >0)
            {
               bullets[bulletsLeft-1].shoot(e, 1);
               bulletsLeft--;
            }
         }
      }
     
      if (n == 2)
      {
         if (e.getKeyCode() == 68 || e.getKeyCode() == 65)
         {
            if (bulletsLeft >0)
            {
               bullets[bulletsLeft-1].shoot(e, 2);
               bulletsLeft--;
            }
         }
      }
      
   }
   
   /*** threadStuff **************************************
   * Purpose: To constantly go through and check certain * 
   * conditions and reassign values                      *   
   * Parameters: none                                    *
   * Returns: none                                       *
   ******************************************************/
   public void threadStuff()
   {
      if (bulletsLeft == 2)
         bullets[2].shoot();
      else if (bulletsLeft == 1)
      {
         bullets[2].shoot();
         bullets[1].shoot();
      }
      else
      {  
         bullets[2].shoot();
         bullets[1].shoot();
         bullets[0].shoot();
      }
   
   }

}

/**********************************
* Class Purpose: To deal with the *
* bullets individually as objects *
**********************************/
 
class Bullet 
{
   ImageIcon bullet = new ImageIcon ("Images/Armour/fireball.png");
   int x , y, v, width, height;
   boolean forward;
   
   /*** Bullet *******************************************
   * Purpose: (Constructor) to initialize certain values * 
   * for the instances of this class                     *
   * Parameters: x and y coordinates of the bullet       *
   * Returns: none                                       *
   ******************************************************/
   public Bullet (int x, int y)
   {
      v = 10;
      this.x = x;
      this.y = y;
      width = bullet.getIconWidth();
      height = bullet.getIconHeight();
   }
   
   /*** displayBullet ************************************
   * Purpose: To display the bullets                     *
   * Parameters: Graphics object                         *
   * Returns: none                                       *
   ******************************************************/
   public void displayBullet (Graphics g)
   {
      g.drawImage(bullet.getImage(), x, y, null);
   }
   
   /*** shoot ********************************************
   * Purpose: to shoot the bullet out of the gun         *
   * Parameters: KeyEvent, integer (1 - P1, 2- P2)       *
   * Returns: none                                       *
   ******************************************************/
   public void shoot(KeyEvent e, int n)
   {
      if (n == 1)
      {
         if (e.getKeyCode() == 39)
         {
            forward = true;
            if (x < 920)
               x+=v;
         }
         else if (e.getKeyCode() == 37)
         {
            forward = false;
            if (x > -20)
               x-=v;
         }
      }
       
      if (n == 2)
      {
         if (e.getKeyCode() == 68)
         {
            forward = true;
            if (x < 920)
               x+=v;
         }
         else if (e.getKeyCode() == 65)
         {
            forward = false;
            if (x > -20)
               x-=v;
         }
      }
   }
   
   /*** shoot ******************************************
   * Purpose: To shoot the bullet (keep it going in a  *
   * certain direction                                 *
   * Parameters: none                                  *
   * Returns: none                                     *
   ****************************************************/
   public void shoot()
   { 
      if (forward)
         if (x < 920)
            x+=v;
            
      if (!forward)
         if (x > -20)
            x-=v;
   }
}