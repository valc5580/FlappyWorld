/*********************************************************
*  Name: Siddharth Pai, Nik Valcic, Ben Stafl            *
*  Course: ICS 4U1 - 01  Pd. 4                           *
*  Final Assignment (Summative): Flappy World            *
*  Class Purpose: To make a class that deals with the    *
*  Game Mode screen (1P or 2P)                           *
*  Due Date: May 27, 2014                                *
*********************************************************/
import java.awt.event.*; 
import java.awt.*; 
import javax.swing.*;

class GameMode
{
   DisplayCharacter bird1 = new DisplayCharacter (120, 230, 165, 150,25, 1);
   DisplayCharacter bird2 = new DisplayCharacter (570, 230, 85, 70,25, 2);
   DisplayCharacter bird3 = new DisplayCharacter (670, 230, 85, 70,25, 3);
   
   int state, sprite;
   boolean onePPressed = false;
   boolean twoPPressed = false;
   
   /*** GameMode *****************************************
   * Purpose: (Constructor) To initialize the instances  *
   * of the class                                        *
   * Parameters: state                                   *
   * Returns: none                                       *
   ******************************************************/
   public GameMode(int s)
   {
      bird3.y+=15;
      bird3.goUp = true;
      
      state = s;
      sprite = 0;  
   }
   
   /*** displayGameMode **********************************
   * Purpose: to display the game mode screen (1P or 2P) *
   * Parameters: Graphics object                         *
   * Returns: none                                       *
   ******************************************************/
   public void displayGameMode (Graphics g1)
   {
      if (state == 1)
      {
         if (sprite == 2)
            g1.drawImage (new ImageIcon ("Images/1P2P/1/2player2.png").getImage(), 0, 0, null);
         else if (sprite == 1)
            g1.drawImage (new ImageIcon ("Images/1P2P/1/1player2.png").getImage(), 0, 0, null);
         else 
            g1.drawImage (new ImageIcon ("Images/1P2P/1/plain2.png").getImage(), 0, 0, null);
      }
      
      else if (state == 2)
      {
         if (sprite == 2)
            g1.drawImage (new ImageIcon ("Images/1P2P/2/2player2.png").getImage(), 0, 0, null);
         else if (sprite == 1)
            g1.drawImage (new ImageIcon ("Images/1P2P/2/1player2.png").getImage(), 0, 0, null);
         else 
            g1.drawImage (new ImageIcon ("Images/1P2P/2/plain2.png").getImage(), 0, 0, null);
      }
      
      else if (state == 3)
      {
         if (sprite == 2)
            g1.drawImage (new ImageIcon ("Images/1P2P/3/2player.png").getImage(), 0, 0, null);
         else if (sprite == 1)
            g1.drawImage (new ImageIcon ("Images/1P2P/3/1player.png").getImage(), 0, 0, null);
         else 
            g1.drawImage (new ImageIcon ("Images/1P2P/3/plain.png").getImage(), 0, 0, null);
      }
      
      else if (state == 4)
      {
         if (sprite == 2)
            g1.drawImage (new ImageIcon ("Images/1P2P/4/2player.png").getImage(), 0, 0, null);
         else if (sprite == 1)
            g1.drawImage (new ImageIcon ("Images/1P2P/4/1player.png").getImage(), 0, 0, null);
         else 
            g1.drawImage (new ImageIcon ("Images/1P2P/4/plain.png").getImage(), 0, 0, null);
      }
   
      bird1.displayDisplayCharacter (g1);
      bird2.displayDisplayCharacter (g1);
      bird3.displayDisplayCharacter (g1);
   }
   
   /*** updateGameMode **************************************
   * Purpose: To update the gameMode (updates the pictures) *
   * Parameters: MouseEvent                                 *
   * Returns: none                                          *
   *********************************************************/
   public void updateGameMode (MouseEvent e)
   {  
      if ((e.getX() >= 512 && e.getX() <= 836) && (e.getY() >= 161 && e.getY() <=520))
         sprite = 2;
      else if ((e.getX() >= 62 && e.getX() <= 386) && (e.getY() >= 161 && e.getY() <=520))
         sprite = 1;
      else 
         sprite = 0;
   }
   
   /*** updateGameMode2 **********************************
   * Purpose: to update the pictures of this screen      *
   * Parameters: MouseEvent                              *
   * Returns: none                                       *
   ******************************************************/
   public void updateGameMode2 (MouseEvent e)
   {
      if ((e.getX() >= 512 && e.getX() <= 836) && (e.getY() >= 161 && e.getY() <=520))
         twoPPressed = true;
      else if ((e.getX() >= 62 && e.getX() <= 386) && (e.getY() >= 161 && e.getY() <=520))
         onePPressed = true;
   }
}