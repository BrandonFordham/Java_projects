import java.util.ArrayList;

public class Player
{
//The most complex file. Handles best hand evaluation and similar procedures

   ArrayList<Card> cards;
   double pot;
   ArrayList<Card> evalCards;
   double bestHand = 11;
   ArrayList<Card> startHand;

  //bestHand is a rank of 1 for royal flush-10 highcard
  //lower is better!
  public void resetBestHand()
  {
     bestHand = 11;
  }

 
  public void printBestHand()
  {
     System.out.print("Your best hand is a: ");
     if(bestHand == 1)
     System.out.print("Royal flush!");
     if(bestHand == 2)
     System.out.print("Straight flush");
     if(bestHand == 3)
     System.out.print("Four of a kind!");
     if(bestHand == 4)
     System.out.print("Full house!");
     if(bestHand == 5)
     System.out.print("Flush!");
     if(bestHand == 6)
     System.out.print("Straight!");
     if(bestHand == 7)
     System.out.print("Three of a kind!");
     if(bestHand == 8)
     System.out.print("Two pair!");
     if(bestHand == 9)
     System.out.print("Pair");
     if(bestHand > 9)
     System.out.print("High card!");

     System.out.print("\nrank is: ");
     System.out.println(bestHand);

  }

//Evaluates and returns the best appropriate function
  public double EvaluateAll(ArrayList<Card> x)
  {
     resetBestHand();
     twoPair(x);
     threeOfKind(x);
     fourOfKind(x);
     Pair(x);
     straight(x);
     flush(x);
     highCard();
     printBestHand();
     return bestHand;
    // resetBestHand();
  }


   public Player()
   {
      evalCards = new ArrayList<Card>();
      cards = new ArrayList<Card>();
      pot = 1000;
   }
 
   
   //gets the initial player hand
   public void startingHand(Deck d)
   {
        for(int i = 0; i < 4; i++)
	{
           Card c;
           c = d.drawTopOfDeck();
	   cards.add(c);
	   
	}	
   }

   

   public ArrayList<Card> returnStartingHand()
   {
      return cards;
   }

   public void sortHand(Deck d)
   {
   String two = "2";
      for(int i = 0; i <4; i++)
      {
         for(int j = 0; j <4; j++)
	 {
            if(cards.get(j).getRank() < cards.get(i).getRank())
	    {
	       String Stemp = cards.get(i).getSuit();
	       int temp = cards.get(i).getRank();
	       int temp1 = cards.get(j).getRank();
	       String Stemp1 = cards.get(j).getSuit();
	       cards.get(i).setSuit(Stemp1);
	       cards.get(i).setRank(temp1);
	       cards.get(j).setRank(temp);
	       cards.get(j).setSuit(Stemp);
	    }
	    
	 }
      }
   }

//twoPair will have explanation on the general idea of each function
   public void flush(ArrayList<Card> x)
   {
       ArrayList<Integer> elements = new ArrayList<>();
       ArrayList<String> stringElements = new ArrayList<>();
       int [] myList = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

        int s = 0;
      int h = 0;
      int c = 0;
      int d = 0;
      int a = 0;

        for(int i = 0; i < evalCards.size(); i++)
      {
         elements.add(evalCards.get(i).getRank());         
      }
      for(int i = 0; i < evalCards.size(); i++)
      {
         String type = evalCards.get(i).getSuit();
         System.out.println(type);
         if( type.equals("Clubs"))
	 {
          c++;
	  if(i < 4 && c > 2)
	     c = 2;
	 }
         else if(type.equals("Hearts"))
         {
	  h++;
          if(i < 4 && h > 2)
             h = 2;
         }
         else if((evalCards.get(i).getSuit()).equals("Spades"))
         {
	  s++;
          if(i < 4 && s > 2)
             s = 2;
         } 
         else if((evalCards.get(i).getSuit()).equals("Diamonds"))
         {
	  d++;
          if(i < 4 && d > 2)
             d = 2;
         }
	 
      } 
      /*
       System.out.print("The flush numbers are: ");
       System.out.println(c);
       System.out.println(h);
       System.out.println(s);
       System.out.println(d);
     */
      if(c >= 5)
      {
          System.out.println("Clubs flush!");	
	  if(bestHand > 5)
	      bestHand = 5;
      }
      if(h >= 5)
      {
          System.out.println("Hearts flush!");
	  if(bestHand > 5)
	      bestHand = 5;
      }
      if(s >= 5)
      {
          System.out.println("Spades flush!");
	  if(bestHand > 5)
	      bestHand = 5;
      }
      if(d >= 5)
      {
          System.out.println("Diamonds flush!");
	  if(bestHand > 5)
	      bestHand = 5;
      }

/*
      for(int i = 0; i < evalCards.size(); i++)
      {
         StringElements.get(i) == Card.suits[0];
      }
*/


      for(int i =0; i < elements.size(); i++)
      {
          int val = elements.get(i);
          myList[val] = myList[val] + 1;
      }
   }

   public void straight(ArrayList<Card> x)
   {
     ArrayList<Integer> elements = new ArrayList<>();
     int [] myList = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
     int [] returnList = { 0, 0, 0, 0, 0, 0, 0 };


     for(int i = 0; i < evalCards.size(); i++)
      {
         elements.add(evalCards.get(i).getRank());
      }

      for(int i =0; i < elements.size(); i++)
      {
          int val = elements.get(i);
          myList[val] = myList[val] + 1;
      }

      int counter = 0;
      int position = 0;
      for(int i = 0; i < myList.length; i++)
      {
          if(myList[i] >= 1)
          {
             counter++;
//	     System.out.print("counter: ");
//	     System.out.println(counter);
             if(counter >= 5)
                position = i;
          }
          else
             counter = 0;
          
          if(position > 0)
          {
	    /*
             System.out.println("We have a straight!!!!");
	     
	     System.out.println(myList[position-4]);
	     System.out.println(myList[position-3]);
	     System.out.println(myList[position-2]);
	     System.out.println(myList[position-1]);
	     System.out.println(myList[position]);
	     
	     i = 100;
	     */
          }
      }

        if(position > 0)
        {
	int handCounter = 0;
	int tableCounter = 0;
	int p = position;
	int p1 = position -1;
	int p2 = position -2;
	int p3 = position -3;
	int p4 = position -4;
        int check = 0;
        int check1 = 0;
        int check2 = 0;
        int check3 = 0;
        int check4 = 0;
     for(int i =0; i < elements.size(); i++)
     {
             if(i < 4)
             {
                if(elements.get(i) == p && check == 0)
                {
                   check = 1;
                   handCounter++;
                }
                else if(elements.get(i) == p1 && check1 == 0)
                {
                   check1 = 2;
                   handCounter++;
                }
                else if(elements.get(i) == p2 && check2 == 0)
                {
                   check2 = 3;
                   handCounter++;
                }
                else if(elements.get(i) == p3 && check3 ==0)
                {
                   check3 = 4;
                   handCounter++;
                }
                else if(elements.get(i) == p4 && check4 ==0)
                {
                   check4 = 5;
                   handCounter++;
                }

             }
             if(i >= 4)
             {
                if(elements.get(i) == p)
                {
                   check = 1;
                   tableCounter++;
                }
                else if(elements.get(i) ==p1)
                {
                   check1 = 2;
                   tableCounter++;
                }
                else if(elements.get(i) == p2)
                {
                   check2 = 3;
                   tableCounter++;
                }
                else if(elements.get(i) == p3 )
                {
                   check3 = 4;
                   tableCounter++;
                }
                else if(elements.get(i) == p4)
                {
                   check4 = 5;
                   tableCounter++;
                }
             }

            if(handCounter >= 2 && tableCounter >= 3)
            {
                System.out.print("Straight! Highest value: ");
		System.out.println(p);
		i = 100;
		if(bestHand > 6)
		   bestHand = 6;

              int checkForFlush = 0;

                for(int u = 0; u < 9; u++)
		{
		  checkForFlush = 0;
                        for(int t = 0; t < 9; t++)
			{
                            if( (evalCards.get(u).getRank()) == ( (evalCards.get(t).getRank() + 1)) )
			       if(   (evalCards.get(u).getSuit()).equals( (evalCards.get(t).getSuit())) )
			           checkForFlush++;

			   if( (evalCards.get(u).getRank()) == ( (evalCards.get(t).getRank() + 2)) )
                               if(   (evalCards.get(u).getSuit()).equals( (evalCards.get(t).getSuit())) )
                                   checkForFlush++;

                           if( (evalCards.get(u).getRank()) == ( (evalCards.get(t).getRank() + 3)) )
                               if(   (evalCards.get(u).getSuit()).equals( (evalCards.get(t).getSuit())) )
                                   checkForFlush++;

                           if( (evalCards.get(u).getRank()) == ( (evalCards.get(t).getRank() + 4)) )
                               if(   (evalCards.get(u).getSuit()).equals( (evalCards.get(t).getSuit())) )
                                   checkForFlush++;

                           if(bestHand > 2 && checkForFlush >= 5)
                           {
                               System.out.print("STRAIGHT FLUSH!@@@@@@@@@@@@!!@@!@!@!@!");
                               bestHand = 2;
                           }

			}
		    
		}

/*
                if(    (evalCards.get(p).getSuit()).equals(evalCards.get(p1).getSuit()) 
		    && (evalCards.get(p).getSuit()).equals(evalCards.get(p2).getSuit())
		    && (evalCards.get(p).getSuit()).equals(evalCards.get(p3).getSuit())
		    && (evalCards.get(p).getSuit()).equals(evalCards.get(p4).getSuit())  )  
		{
                   System.out.print("STRAIGHT FLUSH!@@@@@@@@@@@@!!@@!@!@!@!");
		   if(bestHand > 2)
		       bestHand = 2;
		}
 */
            }
       }
          }
   }




   public void fourOfKind(ArrayList<Card> x)
   {
      ArrayList<Integer> elements = new ArrayList<>();
      int [] myList = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
      int [] returnList = { 0, 0, 0, 0, 0, 0, 0 };

      for(int i = 0; i < evalCards.size(); i++)
      {
         elements.add(evalCards.get(i).getRank());
      }

      for(int i =0; i < elements.size(); i++)
      {
          int val = elements.get(i);
          myList[val] = myList[val] + 1;
      }

//      System.out.println(elements);

      int pair1 = 0;
      int pair2 = 0;
      int countPair = 0;
      int check = 0;
      int k = 0;

      for(int i = 0; i < 16; i++)
      {
         if(myList[i] ==4)
         {
            pair1 = i;
            returnList[k] = pair1;
            k++;
         }
      } 

     int handCounter = 0;
     int tableCounter = 0;
     int phandCounter = 0;
     int ptableCounter = 0;
     for(int i =0; i < elements.size(); i++)
     {
             if(i < 4)
             {
                if(elements.get(i) == returnList[0])
                {
                   handCounter++;
                }
                if(elements.get(i) == returnList[1])
                {
                   phandCounter++;
                }
             }
             if(i >= 4)
             {
                if(elements.get(i) == returnList[0])
                {
                   tableCounter++;
                }
                if(elements.get(i) == returnList[1])
                {
                   ptableCounter++;
                }
             }

            int compare = handCounter + tableCounter;
          if(handCounter == 3 || tableCounter == 4)
          {
             break;
          }
          else if(compare == 4 && returnList[0] > 0)
          {
             System.out.print("A correct 4 of a kind is: ");
             System.out.println(returnList[0]);
             i = 100;
	     if(bestHand > 3)
	         bestHand = 3;
          }
           int pcompare = phandCounter + ptableCounter;
           if(phandCounter == 3 && ptableCounter == 4)
          {
             break;
          }
          else if(compare >= 4 && returnList[1] > 0)
          {
             System.out.print("A correct 4 of a kind is: ");
             System.out.println(returnList[1]);
             i = 100;
	     if(bestHand > 3)
	        bestHand = 3;

          }
      }
   }

   public void highCard()
   {
      double maxValue = 0;
      for(int i = 0; i < evalCards.size(); i++)
      {
        if( (evalCards.get(i).getRank()) > maxValue)
        {
           maxValue = evalCards.get(i).getRank();
        }
      }
       if(bestHand > 10)
           bestHand = 10 - (maxValue/100); 
   }
  
   public void Pair(ArrayList<Card> x)
   {
      ArrayList<Integer> elements = new ArrayList<>();
      int [] myList = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
      int [] returnList = { 0, 0, 0, 0, 0, 0, 0 };

      for(int i = 0; i < evalCards.size(); i++)
      {
         elements.add(evalCards.get(i).getRank());
      }

      for(int i =0; i < elements.size(); i++)
      {
          int val = elements.get(i);
          myList[val] = myList[val] + 1;
      }
 
      int pair1 = 0;
      int pair2 = 0;
      int countPair = 0;
      int check = 0;
      int k = 0;

      for(int i = 0; i < 16; i++)
      {
         if(myList[i] ==2)
         {
            pair1 = i;
            returnList[k] = pair1;
            k++;
         }
      }
      if(pair1 >= 2)
      {
         returnList[0] = pair1;
         System.out.print("The highest pair is : ");
         System.out.println(returnList[0]);     
         if(bestHand > 9)
	     bestHand = 9;
      }
   }

   public void fullHouse(ArrayList<Card> x)
   {
      
   }

   public void threeOfKind(ArrayList<Card> x)
   {
   
      ArrayList<Integer> elements = new ArrayList<>();
      int [] myList = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
      int [] returnList = { 0, 0, 0, 0, 0, 0, 0 };
      int [] returnListPair = { 0, 0, 0, 0, 0, 0, 0 };
/*
      evalCards.addAll(cards);
      evalCards.addAll(x);
*/
      for(int i = 0; i < evalCards.size(); i++)
      {
         elements.add(evalCards.get(i).getRank());
      }

      for(int i =0; i < elements.size(); i++)
      {
          int val = elements.get(i);
	  myList[val] = myList[val] + 1;
      }
      
  //    System.out.println(elements);

      int pair1 = 0;
      int pair2 = 0;
      int countPair = 0;
      int check = 0;
      int k = 0;

      for(int i = 0; i < 16; i++)
      {
         if(myList[i] ==3)
	 {
	    pair1 = i;
	    returnList[k] = pair1;
	    k++;
	 }
      }

     int handCounter = 0;
     int tableCounter = 0;
     int phandCounter = 0;
     int ptableCounter = 0;
     for(int i =0; i < elements.size(); i++)
     {
             if(i < 4)
             {
                if(elements.get(i) == returnList[0])
                {
                   handCounter++;
                }
		if(elements.get(i) == returnList[1])
		{
                   phandCounter++;
		}
             }
             if(i >= 4)
             {
                if(elements.get(i) == returnList[0])
                {
                   tableCounter++;
                }
		if(elements.get(i) == returnList[1])
		{
                   ptableCounter++;
		}
             }
	     
//	     System.out.print(handCounter);
//	     System.out.print(", ");
//	     System.out.println(tableCounter);
         int compare = handCounter + tableCounter;
          if((handCounter == 3 && tableCounter == 0) || 
             (handCounter == 4 && tableCounter == 0))
          {
             break;
          }
          else if(compare >= 3 && returnList[0] > 0)
          {
             System.out.print("A correct 3 of a kind is: ");
             System.out.println(returnList[0]);
	     i = 100;
	     if(bestHand > 7)
	        bestHand = 7;
          }
           int pcompare = phandCounter + ptableCounter;
           if((phandCounter == 3 && ptableCounter == 0) ||
             (phandCounter == 4 && ptableCounter == 0))
          {
             break;
          }
          else if(compare >= 3 && returnList[1] > 0)
          {
             System.out.print("A correct 3 of a kind is: ");
             System.out.println(returnList[1]);
             i = 100;
	     if(bestHand > 7)
	        bestHand = 7;
	        
          }
      }
      int HC = handCounter;
      int TC = tableCounter;
    k = 0;
    for(int i = 0; i < 16; i++)
      {
         if(myList[i] ==2)
         {
            pair1 = i;
            returnListPair[k] = pair1;
            k++;
         }
      } 
      
    for(int p = 0; p < 5; p++)
    {     
       int resetHand = HC;
       int resetTable = TC;
       for(int i =0; i < elements.size(); i++)
       {
          if(i < 4)
          {
             if(elements.get(i) == returnListPair[p])
             {
                resetHand++;
	//	System.out.println(resetHand);
             }
          }
          if(i >= 4)
          {
             if(elements.get(i) == returnListPair[p])
             {
                resetTable++;
	//	System.out.println(resetTable);
             }
          }

          if(resetHand == 2 && resetTable == 3)
          {
             System.out.print("We have a full house!!!!!!!!!");
	     i = 100;
	     if(bestHand > 4)
	        bestHand = 4;
          }
       }
    }
}


//evaCards takes in the hand cards + the 5 cards on table
//myList determines how many duplicate numbers there are. For example,
//if there are four 6s, then at position 6, it will have the number 4.
//returnList contains the suitable numbers for each function that satisfy the fu//nctions property.
//Each function has a counter to take 2 cards from the initial 4 of evalcards which is the hand, and 3 cards from the table.
   public void twoPair(ArrayList<Card> x)
   {
      ArrayList<Integer> elements = new ArrayList<>();
      int [] myList = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
      int [] returnList = { 0, 0, 0, 0, 0, 0, 0 };
      
      evalCards.clear();
      elements.clear();
      evalCards.addAll(cards);
      evalCards.addAll(x);
      cards.clear();
    //  System.out.println(evalCards);
      for(int i = 0; i < evalCards.size(); i++)
      {
         elements.add(evalCards.get(i).getRank());
      }
    

      for(int i =0; i < elements.size(); i++)
      {
         int val = elements.get(i);
	 myList[val] = myList[val] + 1;
      }

      System.out.println(elements);
      boolean retval = elements.contains(4);
      for(int i =1; i < 15; i++)
      {
         System.out.print(myList[i]);
	 System.out.print(" ");
      }
 
      int pair1 = 0; 
      int pair2 = 0;
      int countPair = 0;
      int check = 0;
      int k = 0;
      for(int i =0; i < 16; i++)
      {
         if(myList[i] >= 2 && countPair != 1)
	 {
	    countPair = 1;
            pair1 = i;
	    returnList[k] = pair1;
	    k++;
	 }
	 if(myList[i] >= 2 && pair1 != i && countPair == 1)
	 {
	   countPair = 0;
           pair2 = i;
	   returnList[k] = pair2;
	   k++;
	 }
      }
          System.out.println("");
	 // System.out.print(pair1);
	 // System.out.print(", ");
	 // System.out.println(pair2);
	 // System.out.println("All possible pairs are: ");
	  
	  /*
	  for(int w = 0; w < 6; w++)
	  {
	     if(returnList[w] != 0)
	     {
                System.out.print(returnList[w]);
	        System.out.print(", ");
             }
	  }
	  */
          int handCounter = 0;
	  int tableCounter = 0;
 
          //used to check values in returnList
          int a = 0;
	  int b = 1;
	  


	  for(int i =0; i < elements.size(); i++)
	  {
	     if(i < 4)
	     {
	        if(elements.get(i) == returnList[0] || 
		   elements.get(i) == returnList[1])
	        {
                   handCounter++;
	        }
             }
	     if(i >= 4)
	     {
	        if(elements.get(i) == returnList[0] || 
		   elements.get(i) == returnList[1])
	        {
                   tableCounter++;
	        }
	     }
	       
             if((handCounter >= 2 && tableCounter >= 2) ||
	        (handCounter == 1 && tableCounter == 3))
	     {
	        System.out.print("A correct 2 pair is: ");
	        System.out.print(returnList[0]);
	        System.out.print(", ");
	        System.out.println(returnList[1]);
		i = 100;
		if(bestHand > 8)
		   bestHand = 8;
	     }
          }
	  handCounter = 0;
	  tableCounter = 0;
	  a++;
	  b++;
	 int  hPos13Counter = 0;
	  int tPos13Counter = 0;

          for(int i =0; i < elements.size(); i++)
          {
             if(i < 4)
             {
                if(elements.get(i) == returnList[1] ||
                   elements.get(i) == returnList[2])
                {
                   handCounter++;
                }
		if(elements.get(i) == returnList[0] ||
		   elements.get(i) == returnList[2])
		{
                     hPos13Counter++;
		}
             }
             if(i >= 4)
             {
                if(elements.get(i) == returnList[1] ||
                   elements.get(i) == returnList[2])
                {
                   tableCounter++;
                }

		if(elements.get(i) == returnList[0] ||
		   elements.get(i) == returnList[2])
                {
                    tPos13Counter++;
		}
             }

             if((handCounter >= 2 && tableCounter >= 2) ||
                (handCounter == 1 && tableCounter == 3))
             {
                System.out.print("A correct 2 pair is: ");
                System.out.print(returnList[1]);
                System.out.print(", ");
                System.out.println(returnList[2]);
                i = 100;
		if(bestHand > 8)
		   bestHand = 8;
             }

	     if( (hPos13Counter >= 2 && tPos13Counter >= 2) ||
	        (hPos13Counter == 1 && tPos13Counter == 3))
		{
                   System.out.print("A correct 2 pair is: ");
                   System.out.print(returnList[0]);
                   System.out.print(", ");
                   System.out.println(returnList[2]);
		   i = 100;
		   if(bestHand > 8)
		      bestHand = 8;
 
		}
          }
   }

   public void printHand()
   {
   //   System.out.print(cards.get(0).getRank());
      for(int i =0; i<4; i++)
      {
         System.out.println(cards.get(i)); 	 
      }
      System.out.println("");
   }
}
