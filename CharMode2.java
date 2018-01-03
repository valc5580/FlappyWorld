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

class CharMode2
{
   int x1, y1, x2, y2, state;
   DisplayCharacter current1;
   DisplayCharacter previous1;
   DisplayCharacter current2;
   DisplayCharacter previous2;
   int currentNum1 = 1;
   int currentNum2 = 1;
   ImageIcon screen, screen2;
   DisplayCharacter [] dCharacters1 = new DisplayCharacter [4];
   DisplayCharacter [] dCharacters2 = new DisplayCharacter [4];
   int [] xCords1 = new int [4];
   int [] xCords2 = new int [4];
   int leftLim1, rightLim1, leftLim2, rightLim2;
   
   int clickedX, clickedY;
   
   boolean move1 = false;
   boolean left1 = false;
   boolean select1Pressed = false;
   boolean ready1 = true;
   boolean moveable1 = true;
   boolean move2 = false;
   boolean left2 = false;
   boolean select2Pressed = false;
   boolean ready2 = true;
   boolean moveable2 = true;
   boolean go = false;
   
   boolean s1T = false;
   boolean s2T = false;
   
   Button leftb1 = new Button (75, 335, 15);
   Button rightb1 = new Button (372, 335, 16);
   Button select1 = new Button (157, 496, 17);
   
   Button leftb2 = new Button (492, 335, 15);
   Button rightb2 = new Button (788, 335, 16);
   Button select2 = new Button (575, 496, 19);
 
   int bwidth = 45;
   int bheight = 65;
   int swidth = 180;
   int sheight = 70;
   
   /*** CharMode2 ****************************************
   * Purpose: (Constructor) to initialize the instances  *
   * of the class                                        *
   * Parameters: x coordinate (first bird), y coordinate *
   * (first bird), x coordinate (second bird), y         * 
   * coordinate (second bird), state                     *
   * Returns: none                                       *
   ******************************************************/
   public CharMode2(int x, int y,int x2, int y2, int s)
   {
      state = s;
      
      leftb1.width = bwidth;
      leftb1.height = bheight;
      rightb1.width = bwidth;
      rightb1.height = bheight;
      select1.width = swidth;
      select1.height = sheight;
      
      leftb2.width = bwidth;
      leftb2.height = bheight;
      rightb2.width = bwidth;
      rightb2.height = bheight;
      select2.width = swidth;
      select2.height = sheight;
      
      if (state == 1)
      {
         screen = new ImageIcon ("Images/CharSelect/1/2P/1.png");
         screen2 = new ImageIcon ("Images/CharSelect/1/2P/2.png");
      }
      
      else if (state == 2)
      {
         screen = new ImageIcon ("Images/CharSelect/2/2P/1.png");
         screen2 = new ImageIcon ("Images/CharSelect/2/2P/2.png");
      }
      
      else if (state == 3)
      {
         screen = new ImageIcon ("Images/CharSelect/3/2P/1.png");
         screen2 = new ImageIcon ("Images/CharSelect/3/2P/2.png");
      }
      
      else if (state == 4)
      {
         screen = new ImageIcon ("Images/CharSelect/4/2P/1.png");
         screen2 = new ImageIcon ("Images/CharSelect/4/2P/2.png");
      }
      
      this.x1 = x;
      this.y1 = y;
      this.x2 = x2;
      this.y2 = y2;
            
      for (int i = 0; i < xCords1.length; i++)
         if (i ==0)
            xCords1[i] = x1;
         else
            xCords1[i] = x1 + 208;
            
      for (int i = 0; i < xCords2.length; i++)
         if (i ==0)
            xCords2[i] = x2;
         else
            xCords2[i] = x2 + 208;
            
      leftLim1 = x1-208;
      rightLim1 = x1+208;
      
      leftLim2 = x2-208;
      rightLim2 = x2+208;
      
      for (int i= 0; i < dCharacters1.length; i++)
         dCharacters1[i] = new DisplayCharacter (xCords1[i], y1, 122, 94, 30, i+1);
      
      for (int i= 0; i < dCharacters2.length; i++)
         dCharacters2[i] = new DisplayCharacter (xCords2[i], y2, 122, 94, 30, i+1);
        
      for (int i = 0; i < dCharacters2.length; i++)
         dCharacters2[i].goUp = true;
   }

   /*** displayCharMode2 *********************************
   * Purpose: To display the character choose screen     *
   * Parameters: Graphics object                         *
   * Returns: none                                       *
   ******************************************************/
   public void displayCharMode2(Graphics g)
   {
      g.drawImage(screen.getImage(), 0, 0, null);
      
      for (int i =0; i < dCharacters1.length; i++)
         dCharacters1[i].displayDisplayCharacter (g);
      for (int i =0; i < dCharacters2.length; i++)
         dCharacters2[i].displayDisplayCharacter (g);
      
      g.drawImage(screen2.getImage(), 0, 0, null);
      
      leftb1.displayButton(g, bwidth, bheight);
      rightb1.displayButton(g, bwidth, bheight);
      select1.displayButton(g, swidth, sheight);
      leftb2.displayButton(g, bwidth, bheight);
      rightb2.displayButton(g, bwidth, bheight);
      select2.displayButton(g, swidth, sheight);
   }
   
   /*** updateStuff **************************************
   * Purpose: To update the coordinates of the birds     *
   * Parameters: none                                    *
   * Returns: none                                       *
   ******************************************************/
   public void updateStuff()
   {
      for (int i = 0; i < dCharacters1.length;i++)
      {
         dCharacters1[i].updateCord();
      }
      for (int i = 0; i < dCharacters2.length;i++)
      {
         dCharacters2[i].updateCord();
      }
   }
   
   /*** updateStuff2 *************************************
   * Purpose: To make the birds flap their wings         *
   * Parameters: none                                    *
   * Returns: none                                       *
   ******************************************************/
   public void updateStuff2()
   {
      for (int i = 0; i < dCharacters1.length;i++)
      {
         dCharacters1[i].updateSprite();
      }
      for (int i = 0; i < dCharacters2.length;i++)
      {
         dCharacters2[i].updateSprite();
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
         if (ready1)
         {
            if (moveable1)
            {
               move1 = true;
               left1 = true; 
            }
         }
      }
      
      if (e.getKeyCode() == 39)
      {
         if (ready1)
         {
            if (moveable1)
            {
               move1 = true;
               left1 = false;
            }
         }
      }
      
      if (e.getKeyCode () == 65)
      {
         if (ready2)
         {
            if (moveable2)
            {
               move2 = true;
               left2 = true;
            }
         }
      }
      
      if (e.getKeyCode () == 68)
      {  
         if (ready2)
         {
            if (moveable2)
            {
               move2 = true;
               left2 = false;
            }
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
      if (!s1T)
      {
         if ((e.getX() >= select1.x && e.getX() <= select1.x+select1.width)&&(e.getY()>=select1.y && e.getY()<= select1.y+select1.height))
         {
            if (ready1)
            {
               select1.pressed();
               clickedX = e.getX();
               clickedY = e.getY();
            }
         }
      }
      if(!s2T)
      {
         if ((e.getX() >= select2.x && e.getX() <= select2.x+select2.width)&&(e.getY()>=select2.y && e.getY()<= select2.y+select2.height))
         {
            if (ready2)
            {
               select2.pressed();
               clickedX = e.getX();
               clickedY = e.getY();
            }
         }
      }
      
      if ((e.getX() >= leftb1.x && e.getX() <= leftb1.x+leftb1.width)&&(e.getY()>=leftb1.y && e.getY()<= leftb1.y+leftb1.height))
      {
         if (ready1)
         {
            if (moveable1)
            {
               move1 = true;
               left1 = true;
            }
         }
      }
      else if ((e.getX() >= rightb1.x && e.getX() <= rightb1.x+rightb1.width)&&(e.getY()>=rightb1.y && e.getY()<= rightb1.y+rightb1.height))
      {
         if (ready1)
         {
            if (moveable1)
            {
               move1 = true;
               left1 = false;
            }
         }
      }
      else if ((e.getX() >= leftb2.x && e.getX() <= leftb2.x+leftb2.width)&&(e.getY()>=leftb2.y && e.getY()<= leftb2.y+leftb2.height))
      {
         if (ready2)
         {
            if (moveable2)
            {
               move2 = true;
               left2 = true;
            }
         }
      }
      else if ((e.getX() >= rightb2.x && e.getX() <= rightb2.x+rightb2.width)&&(e.getY()>=rightb2.y && e.getY()<= rightb2.y+rightb2.height))
      {
         if (ready2)
         {
            if (moveable2)
            {
               move2 = true;
               left2 = false;
            }
         }
      }
   }
   
   /*** updateStuff4 *******************
   * Purpose: To "release" the button  * 
   * and make it transparent           *
   * once the mouse is released        *
   * Parameters: none                  *
   * Returns: none                     *
   ************************************/
   public void updateStuff4 (MouseEvent e)
   {
      if ((e.getX() >= select1.x && e.getX() <= select1.x+select1.width)&&(e.getY()>=select1.y && e.getY()<= select1.y+select1.height))
         if (ready1)
            if ((clickedX >= select1.x && clickedX <= select1.x+select1.width)&&(clickedY>=select1.y && clickedY<= select1.y+select1.height))
               select1Pressed = true;
      
      if ((e.getX() >= select2.x && e.getX() <= select2.x+select2.width)&&(e.getY()>=select2.y && e.getY()<= select2.y+select2.height))
         if (ready2)
            if ((clickedX >= select2.x && clickedX <= select2.x+select2.width)&&(clickedY>=select2.y && clickedY<= select2.y+select2.height))
               select2Pressed = true;
      
      if (!s1T)   
         select1.released();
      if (!s2T)
         select2.released();
      
      if (select1Pressed)
      {
         s1T = true;
         leftb1.setTransparent();
         rightb1.setTransparent();
         select1.setTransparent();
         moveable1 = false;
      }
      
      if (select2Pressed)
      {
         s2T = true;
         leftb2.setTransparent();
         rightb2.setTransparent();
         select2.setTransparent();
         moveable2 = false;
      }
   }
   
   /*** threadStuff *********************************************
   * Purpose: checks certain conditions and reassigns variables *
   * Parameters: none                                           *
   * Returns: none                                              *
   *************************************************************/
   public void threadStuff()
   {
      if (select1Pressed && select2Pressed)
      {
         try{
            Thread.sleep (250);
         }
         catch (Exception e){}
         go = true;
      }
         
      if (move1)
      {
         if (!left1)
         {
            if (currentNum1 <=dCharacters1.length-1)
            {
               ready1 = false;
               rightb1.pressed();
               dCharacters1[currentNum1-1].x = xCords1[currentNum1-1]--;
               dCharacters1[currentNum1].x = xCords1[currentNum1]--;
               if (xCords1[currentNum1] == x1)
               {
                  ready1= true;
                  move1 = false;
                  left1 = false;
                  currentNum1 ++;
                  rightb1.released();
               }
            }
         }
         else
         {
            if (currentNum1 >=2)
            {
               ready1 = false;
               leftb1.pressed();
               dCharacters1[currentNum1-1].x = xCords1[currentNum1-1]++;
               dCharacters1[currentNum1-2].x = xCords1[currentNum1-2]++;
               
               if (xCords1[currentNum1-2] == x1)
               {
                  ready1 = true;
                  move1 = false;
                  left1 = false;
                  currentNum1 --;
                  leftb1.released();
               }
            }
         }
      }
         
      if (move2)
      {
         if (!left2)
         {
            if (currentNum2 <=dCharacters2.length-1)
            {
               ready2 = false;
               rightb2.pressed();
               dCharacters2[currentNum2-1].x = xCords2[currentNum2-1]--;
               dCharacters2[currentNum2].x = xCords2[currentNum2]--;
               if (xCords2[currentNum2] == x2)
               {
                  ready2 = true;
                  move2 = false;
                  left2 = false;
                  currentNum2 ++;
                  rightb2.released();
               }
            }
         }
         else
         {
            if (currentNum2 >=2)
            {
               ready2 = false;
               leftb2.pressed();
               dCharacters2[currentNum2-1].x = xCords2[currentNum2-1]++;
               dCharacters2[currentNum2-2].x = xCords2[currentNum2-2]++;
               
               if (xCords2[currentNum2-2] == x2)
               {
                  ready2 = true;
                  move2 = false;
                  left2 = false;
                  currentNum2 --;
                  leftb2.released();
               }
            }
         }
      }
   }
}