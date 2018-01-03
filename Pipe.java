/*********************************************************
*  Name: Siddharth Pai, Nik Valcic, Ben Stafl            *
*  Course: ICS 4U1 - 01  Pd. 4                           *
*  Final Assignment (Summative): Flappy World            *
*  Class Purpose: To make a class that deals with the    *
*  pipes in the game                                     *
*  Due Date: May 27, 2014                                *
*********************************************************/
import javax.swing.*;
import java.awt.*; 

class Pipe
{
   int x, topPipeY, bottomPipeY, topPipeHeight;
   int bottomPipeHeight, width, state, pipeGap;
   int moveSpeed;
   int initHeight;
   
   ImageIcon pipeTube;
   ImageIcon topPipeEnd;
   ImageIcon bottomPipeEnd;
   
   boolean moveUp;
   
   /*** Pipes ********************************************
   * Purpose: (Constructor) initializes instances        *
   * Parameters: x coordinate, state                     *
   * Returns: none                                       *
   ******************************************************/
   public Pipe(int xCord, int s)
   {  
      if (s ==1)
      {
         x = xCord;
         topPipeY = 0;
         topPipeHeight = (int)(Math.random()*120 + 65); 
         pipeGap = 250; 
         bottomPipeY = topPipeHeight + pipeGap;
         bottomPipeHeight = 587 - (topPipeHeight + pipeGap);
         width = 90; 
         state = s;
         moveSpeed = 3;
         
         pipeTube = new ImageIcon ("Images/Pipes/1/tube.png");
         topPipeEnd = new ImageIcon ("Images/Pipes/1/topEnd.png");
         bottomPipeEnd = new ImageIcon ("Images/Pipes/1/bottomEnd.png");
      }
      
      else if (s ==2)
      {
         x = xCord;
         topPipeY = 0;
         topPipeHeight = (int)(Math.random()*120 + 65); 
         pipeGap = 210; 
         bottomPipeY = topPipeHeight + pipeGap;
         bottomPipeHeight = 587 - (topPipeHeight + pipeGap);
         width = 90;
         state = s;
         moveSpeed = 3;
         
         pipeTube = new ImageIcon ("Images/Pipes/2/tube.png");
         topPipeEnd = new ImageIcon ("Images/Pipes/2/topEnd.png");
         bottomPipeEnd = new ImageIcon ("Images/Pipes/2/bottomEnd.png");
      }
      
      else if (s ==3)
      {
         moveUp = false;
         x = xCord;
         topPipeY = 0;
         initHeight = topPipeHeight = (int)(Math.random()*120 + 65); 
         pipeGap = 210; 
         bottomPipeY = topPipeHeight + pipeGap;
         bottomPipeHeight = 587 - (topPipeHeight + pipeGap);
         width = 90; 
         state = s;
         moveSpeed = 3;
         
         pipeTube = new ImageIcon ("Images/Pipes/3/tube.png");
         topPipeEnd = new ImageIcon ("Images/Pipes/3/topEnd.png");
         bottomPipeEnd = new ImageIcon ("Images/Pipes/3/bottomEnd.png");
      }
      
   }
   
   /*** displayPipe ***************
   * Purpose: displays the pipe   *
   * Parameters: Graphics object  *
   * Returns: none                *
   *******************************/
   public void displayPipe(Graphics g1)
   {
      g1.drawImage (pipeTube.getImage(), x, topPipeY,width, topPipeHeight, null);
      g1.drawImage (topPipeEnd.getImage(), x-3 ,topPipeHeight, null); 
      g1.drawImage (pipeTube.getImage(), x, bottomPipeY, width, bottomPipeHeight, null);
      g1.drawImage (bottomPipeEnd.getImage (), x -3, bottomPipeY - 30, null);
      
      if (state == 3)
      {
         if (topPipeHeight >= initHeight + 30)
            moveUp = true;
         else if (topPipeHeight <= initHeight)
            moveUp = false;
      
         if (moveUp)
            topPipeHeight--;
         else 
            topPipeHeight++;
         
         bottomPipeHeight = 587 - (topPipeHeight + pipeGap);
         bottomPipeY = topPipeHeight + pipeGap;
      } 
   }

   /*** movePipe ******************
   * Purpose: moves the pipe      *
   * Parameters: none             *
   * Returns: none                *
   *******************************/
   public void movePipe ()
   {
      x -= moveSpeed;
   }
}