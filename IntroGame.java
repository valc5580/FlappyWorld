/*********************************************************
*  Name: Siddharth Pai, Nik Valcic, Ben Stafl            *
*  Course: ICS 4U1 - 01  Pd. 4                           *
*  Final Assignment (Summative): Flappy World            *
*  Class Purpose: To make a class that deals with the    *
*  introgame (intro screen etc.)                         *
*  Due Date: May 27, 2014                                *
*********************************************************/
import java.awt.event.*;
import javax.swing.*; 
import java.awt.*;

class IntroGame 
{
   int state, counter; 
   IntroScreen introScreen;
   HighScore highScore; 
   GameMode gameMode;
   Settings settings;
   Instructions instructions;
   CharMode1 charMode1;
   CharMode2 charMode2;
   boolean showIntro, showHighScore, showGameMode, showSettings, showInstructions, showCharMode1, showCharMode2; 
   boolean oneP = false;
   boolean twoP = false;
   
   boolean oneShow = true;
   boolean twoShow = true;
   boolean threeShow = true;
   
   boolean [] setStuff = new boolean [3];
   
   int characterType = 1;
   int characterType1 = 1;
   int characterType2 = 1;
   
   /*** IntroGame ****************************************
   * Purpose: (Constructor) initializes the instances    *
   * Parameters: state                                   *
   * Returns: none                                       *
   ******************************************************/
   public IntroGame(int s)
   {
      setStuff [0] = true;
      setStuff [1] = true;
      setStuff [2] = true;
      
      state = s;
      counter = 0;
      
      introScreen = new IntroScreen (state);
      highScore = new HighScore (state);
      gameMode = new GameMode (state);
      instructions = new Instructions (state);
      settings = new Settings (state);
      charMode1 = new CharMode1 (360, 265, state);
      charMode2 = new CharMode2 (174, 300, 593, 300, state);
      
      showIntro = true;
      showHighScore = false;
      showGameMode = false;
      showSettings = false;
      showInstructions = false;
      showCharMode1 = false;
      showCharMode2 = false;
   }
   
   /*** IntroGame ****************************************
   * Purpose: (Constructor) initializes the instances    *
   * Parameters: state , Introgame object                *
   * Returns: none                                       *
   ******************************************************/
   public IntroGame(int s, IntroGame introStuff)
   {
      this.setStuff = introStuff.setStuff;
      
      state = s;
      counter = 0;
      
      introScreen = new IntroScreen (state);
      highScore = new HighScore (state);
      gameMode = new GameMode (state);
      instructions = new Instructions (state);
      settings = new Settings (state);
      charMode1 = new CharMode1 (360, 265, state);
      charMode2 = new CharMode2 (174, 300, 593, 300, state);
      
      settings.updateSettings3(setStuff);
      
      showIntro = true;
      showHighScore = false;
      showGameMode = false;
      showSettings = false;
      showInstructions = false;
      showCharMode1 = false;
      showCharMode2 = false;
      
   }
   
   /*** displayIntroGame ****************
   * Purpose: displays the introGame    *
   * Parameters: Graphics object        *
   * Returns: none                      *
   *************************************/
   public void displayIntroGame(Graphics g1)
   {
      if (showIntro)
         introScreen.displayIntroScreen(g1);
      
      else if (showHighScore)
         highScore.displayHighScore(g1);
         
      else if (showInstructions)
         instructions.displayInstructions(g1);
      
      else if (showSettings)
         settings.displaySettings(g1);
         
      else if (showGameMode)
         gameMode.displayGameMode(g1);
      
      else if (showCharMode1)
         charMode1.displayCharMode1(g1);
         
      else if (showCharMode2)
         charMode2.displayCharMode2(g1);
   }
   
   /*** updateIntroGame1 *********************************
   * Purpose: updates the introgame (different screens)  *
   * Parameters: MouseEvent                              *
   * Returns: none                                       *
   ******************************************************/
   public void updateIntroGame1(MouseEvent e)
   {
      if (showIntro)
      {
         introScreen.updateIntroScreen1(e); 
      }
      
      else if (showHighScore)
      {
         highScore.updateHighScore1(e); 
      }
      
      else if (showSettings)
      {
         setStuff = settings.updateSettings1(e, setStuff[0], setStuff[1], setStuff[2]);
      }
      
      else if (showInstructions)
      {
         instructions.updateInstructions1(e);
      }
      
      else if (showCharMode1)
      {
         charMode1.updateStuff3(e);
      }
      
      else if (showCharMode2)
      {
         charMode2.updateStuff3(e);
      }
   
   }
   
   /*** updateIntroGame2 *********************************
   * Purpose: updates the introgame (different screens)  *
   * Parameters: MouseEvent                              *
   * Returns: none                                       *
   ******************************************************/
   public void updateIntroGame2(MouseEvent e)
   {
      if (showIntro)
      {
         introScreen.updateIntroScreen2(e);
      }
      
      else if (showHighScore)
      {
         highScore.updateHighScore2(e); 
      }
      
      else if (showSettings)
      {
         settings.updateSettings2(e);
      }
      
      else if (showInstructions)
      {
         instructions.updateInstructions2(e);
      }
      
      else if (showGameMode)
      {
         gameMode.updateGameMode2(e);
      }
      
      else if (showCharMode1)
      {
         charMode1.updateStuff4(e);
      }
      
      else if (showCharMode2)
      {
         charMode2.updateStuff4(e);
      }
   }
   
   /*** updateIntroGame3 *********************************
   * Purpose: updates the introgame (different screens)  *
   * Parameters: MouseEvent                              *
   * Returns: none                                       *
   ******************************************************/
   public void updateIntroGame3 (MouseEvent e)
   {
      if (showGameMode)
         gameMode.updateGameMode(e);
      if (showInstructions)
         instructions.updateInstructions3(e);
   }
   
   /*** updateIntroGame4 *********************************
   * Purpose: updates the introgame (different screens)  *
   * Parameters: KeyEvent                                *
   * Returns: none                                       *
   ******************************************************/
   public void updateIntroGame4 (KeyEvent e)
   {
      if (showCharMode1)
         charMode1.pressed(e);
      if (showCharMode2)
         charMode2.pressed(e);
   }
   
   /*** checkIntroGame ***********************************
   * Purpose: checks various conditions and reassigns    *
   * variables                                           *
   * Parameters: none                                    *
   * Returns: none                                       *
   ******************************************************/
   public void checkIntroGame ()
   {
      if (showIntro)
      {
         if (introScreen.playPressed)
         {
            try
            {
               Thread.sleep (50);
            }
            catch(Exception e) {}
            
            showGameMode = true;
            showInstructions = false;
            showSettings = false;
            showHighScore = false;
            showIntro = false;
            showCharMode1 = false;
            showCharMode2 = false;
         }
         else if (introScreen.instructionsPressed)
         {  
            try
            {
               Thread.sleep (50);
            }
            catch(Exception e) {}
            
            showGameMode = false;
            showInstructions = true;
            showSettings = false;
            showHighScore = false;
            showIntro = false;
            showCharMode1 = false;
            showCharMode2 = false;
         }
         else if (introScreen.settingsPressed)
         {
            try
            {
               Thread.sleep (50);
            }
            catch(Exception e) {}
            
            showGameMode = false;
            showInstructions = false;
            showSettings = true;
            showHighScore = false;
            showIntro = false;
            showCharMode1 = false;
            showCharMode2 = false;
         }
         else if (introScreen.highScorePressed)
         {
            try
            {
               Thread.sleep (50);
            }
            catch(Exception e) {}
            
            showGameMode = false;
            showInstructions = false;
            showSettings = false;
            showHighScore = true;
            showIntro = false;
            showCharMode1 = false;
            showCharMode2 = false;
         }
      } 
      
      else if (showSettings)
      {
         if (settings.backPressed)
         {
            try
            {
               Thread.sleep (50);
            }
            catch(Exception e) {}
            
            showGameMode = false;
            showInstructions = false;
            showSettings = false;
            showHighScore = false;
            showIntro = true;
            showCharMode1 = false;
            showCharMode2 = false;
            introScreen.settingsPressed = false;
            settings.backPressed = false;
         }
      }
      
      else if (showInstructions)
      {
         if (instructions.backPressed)
         {            
            showGameMode = false;
            showInstructions = false;
            showSettings = false;
            showHighScore = false;
            showIntro = true;
            introScreen.instructionsPressed = false;
            instructions.backPressed = false;
            showCharMode1 = false;
            showCharMode2 = false;
         }
      }
      
      else if (showHighScore)
      {
         if (highScore.backPressed)
         {
            try
            {
               Thread.sleep (50);
            }
            catch(Exception e) {}
            
            showGameMode = false;
            showInstructions = false;
            showSettings = false;
            showHighScore = false;
            showIntro = true;
            showCharMode1 = false;
            showCharMode2 = false;
            introScreen.highScorePressed = false;
            highScore.backPressed = false;
         }
      }
      
      else if (showGameMode)
      {
         if (gameMode.onePPressed)
         {
            showGameMode = false;
            showInstructions = false;
            showSettings = false;
            showHighScore = false;
            showIntro = false;
            introScreen.highScorePressed = false;
            highScore.backPressed = false;
            showCharMode1 = true;
            showCharMode2 = false;
         }
         else if (gameMode.twoPPressed)
         {
            showGameMode = false;
            showInstructions = false;
            showSettings = false;
            showHighScore = false;
            showIntro = false;
            showCharMode1 = false;
            showCharMode2 = true;
            introScreen.highScorePressed = false;
            highScore.backPressed = false;
         }  
      }
      
      else if (showCharMode1)
      {
         if (charMode1.selectPressed)
         {
            characterType = charMode1.currentNum;
            oneP = true;
            twoP = false;
         }
      }
      
      else if (showCharMode2)
      {
         if (charMode2.go)
         {
            characterType1 = charMode2.currentNum1;
            characterType2 = charMode2.currentNum2;
            oneP = false;
            twoP = true;
         }
      }
   }
   
   /*** threadStuff **************************************
   * Purpose: constantly goes through and checks various *
   * conditions and reassigns variables                  *
   * Parameters: none                                    *
   * Returns: none                                       *
   ******************************************************/
   public void threadStuff()
   {
      characterType1 = charMode2.currentNum1;
      characterType2 = charMode2.currentNum2;
      
      if (showGameMode)
      {
         gameMode.bird1.updateCord();
         gameMode.bird2.updateCord();
         gameMode.bird3.updateCord();
      }
      
      if (showInstructions && instructions.showMode)
      {
         instructions.bird1.updateCord();
         instructions.bird2.updateCord();
         instructions.bird3.updateCord();
      }
      
      if (showInstructions && instructions.showScreen1)
      {
         instructions.screen1.bird.updateCord();
         instructions.screen1.bird.x++;
         if (instructions.screen1.bird.x>=900)
            instructions.screen1.bird.x = -65;
            
         if (instructions.screen1.bird.x == 192||instructions.screen1.bird.x == 385||instructions.screen1.bird.x == 592||instructions.screen1.bird.x == 796)
            instructions.screen1.score.updateScore();
      }
      
      if (showInstructions && instructions.showScreen2)
      {
         instructions.screen2.pDisplay.threadStuff();
         instructions.screen2.lDisplay.threadStuff();
         instructions.screen2.bird.updateCord();
         instructions.screen2.bird.x++;
         if (instructions.screen2.bird.x>=900)
         {
            instructions.screen2.bird.x = -65;
            instructions.screen2.pDisplay.takeAway();
            instructions.screen2.pDisplay.takeAway();
            instructions.screen2.pDisplay.takeAway();
            oneShow = true;
            twoShow = true;
            threeShow = true;
         }
            
         if (instructions.screen2.bird.x+46 == instructions.screen2.one.x)
         {
            oneShow = false;
            instructions.screen2.pDisplay.updatePowerUpsDisplay(1);
         }
         
         if (instructions.screen2.bird.x+46 == instructions.screen2.two.x)
         {  
            twoShow = false;
            instructions.screen2.pDisplay.updatePowerUpsDisplay(2);
         }
         
         if (instructions.screen2.bird.x+46 == instructions.screen2.three.x)
         {
            threeShow = false; 
            instructions.screen2.pDisplay.updatePowerUpsDisplay(3);
         }
      } 
       
      if (oneShow)
      {
         if (instructions.screen2.trans1 < 0.9f)
               instructions.screen2.trans1+=0.1;
         else 
            instructions.screen2.trans1=1.0f;
      }
      else
      {
         if (instructions.screen2.trans1 > 0.1f)
               instructions.screen2.trans1-=0.1;
         else 
            instructions.screen2.trans1=0f;
      } 
      
      if (twoShow)
      {
         if (instructions.screen2.trans2 < 0.9f)
               instructions.screen2.trans2+=0.1;
         else 
            instructions.screen2.trans2=1.0f;
      }
      else
      {
         if (instructions.screen2.trans2 > 0.1f)
               instructions.screen2.trans2-=0.1;
         else 
            instructions.screen2.trans2=0f;
      } 

      if (threeShow)
      {
         if (instructions.screen2.trans3 < 0.9f)
               instructions.screen2.trans3+=0.1;
         else 
            instructions.screen2.trans3=1.0f;
      }
      else
      {
         if (instructions.screen2.trans3 > 0.1f)
               instructions.screen2.trans3-=0.1;
         else 
            instructions.screen2.trans3=0f;
      } 
        
      if (showGameMode)
      { 
         if (counter % 5 == 0){
            gameMode.bird1.updateSprite();
            gameMode.bird2.updateSprite();
            gameMode.bird3.updateSprite();
         }
      }
      if (showInstructions)
      {
         if (instructions.showMode)
         {
            if (counter % 5 == 0){
               instructions.bird1.updateSprite();
               instructions.bird2.updateSprite();
               instructions.bird3.updateSprite();
            }
         }
         
         if (instructions.showScreen2)
         {
            if (counter % 5 == 0)
            {
               instructions.screen2.one.updateCord();
               instructions.screen2.two.updateCord();
               instructions.screen2.three.updateCord();
               instructions.screen2.bird.updateSprite();
            }  
         }
         
         if (instructions.showScreen1)
         {
            if (counter % 5 == 0)
            {
               instructions.screen1.bird.updateSprite();
            }  
         }

      }
        
      counter++;
         
      charMode1.updateStuff();
      charMode2.updateStuff();
         
      if (showCharMode1)
      {
         if (counter % 4 == 0)
            charMode1.updateStuff2();
      }
         
      if (showCharMode2)
      {
         if (counter % 4 == 0)
            charMode2.updateStuff2();
         if (charMode2.go)
            twoP = true;
      }
   }
}