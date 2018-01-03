/*********************************************************
*  Name: Siddharth Pai, Nik Valcic, Ben Stafl            *
*  Course: ICS 4U1 - 01  Pd. 4                           *
*  Final Assignment (Summative): Flappy World            *
*  Class Purpose: To make a class that deals with the    *
*  life display box                                      *
*  Due Date: May 27, 2014                                *
*********************************************************/
import java.awt.*; 
import java.awt.event.*; 
import javax.swing.*; 

class LifeDisplay 
{
   ImageIcon box = new ImageIcon ("Images/DisplayBox/box.png");
   ImageIcon [] lives = new ImageIcon [3];
   ImageIcon life1, life2, life3;
   int characterType, boxX, boxY, yCord;
   int [] xCords = new int [3];
   boolean states [] = new boolean [3];
   float [] transparency = new float [3];
   boolean [] incShow = new boolean [3];
   int counter = 0;
   int sprites [] = new int [3];
   
   /*** LifeDisplay *********************************************
   * Purpose: (Constructor) initializes the instances           *
   * Parameters: x - coordinate, y - coordinate, character type *
   * Returns: none                                              *
   *************************************************************/
   public LifeDisplay(int x, int y, int c)
   {
      boxX = x; 
      boxY = y;
      characterType = c;
      yCord = y + 9; 
      
      xCords[0] = x + 6; 
      xCords[1] = xCords[0] + 36; 
      xCords[2] = xCords[1] + 38; 
      
      if (c == 1)
      {
         life1 = new ImageIcon ("Images/Characters/1/1.png");
         life2 = new ImageIcon ("Images/Characters/1/2.png");
         life3 = new ImageIcon ("Images/Characters/1/3.png");
      }
      else if (c == 2)
      {
         life1 = new ImageIcon ("Images/Characters/2/1.png");
         life2 = new ImageIcon ("Images/Characters/2/2.png");
         life3 = new ImageIcon ("Images/Characters/2/3.png");
      }
      else if (c == 3)
      {
         life1 = new ImageIcon ("Images/Characters/3/1.png");
         life2 = new ImageIcon ("Images/Characters/3/2.png");
         life3 = new ImageIcon ("Images/Characters/3/3.png");
      }
      else if (c == 4)
      {
         life1 = new ImageIcon ("Images/Characters/4/1.png");
         life2 = new ImageIcon ("Images/Characters/4/2.png");
         life3 = new ImageIcon ("Images/Characters/4/3.png");
      }
         
      states [0] = true;
      states [1] = true;
      states [2] = true;
      
      sprites[0] = 1;
      sprites[1] = 1;
      sprites[2] = 1;
      
      lives [0] = life1;
      lives [1] = life1;
      lives [2] = life1;
      
      incShow[0] = true;
      incShow[1] = true;
      incShow[2] = true;
      
      transparency[0] = 1.0f;
      transparency[1] = 1.0f;
      transparency[2] = 1.0f;
   }
   
   /*** displayLifeDisplay *******************************
   * Purpose: displays the life display box              *
   * Parameters: Graphics object                         *
   * Returns: none                                       *
   ******************************************************/
   public void displayLifeDisplay (Graphics g)
   {
      Graphics2D g2 = (Graphics2D)g;
      g.drawImage (box.getImage(), boxX, boxY, null);
      
      for (int i = 0; i < states.length; i++)
      {
         g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,transparency[i]));
         g.drawImage(lives[i].getImage(), xCords[i], yCord,32, 30, null);
         g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));  
      }
   }
   
   /*** addLife **********************
   * Purpose: adds a life to the box *
   * Parameters: none                *
   * Returns: none                   *
   **********************************/
   public void addLife()
   {
      if (!isFull(states))
      {
         boolean done = false; 
         
         for (int i = 0; i < states.length && !done; i++)
            if (!states[i])
            {
               states[i] = true;
               incShow[i] = true;
               done = true;
            }
      }
   }
   
   /*** takeLife *****************************
   * Purpose: takes away a life form the box *
   * Parameters: none                        *
   * Returns: none                           *
   ******************************************/
   public void takeLife()
   {
      if (!isEmpty(states))
      {
         int i = states.length -1;
         
         while (!states[i])
         {
            if (i>0)
               i--;
         }
         
         states[i] = false;
         incShow[i] = false;
      }
   }
   
   /*** lifeNum *************************************
   * Purpose: determines the urrent number of life  *
   * Parameters: none                               *
   * Returns: number of lives left                  *
   *************************************************/
   public int lifeNum ()
   {
      int result = 0; 
      
      for (int i = 0; i < states.length; i++)
         if (states[i])
            result++;
            
      return result;
   }
   
   /*** isFull **************************************
   * Purpose: determines if the life box is full    *
   * Parameters: states array                       *
   * Returns: boolean - true if the box is full     *
   *************************************************/
   public static boolean isFull (boolean [] s)
   {
      boolean full = true;
      
      for (int i = 0; i < s.length && full; i++)
         if (!s[i])
            full = false;
            
      return full; 
   }
   
   /*** isEmpty *************************************
   * Purpose: determines if the life box is empty   *
   * Parameters: states array                       *
   * Returns: boolean - true if the box is empty    *
   *************************************************/
   public static boolean isEmpty (boolean [] s)
   {
      boolean empty = true;
      
      for (int i = 0; i < s.length && empty; i++)
         if (s[i])
            empty = false;
       
      return empty;
   }

   /*** threadStuff *********************************
   * Purpose: constantly checks various conditions  *
   * and reassigns variables                        *
   * Parameters: none                               *
   * Returns: none                                  *
   *************************************************/
   public void threadStuff()
   {
      counter++;
         
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
         
      for (int i = 0; i < states.length; i++)
      {
         if (states[i])
            incShow[i] = true;
         else 
            incShow[i] = false;
      }
         
      if (counter % 5 == 0)
      {
         for (int i = 0; i < sprites.length; i++)
         {
            if (states[i])
            {
               if (sprites[i] == 4)
                  sprites[i] = 0; 
               sprites[i]++;
            }
               
            if (sprites[i] == 1)
               lives[i] = life1;
            else if (sprites[i] == 2)
               lives[i] = life2;
            else if (sprites[i] == 3)
               lives[i] = life3;
            else if (sprites[i] == 4)
               lives[i] = life2; 
         }
      }
   
   }
}