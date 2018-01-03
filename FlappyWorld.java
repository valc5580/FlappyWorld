/*********************************************************
*  Name: Siddharth Pai, Nik Valcic, Ben Stafl            *
*  Course: ICS 4U1 - 01  Pd. 4                           *
*  Final Assignment (Summative): Flappy World            *
*  Class Purpose: To put together all the classes and be *
*  able to play the game                                 *
*  Due Date: May 27, 2014                                *
*********************************************************/
import java.awt.event.*;
import javax.swing.*; 
import java.awt.*;

class FlappyWorld  
{
   int state = (int)(Math.random()*4)+1;
   IntroGame introGame = new IntroGame (state);
   InGame1P onePlayer = new InGame1P (state, introGame);
   EndGame endGame = new EndGame (onePlayer, 0, introGame.highScore.last);
   JFrame frame = new JFrame("Flappy World");
   Drawing draw = new Drawing ();
   MouseStuff mouseStuff = new MouseStuff();
   KeyStuff keyStuff = new KeyStuff();
   Thread1 thread1 = new Thread1();
   Thread2 thread2 = new Thread2();
   Thread3 thread3 = new Thread3();
   Thread4 thread4 = new Thread4();
   int counter = 0;
   int threadCount = 0;
   int control = 0;
   int threadCount2 = 0;
   int charModeCount = 0;
   int startingCount = 0;
   
   InGame2P twoPlayer = new InGame2P (state,introGame);
   EndGame2 endGame2 = new EndGame2 (1, twoPlayer);

   SoundClip hit = new SoundClip ("Sounds/hit.wav");
   
   /*** FlappyWorld **************************************
   * Purpose: To initialize the instances of the class   *
   * Parameters: none                                    *
   * Returns: none                                       *
   ******************************************************/
   public FlappyWorld()
   {  
      draw.addMouseListener (mouseStuff);
      draw.addMouseMotionListener (new MouseStuff2());
      frame.setSize (900,700);
      frame.setResizable (false);
      frame.add (draw);
      frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
      frame.addKeyListener(keyStuff);
      frame.setVisible (true);
      thread1.start();
      thread2.start();
      thread3.start();
      thread4.start();
   }
   
   // There are multiple threads in this game because they "sleep" for varrying times
   
   // First Thread
   class Thread1 extends Thread
   {
      /*** run **********************************************
      * Purpose: to constantly go through this method       * 
      * as a loop and perform certain commands              *
      * Parameters: none                                    *
      * Returns: none                                       *
      ******************************************************/
      public void run ()
      {
         while (true)
         {
            try
            {
               sleep (1000/60);
            }
            catch (Exception e){}
            endGame2.threadStuff();
            twoPlayer.pDisplay.threadStuff();
            twoPlayer.pDisplay2.threadStuff();
            twoPlayer.lDisplay.threadStuff();
            twoPlayer.lDisplay2.threadStuff();
            introGame.threadStuff();
            endGame.threadStuff();
            twoPlayer.character1.a1.threadStuff();
            twoPlayer.character2.a1.threadStuff();
         
            if(counter % 3 == 0)
               draw.repaint(); 
         
            if (introGame.oneP || introGame.twoP)
            {   
               if (introGame.oneP)
               {
                  charModeCount++;
                           
                  if (charModeCount == 1)
                  {
                     onePlayer.character = new Character (onePlayer.introGameVar.characterType);
                     onePlayer.dcharacter = new DisplayCharacter (onePlayer.character.x, onePlayer.character.y, onePlayer.character.width, onePlayer.character.height, 30,onePlayer.introGameVar.characterType);
                  }
                  if (onePlayer.gameOver)
                  {
                     control++;
                     threadCount++;
                     if (threadCount == 1)
                        endGame.threadStart();
                  
                     if (endGame.restartIt)
                     {
                        try
                        {
                           Thread.sleep (250);
                        }
                        catch(Exception f) {}
                        restart();
                     }
                     else if (endGame.quitIt)
                     {
                        try
                        {
                           Thread.sleep (250);
                        }
                        catch(Exception f) {}
                        quit();
                     } 
                  }
                  
                  else 
                     endGame = new EndGame (onePlayer, onePlayer.score.score, introGame.highScore.last);
               
                  if (onePlayer.quit)
                     quit();
               }
               
               else 
               {  
                  startingCount++;
               
                  if (startingCount == 1)
                  {
                     twoPlayer.changeChar (twoPlayer.introGameVar.characterType1,twoPlayer.introGameVar.characterType2);
                  }
                 
                  threadCount2++;             
                  if (threadCount2 == 1)
                     twoPlayer.timerStart(); 
               
                  if (twoPlayer.gameOver)
                  {
                     threadCount++;
                     if (threadCount == 1)
                     {
                        endGame2.threadStart();
                        if (twoPlayer.sounds)
                        {
                           hit.clip.setFramePosition (0);
                           hit.start();
                        }
                     
                     }
                  
                     if (endGame2.restartIt)
                     {
                        try
                        {
                           Thread.sleep (250);
                        }
                        catch(Exception f) {}
                        restart();
                     }
                     else if (endGame2.quitIt)
                     {
                        try
                        {
                           Thread.sleep (250);
                        }
                        catch(Exception f) {}
                        quit();
                     } 
                  }
                  else 
                     endGame2 = new EndGame2 (twoPlayer.whoDied, twoPlayer);  
               } 
            }
            
            else
               introGame.checkIntroGame();
         }
      }
   }
  
   //Second Thread 
   class Thread2 extends Thread
   {
      /*** run **********************************************
      * Purpose: to constantly go through this method       * 
      * as a loop and perform certain commands              *
      * Parameters: none                                    *
      * Returns: none                                       *
      ******************************************************/
      public void run()
      {
         while (true)
         {
            try
            {
               sleep (1000/200);
            }
            catch(Exception e){}
         
            onePlayer.threadStuff();
            twoPlayer.threadStuff();
         }
      }
   }
   
   // Third Thread
   class Thread3 extends Thread
   {
      /*** run **********************************************
      * Purpose: to constantly go through this method       * 
      * as a loop and perform certain commands              *
      * Parameters: none                                    *
      * Returns: none                                       *
      ******************************************************/
      public void run()
      {
         while (true)
         {
            try 
            {
               sleep (1000);
            }
            catch (Exception e){}
            
            twoPlayer.threadStuff2();
            twoPlayer.character1.threadStuff();
            twoPlayer.character2.threadStuff();
         }
      }
   }
   
   //Fourth Thread
   class Thread4 extends Thread
   {
      /*** run **********************************************
      * Purpose: to constantly go through this method       * 
      * as a loop and perform certain commands              *
      * Parameters: none                                    *
      * Returns: none                                       *
      ******************************************************/
      public void run()
      {
         while (true)
         {
            try 
            {
               sleep (1000/340);
            }
            catch (Exception e){}
            
            introGame.charMode1.threadStuff();
            introGame.charMode2.threadStuff();
         }
      }
   }


   /*********************************************************
   * Class Purpose: To draw all the graphics on the screen  *
   *********************************************************/
   class Drawing extends JPanel
   {
      public void paint (Graphics g)
      {
         if (introGame.oneP || introGame.twoP)
         {
            if (introGame.oneP)
            {
               if (!onePlayer.gameOver)
                  onePlayer.displayInGame1P(g);
               else
                  endGame.displayEndGame(g);
            }
            else
            {
               if (!twoPlayer.gameOver)
                  twoPlayer.displayInGame2P(g);
               else
                  endGame2.displayEndGame(g);
            
            }
         }
         else 
            introGame.displayIntroGame(g);
      }
   }
   
   /*********************************************************
   * Class Purpose: To deal with all the mouse actions      *
   *********************************************************/
   class MouseStuff implements MouseListener 
   {
      public void mouseClicked(MouseEvent e)
      {
      }
      
      /*** mousePressed *************************************
      * Purpose: to deal with all the actions (including    * 
      * button states) that need to be performed when the   *
      * mouse is pressed                                    *
      * Parameters: MouseEvent                              *
      * Returns: none                                       *
      ******************************************************/
      public void mousePressed(MouseEvent e)
      {
         if (introGame.oneP || introGame.twoP)
         { 
            if (introGame.oneP)
            {
               if (!onePlayer.gameOver) 
                  onePlayer.updateInGame1P(e);
               else 
                  endGame.updateEndGame(e);
            }
            else
            {
               if (twoPlayer.gameOver) 
                  endGame2.updateEndGame(e);
            }
         }
         else
            introGame.updateIntroGame1(e);
         draw.repaint();
      }
   
      /*** mouseReleased ************************************
      * Purpose: to deal with all the actions (including    * 
      * button states) that need to be performed when the   *
      * mouse is released                                   *
      * Parameters: MouseEvent                              *
      * Returns: none                                       *
      ******************************************************/
      public void mouseReleased(MouseEvent e)
      {
         if (introGame.oneP || introGame.twoP)
         {
            if (introGame.oneP)
            {
               if (onePlayer.gameOver)
               {
                  endGame.updateEndGame2(e);
                  onePlayer.paused = false;
               }
               
               else
                  onePlayer.updateInGame1P2(e);
            }
            else
            {
               if (twoPlayer.gameOver)
               {
                  endGame2.updateEndGame2(e);
               }
            
            }
         }
         else
            introGame.updateIntroGame2(e);
         draw.repaint();
      }
   
      public void mouseEntered(MouseEvent e)
      {
      }
   
      public void mouseExited(MouseEvent e)
      {
      }
   }

   /*********************************************************
   * Class Purpose: To deal with all the keyboard action    *
   *********************************************************/
   class KeyStuff implements KeyListener
   {
      /*** keyPressed ***************************************
      * Purpose: to deal with all the actions (including    * 
      * the bird jumping) that need to be performed when a  *
      * key is pressed                                      *
      * Parameters: KeyEvent                                *
      * Returns: none                                       *
      ******************************************************/
      public void keyPressed (KeyEvent e)
      {
         if (introGame.oneP || introGame.twoP)
         {
            if (introGame.oneP)
            {
               onePlayer.updateInGame1P(e);
               if (onePlayer.gameOver)
                  endGame.updateEndGame3(e);
            }
            else
            {
               twoPlayer.updateInGame2P(e);
            }
         }
         else
            introGame.updateIntroGame4(e);
         draw.repaint();
      }
      
      /*** keyReleased **************************************
      * Purpose: to deal with all the actions (including    * 
      * the bird jumping) that need to be performed when a  *
      * key is released                                     *
      * Parameters: KeyEvent                                *
      * Returns: none                                       *
      ******************************************************/
      public void keyReleased (KeyEvent e)
      {
         if (introGame.oneP)
            onePlayer.updateInGame1P3();
         else
            twoPlayer.updateInGame2P2(e);
      }
      
      public void keyTyped (KeyEvent e)
      {  
      }
   }
   
   /*****************************************************************
   * Class Purpose: To deal with the mouse actions (when it moves)  *
   *****************************************************************/
   class MouseStuff2 implements MouseMotionListener
   {
      public void mouseDragged (MouseEvent e)
      {
      }
      
      /*** mouseMoved *****************************************
      * Purpose: to change the picture of the game mode       *
      * screen when the mouse hovers over certain coordinates *
      * key is pressed                                        *
      * Parameters: MouseEvent                                *
      * Returns: none                                         *
      ********************************************************/
      public void mouseMoved (MouseEvent e)
      {
         introGame.updateIntroGame3(e);
      }
   }
   
   // Main method: makes the "FlappyWorld" object and allows the user to play the game
   public static void main (String [] args)
   {
      new FlappyWorld();
   }
   
   /*** restart ******************************************
   * Purpose: to restart the game                        *
   * Parameters: none                                    *
   * Returns: none                                       *
   ******************************************************/
   public void restart()
   {
      onePlayer.soundc.clip.close();
      twoPlayer.backgroundM.clip.close();
      onePlayer = new InGame1P (state, introGame);
      endGame = new EndGame (onePlayer, 0, introGame.highScore.last);
      
      twoPlayer = new InGame2P (state,introGame);
      twoPlayer.timerStart();
      endGame2 = new EndGame2 (state, twoPlayer);
      counter = 0;
      threadCount = 0;
      
      charModeCount = 0;
      startingCount = 0;
   }
   
   /*** quit *********************************************
   * Purpose: Makes the game go back to the intro screen *
   * Parameters: none                                    *
   * Returns: none                                       *
   ******************************************************/
   public void quit()
   {
      twoPlayer.music = false;
      twoPlayer.sounds = false;
      onePlayer.soundc.clip.close();
      twoPlayer.backgroundM.clip.close();
      state = (int)((Math.random()*4)+1);
      introGame = new IntroGame(state, introGame);
      onePlayer = new InGame1P (state, introGame);
      endGame = new EndGame (onePlayer, 0, introGame.highScore.last);
      
      twoPlayer = new InGame2P (state, introGame);
      endGame2 = new EndGame2 (1, twoPlayer);
      counter = 0;
      threadCount = 0;
      onePlayer.paused = false;
      
      threadCount2 = 0;
      
      charModeCount = 0;
      startingCount = 0;
   }
}