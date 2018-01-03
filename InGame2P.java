/*********************************************************
*  Name: Siddharth Pai, Nik Valcic, Ben Stafl            *
*  Course: ICS 4U1 - 01  Pd. 4                           *
*  Final Assignment (Summative): Flappy World            *
*  Class Purpose: To make a class that deals with the    *
*  two player - in game - stuff                          *
*  Due Date: May 27, 2014                                *
*********************************************************/
import java.awt.event.*; 
import java.awt.*; 
import javax.swing.*;

class InGame2P 
{
   ImageIcon message = new ImageIcon ("Images/switching.png");
   IntroGame introGameVar;
   int state;
   InGameBackground background; 
   Character character1;
   Character character2;
   DisplayCharacter dcharacter1;
   DisplayCharacter dcharacter2;
   ImageIcon label1 = new ImageIcon ("Images/Labels/P1.png");
   ImageIcon label2 = new ImageIcon ("Images/Labels/P2.png");
   Pipe [] pipes = new Pipe [3];

   boolean timer2Start = false;
   
   boolean flick1 = false;
   boolean flicker1 = true;
   boolean flick2 = false;
   boolean flicker2 = true;
   
   int flickerCounter1 = 0;
   int flickerCounter2 = 0;
   
   int initX = 1000;
   int distance = 350;
   int pipeGap = distance - 90;
   int playCounter = 0;

   int counter = 0;
   int died = 0; 
   int whoDied = 0;

   int controlVar = 0;
   
   int controlVar1 = 0;
   int controlVar2 = 0;

   int lifeCounter1 = 0;
   int lifeCounter2 = 0;

   int pipeTrack = 0;
   int pipesPassedNum = 4;

   boolean gameOver = false;
   boolean paused = false;
   boolean quit = false;
   boolean switchChar = false;
   boolean showPowerUp = false;
   boolean goodToGo = false;
   boolean showMessage = false;

   boolean [] setStuff2;
   boolean ninth = true;
   boolean now = false;

   boolean sounds = false;
   boolean music = false;
   
   int pipesPassed = 0;

   PowerUp powerUp;
   LifeDisplay lDisplay;
   PowerUpsDisplay pDisplay = new PowerUpsDisplay(8, 58);

   LifeDisplay lDisplay2;
   PowerUpsDisplay pDisplay2 = new PowerUpsDisplay(700, 58);

   Character backChar, frontChar;

   ImageIcon count = new ImageIcon ("Images/CountDown/3.png");
   int countDown = 3;

   boolean gameStart;
   
   float trans1 = 1.0f;
   float trans2 = 1.0f;

   SoundClip backgroundM = new SoundClip ("Sounds/background(long).wav");
   SoundClip hit1 = new SoundClip ("Sounds/hit.wav");
   SoundClip hit2 = new SoundClip ("Sounds/hit.wav");

   /*** InGame2P *****************************************
   * Purpose: (Constructor) initializes instances        *
   * Parameters: state, Introgame object                 *
   * Returns: none                                       *
   ******************************************************/
   public InGame2P (int s, IntroGame introVar)
   {
      introGameVar = introVar;
      setStuff2 = introGameVar.setStuff;
   	
   	      
      music = setStuff2[0];
      sounds = setStuff2[1];
   
      state = s;
   
      lDisplay = new LifeDisplay (8, 8, 1);
      lDisplay2 = new LifeDisplay (700, 8, 1);
   
      background = new InGameBackground (state);
      background.setPause(false);
   
      character1 = new Character (introVar.characterType1);
      character2 = new Character (introVar.characterType2); 
      
      character2.x = 100;
   
      dcharacter1 = new DisplayCharacter (character1.x, character1.y, character1.width, character1.height, 30,introVar.characterType1);
      dcharacter2 = new DisplayCharacter (character2.x, character2.y, character2.width, character2.height, 30,introVar.characterType2);
   
      dcharacter2.goUp = true;
   
      backChar = character2;
      frontChar = character1;
   
      
      pipes[0] = new Pipe (initX, 1);
   
      for (int i= 1; i < pipes.length; i++)
         pipes[i] = new Pipe (initX + distance*i, 1);
   
      countDown = 3;
      gameStart = false;
   }

   /*** displayInGame2P **********************************
   * Purpose: displays the 2 player mode game            *
   * Parameters: Graphics object                         *
   * Returns: none                                       *
   ******************************************************/
   public void displayInGame2P (Graphics g)
   { 
      Graphics2D g2 = (Graphics2D)g;
      if (playCounter == 10 && music)
      {          
         backgroundM.clip.setFramePosition (0);
         backgroundM.clip.loop(backgroundM.clip.LOOP_CONTINUOUSLY);    
      }
      playCounter ++;
      background.displayInGameBackground(g);
   
      if (countDown >= 0)
         g.drawImage (count.getImage(), (900/2) - (count.getIconWidth()/2), 100, null);
      else
         gameStart = true;
   
      if (showPowerUp)
         powerUp.displayPowerUp (g);
   
      for (int i = 0; i < pipes.length; i++)
         pipes[i].displayPipe(g);
   
      lDisplay.displayLifeDisplay(g);
      pDisplay.displayPowerUpsDisplay (g);
      lDisplay2.displayLifeDisplay(g);
      pDisplay2.displayPowerUpsDisplay (g);
   
      if (showMessage)
      {
         g.drawImage (message.getImage(), 450 - message.getIconWidth()/2, 55, null);
      }
   
      if (!gameOver && gameStart)
      {
         g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,trans1));
         character1.displayCharacter (g, true);
         g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
         g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,trans2));
         character2.displayCharacter (g, true);
         g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
         g.drawImage (label1.getImage(), character1.x + 65/2 - 31/2, character1.y + 60, null);
         g.drawImage (label2.getImage(), character2.x + 65/2 - 31/2, character2.y + 60, null);
      }
      else
      {
         if (gameStart)
         {
            character1.displayCharacter (g, false);
            character2.displayCharacter (g, false);
            g.drawImage (label1.getImage(), character1.x + 65/2 - 31/2, character1.y + 60, null);
            g.drawImage (label2.getImage(), character2.x + 65/2 - 31/2, character2.y + 60, null);
         }
         else
         {
            dcharacter1.displayDisplayCharacter(g);
            dcharacter2.displayDisplayCharacter(g);
            g.drawImage (label1.getImage(), dcharacter1.x + 65/2 - 31/2, dcharacter1.y + 60, null);
            g.drawImage (label2.getImage(), dcharacter2.x + 65/2 - 31/2, dcharacter2.y + 60, null);
         }
      }
      
      
      
      if (!gameOver)
      { 
         if (character1.y >= 538)
            character1.jump2(sounds, hit1);
      
         if (character2.y >= 538)
            character2.jump2(sounds, hit2);
           
         if (!character1.isAlive(pipes, 2))
         {    
            if (!character1.invincible)
            {
               whoDied = 2;
            
               if (lDisplay.lifeNum() >0)
                  controlVar1++;
               
               if (controlVar1 == 10 && lDisplay.lifeNum() >0)
               {
                  lDisplay.takeLife();
                  flick1 = true;
               }
                           
               if (lDisplay.lifeNum() <= 0)
                  gameOver = true;
            } 
         }
         else 
            controlVar1 = 0;
       
                  
         if (!character2.isAlive(pipes, 2))
         {
            if (!character2.invincible)
            {
               whoDied = 1;
            
               if (lDisplay2.lifeNum() >0)
                  controlVar2++;
               
               if (controlVar2 == 10 && lDisplay2.lifeNum() >0)
               {
                  lDisplay2.takeLife();
                  flick2 = true;
               }
                           
               if (lDisplay2.lifeNum() <= 0)
                  gameOver = true;
            } 
         } 
         else  
            controlVar2 = 0;
      }
      
      if (character1.checkHit(character2.a1))
      {
         lifeCounter1++;
         if (lifeCounter1 == 1)
         {
            lDisplay.takeLife(); 
            flick1 = true;
         }
         else;
      }
      else
         lifeCounter1 = 0;
                  
      if (character2.checkHit(character1.a1))
      {  
         lifeCounter2++;
         if (lifeCounter2 == 1)
         {
            lDisplay2.takeLife();
            flick2 = true;
         }
         else;     
      }  
      else
         lifeCounter2 = 0;
         
      if (lDisplay.lifeNum() <= 0)
      {
         whoDied = 1;
         gameOver = true;
      }
             
      if (lDisplay2.lifeNum() <= 0)
      {
         whoDied = 2;
         gameOver = true;
      } 
   }
   
   /*** updateInGame2P ***********************************
   * Purpose: updates the characters and background      *
   * Parameters: KeyEvent                                *
   * Returns: none                                       *
   ******************************************************/
   public void updateInGame2P (KeyEvent e)
   {
      character1.updateCharacter(e, pDisplay, 1, sounds && !gameOver && gameStart);
      character2.updateCharacter(e, pDisplay2, 2, sounds && !gameOver && gameStart);
      background.updateBackground(e);
   } 

   /*** updateInGame2P2 **************
   * Purpose: Makes the birds jump   *
   * Parameters: KeyEvent            *
   * Returns: none                   *
   **********************************/
   public void updateInGame2P2(KeyEvent e)
   {
      if (e.getKeyCode() == 87)
         character2.keyTrack2 = 0;
      else if (e.getKeyCode() == 38)
         character1.keyTrack = 0;
   }

   /*** timerStart ******************************
   * Purpose: allows threadStuff2 to be called  *
   * Parameters: none                           *
   * Returns: none                              *
   *********************************************/
   public void timerStart()
   {
      timer2Start = true;
   }

   /*** init *********************************************
   * Purpose: sets the lifeDisplay, characters, etc. to  * 
   * the correct state                                   *
   * Parameters: integer1 for bird1, integer 2 for bird 2*                                  
   * Returns: none                                       *
   ******************************************************/
   public void changeChar (int c1, int c2)
   {
      lDisplay = new LifeDisplay (8, 8, c1);
      lDisplay2 = new LifeDisplay (700, 8, c2);
   
      character1 = new Character (c1);
      character2 = new Character (c2); 
      
      character2.x = 100;
   
      dcharacter1 = new DisplayCharacter (character1.x, character1.y, character1.width, character1.height, 30,c1);
      dcharacter2 = new DisplayCharacter (character2.x, character2.y, character2.width, character2.height, 30,c2);
   
      dcharacter2.goUp = true;
   
      backChar = character2;
      frontChar = character1;
   
      goodToGo = true;
   }

   /*** threadStuff **************************************
   * Purpose: constantly goes through certain conditions *
   * and reassigns variables                             *
   * Parameters: none                                    *
   * Returns: none                                       *
   ******************************************************/
   public void threadStuff()
   {
      counter++;
      setStuff2 = introGameVar.setStuff; 
      music = setStuff2[0];
      sounds = setStuff2[1];
      
      character1.gameOver = gameOver;
      character2.gameOver = gameOver;
   
      if (goodToGo)
      {
         if (gameStart)
         {     
            if (!gameOver)
            {
               
               if (flick1)
               {
                  flickerCounter1++;
                  if (flickerCounter1 == 400)
                  {
                     flick1 = false;
                     flickerCounter1 = 0;
                     flicker1 = true;
                  }
                  if (flickerCounter1 % 26 == 0)
                     flicker1 = !flicker1;
                  if (flicker1)
                     trans1 = 1.0f;
                  else
                     trans1 = 0.6f;
               }
               else
                  trans1 = 1.0f;
                  
               if (flick2)
               {
                  flickerCounter2++;
                  if (flickerCounter2 == 400)
                  {
                     flick2 = false;
                     flickerCounter2 = 0;
                     flicker2 = true;
                  }
                  if (flickerCounter2 % 26 == 0)
                     flicker2 = !flicker2;
                  if (flicker2)
                     trans2 = 1.0f;
                  else
                     trans2 = 0.6f;
               }
               else
                  trans2 = 1.0f;
               
               if (died == 54)
                  died = 0;
               
               if (counter%5 ==0)
               {
                  character1.updateStuff();
                  character2.updateStuff();
               }
               
               if (showPowerUp && counter % 3 == 0)
               {
                  powerUp.move();
                  character1.gainedPower(powerUp, pDisplay);
                  character2.gainedPower(powerUp, pDisplay2);  
               }
               if (counter%4 == 0 && showPowerUp)
                  powerUp.updateCord();
                  
               if (showPowerUp)
                  if (powerUp.x < -65)
                     showPowerUp = false;
               
               if (counter % 28 == 0)
               {
                  character1.t++;
                  character2.t++;
               }
               
               if (character1.displacement >2)
                  if (character1.angle <90)
                     character1.angle+=1;
               
               if (character1.displacement <0)
                  if (character1.angle >-30)
                     character1.angle-=3;
               
               if (character1.displacement > 5)
                  character1.sprite = 2; 
               
               if (character2.displacement >2)
                  if (character2.angle <90)
                     character2.angle+=1;
               
               if (character2.displacement <0)
                  if (character2.angle >-30)
                     character2.angle-=3;
               
               if (character2.displacement > 5)
                  character2.sprite = 2;   
               
               if (counter % 20 == 0)
               {
                  character1.updateSprite();
                  character2.updateSprite();
               }
               
               background.updateSprite();
               
               if (counter % 3 == 0)
               {
                  for (int i = 0; i < pipes.length; i++)
                  {
                     pipes[i].movePipe();
                     
                     if (pipes[i].x+pipes[i].width <= 0)
                     {
                        pipesPassed++;
                        
                        if (!setStuff2[2])
                        {
                           if (pipesPassed <= 2)
                              pipes[i] = new Pipe ((pipes.length)*(pipeGap) + (pipes.length-1)* (pipes[i].width),1);
                           else if (pipesPassed>2 && pipesPassed <=10)
                              pipes[i] = new Pipe ((pipes.length)*(pipeGap) + (pipes.length-1)* (pipes[i].width),2);
                           else if (pipesPassed >10)
                              pipes[i] = new Pipe ((pipes.length)*(pipeGap) + (pipes.length-1)* (pipes[i].width),3);
                        }
                        else
                           pipes[i] = new Pipe ((pipes.length)*(pipeGap) + (pipes.length-1)* (pipes[i].width),1);
                        
                        
                        if (pipesPassed%5 == 0)
                        {
                           powerUp = new PowerUp ((int)(Math.random()*3)+1, pipes[i].x + pipes[i].width + pipeGap/2 - PowerUp.width/2, (int)(Math.random()*301)+150);
                           showPowerUp = true;                                       
                        }
                                                	
                        if (pipesPassed%5 ==0)
                        {
                           switchChar = true;
                           showMessage = false;
                        }
                           
                        if (pipesPassed == pipesPassedNum)
                        {
                           showMessage = true;
                           pipesPassedNum += 5;
                        }
                           
                     }  
                  }
               }
               
               
               if (switchChar)
               {
                  if (backChar.x < 300)
                     backChar.x+=2;
                  if (frontChar.x> 100)
                     frontChar.x-=2;
                  if (backChar.x == 300 && frontChar.x == 100)
                  {
                     Character temp = backChar;
                     backChar = frontChar;
                     frontChar = temp;
                     switchChar = false;
                  }
               }
            }  
         }
         else
         {
            background.updateSprite();
            
            if (counter %20 ==0)
            {
               dcharacter1.updateSprite();
               dcharacter2.updateSprite();
            }
            
            if (counter % 3==0)
            {
               dcharacter1.updateCord();
               dcharacter2.updateCord();
               character1.x = dcharacter1.x;
               character1.y = dcharacter1.y;
               character2.x = dcharacter2.x;
               character2.y = dcharacter2.y;
            }
         } 
      }
   }
   
   /*** threadStuff2 *************************************
   * Purpose: constantly goes through certain conditions *
   * and reassigns variables                             *
   * Parameters: none                                    *
   * Returns: none                                       *
   ******************************************************/
   public void threadStuff2()
   {
      if (timer2Start)
      {
         if (countDown >0)
            countDown--;
         else 
            countDown = -2;
         
         if (countDown == 3)
            count = new ImageIcon ("Images/CountDown/3.png");
         else if (countDown == 2)
            count = new ImageIcon ("Images/CountDown/2.png");
         else if (countDown == 1)
            count = new ImageIcon ("Images/CountDown/1.png");
         else if (countDown == 0)
            count = new ImageIcon ("Images/CountDown/go.png");
         else if (countDown == -2)
            count = new ImageIcon ("");
      }
   } 
}