/*********************************************************
*  Name: Siddharth Pai, Nik Valcic, Ben Stafl            *
*  Course: ICS 4U1 - 01  Pd. 4                           *
*  Final Assignment (Summative): Flappy World            *
*  Class Purpose: To make a class that deals with the    *
*  characters outside the actual game (introgame)        *
*  Due Date: May 27, 2014                                *
*********************************************************/
import java.awt.*; 
import javax.swing.*; 
import java.awt.event.*; 

class DisplayCharacter 
{
   int state; 
   ImageIcon image, image1, image2, image3; 
   int x,y, width, height, sprite, initY, upperLimit;
   boolean goUp;
   
   /*** DisplayCharacter *********************************
   * Purpose: (Constructor) to initialize the instances  *
   * of the class                                        *
   * Parameters: xcoordinate, y coordinate, width, height*
   * upper limit, state                                  *
   * Returns: none                                       *
   ******************************************************/
   public DisplayCharacter (int xCord, int yCord, int w, int h, int uL, int s)
   {
      state = s; 
      x = xCord; 
      y = initY = yCord; 
      width = w; 
      height = h;
      sprite = 1;
      upperLimit = uL;
      goUp = false;
      
      if (state == 1)
      {
         image1 = new ImageIcon ("Images/Characters/1/1.png");
         image2 = new ImageIcon ("Images/Characters/1/2.png");
         image3 = new ImageIcon ("Images/Characters/1/3.png");
         image = image1;
      }
      
      else if (state == 2)
      {
         image1 = new ImageIcon ("Images/Characters/2/1.png");
         image2 = new ImageIcon ("Images/Characters/2/2.png");
         image3 = new ImageIcon ("Images/Characters/2/3.png");
         image = image1;
      }
      
      else if (state == 3)
      {image1 = new ImageIcon ("Images/Characters/3/1.png");
         image2 = new ImageIcon ("Images/Characters/3/2.png");
         image3 = new ImageIcon ("Images/Characters/3/3.png");
         image = image1;
      }
      
      else if (state == 4)
      {
         image1 = new ImageIcon ("Images/Characters/4/1.png");
         image2 = new ImageIcon ("Images/Characters/4/2.png");
         image3 = new ImageIcon ("Images/Characters/4/3.png");
         image = image1;
      }
   }
   
   /*** displayDisplayCharacter **************************
   * Purpose: to display the bird                        *
   * Parameters: Graphics g                              *
   * Returns: none                                       *
   ******************************************************/
   public void displayDisplayCharacter (Graphics g)
   {
      if (sprite == 1)
         image = image1;
      else if (sprite == 2)
         image = image2;
      else if (sprite == 3)
         image = image3;
      else if (sprite == 4)
         image = image2;
                     
      g.drawImage (image.getImage(), x, y, width, height, null);  
   }
   
   /*** updateSprite ***************************
   * Purpose: To make the bird flap its wings  *
   * Parameters: none                          *
   * Returns: none                             *
   ********************************************/
   public void updateSprite ()
   {
      if (sprite == 4)
         sprite = 0;
      sprite++;
   }
   
   /*** updateCord ***************************************
   * Purpose: To make the bird go up and down            *
   * Parameters: none                                    *
   * Returns: none                                       *
   ******************************************************/
   public void updateCord ()
   {
      if (y == initY + upperLimit)
         goUp = true;
      else if (y == initY - 15)
         goUp = false;
      
      if (goUp)
         y--;
      else
         y++;
   }
   
   /*** updateCord ***************************************
   * Purpose: To make the bird go up and down to certain * 
   * coordinates                                         *
   * Parameters: increment, upper limit coordinate,      *
   * lower limit coordinate                              *
   * Returns: none                                       *
   ******************************************************/
   public void updateCord (int n, int cord1, int cord2)
   {
      if (y == cord2)
         goUp = true;
      else if (y == cord1)
         goUp = false;
      
      if (goUp)
         y-=n;
      else
         y+=n;
   }  
}