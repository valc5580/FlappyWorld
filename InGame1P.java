/*********************************************************
*  Name: Siddharth Pai, Nik Valcic, Ben Stafl            *
*  Course: ICS 4U1 - 01  Pd. 4                           *
*  Final Assignment (Summative): Flappy World            *
*  Class Purpose: To make a class that deals with the    *
*  one player - in game - stuff                          *
*  Due Date: May 27, 2014                                *
*********************************************************/
import java.awt.event.*; 
import java.awt.*; 
import javax.swing.*;

class InGame1P
{
   int state;
   InGameBackground background; 
   IntroGame introGameVar;
   Character character;
   Pipe [] pipes = new Pipe [3];
   Score score = new Score ();
   PauseScreen pauseScreen = new PauseScreen ();
   DisplayCharacter dcharacter;
   ImageIcon introImage = new ImageIcon ("Images/IntroAnimation/GetReady.png");
   SoundClip soundc = new SoundClip ("Sounds/background(long).wav");  


   int initX = 1000;
   int distance = 350;
   int pipeGap = distance - 90;
   int playCounter = 0;
   int soundCounter = 0;

   int counter = 0;
   int scoreCounter = 0;
   int playCounter2 = 0;

   boolean gameOver = false;
   boolean gameStart = false;
   boolean paused = false;
   boolean quit = false;
   boolean sounds = false;
   boolean music = false;

   int initIntroY, introImageFinalX;
   int clickedX, clickedY;

   boolean [] setStuff2;

   float transparency = 1.0f;

   SoundClip point = new SoundClip ("Sounds/point.wav");
   SoundClip hit = new SoundClip ("Sounds/hit.wav");

   /*** InGame1P *****************************************
   * Purpose: (Constructor) to initialize the instances  *
   * of the class                                        *
   * Parameters: state, IntroGame object                 *
   * Returns: none                                       *
   ******************************************************/
   public InGame1P (int s, IntroGame introVar)
   {
      introGameVar = introVar;
      setStuff2 = introGameVar.setStuff;
   
      state = s;
   
      background = new InGameBackground (state);
      character = new Character (introGameVar.characterType);
      dcharacter = new DisplayCharacter (character.x, character.y, character.width, character.height, 30,introGameVar.characterType);
   
      pipes[0] = new Pipe (initX, 1);
   
      for (int i= 1; i < pipes.length; i++)
         pipes[i] = new Pipe (initX + distance*i, 1);
      
      initIntroY= character.y - 85;
   }

   /*** displayInGame1P **********************************
   * Purpose: displays the one player game               *
   * Parameters: Graphics object                         *
   * Returns: none                                       *
   ******************************************************/
   public void displayInGame1P (Graphics g)
   {
      Graphics2D g2 = (Graphics2D)g;
      if (gameStart)
      {
         playCounter++;
         if (music)
         {
            if (playCounter == 10)
            {
               soundc.clip.setFramePosition (0);
               soundc.clip.loop(soundc.clip.LOOP_CONTINUOUSLY);
            }
         }
      
         background.displayInGameBackground(g);
      
         g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,transparency));
         g2.drawImage(introImage.getImage(), introImageFinalX, initIntroY, null);
         g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
      
         if (transparency > 0.1f)
            transparency-=0.1f;
         else 
            transparency = 0.0f;
      
         for (int i = 0; i < pipes.length; i++)
            pipes[i].displayPipe(g);
        
         score.displayScore(g);
         character.displayCharacter (g, (!gameOver && !paused));
      
         if (!character.isAlive(pipes))
         {
            playCounter2++;
            if (playCounter2 == 1)
            {
               if (sounds)
               {
                  hit.clip.setFramePosition (0);
                  hit.start();
               }
            }
            gameOver = true;
         }
         
         if (paused)
         { 
            pauseScreen.displayPauseScreen(g);
            soundc.clip.stop();
         }
         
         if (!paused && music)
            soundc.clip.start();
      }
      
      else
      { 
         background.displayInGameBackground(g);
         score.displayScore(g);
         g.drawImage(introImage.getImage(), character.x-97, initIntroY, null);
         dcharacter.displayDisplayCharacter(g);
         introImageFinalX = character.x-97;
      }
   } 

   /*** updateInGame1P ***********************************
   * Purpose: updates the pictures                       *
   * Parameters: MouseEvent                              *
   * Returns: none                                       *
   ******************************************************/
   public void updateInGame1P (MouseEvent e)
   {
      clickedX = e.getX();
      clickedY = e.getY();
      if (gameStart)
      {
         if (!paused)
         {
            if ((e.getX()>= background.pause.x && e.getX() <= background.pause.x+background.pause.width) && (e.getY() >=background.pause.y && e.getY()<=background.pause.y+background.pause.height))
            {
               paused = true;
               background.pause.pressed();
            }
            
            else
            {
               character.updateCharacter(e, sounds && !gameOver && !paused);
               background.updateBackground(e);
            }
         }
      
         if (paused)
         {
            if ((e.getX()>=pauseScreen.resume.x && e.getX()<=pauseScreen.resume.x+pauseScreen.resume.width) && (e.getY()>=pauseScreen.resume.y && e.getY()<=pauseScreen.resume.y+pauseScreen.resume.height))
            {
               pauseScreen.resume.pressed();
               background.updateBackground2();
            }
            
            else if ((e.getX()>=pauseScreen.quit.x && e.getX()<=pauseScreen.quit.x+pauseScreen.quit.width) && (e.getY()>=pauseScreen.quit.y && e.getY()<=pauseScreen.quit.y+pauseScreen.quit.height))
               pauseScreen.quit.pressed();
         }
      }
      else
         gameStart = true;
   }
   
   /*** updateInGame1P2 **************************************** 
   * Purpose: updates the pictures of the screen (and buttons) *
   * Parameters: MouseEvent                                    *
   * Returns: none                                             *
   ************************************************************/
   public void updateInGame1P2 (MouseEvent e)
   {
      if (paused)
      {
         if ((e.getX()>=pauseScreen.resume.x && e.getX()<=pauseScreen.resume.x+pauseScreen.resume.width) && (e.getY()>=pauseScreen.resume.y && e.getY()<=pauseScreen.resume.y+pauseScreen.resume.height))
            if ((clickedX>=pauseScreen.resume.x && clickedX<=pauseScreen.resume.x+pauseScreen.resume.width) && (clickedY>=pauseScreen.resume.y && clickedY<=pauseScreen.resume.y+pauseScreen.resume.height))
               paused = false; 
         if ((e.getX()>=pauseScreen.quit.x && e.getX()<=pauseScreen.quit.x+pauseScreen.quit.width) && (e.getY()>=pauseScreen.quit.y && e.getY()<=pauseScreen.quit.y+pauseScreen.quit.height))
            if ((clickedX>=pauseScreen.quit.x && clickedX<=pauseScreen.quit.x+pauseScreen.quit.width) && (clickedY>=pauseScreen.quit.y && clickedY<=pauseScreen.quit.y+pauseScreen.quit.height))
               quit = true;
      
         pauseScreen.resume.released();
         pauseScreen.quit.released();
      
         if (!paused)   
            pauseScreen.y = 700;
      }
   }
   
   /*** updateInGame1P ***********************************
   * Purpose: updates the pictures (buttons)             *
   * Parameters: KeyEvent                                *
   * Returns: none                                       *
   ******************************************************/
   public void updateInGame1P (KeyEvent e)
   {
      if (gameStart)
      {
         character.updateCharacter(e, sounds && !gameOver && !paused);
         background.updateBackground(e);
      
         if (e.getKeyCode() == 32)
         {
            paused = true;
            background.pause.pressed();
         }
      }
      
      else 
         gameStart = true;
   }

   /*** updateInGame1P3 **********************************
   * Purpose: reassigns the keyTrack variable            *
   * Parameters: none                                    *
   * Returns: none                                       *
   ******************************************************/
   public void updateInGame1P3 ()
   {
      character.keyTrack= 0;
   }
   
   /*** threadStuff ***********************
   * Purpose: constantly goes through and *
   * reassigns the variables              *
   * Parameters: none                     *
   * Returns: none                        *
   ***************************************/
   public void threadStuff()
   {
      counter++;
      
      if (gameStart)
      { 
         if (!gameOver)
         {
            if (!paused)
            {  
               music = setStuff2[0];
               sounds = setStuff2[1];
               
               if (counter % 28 == 0)
                  character.t++;
            
               if (character.displacement >2)
                  if (character.angle <90)
                     character.angle+=1;
               
               if (character.displacement <0)
                  if (character.angle >-30)
                     character.angle-=3;
            
               if (character.displacement > 5)
                  character.sprite = 2; 
            
               if (counter % 20 == 0)
                  character.updateSprite();
            
               background.updateSprite();
            
               if (counter % 3 == 0)
               {
                  for (int i = 0; i < pipes.length; i++)
                  {
                     pipes[i].movePipe();
                  
                     if (pipes[i].x+pipes[i].width <= 0)
                     {
                        if (!setStuff2[2])
                        {
                           if (score.score <= 2)
                              pipes[i] = new Pipe ((pipes.length)*(pipeGap) + (pipes.length-1)* (pipes[i].width),1);
                           else if (score.score>2 && score.score <=10)
                           { 
                              pipes[i] = new Pipe ((pipes.length)*(pipeGap) + (pipes.length-1)* (pipes[i].width),2);
                           }
                           else if (score.score >10)
                              pipes[i] = new Pipe ((pipes.length)*(pipeGap) + (pipes.length-1)* (pipes[i].width),3);
                        }
                        else
                        {
                           pipes[i] = new Pipe ((pipes.length)*(pipeGap) + (pipes.length-1)* (pipes[i].width),1); 
                        }
                        scoreCounter = 0;
                     }
                  
                     if (Math.abs(pipes[i].x+ pipes[i].width - character.x) <=3)
                     {
                        scoreCounter++;
                        if (scoreCounter == 1)
                        {
                           score.updateScore();
                           if (sounds)
                           {
                              point.clip.setFramePosition (0);
                              point.start();  
                           }
                        }
                     }
                  }
               }
            }
            
            else if (paused)   
               pauseScreen.updateCord(); 
                  
            setStuff2 = introGameVar.setStuff;  
         }
      }
      
      else 
      {
         if (counter %20 ==0)
         {
            dcharacter.updateSprite();
         }
         
         if (counter % 3 == 0)
         {
            dcharacter.updateCord();
            character.x = dcharacter.x;
            character.y = dcharacter.y;
         }
            
         background.updateSprite();
      }
   }  
}