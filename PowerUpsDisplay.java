/*********************************************************
*  Name: Siddharth Pai, Nik Valcic, Ben Stafl            *
*  Course: ICS 4U1 - 01  Pd. 4                           *
*  Final Assignment (Summative): Flappy World            *
*  Class Purpose: To make a class that deals with the    *
*  power ups display box                                 *
*  Due Date: May 27, 2014                                *
*********************************************************/
import java.awt.*; 
import java.awt.event.*; 
import javax.swing.*; 

class PowerUpsDisplay
{
   ImageIcon [] powerUps = new ImageIcon [3];
   ImageIcon box = new ImageIcon ("Images/DisplayBox/box.png");
   ImageIcon bullets = new ImageIcon ("Images/DisplayBox/icon.png");
   ImageIcon invincibility = new ImageIcon ("Images/DisplayBox/icon2.png");
   ImageIcon smaller = new ImageIcon ("Images/DisplayBox/icon3.png");
   
   int boxX, boxY;
   int yCord;
   int [] xCords = new int [3];
   int states [] = new int [3];
   float [] transparency = new float [3];
   boolean [] incShow = new boolean [3];
   
   /*** PowerUpsDisplay **********************************
   * Purpose: (Constructor) initializes instances        *
   * Parameters: x coordinate, y coordinate              *
   * Returns: none                                       *
   ******************************************************/   
   public PowerUpsDisplay(int x, int y)
   {
      boxX = x; 
      boxY = y; 
      
      yCord = y + 10; 
      
      xCords[0] = x+7;  
      xCords[1] = xCords[0] + 38; 
      xCords[2] = xCords[1] + 36; 
      
      states[0] = 0;
      states[1] = 0;
      states[2] = 0;
      
      transparency[0] = 0.0f;
      transparency[1] = 0.0f;
      transparency[2] = 0.0f;
      
      incShow[0] = false;
      incShow[1] = false;
      incShow[2] = false;
      
      powerUps [0] = new ImageIcon("");
      powerUps [1] = new ImageIcon("");
      powerUps [2] = new ImageIcon("");
   
   }
   
   /*** displayPowerUpsDisplay ***************************
   * Purpose: displays the power ups display box         *
   * Parameters: Graphics object                         *
   * Returns: none                                       *
   ******************************************************/
   public void displayPowerUpsDisplay (Graphics g)
   {
      Graphics2D g2 = (Graphics2D)g;
      g.drawImage (box.getImage(), boxX, boxY, null);
      
      for (int i = 0; i < powerUps.length; i++)
      {
         g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,transparency[i]));
         g.drawImage (powerUps[i].getImage(), xCords[i], yCord, null);
         g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
      }
   }
   
   /*** updatePowerUpsDisplay *************
   * Purpose: adds a power up to the box  *
   * Parameters: power up type            *
   * Returns: none                        *
   ***************************************/
   public void updatePowerUpsDisplay(int s)
   {
      if (!isFull(states))
      {
         boolean done = false;
         for (int i = 0; i < states.length && !done; i++)
            if (states[i] == 0) 
            { 
               states[i] = s;
               done = true;
            }   
      } 
   }
   
   /*** takeAway *********************************
   * Purpose: takes away a power up from the box *
   * Parameters: none                            *
   * Returns: none                               *
   **********************************************/
   public int takeAway()
   {
      int result;
      
      if (!isEmpty(states))
      { 
         int i = states.length-1;
      
         while (states[i] == 0)
         {
            if (i>0)
               i--;
         }
         result = states[i];
         states[i] = 0;
         
         return result;
      }
      else 
         return -1; 
   }
   
   /*** isFull *******************************
   * Purpose: determines if the box is full  *
   * Parameters: states - array              *
   * Returns: true - if box is full          *
   ******************************************/
   public static boolean isFull (int [] s)
   {
      boolean full = true;
   
      if (s[0] != 0 && s[1]!= 0 && s[2] != 0)      
         return true;
      else 
         return false;
   }
   
   /*** isEmpty *******************************
   * Purpose: determines if the box is empty  *
   * Parameters: states - array               *
   * Returns: true - if box is empty          *
   ******************************************/
   public static boolean isEmpty (int [] s)
   {
      boolean empty = true;
      
      for (int i = 0; i <s.length && empty; i++)
         if (s[i] != 0)
            empty = false;
            
      return empty;
   }
   
   /*** threadStuff ****************************
   * Purpose: constantly checks conditions and *
   * reassigns variables                       *
   * Parameters: none                          *
   * Returns: none                             *
   ********************************************/
   public void threadStuff()
   {
      for (int i = 0; i < incShow.length; i++)
      {
         if (incShow[i])
         {
            if (transparency[i] < 0.9f) 
               transparency[i]+=0.01f;
            else 
               transparency[i]=1.0f;
         }
            
         else 
         {
            if (transparency[i] > 0.1f)
               transparency[i]-=0.01f;
            else
               transparency[i]=0.0f;
         } 
      }
         
      for (int i = 0; i < powerUps.length; i++)
      {
         if (states[i] == 0)
         {
            incShow[i] = false;
         }
         else if (states[i] == 1)
         {
            powerUps[i] = bullets;
            incShow[i] = true;
         }
         else if (states[i] == 2)
         {
            powerUps[i] = invincibility;
            incShow[i] = true;
         }
         else if (states[i] == 3)
         {
            powerUps[i] = smaller;
            incShow[i] = true;
         }
      }
   }
}