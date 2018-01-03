/*********************************************************
*  Name: Siddharth Pai, Nik Valcic, Ben Stafl            *
*  Course: ICS 4U1 - 01  Pd. 4                           *
*  Final Assignment (Summative): Flappy World            *
*  Class Purpose: To make a class that deals with the    *
*  in-game backgrounds                                   *
*  Due Date: May 27, 2014                                *
*********************************************************/
import java.awt.event.*; 
import javax.swing.*; 
import java.awt.*; 

class InGameBackground
{
   int state; 
   int sprite; 
   ImageIcon image; 
   Button pause = new Button (820, 625, 10);
   boolean showPause;
   
   /*** InGameBackground *********************************
   * Purpose: (Constructor) initializes the instances    *
   * Parameters: state                                   *
   * Returns: none                                       *
   ******************************************************/
   public InGameBackground (int s)
   {
      showPause = true;
      state = s;
      sprite = 1;
      
      if (state == 1)
         image = new ImageIcon ("Images/Backgrounds/1/1.png");
      else if (state == 2)
         image = new ImageIcon ("Images/Backgrounds/2/1.png");
      else if (state == 3)
         image = new ImageIcon ("Images/Backgrounds/3/1.png");
      else if (state == 4)
         image = new ImageIcon ("Images/Backgrounds/4/1.png");  
   } 
   
   /*** displayInGameBackground **************************
   * Purpose: displays the background                    *
   * Parameters: Graphics object                         *
   * Returns: none                                       *
   ******************************************************/
   public void displayInGameBackground(Graphics g)
   {
      g.drawImage (image.getImage(), 0, 0, null);
      if (showPause)
         pause.displayButton (g);
   }
   
   /*** updateSprite *************************************
   * Purpose: changes the background picture             *
   * Parameters: none                                    *
   * Returns: none                                       *
   ******************************************************/
   public void updateSprite ()
   {
      if (sprite == 36)
         sprite = 0;
     
      sprite++;
      
      image = new ImageIcon ("Images/Backgrounds/"+state+"/"+ sprite+".png");      
   }
   
   /*** updateBackground *********************************
   * Purpose: "presses" the pause button                 *
   * Parameters: MouseEvent                              *
   * Returns: none                                       *
   ******************************************************/
   public void updateBackground (MouseEvent e)
   {
      if (showPause)
      {
         if ((e.getX()>=pause.x && e.getX() <=pause.x+pause.width)&&(e.getY()>=pause.y && e.getY() <= pause.y+pause.height))
            pause.pressed();
      }
   }
   
   /*** updateBackground *********************************
   * Purpose: Changes the picture of the pause button    *
   * Parameters: KeyEvent                                *
   * Returns: none                                       *
   ******************************************************/
   public void updateBackground (KeyEvent e)
   {
      if (showPause)
      {
         if (e.getKeyCode() == 32)
            pause.pressed(); 
      }
   }
   
   /*** init *********************************************
   * Purpose: "releases" the pause button                *
   * Parameters: none                                    *
   * Returns: none                                       *
   ******************************************************/
   public void updateBackground2()
   {
      if (showPause)
      {
         pause.released();
      }
   }
   
   /*** init *********************************************
   * Purpose: reassigns "showPause"                      *
   * Parameters: boolean - show pause or not             *
   * Returns: none                                       *
   ******************************************************/
   public void setPause (boolean v)
   {
      showPause = v;
   }
}