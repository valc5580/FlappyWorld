/*********************************************************
*  Name: Siddharth Pai, Nik Valcic, Ben Stafl            *
*  Course: ICS 4U1 - 01  Pd. 4                           *
*  Final Assignment (Summative): Flappy World            *
*  Class Purpose: To make a class that deals with the    *
*  introscreen (title screen)                            *
*  Due Date: May 27, 2014                                *
*********************************************************/
import javax.swing.*; 
import java.awt.*;
import java.awt.event.*; 

class IntroScreen 
{
   int state;
   int clickedX, clickedY;
   ImageIcon image;
   ImageIcon title;
   Button play;
   Button instructions;
   Button settings;
   Button highScore;
   boolean playPressed;
   boolean instructionsPressed;
   boolean settingsPressed;
   boolean highScorePressed;
   int dcx = -65;
   int dcy = 190;
   int counter = 0;
   ImageIcon moon = new ImageIcon ("Images/Misc/moon.png");
   int moonCounter = 0;
   int moonx = 0; 
   int moony = 700;
   int moonW = moon.getIconWidth();

   DisplayCharacter dc = new DisplayCharacter (dcx, dcy, 65, 50, 20, 1);
   DisplayCharacter dc2 = new DisplayCharacter (dcx - 70, dcy + 13, 39, 30, 10, 2);
   DisplayCharacter dc3 = new DisplayCharacter (dcx - 140, dcy + 26, 39, 30, 10, 3);
   DisplayCharacter dc4 = new DisplayCharacter (dcx - 210, dcy + 39, 39, 30, 10, 4);
   SnowFlake [] flakes;
   Star [] stars;
   
   /*** IntroScreen **************************************
   * Purpose: (Constructor) initializes the instances    *
   * Parameters: state                                   *
   * Returns: none                                       *
   ******************************************************/
   public IntroScreen(int s)
   {
      state = s;
      
      playPressed = false;
      instructionsPressed = false;
      settingsPressed = false;
      highScorePressed = false;
      
      if (state == 1)
      {
         image = new ImageIcon ("Images/IntroScreen/5.png");
         title = new ImageIcon ("Images/Titles/introScreen1/Logo.png");
         play = new Button (18);
         instructions = new Button (2);
         settings = new Button (3);
         highScore = new Button (4);
      }
      
      else if (state == 2)
      {
         image = new ImageIcon ("Images/IntroScreen/2.png");
         title = new ImageIcon ("Images/Titles/introScreen1/Logo.png");
         play = new Button (18);
         instructions = new Button (2);
         settings = new Button (3);
         highScore = new Button (4);
         int starNum = 24;
         stars = new Star [starNum];
         int [] xCord = {110, 847, 720, 95, 100, 837, 845, 230, 12, 234, 343, 232, 456, 306, 767, 753, 887, 344, 56, 674, 657, 755, 466, 824};
         int [] yCord = {22, 93, 260, 367, 635, 565, 190, 243, 34, 456, 674, 347, 467, 567, 353, 244, 536, 245, 456, 234, 567, 5, 647, 467};
         
         for (int i = 0; i < stars.length; i++)
            stars[i] = new Star (xCord[i], yCord[i]);
            
         for (int i = starNum/4; i < (starNum/4)*2; i++)
            stars[i].star = new ImageIcon ("Images/Misc/gold.png");
          
         for (int i = 2*(starNum/4); i < (starNum/4)*3; i++)
            stars[i].star = new ImageIcon ("Images/Misc/green.png");
         
         for (int i = 3*(starNum/4); i < starNum; i++)
            stars[i].star = new ImageIcon ("Images/Misc/red.png");
      }
      
      else if (state == 3)
      {
         image = new ImageIcon ("Images/IntroScreen/3.png");
         title = new ImageIcon ("Images/Titles/introScreen1/Logo2.png");
         play = new Button (18);
         instructions = new Button (2);
         settings = new Button (3);
         highScore = new Button (4);
         
         int flakeNum = 12;
         flakes = new SnowFlake [flakeNum];
         int [] xCords = {472, 795, 475, 310, 255, 577, 651, 853, 110, 40, 140, 9};
         int [] yCords = {260, 547,640, 512, 53, 172, 347, 18, 356, 535, 180, 7};
      
         for (int i = 0; i < flakes.length; i++)
         {
            flakes[i] = new SnowFlake (xCords[i], yCords[i]);
         }
         
         flakes [0].speed = 2;
         flakes [5].speed = 2;
         flakes [6].speed = 3;
         flakes [10].speed = 2;
         flakes [9].speed = 3;
      }
      else if (state == 4)
      {
         image = new ImageIcon ("Images/IntroScreen/4.png");
         title = new ImageIcon ("Images/Titles/introScreen1/Logo.png");
         play = new Button (18);
         instructions = new Button (2);
         settings = new Button (3);
         highScore = new Button (4);
      }
   }
   
   /*** displayIntroScreen *******************************
   * Purpose: displays the intro screen                  *
   * Parameters: Graphics object                         *
   * Returns: none                                       *
   ******************************************************/
   public void displayIntroScreen(Graphics g1)
   {
      counter++;
      
      g1.drawString ("", 0, 0);
      g1.drawImage (image.getImage(), 0, 0, null);
      if (state == 3)
      {
         for (int i = 0; i < flakes.length/2; i++)
         {
            flakes[i].displayFlake(g1);
            flakes[i].fall();
            if (flakes[i].y >= 700)
               flakes[i].y =0;
         }
      }
      
      if (state == 2)
      { 
         for (int i = 0; i < stars.length; i++)
            stars[i].displayStar(g1);
            
         g1.drawImage (moon.getImage(), moonx, moony, null);
         
         moonCounter++;
         
         if (moonCounter % 2 == 0)
         {
            moonx++; 
            moony--;
         }
         if (moonx >= 900)
            moonx = 0;
         if (moony <= -200)
            moony = 700;
      }
   
      g1.drawImage (title.getImage(), 195, 56, null);
      play.displayButton (g1);
      instructions.displayButton (g1);
      settings.displayButton (g1);
      highScore.displayButton (g1); 
      
      if (state == 1||state == 4)
      {
         dc.displayDisplayCharacter (g1);
         dc2.displayDisplayCharacter (g1);
         dc3.displayDisplayCharacter (g1);
         dc4.displayDisplayCharacter (g1);
         
         dc.x++;
         dc2.x++;
         dc3.x++;
         dc4.x++;
      
         dc.updateCord();
         
         dc2.updateCord(1, dcy, dcy+40);
         dc3.updateCord(1, dcy, dcy+40);
         dc4.updateCord(1, dcy, dcy+40);
         
         if (counter % 4 ==0)
         {
            dc.updateSprite();
            dc2.updateSprite();
            dc3.updateSprite();
            dc4.updateSprite();
         }
         
         if (dc4.x == 900)
         {
            dc.x = dcx;
            dc2.x = dcx - 70;
            dc3.x = dcx - 140;
            dc4.x = dcx - 210;
         }
      } 
      
      if (state == 3)
      {
         for (int i = flakes.length/2; i < flakes.length; i++)
         {
            flakes[i].displayFlake(g1);
            flakes[i].fall();
            if (flakes[i].y >= 700)
               flakes[i].y = 0;
         }
      }
      
   }
   
   /*** updateIntroScreen1 *******************************
   * Purpose: updates the buttons on the screen          *
   * Parameters: MouseEvent                              *
   * Returns: none                                       *
   ******************************************************/
   public void updateIntroScreen1(MouseEvent e)
   {
      clickedX = e.getX();
      clickedY = e.getY();
      
      if ((e.getX()>= play.x && e.getX()<= (play.x+play.width)) && (e.getY() >= play.y && e.getY() <= (play.y +play.height)))
      {
         play.pressed();
      }
      else if ((e.getX()>= instructions.x && e.getX()<= (instructions.x+instructions.width)) && (e.getY() >= instructions.y && e.getY() <= (instructions.y +instructions.height)))
      {
         instructions.pressed();
      }
      else if ((e.getX()>= settings.x && e.getX()<= (settings.x+settings.width)) && (e.getY() >= settings.y && e.getY() <= (settings.y +settings.height)))
      {
         settings.pressed();
      }
      else if ((e.getX()>= highScore.x && e.getX()<= (highScore.x+highScore.width)) && (e.getY() >= highScore.y && e.getY() <= (highScore.y +highScore.height)))
      {
         highScore.pressed();
      }
   }
      
   /*** updateIntroScreen2 *******************************
   * Purpose: updates the buttons on the screen          *
   * Parameters: MouseEvent                              *
   * Returns: none                                       *
   ******************************************************/
   public void updateIntroScreen2(MouseEvent e)
   {
      if ((e.getX()>= play.x && e.getX()<= (play.x+play.width)) && (e.getY() >= play.y && e.getY() <= (play.y +play.height)))
      {
         if ((clickedX>= play.x && clickedX<= (play.x+play.width)) && (clickedY >= play.y && clickedY <= (play.y +play.height)))
         {
            playPressed = true;
            instructionsPressed = false;
            settingsPressed = false;
            highScorePressed = false;
         }
      }
      else if ((e.getX()>= instructions.x && e.getX()<= (instructions.x+instructions.width)) && (e.getY() >= instructions.y && e.getY() <= (instructions.y +instructions.height)))
      {
         if ((clickedX>= instructions.x && clickedX <= (instructions.x+instructions.width)) && (clickedY >= instructions.y && clickedY <= (instructions.y +instructions.height)))
         {
            playPressed = false;
            instructionsPressed = true;
            settingsPressed = false;
            highScorePressed = false;
         }
      }
      else if ((e.getX()>= settings.x && e.getX()<= (settings.x+settings.width)) && (e.getY() >= settings.y && e.getY() <= (settings.y +settings.height)))
      {
         if ((clickedX>= settings.x && clickedX<= (settings.x+settings.width)) && (clickedY >= settings.y && clickedY <= (settings.y +settings.height)))
         {
            playPressed = false;
            instructionsPressed = false;
            settingsPressed = true;
            highScorePressed = false;
         }
      }
      else if ((e.getX()>= highScore.x && e.getX()<= (highScore.x+highScore.width)) && (e.getY() >= highScore.y && e.getY() <= (highScore.y +highScore.height)))
      {
         if ((clickedX>= highScore.x && clickedX<= (highScore.x+highScore.width)) && (clickedY >= highScore.y && clickedY <= (highScore.y +highScore.height)))
         {
            playPressed = false;
            instructionsPressed = false;
            settingsPressed = false;
            highScorePressed = true;
         }
      } 
      
      play.released(); 
      instructions.released();
      settings.released(); 
      highScore.released();
   } 
}

/*****************************************************************
* Class Purpose: To make snowflakes for one of the intro screens *
******************************************************************/
class SnowFlake
{
   ImageIcon flake = new ImageIcon ("Images/Misc/snowflake.png");
   int x, y;
   int speed = 1;
   
   
   /*** SnowFlake ****************************************
   * Purpose: (Constructor) initializes the instances    *                                     
   * Parameters: x coordinate, y coordinate              *
   * Returns: none                                       *
   ******************************************************/
   public SnowFlake (int x, int y)
   {
      this.x = x;
      this.y = y;
   }
   
   /*** displayFlake *************************************
   * Purpose: displays the snow flake                    *
   * Parameters: Graphics object                         *
   * Returns: none                                       *
   ******************************************************/
   public void displayFlake(Graphics g)
   {
      g.drawImage(flake.getImage(), x, y, null);
   }
   
   /*** fall *********************************************
   * Purpose: makes the snow fall                        *
   * Parameters: none                                    *
   * Returns: none                                       *
   ******************************************************/
   public void fall ()
   {
      y+=speed;
   }
}

/************************************************************
* Class Purpose: To make stars for one of the intro screens *
************************************************************/
class Star
{
   ImageIcon star = new ImageIcon ("Images/Misc/snowflake.png");
   int x, y;
   int counter = 0;
   float transparency =0.0f;
   boolean goUp = true;
   
   /*** Star *********************************************
   * Purpose: )Constructor) initializes the instances    *
   * Parameters: x - coordinate, y - cooridinate         *
   * Returns: none                                       *
   ******************************************************/
   public Star(int x, int y)
   {
      this.x = x;
      this.y = y;
   }
   
   /*** displayStar *****************
   * Purpose: displays the star     *
   * Parameters: Graphics object    *
   * Returns: none                  *
   *********************************/
   public void displayStar(Graphics g)
   {
      if (goUp)
      {
         if (transparency < 0.9)
            transparency+=0.01;
         else 
            counter++;
      }
      else
      {
         if (transparency > 0.1)
            transparency-=0.01;
         else 
            counter--;
      }
        
      if (counter == 300)
      {
         goUp = false;
         counter = 0;
      } 
      
      if (counter == -300)
      {
         goUp = true;
         counter = 0;
      }
      
      Graphics2D g2 = (Graphics2D)g;
      g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,transparency));
      g2.drawImage(star.getImage(), x, y, 12,12,null);
      g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));     
   }
}



