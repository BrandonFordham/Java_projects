import java.io.*;
import java.util.*;
import java.awt.Color;
import javax.swing.JFrame;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.awt.*;
import java.awt.event.*;

class Main
{

   static int playAgain;  //checks for when to quit the game
   static double myPot;  //value of money
   static String myBet;  
   static double bet;  
   static double totalBets;  //has the value of all money
   static double call;
   static int [] foldFlag; //used for folding

   static int counter = 0; 
   static int ante;
   static int num; // used to make 3 player objects
   static int value1; //determines options that arise from 2-3 players
   static int [] arr = new int[12];
   static int gamemode = 0; // 0 for AI, not 0 for human interaction

   ArrayList<Card> cards = new ArrayList<Card>();

   public static void main(String[] args)
   {
      JFrame frame = new JFrame( "Lets play some Omaha poker!" );
      frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
      GraphicsJPanel graphicsJPanel = new GraphicsJPanel();
      graphicsJPanel.setBackground(Color.white );
      frame.add( graphicsJPanel );
      frame.setSize( 800, 600);
      frame.setVisible( true );
      Card c;
      System.out.println("Welcome to poker!");
      
      int typeOfGame =     JOptionPane.showConfirmDialog(frame, "Would you like to play against bots?", "Gamemode", JOptionPane.YES_NO_OPTION);
      if( typeOfGame == JOptionPane.YES_OPTION)
         JOptionPane.showMessageDialog(frame, "Good luck vs AI!");
      else
      {
         JOptionPane.showMessageDialog(frame, "Good luck vs a human!");
         gamemode = 1;
      }

      if(gamemode == 0)
         value1 = 3;
      else
         value1 = 2;
      num = 3;
      
      Player[] player = new Player[num];       
      for(int j = 0; j < num; j++)
      {
         System.out.println("Player: ");
         player[j] = new Player();
      }
    
      playAgain = 1;
      myPot = 1000;

     //loop starts here for each beginning of a new round
      while(playAgain != 0)
      {
         bet = 0;
         totalBets = 0;
         call = 0;
         foldFlag = new int[5];
         for(int j = 0; j < num; j++)
            foldFlag[j] = 0;
       
         Deck deck = new Deck();
         Dealer dealer = new Dealer();
	 System.out.println("Lets play some poker!");
         for(int j = 0; j < num; j++)
	 {
	    System.out.println("Player: ");
	    
            player[j].startingHand(deck);
	    graphicsJPanel.getMyHand(player[j], deck);	    
	    player[j].sortHand(deck);
	    player[j].printHand();
	    anteUp(player[j]);
         }
	 graphicsJPanel.getMyHand(player[0], deck);
	 graphicsJPanel.getMyHand(player[1], deck);
	 if(value1 >= 2)
	    graphicsJPanel.getMyHand(player[2], deck);
         graphicsJPanel.player1Hand();
         graphicsJPanel.player2Hand();
	 if(value1 >= 2)
            graphicsJPanel.player3Hand(value1);

	 System.out.print("Your current amount of cash is: ");
	 System.out.println( player[0].pot);

         decideP1(player[0], player[1], player[2]);
	 if(gamemode != 0 )
            decideP2(player[1]);
         dealer.theFlop(deck);
         dealer.dealerPrintFlop();
	 graphicsJPanel.getFlop(dealer, deck);
	 graphicsJPanel.showFlop();

         System.out.print("Your current amount of cash is: ");
         System.out.println( player[0].pot);

         decideP1(player[0], player[1], player[2]);
	 if(gamemode != 0)
	    decideP2(player[1]);
  
	 dealer.theTurn(deck);
	 graphicsJPanel.getTurn(dealer);
	 graphicsJPanel.showTurn();
         dealer.dealerPrintTurn();

         System.out.print("Your current amount of cash is: ");
         System.out.println( player[0].pot);

         decideP1(player[0], player[1], player[2]);
	 if(gamemode != 0)
	    decideP2(player[1]);
         dealer.theRiver(deck);
	 graphicsJPanel.getRiver(dealer);
	 graphicsJPanel.showRiver();
         dealer.dealerPrintRiver();

         System.out.print("Your current amount of cash is: ");
         System.out.println( player[0].pot);
 
         decideP1(player[0], player[1], player[2]);
	 if(gamemode != 0)
	    decideP2(player[1]);

        
        double winner = 11;
	int winningPlayer = 0;
        double temp = 11;

         //check to see if player1 folded.
         int fcp = 0;
	 if(foldFlag[0] == 1)
	    fcp = 1;

         //check to see if 3 or 2 players in game
         if(value1 < 3)
	    num = 2;

         while(fcp < num)
	 {          
	    System.out.println("********************************");
            temp = player[fcp].EvaluateAll(dealer.publicCards()); 
	    System.out.println("********************************");
               if(winner > temp)
	       {
                  winner = temp;
		  winningPlayer = fcp;
	       }
	       fcp++;	    
         }
       
         player[winningPlayer].pot = totalBets +  player[winningPlayer].pot;

         System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
	 System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
         System.out.print("\nThe winning player is: ");
         System.out.println(winningPlayer+1);
         System.out.print("Wins with rank: ");

         System.out.println(winner);
	 System.out.println("");
	 winningPlayer = winningPlayer + 1;
	 JOptionPane.showMessageDialog(frame, "winner is player "  + winningPlayer );

        for(int j = 0; j < num; j++)
        {
           System.out.print("player");
           System.out.print(j+1);
           System.out.print(" cash: ");
           System.out.println(player[j].pot);
        }

	graphicsJPanel.resetAllValues();         
	myBet =  JOptionPane.showInputDialog( 
	       "Enter any number besides 0 to keep playing! " );
	playAgain = Integer.parseInt(myBet);
	if(playAgain == 0)
	   System.exit(0);
     }
 } 

   //starts ante at each beginning of hand
   static void anteUp(Player player)
   {
      player.pot = player.pot - 25;
      totalBets = totalBets + 25;
   }
   
   //determiens if betting or folding and how the AI bots work
   static void decideP1(Player player, Player player2, Player player3)
   {
   
      if(foldFlag[0] == 0)
      {
         bet = 0;
         int betOrFold =   JOptionPane.showConfirmDialog(null, "P1, Would you like to bet?", "Bet or Fold", JOptionPane.YES_NO_OPTION);
        if( betOrFold == JOptionPane.YES_OPTION)
        {
           myBet =  JOptionPane.showInputDialog( "P1 bets now: " );
           bet = Integer.parseInt(myBet);
        }
      }
      if(bet > 0 && foldFlag[0] == 0)
      { 
         player.pot = player.pot - bet;
         totalBets = bet + totalBets;
      }
      else
      {
         bet = 25;
         foldFlag[0] = 1;
      }

      //gamemode is set 0 for AI.
      if(gamemode == 0)
      {
            player2.pot = player2.pot-bet;
	    totalBets = bet + totalBets;
	    player3.pot = player3.pot-bet;
	    totalBets = bet + totalBets;
      }
   }
   
   //checks for when no AI is in game
   static void decideP2(Player player)
   {
      if(foldFlag[1] == 0)
      {
        bet = 0;
        int betOrFold1 =   
        JOptionPane.showConfirmDialog(null, "P2, Would you like to bet?", 
           "Bet or Fold", JOptionPane.YES_NO_OPTION);
        if( betOrFold1 == JOptionPane.YES_OPTION)
        {
           myBet =  JOptionPane.showInputDialog( "P2 bets now: " );
           bet = Integer.parseInt(myBet);
        }

      }
      if(bet > 0 && foldFlag[1] == 0)
      {
            player.pot = player.pot - bet;
            totalBets = bet + totalBets;
      }
      else
      {
           bet = 25;
           foldFlag[1] = 1;
      }
   }
}
