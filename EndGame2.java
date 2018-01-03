/*********************************************************
*  Name: Siddharth Pai, Nik Valcic, Ben Stafl            *
*  Course: ICS 4U1 - 01  Pd. 4                           *
*  Final Assignment (Summative): Flappy World            *
*  Class Purpose: To make a class that deals with the    *
*  end game (when the bird dies)                         *
*  Due Date: May 27, 2014                                *
*********************************************************/
import java.awt.*; 
import java.awt.event.*; 
import javax.swing.*; 

class EndGame2
{
   GameOverScreen gOver;
   InGame2P twoPlayer;
   boolean restartIt = false;
   boolean quitIt = false;
   ImageIcon redScreen = new ImageIcon ("Images/RedScreen/redScreen.png");
   int redCounter = 0;
   int whoDied;
   boolean threadStart = false;
   
   /*** EndGame2 ******************************************
   * Purpose: (Constructor) to initialize the instances   *
   * of the class                                         *
   * Parameters: integer (1 - P1, 2 - P2), InGame2P object*
   * Returns: none                                        *
   *******************************************************/
   public EndGame2 (int n,InGame2P t)
   {
      whoDied = n;
      gOver = new GameOverScreen (n);
      twoPlayer = t;
   }
   
   
   /*** displayEndGame2 **********************
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
         if (whoDied == 1)
         {
            if (twoPlayer.character2.y<(587-twoPlayer.character2.height))
               twoPlayer.character2.y+=9;
            if (twoPlayer.character2.angle <90)
               twoPlayer.character2.angle+=4;
         }
         else
         {
            if (twoPlayer.character1.y<(587-twoPlayer.character1.height))
               twoPlayer.character1.y+=9;
            if (twoPlayer.character1.angle <90)
               twoPlayer.character1.angle+=4;
         }
         twoPlayer.displayInGame2P(g);
         gOver.displayGameOverScreen (g);
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
      gOver.updateScreen(e);
   }
   
   /*** udateEndGame2 ************************************
   * Purpose: updates the end game                       *
   * Parameters: MouseEvent                              *
   * Returns: none                                       *
   ******************************************************/
   public void updateEndGame2(MouseEvent e)
   {
      gOver.updateScreen2(e);
      this.restartIt = gOver.restartIt;
      this.quitIt = gOver.quitIt;
   }
   
   /*** threadStart **************************************
   * Purpose: allows the threadStuff method to be called *
   * Parameters: none                                    *
   * Returns: none                                       *
   ******************************************************/
   public void threadStart()
   {
      threadStart = true;
   }
   
   /*** threadStuff *********************************************
   * Purpose: checks certain conditions and reassigns variables *
   * Parameters: none                                           *
   * Returns: none                                              *
   *************************************************************/
   public void threadStuff()
   {
      if (threadStart)
      {
         if (gOver.y > gOver.finalY)   
            gOver.updateCord();
      }
   }
}