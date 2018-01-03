/*********************************************************
*  Name: Siddharth Pai, Nik Valcic, Ben Stafl            *
*  Course: ICS 4U1 - 01  Pd. 4                           *
*  Final Assignment (Summative): Flappy World            *
*  Class Purpose: To make a class that deals with the    *
*  score in game   (one player)                          *
*  Due Date: May 27, 2014                                *
*********************************************************/
import java.awt.event.*; 
import java.awt.*; 
import javax.swing.*;

class Score
{  
   int score, digits, y; 
   String scoreString;
   ImageIcon [] scoreImage;
   int [] scoreImageWidths;
   int [] xCords;
   ImageIcon one;
   ImageIcon two;
   ImageIcon three;
   ImageIcon four;
   ImageIcon five;
   ImageIcon six;
   ImageIcon seven;
   ImageIcon eight;
   ImageIcon nine;
   ImageIcon zero;
   
   /*** Score ****************************************
   * Purpose: (Constructor) intializes the instances *
   * Parameters: none                                *
   * Returns: none                                   *
   **************************************************/
   public Score()
   {
      y = 20;
      
      one = new ImageIcon ("Images/Score/1.png");
      two = new ImageIcon ("Images/Score/2.png");
      three = new ImageIcon ("Images/Score/3.png");
      four = new ImageIcon ("Images/Score/4.png");
      five = new ImageIcon ("Images/Score/5.png");
      six = new ImageIcon ("Images/Score/6.png");
      seven = new ImageIcon ("Images/Score/7.png");
      eight = new ImageIcon ("Images/Score/8.png");
      nine = new ImageIcon ("Images/Score/9.png");
      zero = new ImageIcon ("Images/Score/0.png");
      
      score = 0;
      digits= 1;
      scoreString = score + "";
      scoreImage = new ImageIcon [1];
      scoreImageWidths = new int [1];
      scoreImage[0] = zero;
      scoreImageWidths[0] = 50;
      xCords = new int [1];
      xCords[0] = (900/2) - (50/2);
   }
   
   /*** Score ****************************************
   * Purpose: (Constructor) intializes the instances *
   * Parameters: y-coordinate                        *
   * Returns: none                                   *
   **************************************************/
   public Score(int y)
   {
      this.y = y;
      
      one = new ImageIcon ("Images/Score/1s.png");
      two = new ImageIcon ("Images/Score/2s.png");
      three = new ImageIcon ("Images/Score/3s.png");
      four = new ImageIcon ("Images/Score/4s.png");
      five = new ImageIcon ("Images/Score/5s.png");
      six = new ImageIcon ("Images/Score/6s.png");
      seven = new ImageIcon ("Images/Score/7s.png");
      eight = new ImageIcon ("Images/Score/8s.png");
      nine = new ImageIcon ("Images/Score/9s.png");
      zero = new ImageIcon ("Images/Score/0s.png");
      
      score = 0;
      digits= 1;
      scoreString = score + "";
      scoreImage = new ImageIcon [1];
      scoreImageWidths = new int [1];
      scoreImage[0] = zero;
      scoreImageWidths[0] = 50;
      xCords = new int [1];
      xCords[0] = (900/2) - (50/2)+25;
   }

   
   /*** displayScore **************
   * Purpose: displays the score  *
   * Parameters: Graphics object  *
   * Returns: none                *
   *******************************/
   public void displayScore (Graphics g)
   {
      for (int i = 0; i < scoreImage.length; i++)
         g.drawImage (scoreImage[i].getImage(), xCords[i], y, null);
   }
   
   /*** updateScore ****************
   * Purpose: increases the score  *
   * Parameters: none              *
   * Returns: none                 *
   ********************************/
   public void updateScore ()
   {
      score++;
      
      int totalWidth = 0;
      
      if (score<10)
         digits = 1;
      else if (score >= 10 && score <100)
         digits = 2;
      else if (score >=100 && score <1000)
         digits = 3;
      else if (score >= 1000 && score < 10000)
         digits = 4;
         
      scoreString = score + "";
      
      scoreImage = new ImageIcon [digits];
      scoreImageWidths = new int [digits];
      xCords = new int [digits];
      
      for (int i = 0; i < scoreImage.length; i++)
      {
         if (scoreString.charAt(i)=='0')
            scoreImage[i] = zero;
         else if (scoreString.charAt(i)=='1')
            scoreImage[i] = one;
         else if (scoreString.charAt(i)=='2')
            scoreImage[i] = two; 
         else if (scoreString.charAt(i)=='3')
            scoreImage[i] = three;
         else if (scoreString.charAt(i)=='4')
            scoreImage[i] = four;
         else if (scoreString.charAt(i)=='5')
            scoreImage[i] = five;
         else if (scoreString.charAt(i)=='6')
            scoreImage[i] = six;
         else if (scoreString.charAt(i)=='7')
            scoreImage[i] = seven;
         else if (scoreString.charAt(i)=='8')
            scoreImage[i] = eight;
         else if (scoreString.charAt(i)=='9')
            scoreImage[i] = nine;
            
         if (scoreString.charAt(i)== '1')
            scoreImageWidths[i] = one.getIconWidth();
         else if (scoreString.charAt(i) == '7')
            scoreImageWidths[i] = seven.getIconWidth();
         else 
            scoreImageWidths[i] = zero.getIconWidth();
            
         totalWidth += scoreImageWidths[i];
      }
      
      xCords[0] = (900/2) - (totalWidth/2);
      
      for (int i = 1; i < xCords.length; i++)
      {
         xCords[i] = xCords[i-1] + scoreImageWidths[i-1];
      }
      
   }
}