 import java.util.ArrayList;
 import java.util.Iterator;
 
 public class Player
 {
   private String name;
   private CardStack cards;
   private int turns;
   private int points;
   private int playerID;
 
   public Player(String paramString1, String paramString2)
   {
     this.playerID = Integer.valueOf(paramString1).intValue();
     this.name = paramString2;
     this.cards = new CardStack();
     this.turns = 100;
     this.points = 0;
   }
 
   public String getName()
   {
     return this.name;
   }
 
   public void addCard(MatrixCard paramMatrixCard)
   {
     this.cards.addCard(paramMatrixCard);
   }
 
   public MatrixCard getTopCard()
   {
     return this.cards.getTopCard();
   }
 
   public void removeTopCard()
   {
     this.cards.removeTopCard();
   }
 
   public boolean hasMoreCards()
   {
     return this.cards.hasMoreCards();
   }
 
   public void quit()
   {
     this.cards.removeAllCards();
   }
 
   public Object[] getCardList()
   {
     return this.cards.getCardList();
   }
 
   public int countCards()
   {
     int i = this.cards.countCards();
     return i;
   }
 
   public void newGame()
   {
     this.cards.clear();
   }
 
   public void removeCard(int paramInt)
   {
     this.cards.removeCard(paramInt);
   }
 
   public void addPoints(int paramInt)
   {
     this.points += paramInt;
   }
 
   public void useTurn()
   {
     this.turns -= 1;
   }
 
   public int getTurnsLeft()
   {
     return this.turns;
   }
 
   public int score()
   {
     return this.turns * 5 + this.points + countCards() * 10;
   }
 
   public int getPlayerID()
   {
     return this.playerID;
   }
 
   public void moveCardsToBottom(ArrayList paramArrayList)
   {
     String[] arrayOfString = this.cards.getCardListAsIDs();
     int i = 0;
     int[] arrayOfInt = new int[3];
     int j = arrayOfString.length;
     int k = 0;
     while (k < j) {
       String str1 = arrayOfString[k];
       Iterator localIterator = paramArrayList.iterator();
       int i1 = 0;
       while ((i1 == 0) && (localIterator.hasNext())) {
         String str2 = (String)localIterator.next();
         if (str2.equals(str1)) {
           i1 = 1;
         }
       }
       if (i1 != 0) {
         arrayOfInt[i] = (k + 1);
         i++;
       }
       k++;
     }
 
     int m = 0;
     int n = 0;
     try {
       while (arrayOfInt[m] != 0) {
         addCard(getCard(arrayOfInt[m] - 1 - n));
         removeCard(arrayOfInt[m] - 1 - n);
         m++;
         n++;
       }
     }
     catch (Throwable localThrowable)
     {
     }
   }
 
   public MatrixCard getCard(int paramInt)
   {
     return (MatrixCard)this.cards.get(paramInt);
   }
 }

/* Location:           \\tumo.lab\UserStore\Staff\hayk.galstyan\Desktop\test\
 * Qualified Name:     Player
 * JD-Core Version:    0.6.2
 */