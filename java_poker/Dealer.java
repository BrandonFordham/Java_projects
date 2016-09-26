import java.util.ArrayList;

public class Dealer
{
//dealer is used to set up/display the flop/turn/river

   ArrayList<Card> cards = new ArrayList<Card>();
   public void theFlop(Deck d)
   {
      for(int i = 0; i < 3; i++)
      {
         Card c;
	 c = d.drawTopOfDeck();
	 cards.add(c);
      }
   }

   public void theTurn(Deck d)
   {
      Card c;
      c = d.drawTopOfDeck();
      cards.add(c);
   }

   public ArrayList<Card> returnFlop()
   {
      return cards;
   }
   

   public ArrayList<Card> returnTurn()
   {
       return cards;
   }

   public ArrayList<Card> returnRiver()
   {
       return cards;
   }

   public void theRiver(Deck d)
   {
      Card c;
      c = d.drawTopOfDeck();
      cards.add(c);
   }

   public void dealerPrintFlop()
   {
      for(int i =0; i < 3; i++)
      {
         System.out.print(cards.get(i));
	 System.out.print(" ");
      }
      System.out.println("");
   }

   public void dealerPrintTurn()
   {
       for(int i =0; i < 4; i++)
       {
          System.out.print(cards.get(i));
	   System.out.print(" ");
       }
        System.out.println(" ");
   }

    public void dealerPrintRiver()
    {
       for(int i =0; i < 5; i++)
       {
          System.out.print(cards.get(i));
	   System.out.print(" ");
       }
        System.out.println(" ");
    }

   public ArrayList<Card> publicCards()
   {
      return cards;
   }


}
