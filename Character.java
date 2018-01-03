/*********************************************************
*  Name: Siddharth Pai, Nik Valcic, Ben Stafl            *
*  Course: ICS 4U1 - 01  Pd. 4                           *
*  Final Assignment (Summative): Flappy World            *
*  Class Purpose: To make a class that deals with the    *
*  characters (different birds) throughout the game      *
*  Due Date: May 27, 2014                                *
*********************************************************/

import java.awt.*; 
import javax.swing.*; 
import java.awt.event.*;

class Character 
{
   int state, x, y, time, initV, t, a, sprite, width, height, counter;
   int angle;
   double displacement;
   ImageIcon character; 
   boolean paused = false;
   boolean invincible = false;
   boolean bullets = false;
   boolean smaller = false;
   boolean hit = false;
   boolean startFlashing = false;
   boolean flashing = false;
   
   boolean addPCounter = false;
   boolean aGoUp = false;
   
   int armours = 0;
   int flashCounter = 0;
   
   SoundClip fly = new SoundClip ("Sounds/wing.wav");
   
   int seconds1 = 0; 
   int seconds2 = 0;
   int seconds3 = 0;
   
   int keyTrack = 0;
   int keyTrack2 = 0;
   
   int er = 0;
   
   boolean grow = true;
   boolean incShow = true;
   boolean gotArmour = false;
   
   boolean gameOver = false;
   
   float transparency = 1.0f;
   
   int pCounter = 0;
   
   Armour a1;
   
   /*** Character ****************************************
   * Purpose: (Constructor) To initialize the instances  * 
   * of the class                                        *
   * Parameters: state (which kind of bird)              *
   * Returns: none                                       *
   ******************************************************/
   public Character (int s)
   {
      state = s;
      x = 300; 
      y = 285; 
      initV = 0;
      displacement = 0;
      t = 0;
      a = 2;
      sprite = 1;
      counter = 0;
      angle = 0;
      
      a1 = new Armour (x, y); 
      
      if (state == 1)
      {
         character = new ImageIcon ("Images/Characters/1/1.png");
         width = 65; 
         height = 50;  
      }
      
      else if (state == 2)
      {
         character = new ImageIcon ("Images/Characters/2/1.png");
         width = 65; 
         height = 50;  
      }
      
      else if (state == 3)
      {
         character = new ImageIcon ("Images/Characters/3/1.png");
         width = 65; 
         height = 50;  
      }
      
      else if (state == 4)
      {
         character = new ImageIcon ("Images/Characters/4/1.png");
         width = 65; 
         height = 50;  
      }
   }
   
   /*** displayCharacter *********************************
   * Purpose: To display the character                   *
   * Parameters: Graphics object, boolean (move or not)  *
   * Returns: none                                       *
   ******************************************************/
   public void displayCharacter (Graphics g1, boolean move)
   {
      Graphics2D g2 = (Graphics2D) g1;
      
      if (state == 1)
      {
         if (sprite == 1)
            character = new ImageIcon ("Images/Characters/1/1.png");
         else if (sprite == 2)
            character = new ImageIcon ("Images/Characters/1/2.png");
         else if (sprite == 3)
            character = new ImageIcon ("Images/Characters/1/3.png");
         else if (sprite == 4)
            character = new ImageIcon ("Images/Characters/1/2.png");
      }
      else if (state == 2)
      {
         if (sprite == 1)
            character = new ImageIcon ("Images/Characters/2/1.png");
         else if (sprite == 2)
            character = new ImageIcon ("Images/Characters/2/2.png");
         else if (sprite == 3)
            character = new ImageIcon ("Images/Characters/2/3.png");
         else if (sprite == 4)
            character = new ImageIcon ("Images/Characters/2/2.png");
      }
      else if (state == 3)
      {
         if (sprite == 1)
            character = new ImageIcon ("Images/Characters/3/1.png");
         else if (sprite == 2)
            character = new ImageIcon ("Images/Characters/3/2.png");
         else if (sprite == 3)
            character = new ImageIcon ("Images/Characters/3/3.png");
         else if (sprite == 4)
            character = new ImageIcon ("Images/Characters/3/2.png");
      }
      else if (state == 4)
      {
         if (sprite == 1)
            character = new ImageIcon ("Images/Characters/4/1.png");
         else if (sprite == 2)
            character = new ImageIcon ("Images/Characters/4/2.png");
         else if (sprite == 3)
            character = new ImageIcon ("Images/Characters/4/3.png");
         else if (sprite == 4)
            character = new ImageIcon ("Images/Characters/4/2.png");
      }
   
      
      if (gotArmour && !aGoUp )
         a1.updateCord(x,y);
         
      if (aGoUp && !gameOver)
         if (a1.y > -350)
            a1.y-=8;
            
      if (aGoUp&& !gameOver)  
         if (a1.y < -345)
         {
            aGoUp = false;
            gotArmour = false;
         }
         
      if (gotArmour)
         a1.displayArmour(g1);
         
      if (invincible)
         g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,transparency));
         
      g2.rotate (Math.toRadians(angle), x+width/2, y+height/2);  
          
      g1.drawImage (character.getImage(), x, y,width, height, null);
      
      g2.rotate (Math.toRadians(360-angle), x+width/2, y+height/2);
      
      g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));  
   
      if (move)
      {
         displacement = (initV*t + 0.5*a*t*t) - (initV*(t-1) + 0.5*a*(t-1)*(t-1));
         y = (int) (y + displacement);
      }
   }
   
   /*** isAlive ******************************************
   * Purpose: To determine whether the bird is alive     *
   * Parameters: Pipes (to check the pipes's coordinates)*
   * Returns: boolean - alive or not                     *
   ******************************************************/
   public boolean isAlive(Pipe [] p)
   {
      
      boolean cAlive = true;
      
      for (int i = 0; i < p.length && cAlive; i++)
      {
         if (((x + width) >= p[i].x && x <= (p[i].x + p[i].width)) && (((y + height) >=-10000 && y <= p[i].topPipeHeight + 49) || (y + height > (p[i].topPipeHeight + p[i].pipeGap - 23)))) 
         {
            cAlive = false;
         }
      }
      
      if (y + height >= 588)
         cAlive = false;
         
      return cAlive;
   }
   
   /*** isAlive ******************************************
   * Purpose: To determine whether the bird is alive (2P)*
   * Parameters: Pipes, integer                          *
   * Returns: boolean - alive or not                     *
   ******************************************************/
   public boolean isAlive(Pipe [] p, int n)
   {
      
      boolean cAlive = true;
      
      for (int i = 0; i < p.length && cAlive; i++)
      {
         if (((x + width) >= p[i].x && x <= (p[i].x + p[i].width)) && (((y + height) >=-10000 && y <= p[i].topPipeHeight + 49) || (y + height > (p[i].topPipeHeight + p[i].pipeGap - 23)))) 
         {
            cAlive = false;
         }
      }
      
      if (y + height >= 538)
         cAlive = false;
         
      return cAlive;
   }

   /*** updateSprite ****************************
   * Purpose: To make the bird flap its wings   *
   * Parameters: none                           *
   * Returns: none                              *
   *********************************************/
   public void updateSprite ()
   {
      if (displacement <= 5)
      {
         if (sprite == 4)
            sprite = 0;
         sprite ++;
      }
   }
   
   /*** updateCharacter **********************************
   * Purpose: To make the bird "jump" when the correct   *
   * key is pressed                                      *
   * Parameters: KeyEvent, boolean - sounds on or not    *
   * Returns: none                                       *
   ******************************************************/
   public void updateCharacter (KeyEvent e, boolean b)
   {
      if (e.getKeyCode() == 38)
      {  
         keyTrack++;
         if (keyTrack == 1)
         {
            t= 0;
            initV = -4; 
            if (b)
            {
               fly.clip.setFramePosition (0);
               fly.start();
            }
         }  
      } 
   }
   
   /*** jump *********************************************
   * Purpose: To make the bird jump (this is when the    *
   * bird is invincible)                                 *
   * Parameters: boolean - sounds on or not              *
   * Returns: none                                       *
   ******************************************************/
   public void jump(boolean b)
   {
      t= 0;
      initV = -4; 
      if (b)
      {
         fly.clip.setFramePosition (0);
         fly.start();
      }
   }
   
   /*** jump2 ********************************************
   * Purpose: To make the bird jump (this is when the    *
   * bird is invincible) with a different sound          *
   * Parameters: boolean - sounds on or not              *
   * Returns: none                                       *
   ******************************************************/
   public void jump2(boolean b, SoundClip scp)
   {
      t= 0;
      initV = -4; 
      if (b)
      {
         scp.clip.setFramePosition (0);
         scp.start();
      }
   }

   
   /*** updateCharacter **********************************
   * Purpose: To make the bird jump and to activate a    *
   * power up when the correct key is pressed            *
   * Parameters: KeyEvent, PowerUpsDisplay box, integer  *
   * (1 - P1, 2 - P2), booleans - sounds on or not       *
   * Returns: none                                       *
   ******************************************************/
   public void updateCharacter (KeyEvent e, PowerUpsDisplay pDisplay, int n, boolean b)
   {
      if (n == 1)
      {
         if (e.getKeyCode() == 38)
         {
            keyTrack++;
            if (keyTrack == 1)
            {
               t= 0;
               initV = -4; 
               if (b)
               {
                  fly.clip.setFramePosition (0);
                  fly.start();
               }
            
            }  
         }
         
         if (e.getKeyCode() == 40 && !gameOver)
         {
            int check = pDisplay.takeAway();
         
            if (check == 1)
            {
               if (armours > 0)
               {
                  a1 = new Armour (x, y);
                  gotArmour = true;
               }
            } 
               
            else if (check == 2)
            {
               invincible = true;
               incShow = false;
               seconds1 = 7;
            }
            else if (check == 3)
            {  
               grow = false;
               seconds2 = 7;
            }
         } 
      }
      else 
      {
         if (e.getKeyCode() == 87)
         {
            keyTrack2++;
            if (keyTrack2 ==1)
            {
               t= 0;
               initV = -4;
               if (b)
               {
                  fly.clip.setFramePosition (0);
                  fly.start();
               }
            
            }  
         } 
         
         if (e.getKeyCode() == 83 && !gameOver)
         {
            int check = pDisplay.takeAway();
         
            if (check == 1)
            {
               if (armours > 0)
               {
                  a1 = new Armour (x, y);
                  gotArmour = true;
               }
            }
            else if (check == 2)
            {
               invincible = true;
               incShow = false;
               seconds1 = 7;
            }
            else if (check == 3)
            {  
               grow = false;
               seconds2 = 7;
            }
         }
      }
      
      if (gotArmour && !gameOver)                 
         a1.updateArmour (e, n);
         
      if (a1.bulletsLeft <=0)
      { 
         aGoUp = true;
      }
   }
   
   /*** updateCharacter **********************************
   * Purpose: To make the bird (in 1 Player) "jump" when * 
   * the mouse is clicked                                *
   * Parameters: MouseEvent, boolean - sounds on or not  *
   * Returns: none                                       *
   ******************************************************/
   public void updateCharacter (MouseEvent e, boolean b)
   {
      t= 0;
      initV = -4;
      if (b)
      {
         fly.clip.setFramePosition (0);
         fly.start();
      }
   }
   
   /*** gainedPower **************************************
   * Purpose: to check if the bird gained a powerUp      *
   * Parameters: PowerUp, PowerUpsDisplay box            *
   * Returns: none                                       *
   ******************************************************/
   public void gainedPower (PowerUp p, PowerUpsDisplay pd)
   {
      if ((p.x-15 <= x+width && p.x+p.width+15 >= x)&&(p.y-15 <= y+height && p.y+p.height+15>= y))
      {
         pCounter++;
         if (pCounter == 1)
         {
            if (p.state == 1)
            {
               pd.updatePowerUpsDisplay(1);
               armours ++;
               if (armours >3)
                  armours = 3;
            }
            else if (p.state == 2)
            {
               pd.updatePowerUpsDisplay(2);
            }
            else if (p.state == 3)
            {
               pd.updatePowerUpsDisplay(3);
            }
            p.x = -100;
            p.setVisible (false);
         } 
      }
      else 
         pCounter = -15; 
   }
   
   /*** checkHit *****************************************
   * Purpose: To check if the bird got hit by a bullet   *
   * Parameters: Armour object (of the opponent)         *
   * Returns: boolean - hit or not                       *
   ******************************************************/
   public boolean checkHit (Armour a)
   {
      boolean result = false;
   
      
      if (a.bulletsLeft == 2)
      {
         if ((a.bullets[2].x + a.bullets[2].width >= x-er && a.bullets[2].x <= x+width+er) && (a.bullets[2].y <= y+height+er && a.bullets[2].y+a.bullets[2].height >= y-er))
            result = true;
         else;
      }
      else if (a.bulletsLeft == 1)
      {
         if ((a.bullets[2].x + a.bullets[2].width >= x-er && a.bullets[2].x <= x+width+er) && (a.bullets[2].y <= y+height+er && a.bullets[2].y+a.bullets[2].height >= y-er))
            result = true;
         else;
         if ((a.bullets[1].x + a.bullets[1].width >= x-er && a.bullets[1].x <= x+width+er) && (a.bullets[1].y <= y+height+er && a.bullets[1].y+a.bullets[1].height >= y-er))
            result = true;
         else;
      }
      else if (a.bulletsLeft == 0)
      {
         if ((a.bullets[2].x + a.bullets[2].width >= x-er && a.bullets[2].x <= x+width+er) && (a.bullets[2].y <= y+height+er && a.bullets[2].y+a.bullets[2].height >= y-er))
            result = true;
         else;
         if ((a.bullets[1].x + a.bullets[1].width >= x-er && a.bullets[1].x <= x+width+er) && (a.bullets[1].y <= y+height+er && a.bullets[1].y+a.bullets[1].height >= y-er))
            result = true;
         else;
         if ((a.bullets[0].x + a.bullets[0].width >= x-er && a.bullets[0].x <= x+width+er) && (a.bullets[0].y <= y+height+er && a.bullets[0].y+a.bullets[0].height >= y-er))
            result = true;
         else;
      }
      
      return result;
   }
   
   /*** updateStuff ************************************************
   * Purpose: To update the character (grow or become invincible)  *
   * Parameters: none                                              *
   * Returns: none                                                 *
   ****************************************************************/
   public void updateStuff()
   {  
      if (grow)
      {  
         if (width <65)
            width++;
         if (height <50)
            height++;
      }
      else
      {
         if (width >50)
            width--;
         if (height >35)
            height--;
      }
       
      if (!startFlashing)
      {  
         if (incShow)
         {
            if (transparency < 0.9f)
               transparency+= 0.01f;
            else 
               transparency = 1.0f;
         }
         
         else 
         {
            if (transparency > 0.5f)
               transparency-=0.01f;
         }
      }
      else
      {  
         flashCounter++;
         
         if (flashCounter % 6 == 0)
            flashing = !flashing;
            
         if (flashing)
            transparency = 0.8f;
         else
            transparency = 0.5f;
      }
   }
   
   /*** threadStuff *********************************************
   * Purpose: checks certain conditions and reassigns variables *
   * Parameters: none                                           *
   * Returns: none                                              *
   *************************************************************/
   public void threadStuff()
   {
      seconds1--;
      seconds2--;
            
      if (seconds1 == 2)
         startFlashing = true;
         
      if (seconds1 <= 0)
      {
         invincible = false;
         startFlashing = false;
         flashCounter = 0;
         seconds1 = -1;
      }
         
      if (seconds2 <= 0)
      {
         smaller = false;
         grow = true;
         seconds2 = -1;
      }    
   }
}