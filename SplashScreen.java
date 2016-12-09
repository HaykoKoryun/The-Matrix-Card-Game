 import java.awt.Color;
 import java.awt.Dimension;
 import java.awt.Font;
 import java.awt.Frame;
 import java.awt.Graphics;
 import java.awt.Image;
 import java.awt.MediaTracker;
 import java.awt.Toolkit;
 import java.awt.Window;
 import java.lang.reflect.Method;
 import java.net.URL;
 
 public class SplashScreen extends Window
 {
   private Image splashImage;
   private String loadStat;
   private Object gui;
 
   public SplashScreen(Frame paramFrame, Image paramImage)
   {
     super(paramFrame);
     this.splashImage = paramImage;
     MediaTracker localMediaTracker = new MediaTracker(this);
     localMediaTracker.addImage(paramImage, 0);
     try
     {
       localMediaTracker.waitForID(0);
     } catch (InterruptedException localInterruptedException) {
     }
   }
 
   public void update(Graphics paramGraphics) {
     paint(paramGraphics);
   }
 
   public void paint(Graphics paramGraphics)
   {
     paramGraphics.setColor(new Color(255, 255, 255));
 
     paramGraphics.setFont(new Font("Dialog", 1, 12));
 
     paramGraphics.drawImage(this.splashImage, 0, 0, this);
 
     paramGraphics.drawString(this.loadStat, 2, 295);
     toFront();
   }
 
   public static void main(String[] paramArrayOfString)
   {
     Frame localFrame1 = new Frame();
     URL localURL = SplashScreen.class.getResource("images/GUI/splash.jpg");
     SplashScreen localSplashScreen = new SplashScreen(localFrame1, Toolkit.getDefaultToolkit().createImage(localURL));
     localSplashScreen.setSize(500, 300);
     Dimension localDimension = Toolkit.getDefaultToolkit().getScreenSize();
     localSplashScreen.setLocation((localDimension.width - 500) / 2, (localDimension.height - 300) / 2);
     Method localMethod1 = null;
     Method localMethod2 = null;
     Method localMethod3 = null;
     try {
       localMethod2 = Class.forName("GameGUI").getMethod("createGUI", new Class[] { Frame.class });
       localMethod3 = Class.forName("MatrixCardGame").getMethod("startGame", new Class[] { Object.class, Object.class });
       localMethod1 = Class.forName("tMCG_HighScores").getMethod("createDB");
     }
     catch (Throwable localThrowable) {
     }
     try {
       localSplashScreen.setStat("Loading Graphical User Interface...");
       localSplashScreen.gui = localMethod2.invoke(null, new Object[] { localFrame1 });
 
       localSplashScreen.setStat("Loading High Scores list...");
       localSplashScreen.update(localSplashScreen.getGraphics());
       Object localObject = localMethod1.invoke(null, new Object[0]);
 
       localSplashScreen.setStat("Loading Game...");
       localSplashScreen.update(localSplashScreen.getGraphics());
 
       localMethod3.invoke(null, new Object[] { localSplashScreen.gui, localObject });
     } catch (Exception localException) {
       Frame localFrame2 = (Frame)localSplashScreen.gui;
       localFrame2.dispose();
       System.exit(0);
     }
   }
 
   private void setStat(String paramString)
   {
     this.loadStat = paramString;
     show();
   }
 }

/* Location:           \\tumo.lab\UserStore\Staff\hayk.galstyan\Desktop\test\
 * Qualified Name:     SplashScreen
 * JD-Core Version:    0.6.2
 */