public class Card
{
//standard card class

   private String suit;
   private int rank;

   private String[] suits = {"Clubs", "Diamonds", "Hearts", "Spades" };
   private String[] ranks = { "two", "three", "four", "five", "six", "seven", "eight", "9", "10", "Jack", "Queen", "King", "Ace" };
   
   public void setSuit(String s)
   {
      this.suit = s;
   }

   public void setRank(int r)
   {
      this.rank = r;
   }
 
   public String toString()
   {
      StringBuilder theString = new StringBuilder("");
      theString.append(rank);
      theString.append(suit);
      return theString.toString();
   }

   public int getRank() 
   {
      return rank;
   }

   public String getSuit()
   {
      return suit;
   }
}
