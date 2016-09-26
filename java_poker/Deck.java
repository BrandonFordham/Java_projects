import java.util.ArrayList;
import java.util.Random;

public class Deck 
{
//standard Deck class

   ArrayList<Card> cards;

   public Deck()
   {
      cards = new ArrayList<Card>();
       
      //Creates a card deck in order.
      for(int i = 0; i < 52; i++)
      {
         //Values are given as 2-14
         Card card = new Card();      
         int value = (i % 13) + 2;
         String p = Integer.toString(value);
         if(i < 13)
         {
            card.setSuit("Hearts"); 
         }
         else if((i >= 13) && (i < 26))
         {
            card.setSuit("Clubs");
         }
         else if((i >= 26) && (i < 39))
         {
            card.setSuit("Spades");
         }
         else if((i >= 39) && (i < 53))
         {
            card.setSuit("Diamonds");
         }

      card.setRank(Integer.parseInt(p));
      cards.add(card);
      }
      
      Random randomNumber = new Random();

      //Shuffles the deck i times, 10 in this instance.
      for(int i = 0; i < 10; i++)
      {
         for(int k = 0; k < 52; k++)
	 {
            int number = randomNumber.nextInt(52);
	    Card c = cards.get(k);
	    cards.set(k, cards.get(number));
	    cards.set(number, c);
	 }
      }


   }


   public void deckPrint()
   {
   
      for(int i = 0; i < cards.size(); i++)
      {
	    System.out.println("");

	 System.out.print(cards.get(i));
      }
      System.out.println("");     
      
   }

   public Card drawTopOfDeck()
   {
      return cards.remove(0);
   }

}

