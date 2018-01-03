/*********************************************************
*  Name: Siddharth Pai, Nik Valcic, Ben Stafl            *
*  Course: ICS 4U1 - 01  Pd. 4                           *
*  Final Assignment (Summative): Flappy World            *
*  Class Purpose: To make a class that deals with the    *
*  instructions screen                                   *
*  Due Date: May 27, 2014                                *
*********************************************************/
import javax.swing.*; 
import java.awt.event.*; 
import java.awt.*; 

class Instructions
{
   int state;
   ImageIcon image;
   Screen1 screen1;
   Screen2 screen2; 
   Button back = new Button (385, 595, 5);
   boolean backPressed;
   int clickedX, clickedY;
   int sprite = 0;
   boolean showMode = true;
   boolean showScreen1 = false;
   boolean showScreen2 = false;
   
   DisplayCharacter bird1 = new DisplayCharacter (120, 230, 165, 150,25, 1);
   DisplayCharacter bird2 = new DisplayCharacter (570, 230, 85, 70,25, 2);
   DisplayCharacter bird3 = new DisplayCharacter (670, 230, 85, 70,25, 3);
   
   /*** Instructions *************************************
   * Purpose: (Constructor) initializes the instances    *
   * Parameters: state                                   *
   * Returns: none                                       *
   ******************************************************/
   public Instructions (int s)
   {
      state = s;
      backPressed = false;
      screen1 = new Screen1 (state);
      screen2 = new Screen2 (state);
      image = new ImageIcon ("Images/Instructions/"+state+"/plain.png");
      bird3.y+=15;
      bird3.goUp = true;
   }
   
   /*** displayInstructions ******************************
   * Purpose: displays the intructions screen            *
   * Parameters: Graphics object                         *
   * Returns: none                                       *
   ******************************************************/
   public void displayInstructions (Graphics g1)
   {
      if (showMode)
      {
         g1.drawImage(image.getImage(), 0, 0, null);
         back.displayButton (g1);
         bird1.displayDisplayCharacter (g1);
         bird2.displayDisplayCharacter (g1);
         bird3.displayDisplayCharacter (g1);
      }
      else if (showScreen1)
      {
         screen1.displayScreen1(g1);
      }
      else if (showScreen2)
      {
         screen2.displayScreen2(g1);
      }
   }
   
   /*** updateInstructions1 ******************************
   * Purpose: updates the instructions screen (buttons)  *
   * Parameters: MouseEvent                              *
   * Returns: none                                       *
   ******************************************************/
   public void updateInstructions1 (MouseEvent e)
   {
      clickedX = e.getX();
      clickedY = e.getY();
      if (showMode)
      {
         if ((e.getX() >= back.x && e.getX() <= (back.x + back.width)) && (e.getY() >= back.y && e.getY() <= (back.y + back.height)))
            back.pressed();
         
         if ((e.getX() >= 512 && e.getX() <= 836) && (e.getY() >= 161 && e.getY() <=520))
         {   
            showScreen1 = false;
            showScreen2 = true;
            showMode = false;
         }
         else if ((e.getX() >= 62 && e.getX() <= 386) && (e.getY() >= 161 && e.getY() <=520))
         {
            showScreen2 = false;
            showScreen1 = true;
            showMode = false;
         }
      }
      else if (showScreen1)
         screen1.updateScreenS(e);
      else if (showScreen2)
         screen2.updateScreenS(e);
   }
   
   /*** updateInstructions2 ******************************
   * Purpose: updates the buttons                        *
   * Parameters: MouseEvent                              *
   * Returns: none                                       *
   ******************************************************/
   public void updateInstructions2 (MouseEvent e)
   {
      if (showMode)
      {
         if ((e.getX() >= back.x && e.getX() <= (back.x + back.width)) && (e.getY() >= back.y && e.getY() <= (back.y + back.height)))
         {
            if ((clickedX >= back.x && clickedX <= (back.x + back.width)) && (clickedY >= back.y && clickedY <= (back.y + back.height)))
               backPressed = true;
            else;
         }   
         back.released(); 
      }
      
      if (showScreen1)
      {
         screen1.updateScreenP(e);
         if (screen1.donePressed)
            backPressed = true;
      }
      
      if (showScreen2)
      {
         screen2.updateScreenP(e);
         if (screen2.donePressed) 
            backPressed = true;
      }
      
      if (backPressed)
      {
         showMode = true;
         showScreen1 = false;
         showScreen2 = false;
         screen1.donePressed = false;
         screen2.donePressed = false;
      }
   }
   
   /*** updateInstructions3 ******************************
   * Purpose: shows the right instructions screen        *
   * Parameters: MouseEvent                              *
   * Returns: none                                       *
   ******************************************************/
   public void updateInstructions3 (MouseEvent e)
   {
      if (showMode)
      {
         if ((e.getX() >= 512 && e.getX() <= 836) && (e.getY() >= 161 && e.getY() <=520))
            image = new ImageIcon ("Images/Instructions/"+state+"/2player.png");
         else if ((e.getX() >= 62 && e.getX() <= 386) && (e.getY() >= 161 && e.getY() <=520))
            image = new ImageIcon ("Images/Instructions/"+state+"/1player.png");
         else 
            image = new ImageIcon ("Images/Instructions/"+state+"/plain.png");
      }
   }
}

/******************************************************
* Class Purpose: To create the 1P instructions screen *
******************************************************/

class Screen1
{  
   int state, clickedX, clickedY; 
   ImageIcon screen, screen2;
   Button done = new Button (398, 615, 21);
   boolean donePressed = false;
   int y = 410;
   DisplayCharacter bird = new DisplayCharacter (-65, y+6, 46, 35, 10, 1);
   Score score = new Score (320);
   
   /*** Screen1 ******************************************
   * Purpose: (Constructor) initializes the instances    *
   * Parameters: state                                   *
   * Returns: none                                       *
   ******************************************************/
   public Screen1 (int n)
   {
      state = n;
      screen = new ImageIcon ("Images/Instructions/"+state+"/screen1.png");
      screen2 = new ImageIcon ("Images/Instructions/"+state+"/screen1t.png");
   }
   
   /*** displayScreen1 ***********************************
   * Purpose: displays the screen                        *
   * Parameters: Graphics object                         *
   * Returns: none                                       *
   ******************************************************/
   public void displayScreen1(Graphics g)
   {
      g.drawImage(screen.getImage(), 0, 0, null);
      bird.displayDisplayCharacter(g);
      g.drawImage(screen2.getImage(), 0, 0, null);
      score.displayScore(g);
      done.displayButton (g);
   }
   
   /*** updateScreenS ************************************
   * Purpose: "presses" the done button on the screen    *
   * Parameters: MouseEvent                              *
   * Returns: none                                       *
   ******************************************************/
   public void updateScreenS(MouseEvent e)
   {
      clickedX = e.getX();
      clickedY = e.getY();
      if ((e.getX() >= done.x && e.getX() <= (done.x + done.width)) && (e.getY() >= done.y && e.getY() <= (done.y + done.height)))
         done.pressed();
   }
   
   /*** updateScreenP ************************************
   * Purpose: "releases" the done button on the screen   *
   * Parameters: MouseEvent                              *
   * Returns: none                                       *
   ******************************************************/
   public void updateScreenP (MouseEvent e)
   {
      if ((e.getX() >= done.x && e.getX() <= (done.x + done.width)) && (e.getY() >= done.y && e.getY() <= (done.y + done.height)))
      {
         if ((clickedX >= done.x && clickedX <= (done.x + done.width)) && (clickedY >= done.y && clickedY <= (done.y + done.height)))
            donePressed = true;
      } 
      done.released();  
   }
}

/******************************************************
* Class Purpose: To create the 2P instructions screen *
******************************************************/
class Screen2
{  
   int state, clickedX, clickedY;
   int x = 424;
   int d = 160;
   int y = 549;
   ImageIcon screen;
	ImageIcon t1  = new ImageIcon ("Images/PUpT/gun.png");
	ImageIcon t2  = new ImageIcon ("Images/PUpT/invincible.png");
	ImageIcon t3  = new ImageIcon ("Images/PUpT/shrink.png");
   Button done = new Button (398, 610, 21);
   boolean donePressed = false;
   PowerUp one = new PowerUp (4, x-d, y);
   PowerUp two = new PowerUp (5, x, y);
   PowerUp three = new PowerUp (6,x+d,y);
   DisplayCharacter bird = new DisplayCharacter (-65, y+6, 46, 35, 10, 4);
   PowerUpsDisplay pDisplay = new PowerUpsDisplay(390, 486);
   LifeDisplay lDisplay = new LifeDisplay (390, 406, 4);
   float trans1 = 1.0f;
   float trans2 = 1.0f;
   float trans3 = 1.0f;
   
   /*** Screen2 ******************************************
   * Purpose: (Constructor) initializes the instances    *
   * Parameters: state                                   *
   * Returns: none                                       *
   ******************************************************/
   public Screen2 (int n)
   {
      state = n;
      screen = new ImageIcon ("Images/Instructions/"+state+"/screen2.png");
      one.y-=10;
      two.y-=5;
      one.moveUp = true;
      two.moveUp = true;
      three.moveUp = false;
      one.c = 5;
      two.c = 5;
      three.c = 5;   
   }
   
   /*** displayScreen2 ***********************************
   * Purpose: displays the screen                        *
   * Parameters: Graphics object                         *
   * Returns: none                                       *
   ******************************************************/
   public void displayScreen2(Graphics g)
   {
      Graphics2D g2 = (Graphics2D)g;
      g.drawImage(screen.getImage(), 0, 0, null);
      done.displayButton (g);
      
      g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,trans1));  
      one.displayPowerUp(g);
		g.drawImage(t1.getImage(), one.x-1, one.y + 45, null);
      g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
      
      g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,trans2));  
      two.displayPowerUp(g);
		g.drawImage(t2.getImage(), two.x-30, two.y + 40, null);
      g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
   
   
      g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,trans3));  
      three.displayPowerUp(g);
		g.drawImage(t3.getImage(), three.x-15, three.y + 45, null);
      g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
   
      
      bird.displayDisplayCharacter(g);
      pDisplay.displayPowerUpsDisplay(g);
      lDisplay.displayLifeDisplay(g);      
   }
   
   /*** updateScreenS ************************************
   * Purpose: "presses" the done button on the screen    *
   * Parameters: MouseEvent                              *
   * Returns: none                                       *
   ******************************************************/
   public void updateScreenS(MouseEvent e)
   {
      clickedX = e.getX();
      clickedY = e.getY();
      if ((e.getX() >= done.x && e.getX() <= (done.x + done.width)) && (e.getY() >= done.y && e.getY() <= (done.y + done.height)))
         done.pressed();
   }
   
   /*** updateScreenP ************************************
   * Purpose: "releases" the done button on the screen   *
   * Parameters: MouseEvent                              *
   * Returns: none                                       *
   ******************************************************/
   public void updateScreenP (MouseEvent e)
   {
      if ((e.getX() >= done.x && e.getX() <= (done.x + done.width)) && (e.getY() >= done.y && e.getY() <= (done.y + done.height)))
      {
         if ((clickedX >= done.x && clickedX <= (done.x + done.width)) && (clickedY >= done.y && clickedY <= (done.y + done.height)))
            donePressed = true;
      } 
      done.released();  
   }
}
