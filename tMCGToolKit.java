 import java.awt.Font;
 import java.awt.Image;
 import java.awt.Toolkit;
 import java.io.FileInputStream;
 import java.net.URL;
 import java.util.Calendar;
 import javax.swing.ImageIcon;
 
 public class tMCGToolKit
 {
   private static final Toolkit tk = Toolkit.getDefaultToolkit();
 
   public static ImageIcon getImageIcon(String paramString1, String paramString2)
   {
     return new ImageIcon(getImage(paramString1, paramString2));
   }
 
   public static ImageIcon getCardImageIcon(String paramString)
   {
     return new ImageIcon(getCardImage(paramString));
   }
 
   public static Image getImage(String paramString1, String paramString2)
     throws NullPointerException
   {
     URL localURL = tMCGToolKit.class.getResource(paramString2 + paramString1);
     return tk.getImage(localURL);
   }
 
   public static Image getCardImage(String paramString)
   {
     try
     {
       URL localURL = tMCGToolKit.class.getResource("images/cardFaces/" + paramString);
       return tk.getImage(localURL);
     } catch (NullPointerException localNullPointerException) {
     }
     return getImage("noCardFace.jpg", "images/CardFaces/");
   }
 
   public static ImageIcon getCardFace(String paramString)
   {
     return getCardImageIcon(paramString);
   }
 
   public static ImageIcon getWinnerCardFace(String paramString)
   {
     Image localImage = getCardImage(paramString);
     localImage = localImage.getScaledInstance(105, 131, 4);
     return new ImageIcon(localImage);
   }
 
   public static ImageIcon getLoserCardFace(String paramString)
   {
     Image localImage = getCardImage(paramString);
     localImage = localImage.getScaledInstance(90, 112, 4);
     return new ImageIcon(localImage);
   }
 
   public static ImageIcon getSmallCardFace(String paramString)
   {
     Image localImage = getCardImage(paramString);
     localImage = localImage.getScaledInstance(30, 37, 4);
     return new ImageIcon(localImage);
   }
 
   public static ImageIcon getGuiImage(String paramString)
   {
     return getImageIcon(paramString, "images/GUI/");
   }
 
   public static String padTime()
   {
     String str = "";
 
     Calendar localCalendar = Calendar.getInstance();
 
     int i = localCalendar.get(11);
     int j = localCalendar.get(12);
 
     if (i < 10) {
       str = str + "0" + i;
     }
     else {
       str = str + i;
     }
     if (j < 10) {
       str = str + "0" + j;
     }
     else {
       str = str + j;
     }
     return str;
   }
 
   public static String padDate()
   {
     String str = "";
     Calendar localCalendar = Calendar.getInstance();
 
     int i = localCalendar.get(5);
     int j = localCalendar.get(2) + 1;
     int k = localCalendar.get(1);
 
     if (i < 10) {
       str = str + "0" + i;
     }
     else {
       str = str + i;
     }
     if (j < 10) {
       str = str + "0" + j;
     }
     else {
       str = str + j;
     }
     str = str + k;
     return str;
   }
 
   public static String getReadableDate(String paramString)
   {
     String str1 = "" + Integer.valueOf(paramString.substring(0, 2)).intValue();
     String str2 = paramString.substring(2, 4);
     String str3 = paramString.substring(4, 8);
 
     switch (Integer.valueOf(str2).intValue())
     {
     case 1:
       str2 = "January";
       break;
     case 2:
       str2 = "February";
       break;
     case 3:
       str2 = "March";
       break;
     case 4:
       str2 = "April";
       break;
     case 5:
       str2 = "May";
       break;
     case 6:
       str2 = "June";
       break;
     case 7:
       str2 = "July";
       break;
     case 8:
       str2 = "August";
       break;
     case 9:
       str2 = "Septmeber";
       break;
     case 10:
       str2 = "October";
       break;
     case 11:
       str2 = "November";
       break;
     case 12:
       str2 = "December";
     }
 
     return new String(str1 + " " + str2 + " " + str3);
   }
 
   public static String getReadableTime(String paramString)
   {
     int i = Integer.valueOf(paramString.substring(0, 2)).intValue();
     String str1 = paramString.substring(2, 4);
     String str2 = "am";
     if (i > 11) {
       str2 = "pm";
       if (i > 12) {
         i -= 12;
       }
     }
     return new String(i + ":" + str1 + str2);
   }
 
   public static Font getFont(String paramString)
   {
     URL localURL = tMCGToolKit.class.getResource("data/" + paramString);
     try {
       return Font.createFont(0, new FileInputStream("data/" + paramString));
     }
     catch (Throwable localThrowable) {
     }
     return new Font(null, 0, 16);
   }
 }

/* Location:           \\tumo.lab\UserStore\Staff\hayk.galstyan\Desktop\test\
 * Qualified Name:     tMCGToolKit
 * JD-Core Version:    0.6.2
 */