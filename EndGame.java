/*********************************************************
*  Name: Siddharth Pai, Nik Valcic, Ben Stafl            *
*  Course: ICS 4U1 - 01  Pd. 4                           *
*  Final Assignment (Summative): Flappy World            *
*  Class Purpose: To make a class that deals with the    *
*  end game (when the bird dies)                         *
*  Due Date: May 27, 2014                                *
*********************************************************/
import java.awt.event.*;
import javax.swing.*; 
import java.awt.*;

class EndGame
{
   GameOverScreen gameOver = new GameOverScreen();
   HighScoreScreen hsScreen;
   InGame1P onePlayer;
   ImageIcon redScreen = new ImageIcon ("Images/RedScreen/redScreen.png");
   int redCounter = 0;
   int score;
   
   int highestScore;
   
   boolean restartIt = false;
   boolean quitIt = false;
   boolean ready = false;
   boolean showingHsScreen = false;
   boolean moveOut = false;
   boolean threadRun = false;
   
   /*** EndGame ******************************************
   * Purpose: (Constructor) to initialize the instances  *
   * of the class                                        *
   * Parameters: InGame1P object, score, highest current *
   * score                                               *
   * Returns: none                                       *
   ******************************************************/
   public EndGame (InGame1P s, int num, String num2)
   {
      onePlayer = s;
      score = num;
      hsScreen = new HighScoreScreen (score);
      highestScore = Integer.parseInt (num2);
   }
   
   /*** displayEndGame ***********************
   * Purpose: To display the end game        *
   * Parameters: Graphics object             *
   * Returns: none                           *
   ******************************************/
   public void displayEndGame(Graphics g)
   {
      if (redCounter >= 1 && redCounter <= 5)
         g.drawImage(redScreen.getImage(), 0, 0, null);
      else
      {
         onePlayer.displayInGame1P(g);
         if (onePlayer.character.y<(587-onePlayer.character.height))
            onePlayer.character.y+=9;
         if (onePlayer.character.angle <90)
            onePlayer.character.angle+=4;
            
         if (ready)
            gameOver.displayGameOverScreen(g);
         
         if (score > highestScore)
         {  
            showingHsScreen = true;
            hsScreen.displayHighScoreScreen(g);
         }
         
         else 
         {
            ready = true;
            showingHsScreen = false;
         }
         
      }    
      redCounter++;
   }
   
   /*** updateEndGame ************************************
   * Purpose: Updates the end game                       *
   * Parameters: MouseEvent                              *
   * Returns: none                                       *
   ******************************************************/
   public void updateEndGame(MouseEvent e)
   {
      if (ready)
         gameOver.updateScreen(e);
      if (showingHsScreen)
         hsScreen.updateScreen(e);
   }
   
   /*** udateEndGame2 ************************************
   * Purpose: updates the end game                       *
   * Parameters: MouseEvent                              *
   * Returns: none                                       *
   ******************************************************/
   public void updateEndGame2(MouseEvent e)
   {
      if (ready)
      {
         gameOver.updateScreen2(e);
         this.restartIt = gameOver.restartIt;
         this.quitIt = gameOver.quitIt;
      }
      
      if (showingHsScreen)
         hsScreen.updateScreen2(e);
   }
   
   /*** updateEndGame3 ***********************************
   * Purpose: updates the end game                       *
   * Parameters: MouseEvent                              *
   * Returns: none                                       *
   ******************************************************/
   public void updateEndGame3 (KeyEvent e)
   {
      hsScreen.updateScreen3(e);
      hsScreen.updateScreen4(e);
   }
   
   /*** threadStart **************************************
   * Purpose: allows the threadStuff method to be called *
   * Parameters: none                                    *
   * Returns: none                                       *
   ******************************************************/
   public void threadStart()
   {
      threadRun = true;
   }
   
   /*** threadStuff **************************************
   * Purpose: constantly goes through certain conditions * 
   * and reassigns variables                             *
   * Parameters: none                                    *
   * Returns: none                                       *
   ******************************************************/
   public void threadStuff()
   {  
      if (threadRun)
      {
         if (ready)
            if (gameOver.y > gameOver.finalY)   
               gameOver.updateCord();
         
         if (showingHsScreen && !ready)
         {
            if (hsScreen.y > hsScreen.finalY)
               hsScreen.updateCord();
            if (hsScreen.submitted)
            {
               showingHsScreen = false;
               ready = true;
               moveOut = true;
            }
         }
         
         if (moveOut)
            if (hsScreen.x > hsScreen.finalX)
               hsScreen.updateCord2(); 
      }
   }
}