/*********************************************************
*  Name: Siddharth Pai, Nik Valcic, Ben Stafl            *
*  Course: ICS 4U1 - 01  Pd. 4                           *
*  Final Assignment (Summative): Flappy World            *
*  Class Purpose: To make a class that deals with the    *
*  special font (for the highscore screen)               *
*  Due Date: May 27, 2014                                *
*********************************************************/
import java.awt.*; 
import java.awt.event.*; 
import javax.swing.*; 

class OurFont  
{
   ImageIcon[] name = new ImageIcon [5];
   ImageIcon[] score;
   int [] scoreX; 
   int [] nameX; 
   int [] states = new int [5];
   
   int scoreY;
   int nameY;
   
   String gameScore;
   String finalName = ""; 
   
   ImageIcon [] alphabet = new ImageIcon [36];
   char [] realChar = new char [36];
   
   /*** OurFont ******************************************
   * Purpose: (Constructor) initializes instances        *
   * Parameters: x coordinate, y coordinate, state       *
   * Returns: none                                       *
   ******************************************************/
   public OurFont (int x, int y, int s)
   {
      scoreY = y + 89; 
      nameY = y + 195; 
      
      alphabet[0] = new ImageIcon ("Images/HighScoreScreen/Alphabet/0.png");
      alphabet[1] = new ImageIcon ("Images/HighScoreScreen/Alphabet/1.png");
      alphabet[2] = new ImageIcon ("Images/HighScoreScreen/Alphabet/2.png");
      alphabet[3] = new ImageIcon ("Images/HighScoreScreen/Alphabet/3.png");
      alphabet[4] = new ImageIcon ("Images/HighScoreScreen/Alphabet/4.png");
      alphabet[5] = new ImageIcon ("Images/HighScoreScreen/Alphabet/5.png");
      alphabet[6] = new ImageIcon ("Images/HighScoreScreen/Alphabet/6.png");
      alphabet[7] = new ImageIcon ("Images/HighScoreScreen/Alphabet/7.png");
      alphabet[8] = new ImageIcon ("Images/HighScoreScreen/Alphabet/8.png");
      alphabet[9] = new ImageIcon ("Images/HighScoreScreen/Alphabet/9.png");
      alphabet[10] = new ImageIcon ("Images/HighScoreScreen/Alphabet/10.png");
      alphabet[11] = new ImageIcon ("Images/HighScoreScreen/Alphabet/11.png");
      alphabet[12] = new ImageIcon ("Images/HighScoreScreen/Alphabet/12.png");
      alphabet[13] = new ImageIcon ("Images/HighScoreScreen/Alphabet/13.png");
      alphabet[14] = new ImageIcon ("Images/HighScoreScreen/Alphabet/14.png");
      alphabet[15] = new ImageIcon ("Images/HighScoreScreen/Alphabet/15.png");
      alphabet[16] = new ImageIcon ("Images/HighScoreScreen/Alphabet/16.png");
      alphabet[17] = new ImageIcon ("Images/HighScoreScreen/Alphabet/17.png");
      alphabet[18] = new ImageIcon ("Images/HighScoreScreen/Alphabet/18.png");
      alphabet[19] = new ImageIcon ("Images/HighScoreScreen/Alphabet/19.png");
      alphabet[20] = new ImageIcon ("Images/HighScoreScreen/Alphabet/20.png"); 
      alphabet[21] = new ImageIcon ("Images/HighScoreScreen/Alphabet/21.png");
      alphabet[22] = new ImageIcon ("Images/HighScoreScreen/Alphabet/22.png");
      alphabet[23] = new ImageIcon ("Images/HighScoreScreen/Alphabet/23.png");
      alphabet[24] = new ImageIcon ("Images/HighScoreScreen/Alphabet/24.png");
      alphabet[25] = new ImageIcon ("Images/HighScoreScreen/Alphabet/25.png");
      alphabet[26] = new ImageIcon ("Images/HighScoreScreen/Alphabet/26.png");
      alphabet[27] = new ImageIcon ("Images/HighScoreScreen/Alphabet/27.png");
      alphabet[28] = new ImageIcon ("Images/HighScoreScreen/Alphabet/28.png");
      alphabet[29] = new ImageIcon ("Images/HighScoreScreen/Alphabet/29.png");
      alphabet[30] = new ImageIcon ("Images/HighScoreScreen/Alphabet/30.png");
      alphabet[31] = new ImageIcon ("Images/HighScoreScreen/Alphabet/31.png");
      alphabet[32] = new ImageIcon ("Images/HighScoreScreen/Alphabet/32.png");
      alphabet[33] = new ImageIcon ("Images/HighScoreScreen/Alphabet/33.png");
      alphabet[34] = new ImageIcon ("Images/HighScoreScreen/Alphabet/34.png");
      alphabet[35] = new ImageIcon ("Images/HighScoreScreen/Alphabet/35.png");
      
      realChar [0] = '0';
      realChar [1] = '1';
      realChar [2] = '2';
      realChar [3] = '3';
      realChar [4] = '4';
      realChar [5] = '5';
      realChar [6] = '6';
      realChar [7] = '7';
      realChar [8] = '8';
      realChar [9] = '9';
      realChar [10] = 'a';
      realChar [11] = 'b';
      realChar [12] = 'c';
      realChar [13] = 'd';
      realChar [14] = 'e';
      realChar [15] = 'f';
      realChar [16] = 'g';
      realChar [17] = 'h';
      realChar [18] = 'i';
      realChar [19] = 'j';
      realChar [20] = 'k';
      realChar [21] = 'l';
      realChar [22] = 'm';
      realChar [23] = 'n';
      realChar [24] = 'o';
      realChar [25] = 'p';
      realChar [26] = 'q';
      realChar [27] = 'r';
      realChar [28] = 's';
      realChar [29] = 't';
      realChar [30] = 'u';
      realChar [31] = 'v';
      realChar [32] = 'w';
      realChar [33] = 'x';
      realChar [34] = 'y';
      realChar [35] = 'z';
      
 
      gameScore = s + "";
      
      score = new ImageIcon [gameScore.length()];
      
      for (int i = 0; i < gameScore.length(); i++)
      {
         if (gameScore.charAt(i) == '0')
            score[i] = alphabet[0];
         else if (gameScore.charAt(i) == '1')   
            score[i] = alphabet[1];
         else if (gameScore.charAt(i) == '2')   
            score[i] = alphabet[2];
         else if (gameScore.charAt(i) == '3')   
            score[i] = alphabet[3];
         else if (gameScore.charAt(i) == '4')   
            score[i] = alphabet[4];
         else if (gameScore.charAt(i) == '5')   
            score[i] = alphabet[5];
         else if (gameScore.charAt(i) == '6')   
            score[i] = alphabet[6];
         else if (gameScore.charAt(i) == '7')   
            score[i] = alphabet[7];
         else if (gameScore.charAt(i) == '8')   
            score[i] = alphabet[8];
         else if (gameScore.charAt(i) == '9')   
            score[i] = alphabet[9];
      }
      
      scoreX = new int [score.length];
      nameX = new int [name.length];
      
      scoreX[0] = x+224; 
      
      nameX[0] = x + 230;
      nameX[1] = nameX[0] + 55;
      nameX[2] = nameX[1] + 55;
      nameX[3] = nameX[2] + 55;
      nameX[4] = nameX[3] + 55;
      
      for (int i = 1; i < score.length; i++)
      {
         scoreX[i] = scoreX[i-1] + score[i-1].getIconWidth();
      }
      
      for (int i = 0; i < states.length; i++)
         states[i] = -1;
      
      name [0] = new ImageIcon ("");
      name [1] = new ImageIcon ("");
      name [2] = new ImageIcon ("");
      name [3] = new ImageIcon ("");
      name [4] = new ImageIcon ("");
   }
     
   /*** displayOurFont ***********************************
   * Purpose: displays the font                          *
   * Parameters: Graphics object                         *
   * Returns: none                                       *
   ******************************************************/ 
   public void displayOurFont (Graphics g)
   {
      for (int i = 0; i < score.length; i++)
         g.drawImage(score[i].getImage(), scoreX[i], scoreY, null);
      for (int i = 0; i < name.length; i++)   
         g.drawImage (name[i].getImage(), nameX[i], nameY, null);
   }  
   
   /*** add *****************
   * Purpose: adds a letter *
   * Parameters: KeyEvent   *
   * Returns: none          *
   *************************/
   public void add (KeyEvent e)
   {
      if (!isFull(states))
      {  
         int i = -1; 
         boolean done = false;
          
         if (e.getKeyCode()>= 48 && e.getKeyCode() <= 57)
            i = e.getKeyCode() - 48;
         else if (e.getKeyCode() >= 65 && e.getKeyCode() <= 90)
            i = e.getKeyCode() - 55;
            
         for (int n = 0; n< states.length && !done; n++)
            if (states [n] == -1)
            {
               states [n] = i;
               done = true;
            }
      }  
   }
  
   /*** take *******************
   * Purpose: deletes a letter *
   * Parameters: none          *
   * Returns: none             *
   ****************************/
   public void take()
   {
      if (!isEmpty(states))
      {
         int n = states.length - 1;
         
         while (states[n] == -1)
         {
            if (n>0)
               n--;
         }
         
         states [n] = -1;
      }
   }
   
   /*** isFull *******************************************
   * Purpose: determines if the states are full          *
   * Parameters: states - array                          *
   * Returns: true - if the state is full                *
   ******************************************************/
   public static boolean isFull (int [] s)
   {
      boolean full = true; 
        
      for (int i = 0; i < s.length && full; i++)
         if (s[i] == -1)
            full = false;
            
      return full;
   }
   
   /*** isEmpty ******************************************
   * Purpose: determines if the states are empty         *
   * Parameters: states - array                          *
   * Returns: true - if the state is empty               *
   ******************************************************/
   public static boolean isEmpty (int [] s)
   {
      boolean empty = true; 
        
      for (int i = 0; i < s.length && empty; i++)
         if (s[i] != -1)
            empty = false;
            
      return empty;
   }
   
   /*** updateStuff *********************
   * Purpose: updates the states array  *
   * Parameters: none                   *
   * Returns: none                      *
   *************************************/
   public void updateStuff()
   {
      for (int i = 0; i < states.length; i++)
      {
         if (states[i] == -1)
            name[i] = new ImageIcon ("");
         else 
            name[i] = alphabet[states[i]];
      }
      
      finalName = "";
      
      for (int i = 0; i < states.length; i++)
         if (states[i] > -1)
            finalName+= realChar[states[i]];
   }
}