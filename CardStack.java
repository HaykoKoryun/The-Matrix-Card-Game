 import java.util.ArrayList;
 
 public class CardStack extends ArrayList
 {
   public void addCard(MatrixCard paramMatrixCard)
   {
     add(paramMatrixCard);
   }
 
   public boolean hasMoreCards()
   {
     return !isEmpty();
   }
 
   public MatrixCard getTopCard()
   {
     return (MatrixCard)get(0);
   }
 
   public void removeTopCard()
   {
     remove(0);
   }
 
   public void removeCard(int paramInt)
   {
     remove(paramInt);
   }
 
   public void removeAllCards()
   {
     removeAll(this);
   }
 
   public Object[] getCardList()
   {
     int i = 0;
     int j = countCards();
     String[] arrayOfString = new String[j];
     Object[] arrayOfObject = new Object[j];
     MatrixCard localMatrixCard = null;
     while (i < j) {
       localMatrixCard = (MatrixCard)get(i);
       arrayOfString[i] = localMatrixCard.getName();
       arrayOfObject[i] = localMatrixCard.getSmallCardImage();
       i++;
     }
     return new Object[] { arrayOfString, arrayOfObject };
   }
 
   public String[] getCardListAsIDs()
   {
     int i = 0;
     int j = countCards();
     String[] arrayOfString = new String[j];
     MatrixCard localMatrixCard = null;
     while (i < j) {
       localMatrixCard = (MatrixCard)get(i);
       arrayOfString[i] = localMatrixCard.getCardID();
       i++;
     }
     return arrayOfString;
   }
 
   public int countCards()
   {
     int i = size();
     return i;
   }
 }

/* Location:           \\tumo.lab\UserStore\Staff\hayk.galstyan\Desktop\test\
 * Qualified Name:     CardStack
 * JD-Core Version:    0.6.2
 */