 import javax.swing.ImageIcon;
 
 public class MatrixCard
 {
   private String cardID;
   private String name;
   private String[] descriptions;
   private int[] attribValues;
   private boolean special;
   private ImageIcon cardImage;
   private ImageIcon cardImage_win;
   private ImageIcon cardImage_lose;
   private ImageIcon cardImage_small;
 
   public MatrixCard(String paramString1, String[] paramArrayOfString, String paramString2, int[] paramArrayOfInt)
   {
     this.name = paramString1;
     this.descriptions = paramArrayOfString;
     this.cardID = paramString2;
     this.attribValues = paramArrayOfInt;
     this.cardImage = tMCGToolKit.getCardFace(this.cardID + ".jpg");
     this.cardImage_win = tMCGToolKit.getWinnerCardFace(this.cardID + ".jpg");
     this.cardImage_lose = tMCGToolKit.getLoserCardFace(this.cardID + ".jpg");
     this.cardImage_small = tMCGToolKit.getSmallCardFace(this.cardID + ".jpg");
     if (this.attribValues[0] == 0) {
       this.special = false;
     }
     else
       this.special = true;
   }
 
   public String getName()
   {
     return this.name;
   }
 
   public String getDescription()
   {
     return getDescriptions(0);
   }
 
   public String getSpecialDescription()
   {
     if (!this.special) {
       return "";
     }
     return getDescriptions(1);
   }
 
   private String getDescriptions(int paramInt)
   {
     return this.descriptions[paramInt];
   }
 
   public int getSpecialAction()
   {
     if (!this.special) {
       return 0;
     }
     return getAttributeValue(0);
   }
 
   public int getAttributeValue(int paramInt)
   {
     return this.attribValues[paramInt];
   }
 
   public int getNumberOfAttributes()
   {
     return this.attribValues.length;
   }
 
   public boolean isSpecial()
   {
     return this.special;
   }
 
   public String getCardID()
   {
     return this.cardID;
   }
 
   public ImageIcon getSmallCardImage()
   {
     return this.cardImage_small;
   }
 
   public ImageIcon getCardImage()
   {
     return this.cardImage;
   }
 
   public ImageIcon getCardLoserImage()
   {
     return this.cardImage_lose;
   }
 
   public ImageIcon getCardWinnerImage()
   {
     return this.cardImage_win;
   }
 }

/* Location:           \\tumo.lab\UserStore\Staff\hayk.galstyan\Desktop\test\
 * Qualified Name:     MatrixCard
 * JD-Core Version:    0.6.2
 */