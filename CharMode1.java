/*********************************************************
*  Name: Siddharth Pai, Nik Valcic, Ben Stafl            *
*  Course: ICS 4U1 - 01  Pd. 4                           *
*  Final Assignment (Summative): Flappy World            *
*  Class Purpose: To make a class that deals with the    *
*  character choose screen                               *
*  Due Date: May 27, 2014                                *
*********************************************************/

import java.awt.event.*; 
import java.awt.*; 
import javax.swing.*; 

class CharMode1 
{
   int x, y, state;
   DisplayCharacter current;
   DisplayCharacter previous;
   int currentNum = 1;
   ImageIcon screen, screen2;
   DisplayCharacter [] dCharacters = new DisplayCharacter [4];
   int [] xCords = new int [4];
   int leftLim, rightLim;
   
   int clickedX, clickedY;
   
   boolean move = false;
   boolean left = false;
   boolean selectPressed = false;
   boolean ready = true;
   
   Button leftb = new Button (180, 300, 15);
   Button rightb = new Button (625, 300, 16);
   Button select = new Button (330, 530, 20);
   
   /*** CharMode1 ****************************************
   * Purpose: (Constructor) to initialize the instances  *
   * of the class                                        *
   * Parameters: x coordinate, y coordinate, state       *
   * Returns: none                                       *
   ******************************************************/
   public CharMode1(int x, int y, int s)
   {
      state = s;
      
      if (state == 1)
      {
         screen = new ImageIcon ("Images/CharSelect/1/1P/1.png");
         screen2 = new ImageIcon ("Images/CharSelect/1/1P/2.png");
      }
      
      else if (state == 2)
      {
         screen = new ImageIcon ("Images/CharSelect/2/1P/1.png");
         screen2 = new ImageIcon ("Images/CharSelect/2/1P/2.png");
      }
      else if (state == 3)
      {
         screen = new ImageIcon ("Images/CharSelect/3/1P/1.png");
         screen2 = new ImageIcon ("Images/CharSelect/3/1P/2.png");
      }
      else if (state == 4)
      {
         screen = new ImageIcon ("Images/CharSelect/4/1P/1.png");
         screen2 = new ImageIcon ("Images/CharSelect/4/1P/2.png");
      }
   
      this.x = x;
      this.y = y;
      
      for (int i = 0; i < xCords.length; i++)
         if (i ==0)
            xCords[i] = x;
         else
            xCords[i] = x + 290;
            
      leftLim = x-470;
      rightLim = x+470;
      
      for (int i= 0; i < dCharacters.length; i++)
         dCharacters[i] = new DisplayCharacter (xCords[i], y, 180, 138, 30, i+1);
   }
   
   
   /*** displayCharMode1 *********************************
   * Purpose: To display the character choose screen     *
   * Parameters: Graphics object                         *
   * Returns: none                                       *
   ******************************************************/
   public void displayCharMode1(Graphics g)
   {
      g.drawImage(screen.getImage(), 0, 0, null);
      
      for (int i =0; i < dCharacters.length; i++)
         dCharacters[i].displayDisplayCharacter (g);
      
      g.drawImage(screen2.getImage(), 0, 0, null);
      leftb.displayButton(g);
      rightb.displayButton(g);
      select.displayButton(g);
   }
   
   /*** updateStuff **************************************
   * Purpose: To update the coordinates of the birds     *
   * Parameters: none                                    *
   * Returns: none                                       *
   ******************************************************/
   public void updateStuff()
   {
      for (int i = 0; i < dCharacters.length;i++)
      {
         dCharacters[i].updateCord();
      }
   }
   
   /*** updateStuff2 *************************************
   * Purpose: To make the birds flap their wings         *
   * Parameters: none                                    *
   * Returns: none                                       *
   ******************************************************/
   public void updateStuff2()
   {
      for (int i = 0; i < dCharacters.length;i++)
      {
         dCharacters[i].updateSprite();
      }
   }
   
   /*** pressed ******************************************
   * Purpose: To scroll through the different birds when * 
   * the correct key is pressed                          *
   * Parameters: KeyEvent object                         *
   * Returns: none                                       *
   ******************************************************/
   public void pressed(KeyEvent e)
   {
      if (e.getKeyCode() == 37)
      {
         if (ready)
         {
            move = true;
            left = true;
         }   
      }
      else if (e.getKeyCode() == 39)
      {
         if (ready)
         {
            move = true;
            left = false;
         }
      }
   }
   
   /*** updateStuff3 *************************************
   * Purpose: To determine if the mouse clicked one of   *
   * the buttons                                         *
   * Parameters: MouseEvent object                       *
   * Returns: none                                       *
   ******************************************************/
   public void updateStuff3 (MouseEvent e)
   {
      if ((e.getX() >= select.x && e.getX() <= select.x+select.width)&&(e.getY()>=select.y && e.getY()<= select.y+select.height))
      {
         if (ready)
         {
            select.pressed();
            clickedX = e.getX();
            clickedY = e.getY();
         }
      }
      else if ((e.getX() >= leftb.x && e.getX() <= leftb.x+leftb.width)&&(e.getY()>=leftb.y && e.getY()<= leftb.y+leftb.height))
      {
         if (ready)
         {
            move = true;
            left = true;
         }
      }
      else if ((e.getX() >= rightb.x && e.getX() <= rightb.x+rightb.width)&&(e.getY()>=rightb.y && e.getY()<= rightb.y+rightb.height))
      {
         if (ready)
         {
            move = true;
            left = false;
         }
      }
   }
   
   /*** updateStuff4 *******************
   * Purpose: To "release" the button  *
   * once the mouse is released        *
   * Parameters: none                  *
   * Returns: none                     *
   ************************************/
   public void updateStuff4 (MouseEvent e)
   {
      if ((e.getX() >= select.x && e.getX() <= select.x+select.width)&&(e.getY()>=select.y && e.getY()<= select.y+select.height))
         if (ready)
            if ((clickedX >= select.x && clickedX <= select.x+select.width)&&(clickedY>=select.y && clickedY<= select.y+select.height))
               selectPressed = true;
      select.released();
   }
   
   /*** threadStuff *********************************************
   * Purpose: checks certain conditions and reassigns variables *
   * Parameters: none                                           *
   * Returns: none                                              *
   *************************************************************/
   public void threadStuff()
   {
      if (move)
      {
         if (!left)
         {
            if (currentNum <=dCharacters.length-1)
            {
               ready = false;
               rightb.pressed();
               dCharacters[currentNum-1].x = xCords[currentNum-1]--;
               dCharacters[currentNum].x = xCords[currentNum]--;
               if (xCords[currentNum] == x)
               {
                  ready = true;
                  move = false;
                  left = false;
                  currentNum ++;
                  rightb.released();
               }
            }
         }
         else
         {
            if (currentNum >=2)
            {
               ready = false;
               leftb.pressed();
               dCharacters[currentNum-1].x = xCords[currentNum-1]++;
               dCharacters[currentNum-2].x = xCords[currentNum-2]++;
               
               if (xCords[currentNum-2] == x)
               {
                  ready = true;
                  move = false;
                  left = false;
                  currentNum --;
                  leftb.released();
               }
            }
         }
      }
   }
}