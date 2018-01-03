/*********************************************************
*  Name: Siddharth Pai, Nik Valcic, Ben Stafl            *
*  Course: ICS 4U1 - 01  Pd. 4                           *
*  Final Assignment (Summative): Flappy World            *
*  Class Purpose: To make a class that deals with the    *
*  Highscore screen (in game)                            *
*  Due Date: May 27, 2014                                *
*********************************************************/
import java.awt.*; 
import java.awt.event.*; 
import javax.swing.*; 
import java.io.*;
import java.util.*;

class HighScoreScreen
{
   ImageIcon screen = new ImageIcon ("Images/HighScoreScreen/screen.png");
   OurFont text; 
   Button submit = new Button (14);
   
   int clickedX, clickedY;
   
   int x, y, width, height, finalY, score, finalX, place;
   
   boolean submitted = false;
   boolean exit = false;
   
   String file = "HighScoreData/highScores.txt";
   String [] highScores;
   String [] scores;
   
   /*** HighScoreScreen ************************************
   * Purpose: (Constructor) to initialize the instances of * 
   * the class                                             *
   * Parameters: state                                     *
   * Returns: none                                         *
   ********************************************************/
   public HighScoreScreen (int s)
   {
      width = 545;
      height = 385;
      x = (900/2)- (width/2);
      finalY = 170;
      finalX = -900;
      y = 700;
      submit = new Button ((2*x+width)/2 - 100, y+280, 14);
      score = s;
      text = new OurFont (x, y,score);
      
      highScores = new String [0];
      scores = new String [0];
      
      try
      {
         FileReader fr = new FileReader("HighScoreData/highScores.txt");
         BufferedReader br = new BufferedReader(fr);
         String f = br.readLine();
         int i = 0;
         StringTokenizer st;
         
         while (f != null && i <5)
         {
            f = f.trim();
            f = f.toLowerCase();
            
            st = new StringTokenizer (f);
            
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
            f = br.readLine();  
         }
         br.close();
      } 
      catch(IOException e){}
      
      for (place = 0; place <scores.length && !exit; place++)
      {
         if (scores.length > 0)
         {
            if (score > Integer.parseInt(scores[place]))
               exit = true;
         }
      }
      
      
      if (place == 0)
         place = 1;
   }
   
   /*** displayHighScoreScreen ***************************
   * Purpose: to display the high score screen           *
   * Parameters: Graphics                                *
   * Returns: none                                       *
   ******************************************************/
   public void displayHighScoreScreen(Graphics g)
   {
      g.drawImage (screen.getImage(), x, y, null);
      text.displayOurFont (g);
      submit.displayButton(g);
   }
   
   /*** updateCord ***************************************
   * Purpose: updates the y coordinate of the screen     *
   * Parameters: none                                    *
   * Returns: none                                       *
   ******************************************************/
   public void updateCord()
   {
      if (y >finalY)
      {
         y-=10;  
         submit = new Button ((2*x+width)/2 - 100, y+280, 14);
         text = new OurFont (x, y, score);
      }
   }
   
   /*** updateCord2 **************************************
   * Purpose: updates the x coordinate of the screen     *
   * Parameters: none                                    *
   * Returns: none                                       *
   ******************************************************/
   public void updateCord2()
   {
      if (x >finalX)
      {
         x-=30;  
         submit = new Button ((2*x+width)/2 - 100, y+280, 14);
         text = new OurFont (x, y, score);
      }
   }

   /*** updateScreen *************************************
   * Purpose: updates the screen (buttons)               *
   * Parameters: MouseEvent                              *
   * Returns: none                                       *
   ******************************************************/
   public void updateScreen(MouseEvent e)
   {
      clickedX = e.getX();
      clickedY = e.getY();
      if ((e.getX() >= submit.x && e.getX() <= submit.x + submit.width) && (e.getY() >= submit.y && e.getY() <= submit.y+ submit.height))
      {
         submit.pressed();
      }      
   }
   
   /*** updateSCreen2 ************************
   * Purpose: updates the screen (buttons)   *
   * Parameters: MouseEvent                  *
   * Returns: none                           *
   *************************************** **/
   public void updateScreen2 (MouseEvent e)
   {
      if ((e.getX() >= submit.x && e.getX() <= submit.x + submit.width) && (e.getY() >= submit.y && e.getY() <= submit.y+ submit.height))
      {
         if ((clickedX >= submit.x && clickedX <= submit.x + submit.width) && (clickedY >= submit.y && clickedY <= submit.y+ submit.height))
         {
            if (OurFont.isFull (text.states))
            { 
               submitted = true;
            
               String name = text.finalName;
            
               if (highScores.length == 5)
               {
                  highScores[4] = null;
               }
            
               if (highScores.length <=5)
               {
                  String [] temp = new String [highScores.length+1];
                  String [] temp2 = new String [scores.length+1];
               
                  for (int i = 0 ; i < temp.length; i++)
                  {
                     if (i < highScores.length)
                     {
                        temp[i] = highScores[i];
                        temp2[i] = scores[i];
                     }
                     else 
                     {
                        temp[i] = " ";
                        temp2[i] = " ";
                     }
                  }
                  highScores = temp;
                  scores = temp2;
               
                  int i;
               
                  for (i = highScores.length - 1; i > place-1; i --)
                  {
                     if (i > 0)
                     {
                        highScores[i] = highScores[i-1];
                        scores[i] = scores [i-1];
                     }
                  }
                  
                  highScores[place-1] = name;
                  scores[place-1] = score + "";
               
               }
             
            
               try
               {
                  FileWriter fw = new FileWriter(file);
                  PrintWriter pw = new PrintWriter(fw);
                  for (int i = 0; i < highScores.length; i++)
                  {
                     pw.println(highScores[i] + " " +scores[i]);
                  }
                  pw.close();
               } 
               catch (IOException g){}
            }
         }
      }
      submit.released();  
   }
   
   /*** updateScreen4 ************************************
   * Purpose: adds the name to the highScore file        *
   * Parameters: KeyEvent                                *
   * Returns: none                                       *
   ******************************************************/
   public void updateScreen4 (KeyEvent e)
   {
      if (e.getKeyCode() == 10)
      {
         if (OurFont.isFull (text.states))
         { 
            submitted = true;
            
            String name = text.finalName;
            
            if (highScores.length == 5)
            {
               highScores[4] = null;
            }
            
            if (highScores.length <=5)
            {
               String [] temp = new String [highScores.length+1];
               String [] temp2 = new String [scores.length+1];
               
               for (int i = 0 ; i < temp.length; i++)
               {
                  if (i < highScores.length)
                  {
                     temp[i] = highScores[i];
                     temp2[i] = scores[i];
                  }
                  else 
                  {
                     temp[i] = " ";
                     temp2[i] = " ";
                  }
               }
               highScores = temp;
               scores = temp2;
               
               int i;
               
               for (i = highScores.length - 1; i > place-1; i --)
               {
                  if (i > 0)
                  {
                     highScores[i] = highScores[i-1];
                     scores[i] = scores [i-1];
                  }
               }
                  
               highScores[place-1] = name;
               scores[place-1] = score + "";
               
            }
             
            
            try
            {
               FileWriter fw = new FileWriter(file);
               PrintWriter pw = new PrintWriter(fw);
               for (int i = 0; i < highScores.length; i++)
               {
                  pw.println(highScores[i] + " " +scores[i]);
               }
               pw.close();
            } 
            catch (IOException g){}
         }
      }
   }
   
   /*** updateScreen3 ************************************
   * Purpose: adds and deletes letters from the screen   *
   * Parameters: KeyEvent e                              *
   * Returns: none                                       *
   ******************************************************/
   public void updateScreen3 (KeyEvent e)
   {
      if (e.getKeyCode () == 8)
         text.take();
      else
         text.add(e);
         
      text.updateStuff();
   }
}