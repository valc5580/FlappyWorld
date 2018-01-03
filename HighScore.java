/*********************************************************
*  Name: Siddharth Pai, Nik Valcic, Ben Stafl            *
*  Course: ICS 4U1 - 01  Pd. 4                           *
*  Final Assignment (Summative): Flappy World            *
*  Class Purpose: To make a class that deals with the    *
*  Highscore screen                                      *
*  Due Date: May 27, 2014                                *
*********************************************************/
import javax.swing.*; 
import java.awt.event.*;
import java.awt.*; 
import java.io.*;
import java.util.*;

class HighScore
{
   int state, x, x2;
   int clickedX, clickedY; 
   ImageIcon image; 
   Button back;
   Font coolFont;
   String [] highScores;
   String [] scores;
   String last; 
   int [] y = {305, 369, 429, 493, 557}; 
   boolean backPressed;
   
   /*** HighScore ***************************
   * Purpose: (Constructor) initializes the * 
   * instances of the class                 *
   * Parameters: state                      *
   * Returns: none                          *
   *****************************************/
   public HighScore (int state)
   {
      this.state = state;
      x = 235;
      x2 = 610;
      highScores = new String [0];
      scores = new String [0];
      backPressed = false;
      
      coolFont = new Font ("Stencil", Font.BOLD, 65);
      
      if (state == 1)
      {
         image = new ImageIcon ("Images/HighScore/HighScore1.png");
         back = new Button (385, 589, 5);
      }
      else if (state == 2)
      {
         image = new ImageIcon ("Images/HighScore/HighScore2.png");
         back = new Button (385, 589, 5);
      }
      else if (state == 3)
      {
         image = new ImageIcon ("Images/HighScore/HighScore3.png");
         back = new Button (385, 589, 5);
      }
      else if (state == 4)
      {
         image = new ImageIcon ("Images/HighScore/HighScore4.png");
         back = new Button (385, 589, 5);
      }
   
      try
      {
         FileReader fr = new FileReader("HighScoreData/highScores.txt");
         BufferedReader br = new BufferedReader(fr);
         String s = br.readLine();
         int i = 0;
         StringTokenizer st;
         
         while (s != null && i <5)
         {
            s = s.trim();
            s = s.toLowerCase();
            
            st = new StringTokenizer (s);
            
            if (i == highScores.length)
            {
               String [] temp = new String [i+1];
               String [] temp2 = new String [i+1];
               for (int j = 0; j <i; j++)
               {
                  temp [j] = highScores[j];
                  temp2[j] = scores[j];
               }
               highScores = temp;
               scores = temp2;
            }
            
            if (st.hasMoreTokens())
            {
               highScores [i] = st.nextToken();
               scores[i] = st.nextToken();
            }
            i++;
            s = br.readLine();  
         }
         br.close();
      } 
      catch(IOException e){}
      
      for (int i = 0; i < scores.length; i ++)
         last = scores[i];
      if (last == null || scores.length < 5)
         last = "0";
   }
   
   /*** displayHighScore *********************************
   * Purpose: Displays the high score screen             *
   * Parameters: Graphics object                         *
   * Returns: none                                       *
   ******************************************************/
   public void displayHighScore(Graphics g1)
   {
      g1.drawImage (image.getImage(), 0, 0, null);
      back.displayButton(g1);
      g1.setFont(coolFont);
      
      for (int i = 0; i < highScores.length; i++)
      {
         g1.drawString (highScores[i], x, y[i]);
         g1.drawString (scores[i], x2, y[i]);
      }
   }
   
   /*** updateHighScore1 *********************************
   * Purpose: updates the high score screen (buttons)    *
   * Parameters: MouseEvent                              *
   * Returns: none                                       *
   ******************************************************/
   public void updateHighScore1(MouseEvent e)
   {  
      clickedX = e.getX();
      clickedY = e.getY();
      if ((e.getX() >= back.x && e.getX() <= (back.x + back.width)) && (e.getY() >= back.y && e.getY() <= (back.y + back.height)))
         back.pressed();
   }
   
   /*** updateHighScore2 *********************************
   * Purpose: to update the high score screen (buttons)  *
   * Parameters: MouseEvent                              *
   * Returns: none                                       *
   ******************************************************/
   public void updateHighScore2(MouseEvent e)
   {
      if ((e.getX() >= back.x && e.getX() <= (back.x + back.width)) && (e.getY() >= back.y && e.getY() <= (back.y + back.height)))
      {  
         if ((clickedX >= back.x && clickedX <= (back.x + back.width)) && (clickedY >= back.y && clickedY <= (back.y + back.height)))
            backPressed = true;
      }  
      
      back.released(); 
   }
}
