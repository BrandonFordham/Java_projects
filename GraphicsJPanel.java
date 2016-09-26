import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GraphicsJPanel extends JPanel
{
   ArrayList<Card> cards = new ArrayList<Card>();
   int [] arr = new int[16];
   String [] str1 = new String[16];
   String[] arr1 = new String[16];
   int p1 = 0;
   int p3 = 0;
   int p2 = 0;
   int hp = 0;
   int k = 0;
   int flop = 0;
   int turn = 0;
   int river = 0;
   int [] publicPool = new int[5];
   String [] publicPool1 = new String[5];
   String [] publicPool1s = new String[5];
   String [] hands = new String[16];
   char [] publicPool1ss = new char[5];

   public void paintComponent( Graphics g )
   {

      super.paintComponent( g );
      this.setBackground( Color.WHITE );
     
     //converts number values to string values for players
      for(int i =0; i < 12; i++)
      {
         if(arr[i] == 11)
	    arr1[i] = "Jack";
	 else if(arr[i] == 12)
	    arr1[i] = "Queen";
	 else if(arr[i] == 13)
	    arr1[i] = "King";
	 else if(arr[i] == 14)
	    arr1[i] = "Ace";
	 else   
            arr1[i] = Integer.toString(arr[i]);
      }

      //converts values to appropriate values for the table cards
      for(int i =0; i < 5; i++)
      {  
         if(publicPool[i] == 11)
            publicPool1[i] = "Jack";
         else if(publicPool[i] == 12)
            publicPool1[i] = "Queen";
         else if(publicPool[i] == 13)
            publicPool1[i] = "King";
         else if(publicPool[i] == 14)
            publicPool1[i] = "Ace";
	 else
            publicPool1[i] = Integer.toString(publicPool[i]);
      }

    //print player 1
    if(p1 == 1)
    {
      g.drawString("Player 1: ", 20, 20);
      g.drawString(arr1[0], 20, 30);
      g.drawString(arr1[1], 20, 40);
      g.drawString(arr1[2], 20, 50);
      g.drawString(arr1[3], 20, 60); 
      g.drawString(hands[0], 70, 30);
      g.drawString(hands[1], 70, 40);
      g.drawString(hands[2], 70, 50);
      g.drawString(hands[3], 70, 60);
    }
    //print player 2
    if(p2 == 2)
    {
      g.drawString("Player 2: ", 20, 140);
      g.drawString(arr1[4], 20, 150);
      g.drawString(arr1[5], 20, 160);
      g.drawString(arr1[6], 20, 170);
      g.drawString(arr1[7], 20, 180);
      g.drawString(hands[4], 70, 150);
      g.drawString(hands[5], 70, 160);
      g.drawString(hands[6], 70, 170);
      g.drawString(hands[7], 70, 180); 
    }
    //print player 3
    if(p3 == 3)
    {
      g.drawString("Player 3: ", 20, 290);
      g.drawString(arr1[8], 20,  300);
      g.drawString(arr1[9], 20,  310);
      g.drawString(arr1[10], 20, 320);
      g.drawString(arr1[11], 20, 330);
      g.drawString(hands[8], 70, 300);
      g.drawString(hands[9], 70, 310);
      g.drawString(hands[10], 70, 320);
      g.drawString(hands[11], 70, 330);
    }
    //rectangles for each each player
    g.drawRect( 10, 10, 100, 70);
    g.drawRect( 10, 130, 100, 70);
    g.drawRect( 10, 280, 100, 70);
    
    //rectangle for table
    g.drawRect( 150, 10, 600, 150);

    if(flop == 1)
    {
       g.drawString(publicPool1[0], 200, 50);
       g.drawString(publicPool1[1], 300, 50);
       g.drawString(publicPool1[2], 400, 50);
       g.drawString(publicPool1s[0], 200, 60);
       g.drawString(publicPool1s[1], 300, 60);
       g.drawString(publicPool1s[2], 400, 60);

       if(turn == 1)
       {
          g.drawString(publicPool1[3], 500, 50);
          g.drawString(publicPool1s[3], 500, 60);
       }
       if(river == 1)
       {
          g.drawString(publicPool1[4], 600, 50);
          g.drawString(publicPool1s[4], 600, 60);
       }	  
    }

    
    
 
    

   }
/*
   public void handd(int [] arrr)
   {
      for(int i = 0; i < 4; i++)
           {
	     arr[i] =  arrr[i];
           }
   }
*/
   public void player2Hand()
   {
      p2 = 2;
      repaint();
      
   }

   public void player1Hand()
   {
      p1 = 1;
      repaint();
   }

   public void player3Hand(int a)
   {
      if(a < 3)
      p3 = 0;
      else
      {
         p3 = 3;
         repaint();
      }
   }

   public void showFlop()
   {
      flop = 1;
      repaint();
   }
 
   public void showTurn()
   {
      turn = 1;
      repaint();
   }

   public void showRiver()
   {
      river = 1;
      repaint();
   }
 
   public void getFlop(Dealer dealer, Deck d)
   {
      cards = dealer.returnFlop();
      for(int i = 0; i < 3; i++)
      {
         publicPool[i] = cards.get(i).getRank();
	 publicPool1s[i] = cards.get(i).getSuit();

	 if((cards.get(i).getSuit()).equals("Hearts"))
	 {
            publicPool1s[i] = "" + '\u2661';
	 }
	 if((cards.get(i).getSuit()).equals("Diamonds"))
	 {
            publicPool1s[i] = "" + '\u2662';
	 }
         if((cards.get(i).getSuit()).equals("Spades"))
         {
            publicPool1s[i] = "" + '\u2660';
         }
         if((cards.get(i).getSuit()).equals("Clubs"))
         {
            publicPool1s[i] = "" + '\u2663';
         }
	 
      }
   }

   public void getTurn(Dealer dealer)
   {
       cards = dealer.returnTurn();
       for(int i = 0; i < 4; i++)
       {
          publicPool[i] = cards.get(i).getRank();
           publicPool1s[i] = cards.get(i).getSuit();

         if((cards.get(i).getSuit()).equals("Hearts"))
         {
            publicPool1s[i] = "" + '\u2661';
         }
         if((cards.get(i).getSuit()).equals("Diamonds"))
         {
            publicPool1s[i] = "" + '\u2662';
         }
         if((cards.get(i).getSuit()).equals("Spades"))
         {
            publicPool1s[i] = "" + '\u2660';
         }
         if((cards.get(i).getSuit()).equals("Clubs"))
         {
            publicPool1s[i] = "" + '\u2663';
         }
       }
   }

   public void getRiver(Dealer dealer)
   {
       cards = dealer.returnRiver();
       for(int i = 0; i < 5; i++)
       {
          publicPool[i] = cards.get(i).getRank();
           publicPool1s[i] = cards.get(i).getSuit();

         if((cards.get(i).getSuit()).equals("Hearts"))
         {
            publicPool1s[i] = "" + '\u2661';
         }
         if((cards.get(i).getSuit()).equals("Diamonds"))
         {
            publicPool1s[i] = "" + '\u2662';
         }
         if((cards.get(i).getSuit()).equals("Spades"))
         {
            publicPool1s[i] = "" + '\u2660';
         }
         if((cards.get(i).getSuit()).equals("Clubs"))
         {
            publicPool1s[i] = "" + '\u2663';
         }
       }
   }

   public void resetAllValues()
   {
      flop = 0;
      turn = 0;
      river = 0;
      k = 0;
  
      for(int i = 0;i < 16; i++)
      {
         arr[i] = 0;
	 arr1[i] = "";
      }

   }


/*
   public void getFlop(Dealer dealer, Deck d)
   {
      cards = dealer.returnTurn();
   }
*/

//   public void get

   public void getMyHand(Player player, Deck d)
   {
      System.out.println("HEREEEEEEEEEEEEE");
      System.out.print(cards);

      cards = player.returnStartingHand();
     int p = 0;

     if(k == 2)
     for(int i = 8; i < 12; i++)
     {
      arr[i] =  cards.get(p).getRank();

         if((cards.get(p).getSuit()).equals("Hearts"))
         {
            hands[i] = "" + '\u2661';
         }
         if((cards.get(p).getSuit()).equals("Diamonds"))
         {
            hands[i] = "" + '\u2662';
         }
         if((cards.get(p).getSuit()).equals("Spades"))
         {
            hands[i] = "" + '\u2660';
         }
         if((cards.get(p).getSuit()).equals("Clubs"))
         {
            hands[i] = "" + '\u2663';
         } 

      p++;
     }

     if(k == 1)
     for(int i = 4; i < 8; i++)
     {
      arr[i] =  cards.get(p).getRank();

         if((cards.get(p).getSuit()).equals("Hearts"))
         {
            hands[i] = "" + '\u2661';
         }
         if((cards.get(p).getSuit()).equals("Diamonds"))
         {
            hands[i] = "" + '\u2662';
         }
         if((cards.get(p).getSuit()).equals("Spades"))
         {
            hands[i] = "" + '\u2660';
         }
         if((cards.get(p).getSuit()).equals("Clubs"))
         {
            hands[i] = "" + '\u2663';
         }

      p++;
     }

     if(k == 0)
     for(int i = 0; i < 4; i++)
     {
      arr[i] =  cards.get(i).getRank();
      
        if((cards.get(i).getSuit()).equals("Hearts"))
         {
            hands[i] = "" + '\u2661';
         }
         if((cards.get(i).getSuit()).equals("Diamonds"))
         {
            hands[i] = "" + '\u2662';
         }
         if((cards.get(i).getSuit()).equals("Spades"))
         {
            hands[i] = "" + '\u2660';
         }
         if((cards.get(i).getSuit()).equals("Clubs"))
         {
            hands[i] = "" + '\u2663';
         }

     }     

     k++;

     /* 
    for(int i = 0; i < 4; i++)
    {
       System.out.print("************");
       System.out.println(arr[i]);
    }
    */
   }
   
}
