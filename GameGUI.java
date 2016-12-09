 import java.awt.Color;
 import java.awt.Component;
 import java.awt.Container;
 import java.awt.Dimension;
 import java.awt.FlowLayout;
 import java.awt.Font;
 import java.awt.Frame;
 import java.awt.Graphics;
 import java.awt.Graphics2D;
 import java.awt.GridLayout;
 import java.awt.Image;
 import java.awt.LayoutManager;
 import java.awt.Point;
 import java.awt.RenderingHints;
 import java.awt.Toolkit;
 import java.awt.event.ActionEvent;
 import java.awt.event.ActionListener;
 import java.awt.event.MouseAdapter;
 import java.awt.event.MouseEvent;
 import java.awt.event.WindowAdapter;
 import java.awt.event.WindowEvent;
 import java.io.IOException;
 import javax.swing.BorderFactory;
 import javax.swing.BoxLayout;
 import javax.swing.ButtonGroup;
 import javax.swing.ButtonModel;
 import javax.swing.ImageIcon;
 import javax.swing.JButton;
 import javax.swing.JDialog;
 import javax.swing.JEditorPane;
 import javax.swing.JFrame;
 import javax.swing.JLabel;
 import javax.swing.JMenu;
 import javax.swing.JMenuBar;
 import javax.swing.JMenuItem;
 import javax.swing.JOptionPane;
 import javax.swing.JPanel;
 import javax.swing.JProgressBar;
 import javax.swing.JRadioButton;
 import javax.swing.JScrollPane;
 import javax.swing.JTabbedPane;
 import javax.swing.JTextArea;
 import javax.swing.JTextField;
 import javax.swing.border.Border;
 
 public class GameGUI extends JFrame
 {
   private Container cPane;
   private WindowAdapter winAdapter;
   private static final Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize();
   private Border borderType;
   private JTextField player1nameField;
   private JTextField player2nameField;
   private JLabel backImage;
   private JLabel logo;
   private JLabel cardFaceBack;
   private JPanel mainPanel;
   private JPanel cardPanel;
   private JPanel descriptionPanel;
   private JPanel cardAttributePanel;
   private JPanel cardAttributeNames;
   private JPanel buttonsPanel;
   private JPanel gameButtonsPanel;
   private JButton newGame;
   private JButton countCards;
   private JButton cardList;
   private Point cardLoc;
   private boolean playerNamesSet;
   private int game;
   private boolean madePlayChoice;
   private int playChoice;
   private int counter;
   private boolean dialogueClose;
   private ActionListener listener;
   private Frame externalFrame;
   private boolean wantToQuit;
   private Font MatrixFont;
   private JMenuBar gameOptionsBar;
   private JProgressBar progressbar;
   private static final Image appImage = tMCGToolKit.getImage("icon_s.jpg", "data/");
   private ButtonGroup p1RBgroup;
   private ButtonGroup p2RBgroup;
 
   public GameGUI(Frame paramFrame)
   {
     super("The Matrix Card Game");
     setIconImage(appImage);
     this.externalFrame = paramFrame;
 
     this.winAdapter = new WindowAdapter()
     {
       public void windowClosing(WindowEvent paramAnonymousWindowEvent)
       {
         GameGUI.this.dispose();
         System.exit(0);
       }
     };
     addWindowListener(this.winAdapter);
 
     setDefaultCloseOperation(0);
 
     setResizable(false);
 
     this.listener = new GameGUI.GameMainMenuListener();
 
     this.counter = 0;
     this.playerNamesSet = false;
     this.game = 0;
     this.madePlayChoice = false;
     this.playChoice = 0;
     this.dialogueClose = false;
     this.wantToQuit = false;
     this.MatrixFont = tMCGToolKit.getFont("Matrix.ttf");
     this.MatrixFont = this.MatrixFont.deriveFont(20.0F);
 
     this.borderType = BorderFactory.createEmptyBorder();
 
     this.cPane = getContentPane();
 
     this.backImage = new JLabel(tMCGToolKit.getGuiImage("code.jpg"));
     this.logo = new JLabel(tMCGToolKit.getGuiImage("logo.jpg"));
 
     this.progressbar = new JProgressBar();
     this.progressbar.setStringPainted(true);
 
     this.backImage.add(this.logo);
     this.logo.setSize(323, 97);
     this.logo.setLocation(new Point(104, 6));
     this.cPane.add(this.backImage);
 
     this.gameOptionsBar = new JMenuBar();
     JMenu localJMenu1 = new JMenu("Utilities");
     JMenu localJMenu2 = new JMenu("Get all games played by...");
     JMenuItem localJMenuItem1 = new JMenuItem("...Name");
     JMenuItem localJMenuItem2 = new JMenuItem("...player ID");
     localJMenuItem1.setActionCommand("4");
     localJMenuItem2.setActionCommand("5");
     localJMenuItem1.addActionListener(this.listener);
     localJMenuItem2.addActionListener(this.listener);
     localJMenu2.add(localJMenuItem1);
     localJMenu2.add(localJMenuItem2);
     localJMenu1.add(localJMenu2);
     this.gameOptionsBar.add(localJMenu1);
   }
 
   private void setupBackground()
   {
     setVisible(false);
 
     this.backImage.removeAll();
 
     this.backImage.add(this.logo);
   }
 
   public void getPlayerNamesDialog()
   {
     setupBackground();
     remove(this.gameOptionsBar);
 
     final JLabel localJLabel1 = new JLabel(tMCGToolKit.getGuiImage("p1ID.jpg"));
     final JLabel localJLabel2 = new JLabel(tMCGToolKit.getGuiImage("p2ID.jpg"));
 
     this.player1nameField = new JTextField("player 1 name");
     this.player2nameField = new JTextField("player 2 name");
 
     JButton localJButton = new JButton(tMCGToolKit.getGuiImage("enter.jpg"));
 
     localJLabel1.add(this.player1nameField);
     localJLabel2.add(this.player2nameField);
 
     this.player1nameField.setSize(264, 16);
     this.player2nameField.setSize(264, 16);
 
     this.player1nameField.setBorder(this.borderType);
     this.player2nameField.setBorder(this.borderType);
 
     this.player1nameField.setLocation(new Point(106, 45));
     this.player2nameField.setLocation(new Point(106, 45));
 
     this.backImage.add(localJLabel1);
     this.backImage.add(localJLabel2);
 
     localJLabel1.setSize(393, 75);
     localJLabel2.setSize(393, 75);
 
     localJLabel1.setBorder(this.borderType);
     localJLabel2.setBorder(this.borderType);
 
     localJLabel1.setLocation(new Point(13, 104));
     localJLabel2.setLocation(new Point(13, 185));
 
     this.backImage.add(localJButton);
 
     localJButton.setSize(248, 39);
 
     localJButton.setBorder(this.borderType);
 
     localJButton.setLocation(new Point(127, 260));
 
     localJButton.setActionCommand("6");
     localJButton.addActionListener(this.listener);
 
     ActionListener local2 = new ActionListener()
     {
       public void actionPerformed(ActionEvent paramAnonymousActionEvent)
       {
         localJLabel1.setIcon(tMCGToolKit.getGuiImage(paramAnonymousActionEvent.getActionCommand() + ".jpg"));
         GameGUI.this.repaint();
       }
     };
     ActionListener local3 = new ActionListener()
     {
       public void actionPerformed(ActionEvent paramAnonymousActionEvent)
       {
         localJLabel2.setIcon(tMCGToolKit.getGuiImage(paramAnonymousActionEvent.getActionCommand() + ".jpg"));
         GameGUI.this.repaint();
       }
     };
     JRadioButton localJRadioButton1 = new JRadioButton("ID", true);
     localJRadioButton1.setActionCommand("p1ID");
     localJRadioButton1.addActionListener(local2);
 
     JRadioButton localJRadioButton2 = new JRadioButton("Name");
     localJRadioButton2.setActionCommand("p1name");
     localJRadioButton2.addActionListener(local2);
 
     JRadioButton localJRadioButton3 = new JRadioButton("ID", true);
     localJRadioButton3.setActionCommand("p2ID");
     localJRadioButton3.addActionListener(local3);
 
     JRadioButton localJRadioButton4 = new JRadioButton("Name");
     localJRadioButton4.setActionCommand("p2name");
     localJRadioButton4.addActionListener(local3);
 
     this.p1RBgroup = new ButtonGroup();
     this.p1RBgroup.add(localJRadioButton1);
     this.p1RBgroup.add(localJRadioButton2);
 
     localJLabel1.add(localJRadioButton1);
     localJRadioButton1.setSize(40, 20);
     localJRadioButton1.setLocation(264, 17);
 
     localJLabel1.add(localJRadioButton2);
     localJRadioButton2.setSize(60, 20);
     localJRadioButton2.setLocation(300, 17);
 
     this.p2RBgroup = new ButtonGroup();
     this.p2RBgroup.add(localJRadioButton3);
     this.p2RBgroup.add(localJRadioButton4);
 
     localJLabel2.add(localJRadioButton3);
     localJRadioButton3.setSize(40, 20);
     localJRadioButton3.setLocation(264, 16);
 
     localJLabel2.add(localJRadioButton4);
     localJRadioButton4.setSize(60, 20);
     localJRadioButton4.setLocation(300, 16);
 
     pack();
     setLocation((screenDimension.width - 500) / 2, (screenDimension.height - 300) / 2);
     setVisible(true);
   }
 
   public synchronized String getName()
   {
     while (!this.playerNamesSet)
       try {
         wait();
       }
       catch (InterruptedException localInterruptedException) {
       }
     String str1 = "";
     String str2 = "";
     int i = 0;
     if (this.counter == 0) {
       str2 = this.player1nameField.getText();
 
       if (this.p1RBgroup.getSelection().getActionCommand().equals("p1name"))
         str1 = "%";
       else {
         try
         {
           i = Integer.valueOf(str2).intValue();
         }
         catch (Throwable localThrowable1) {
           return "" + i;
         }
       }
       return str1 + str2;
     }
 
     str2 = this.player1nameField.getText();
 
     if (this.p2RBgroup.getSelection().getActionCommand().equals("p2name"))
       str1 = "%";
     else {
       try
       {
         i = Integer.valueOf(str2).intValue();
       }
       catch (Throwable localThrowable2) {
         return "" + i;
       }
     }
     return str1 + this.player2nameField.getText();
   }
 
   public void playerNameRetrieved()
   {
     if (this.counter == 0) {
       this.counter += 1;
     }
     else
       this.counter = 0;
   }
 
   public void playerNameRetrievalFailed()
   {
     this.playerNamesSet = false;
   }
 
   private synchronized void setNames()
   {
     if ((this.player1nameField.getText().length() > 0) && (this.player2nameField.getText().length() > 0)) {
       this.playerNamesSet = true;
       notifyAll();
     }
   }
 
   public void showGameOptions()
   {
     setupBackground();
 
     JButton localJButton1 = new JButton(tMCGToolKit.getGuiImage("mGame.jpg"));
 
     localJButton1.setRolloverIcon(tMCGToolKit.getGuiImage("mGame_over.jpg"));
 
     localJButton1.setRolloverEnabled(true);
 
     JButton localJButton2 = new JButton(tMCGToolKit.getGuiImage("help.jpg"));
 
     localJButton2.setRolloverIcon(tMCGToolKit.getGuiImage("help_over.jpg"));
 
     localJButton2.setRolloverEnabled(true);
 
     JButton localJButton3 = new JButton(tMCGToolKit.getGuiImage("hScores.jpg"));
 
     localJButton3.setRolloverIcon(tMCGToolKit.getGuiImage("hScores_over.jpg"));
 
     localJButton3.setRolloverEnabled(true);
 
     this.backImage.add(localJButton1);
     this.backImage.add(localJButton2);
     this.backImage.add(localJButton3);
 
     localJButton1.setSize(338, 28);
     localJButton2.setSize(127, 33);
     localJButton3.setSize(201, 35);
 
     localJButton1.setBorder(this.borderType);
     localJButton2.setBorder(this.borderType);
     localJButton3.setBorder(this.borderType);
 
     localJButton1.setToolTipText("Play \"The Matrix Game\"");
     localJButton2.setToolTipText("Find out how to play the games.");
     localJButton3.setToolTipText("Display the highscores. Can you beat the top score?!");
 
     localJButton1.setMnemonic(77);
     localJButton2.setMnemonic(72);
 
     localJButton1.setLocation(new Point(84, 121));
     localJButton2.setLocation(new Point(186, 206));
     localJButton3.setLocation(new Point(160, 251));
 
     localJButton1.setActionCommand("1");
     localJButton1.addActionListener(this.listener);
 
     localJButton2.setActionCommand("3");
     localJButton2.addActionListener(this.listener);
 
     localJButton3.setActionCommand("2");
     localJButton3.addActionListener(this.listener);
 
     setLocation((screenDimension.width - 500) / 2, (screenDimension.height - 300) / 2);
 
     setJMenuBar(this.gameOptionsBar);
     pack();
 
     this.externalFrame.dispose();
     setVisible(true);
   }
 
   public synchronized int getGame()
   {
     while (this.game == 0)
       try {
         wait();
       }
       catch (InterruptedException localInterruptedException) {
       }
     return this.game;
   }
 
   private synchronized void setGame(int paramInt)
   {
     this.game = paramInt;
     notifyAll();
   }
 
   public void prepareMainGameResources()
   {
     dispose();
 
     this.cPane.removeAll();
 
     this.cardFaceBack = new JLabel(tMCGToolKit.getCardFace("cardBack.jpg"));
 
     this.cardFaceBack.setSize(206, 242);
 
     this.cardFaceBack.setBorder(BorderFactory.createLoweredBevelBorder());
 
     this.mainPanel = new JPanel();
     this.mainPanel.setLayout(new FlowLayout(1, 3, 3));
 
     this.cardPanel = new JPanel();
     this.cardPanel.setLayout(new FlowLayout(1, 3, 3));
     this.cardPanel.setBorder(BorderFactory.createRaisedBevelBorder());
     this.cardPanel.setSize(600, 300);
 
     this.descriptionPanel = new JPanel();
     this.descriptionPanel.setLayout(new BoxLayout(this.descriptionPanel, 1));
     this.descriptionPanel.setBorder(BorderFactory.createLoweredBevelBorder());
     this.descriptionPanel.setSize(400, 300);
 
     this.cardPanel.add(this.cardFaceBack);
 
     this.cardPanel.add(this.descriptionPanel);
 
     this.mainPanel.add(this.cardPanel);
 
     this.cardAttributePanel = new JPanel();
     this.cardAttributePanel.setLayout(new BoxLayout(this.cardAttributePanel, 0));
     this.cardAttributePanel.setSize(400, 400);
 
     this.cardAttributeNames = new JPanel();
     this.cardAttributeNames.setLayout(new GridLayout(4, 1, 2, 2));
     this.cardAttributeNames.setSize(300, 500);
 
     JLabel localJLabel1 = new JLabel("Mastery of the Matrix:");
     JLabel localJLabel2 = new JLabel("Navigational Skills:");
     JLabel localJLabel3 = new JLabel("Hacking the Matrix:");
     JLabel localJLabel4 = new JLabel("Fighting in the Matrix:");
 
     this.cardAttributeNames.add(localJLabel1);
     this.cardAttributeNames.add(localJLabel2);
     this.cardAttributeNames.add(localJLabel3);
     this.cardAttributeNames.add(localJLabel4);
 
     this.cardAttributePanel.add(this.cardAttributeNames);
 
     this.buttonsPanel = new JPanel();
     this.buttonsPanel.setLayout(new FlowLayout(0, 3, 3));
     this.buttonsPanel.setSize(400, 300);
 
     JPanel localJPanel = new JPanel();
     localJPanel.setSize(100, 340);
     localJPanel.setLayout(new GridLayout(3, 1, 10, 5));
 
     this.newGame = new JButton("Quit");
     this.newGame.setSize(100, 100);
     this.countCards = new JButton("Count Cards");
     this.countCards.setSize(100, 100);
     this.cardList = new JButton("List my Cards");
     this.cardList.setSize(100, 100);
 
     this.newGame.setActionCommand("7");
     this.newGame.addActionListener(this.listener);
 
     this.countCards.setActionCommand("8");
     this.countCards.addActionListener(this.listener);
 
     this.cardList.setActionCommand("9");
     this.cardList.addActionListener(this.listener);
 
     localJPanel.add(this.newGame);
     localJPanel.add(this.countCards);
     localJPanel.add(this.cardList);
 
     this.buttonsPanel.add(localJPanel);
 
     this.gameButtonsPanel = new JPanel();
     this.gameButtonsPanel.setSize(100, 340);
     this.gameButtonsPanel.setLayout(new GridLayout(5, 1, 5, 5));
 
     JButton localJButton1 = new JButton("Compare \"Mastery of the Matrix\"");
     JButton localJButton2 = new JButton("Compare \"Navigational Skills\"");
     JButton localJButton3 = new JButton("Compare \"Matrix Hacking\"");
     JButton localJButton4 = new JButton("Compare \"Fighting in the Matrix\"");
 
     localJButton1.setActionCommand("10");
     localJButton1.addActionListener(this.listener);
     localJButton2.setActionCommand("11");
     localJButton2.addActionListener(this.listener);
     localJButton3.setActionCommand("12");
     localJButton3.addActionListener(this.listener);
     localJButton4.setActionCommand("13");
     localJButton4.addActionListener(this.listener);
 
     this.gameButtonsPanel.add(localJButton1);
     this.gameButtonsPanel.add(localJButton2);
     this.gameButtonsPanel.add(localJButton3);
     this.gameButtonsPanel.add(localJButton4);
 
     this.buttonsPanel.add(this.gameButtonsPanel);
 
     this.mainPanel.add(this.cardAttributePanel);
 
     this.mainPanel.add(this.buttonsPanel);
 
     this.cPane.add(this.mainPanel);
 
     this.cardLoc = new Point(25, 26);
 
     setGame(2);
 
     setSize(610, 452);
   }
 
   public void playMatrixGame(boolean paramBoolean1, String paramString1, boolean paramBoolean2, String paramString2, String paramString3, String paramString4, String paramString5, ImageIcon paramImageIcon, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6)
   {
     if (paramBoolean1)
     {
       try {
         this.gameButtonsPanel.remove(4);
       }
       catch (Exception localException) {
       }
       dispose();
 
       this.cardFaceBack.removeAll();
 
       this.descriptionPanel.removeAll();
       try
       {
         this.cardAttributePanel.remove(1);
       }
       catch (ArrayIndexOutOfBoundsException localArrayIndexOutOfBoundsException)
       {
       }
 
       JLabel localJLabel1 = new JLabel(paramImageIcon);
 
       localJLabel1.setBorder(this.borderType);
 
       this.cardFaceBack.add(localJLabel1);
 
       localJLabel1.setSize(150, 187);
 
       localJLabel1.setLocation(this.cardLoc);
 
       JTextArea localJTextArea = new JTextArea(paramString4, 6, 32);
       localJTextArea.setEditable(false);
       localJTextArea.setLineWrap(true);
       localJTextArea.setWrapStyleWord(true);
 
       JScrollPane localJScrollPane = new JScrollPane(localJTextArea);
 
       localJScrollPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(128, 128, 128), 2), "Character Description", 3, 2));
       int i;
       Object localObject1; Object localObject2; Object localObject3;
       if (paramBoolean2){
         localObject1 = new JTextArea(paramString2, 4, 28);
         ((JTextArea)localObject1).setEditable(false);
         ((JTextArea)localObject1).setLineWrap(true);
         ((JTextArea)localObject1).setWrapStyleWord(true);
 
         localObject2 = new JScrollPane((Component)localObject1);
 
         ((JScrollPane)localObject2).setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(64, 64, 64), 2), "Special Action", 1, 2));
 
         this.descriptionPanel.add((Component)localObject2);
 
         localObject3 = new JButton("Special Action");
         ((JButton)localObject3).setSize(100, 100);
         ((JButton)localObject3).setActionCommand("14");
         ((JButton)localObject3).addActionListener(this.listener);
         this.gameButtonsPanel.add((Component)localObject3);
 
         i = 490;
       }
       else
       {
         i = 452;
       }
 
       this.descriptionPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLoweredBevelBorder(), BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(32, 32, 32), 2), "ID: " + paramString5 + " | Name: " + paramString3, 2, 2)));
 
       this.descriptionPanel.add(localJScrollPane);
 
       localObject1 = new JPanel();
       ((JPanel)localObject1).setLayout(new GridLayout(4, 1, 2, 2));
       ((JPanel)localObject1).setSize(200, 500);
 
       localObject2 = new JLabel(" " + paramInt1 + " ");
       localObject3 = new JLabel(" " + paramInt2 + " ");
       JLabel localJLabel2 = new JLabel(" " + paramInt3 + " ");
       JLabel localJLabel3 = new JLabel(" " + paramInt4 + " ");
 
       ((JLabel)localObject2).setBorder(BorderFactory.createRaisedBevelBorder());
       ((JLabel)localObject3).setBorder(BorderFactory.createRaisedBevelBorder());
       localJLabel2.setBorder(BorderFactory.createRaisedBevelBorder());
       localJLabel3.setBorder(BorderFactory.createRaisedBevelBorder());
 
       ((JPanel)localObject1).add((Component)localObject2);
       ((JPanel)localObject1).add((Component)localObject3);
       ((JPanel)localObject1).add(localJLabel2);
       ((JPanel)localObject1).add(localJLabel3);
 
       this.cardAttributePanel.add((Component)localObject1);
 
       this.cardAttributePanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(32, 32, 32), 2), paramString3 + "'s Abilities", 1, 2));
 
       setTitle("The Matrix Card Game [" + paramString1 + "] playing... >" + paramInt5 + "< turns left! Current Score: " + paramInt6);
 
       setLocation((screenDimension.width - 610) / 2, (screenDimension.height - i) / 2);
       setSize(610, i);
       setVisible(true);
     }
   }
 
   public synchronized int getPlayChoice()
   {
     while (!this.madePlayChoice)
       try {
         wait();
       }
       catch (InterruptedException localInterruptedException) {
       }
     this.madePlayChoice = false;
     return this.playChoice;
   }
 
   private synchronized void setPlayChoice(int paramInt)
   {
     this.playChoice = paramInt;
     this.madePlayChoice = true;
     notifyAll();
   }
 
   public void displayWinnerOfHand(ImageIcon paramImageIcon1, ImageIcon paramImageIcon2, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5)
   {
     JDialog localJDialog = new JDialog(this, paramString1 + " wins the hand!", true);
     localJDialog.setSize(400, 320);
     localJDialog.setDefaultCloseOperation(0);
 
     Container localContainer = localJDialog.getContentPane();
 
     JPanel localJPanel = getDialogPanel(localJDialog, 0, paramString5, paramString1, paramString2, paramImageIcon1, paramImageIcon2, paramString3, paramString4, "");
 
     localContainer.add(localJPanel);
     localJDialog.setLocationRelativeTo(this);
     localJDialog.setVisible(true);
   }
 
   private JPanel getDialogPanel(JDialog paramJDialog, int paramInt, String paramString1, String paramString2, String paramString3, ImageIcon paramImageIcon1, ImageIcon paramImageIcon2, String paramString4, String paramString5, String paramString6)
   {
     final JDialog localJDialog = paramJDialog;
 
     String str = new String("");
 
     Color localColor1 = new Color(0, 1, 252);
     Color localColor2 = new Color(0, 1, 252);
 
     Dimension localDimension = new Dimension(200, 220);
 
     Border localBorder1 = BorderFactory.createLoweredBevelBorder();
     Border localBorder2 = localBorder1;
     Border localBorder3 = localBorder1;
 
     switch (paramInt)
     {
     case 0:
       str = paramString2 + " won the hand!";
 
       localColor2 = new Color(255, 203, 0);
 
       localBorder2 = BorderFactory.createRaisedBevelBorder();
       break;
     case 1:
       str = "Last hand was a draw! " + paramString6 + " cards in the pile for the next winner.";
     }
 
     JPanel localJPanel1 = new JPanel();
     localJPanel1.setLayout(new BoxLayout(localJPanel1, 1));
     localJPanel1.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(251, 1, 2), 2), "Compared " + paramString1, 2, 2));
 
     JPanel localJPanel2 = new JPanel();
     localJPanel2.setLayout(new FlowLayout(1, 3, 3));
     localJPanel2.add(new JLabel(str));
 
     localJPanel1.add(localJPanel2);
 
     JPanel localJPanel3 = new JPanel();
     localJPanel3.setLayout(new GridLayout(1, 2, 3, 3));
 
     JPanel localJPanel4 = createPlayerPanel(new FlowLayout(0), localDimension, paramString1, localColor1, localBorder2, paramString2, paramString4, paramImageIcon1);
 
     JPanel localJPanel5 = createPlayerPanel(new FlowLayout(2), localDimension, paramString1, localColor2, localBorder3, paramString3, paramString5, paramImageIcon2);
 
     localJPanel3.add(localJPanel4);
     localJPanel3.add(localJPanel5);
 
     localJPanel1.add(localJPanel3);
 
     JButton localJButton = new JButton("continue");
     localJButton.addActionListener(new ActionListener() {
       public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
         GameGUI.this.setDialogClose();
         localJDialog.setVisible(false);
       }
     });
     JPanel localJPanel6 = new JPanel();
     localJPanel6.setLayout(new FlowLayout(1, 3, 3));
 
     localJPanel6.add(localJButton);
 
     localJPanel1.add(localJPanel6);
 
     return localJPanel1;
   }
 
   private JPanel createPlayerPanel(LayoutManager paramLayoutManager, Dimension paramDimension, String paramString1, Color paramColor, Border paramBorder, String paramString2, String paramString3, ImageIcon paramImageIcon)
   {
     JPanel localJPanel = new JPanel();
     localJPanel.setLayout(paramLayoutManager);
     localJPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(paramColor, 2), paramString2 + "'s Card", 2, 2), BorderFactory.createRaisedBevelBorder()));
 
     localJPanel.setPreferredSize(paramDimension);
 
     JLabel localJLabel1 = new JLabel(paramString1 + ": " + paramString3);
     localJPanel.add(localJLabel1);
 
     JLabel localJLabel2 = new JLabel(paramImageIcon);
 
     localJLabel2.setSize(paramImageIcon.getIconHeight(), paramImageIcon.getIconWidth());
     localJLabel2.setBorder(paramBorder);
 
     localJPanel.add(localJLabel2);
 
     return localJPanel;
   }
 
   public void displayDrawOutcome(String paramString1, String paramString2, String paramString3, ImageIcon paramImageIcon1, ImageIcon paramImageIcon2, String paramString4, String paramString5, String paramString6)
   {
     JDialog localJDialog = new JDialog(this, "Draw!", true);
     localJDialog.setSize(400, 320);
     localJDialog.setDefaultCloseOperation(0);
 
     Container localContainer = localJDialog.getContentPane();
 
     JPanel localJPanel = getDialogPanel(localJDialog, 1, paramString1, paramString2, paramString3, paramImageIcon1, paramImageIcon2, paramString4, paramString5, paramString6);
 
     localContainer.add(localJPanel);
 
     localJDialog.setLocationRelativeTo(this);
     localJDialog.setVisible(true);
   }
 
   public synchronized boolean waitForDialogClose()
   {
     while (!this.dialogueClose)
       try {
         wait();
       }
       catch (InterruptedException localInterruptedException) {
       }
     this.dialogueClose = false;
     this.game = 0;
     return true;
   }
 
   public synchronized void setDialogClose()
   {
     this.dialogueClose = true;
     notifyAll();
   }
 
   public void displayNumberOfCards(String paramString1, String paramString2, String paramString3, String paramString4)
   {
     final JDialog localJDialog = new JDialog(this, "", true);
     localJDialog.setDefaultCloseOperation(0);
     Container localContainer = localJDialog.getContentPane();
 
     JPanel localJPanel = new JPanel();
     localJPanel.setLayout(new GridLayout(3, 1));
 
     JLabel localJLabel1 = new JLabel(paramString1 + " has " + paramString3 + " cards.", 0);
     JLabel localJLabel2 = new JLabel(paramString2 + " has " + paramString4 + " cards.", 0);
 
     localJPanel.add(localJLabel1);
     localJPanel.add(localJLabel2);
 
     localJDialog.addMouseListener(new MouseAdapter()
     {
       public void mouseClicked(MouseEvent paramAnonymousMouseEvent)
       {
         localJDialog.setVisible(false);
         localJDialog.dispose();
         GameGUI.this.setDialogClose();
       }
     });
     localContainer.add(localJPanel);
     localJDialog.setSize(200, 70);
     localJDialog.setLocationRelativeTo(this);
     localJDialog.setVisible(true);
   }
 
   public void displayCardsList(String paramString, String[] paramArrayOfString, Object[] paramArrayOfObject)
   {
     final JDialog localJDialog = new JDialog(this, paramString + "'s Cards", true);
     localJDialog.setDefaultCloseOperation(0);
     Container localContainer = localJDialog.getContentPane();
 
     JPanel localJPanel1 = new JPanel();
     localJPanel1.setLayout(new BoxLayout(localJPanel1, 1));
 
     JPanel localJPanel2 = new JPanel();
     localJPanel2.setLayout(new FlowLayout(0, 2, 2));
 
     int i = paramArrayOfObject.length; int j = 0;
 
     for (j = 0; j < i; j++) {
       JLabel localJLabel = new JLabel(j + 1 + ": " + paramArrayOfString[j], (ImageIcon)paramArrayOfObject[j], 2);
       localJLabel.setPreferredSize(new Dimension(140, 35));
       localJPanel2.add(localJLabel);
     }
 
     localJPanel1.add(localJPanel2);
     localContainer.add(localJPanel1);
 
     localJDialog.addMouseListener(new MouseAdapter()
     {
       public void mouseClicked(MouseEvent paramAnonymousMouseEvent)
       {
         localJDialog.setVisible(false);
         localJDialog.dispose();
         GameGUI.this.setDialogClose();
       }
     });
     j = 0;
 
     float f1 = i;
     float f2 = 3.0F;
     float f3 = f1 / f2;
     Integer localInteger = new Integer(i / 3);
 
     if (f3 != localInteger.floatValue()) {
       j = 1;
     }
 
     int k = (new Integer(i / 3).intValue() + j) * 39 + 70;
     localJDialog.setSize(450, k);
     localJDialog.setLocationRelativeTo(this);
     localJDialog.setVisible(true);
   }
 
   public void displayWantToQuitDialog()
   {
     JOptionPane localJOptionPane = new JOptionPane();
     int i = 0;
     i = JOptionPane.showConfirmDialog(this, "Are you sure you want to quit?\nYou will resign and your opponent will automatically win!", "Quit?", 0, 3);
 
     if (i == 0) {
       this.wantToQuit = true;
       dispose();
     }
     else {
       this.wantToQuit = false;
     }
   }
 
   public boolean wantToQuit()
   {
     return this.wantToQuit;
   }
 
   public void displayGameWinner(String paramString1, String paramString2)
   {
     dispose();
     this.externalFrame = new GameGUI.gameWinner(paramString1, paramString2);
   }
 
   public void displayTopTenHighScores(Object[] paramArrayOfObject)
   {
     if (this.externalFrame != null) {
       this.externalFrame.dispose();
     }
     GameGUI.TopTenScores localTopTenScores = new GameGUI.TopTenScores(paramArrayOfObject);
     dispose();
     localTopTenScores.setVisible(true);
   }
 
   public String getPlayerName()
   {
     this.game = 0;
     return JOptionPane.showInputDialog("Type in the name of the player");
   }
 
   public String getPlayerID()
   {
     this.game = 0;
     return JOptionPane.showInputDialog("Type in the ID of the player");
   }
 
   public void displayAllGamesPlayedBy(String paramString1, String paramString2, int paramInt1, int paramInt2, int paramInt3, Object[] paramArrayOfObject)
   {
     final JDialog localJDialog = new JDialog(this, "All games played by " + paramString1 + " ID: " + paramString2, true);
     Container localContainer = localJDialog.getContentPane();
 
     int i = paramArrayOfObject.length;
     JTabbedPane localJTabbedPane = new JTabbedPane(1);
 
     JPanel localJPanel1 = new JPanel();
     localJPanel1.setLayout(new BoxLayout(localJPanel1, 1));
     JLabel localJLabel1 = new JLabel("Name: " + paramString1);
     JLabel localJLabel2 = new JLabel("ID: " + paramString2);
     JLabel localJLabel3 = new JLabel("Games won: " + paramInt1);
     JLabel localJLabel4 = new JLabel("Games lost: " + paramInt2);
     JLabel localJLabel5 = new JLabel("Win Ratio: " + paramInt3 + "%");
     String[] arrayOfString1 = (String[])paramArrayOfObject[(i - 1)];
     JLabel localJLabel6 = new JLabel("Last Game Played on " + arrayOfString1[4]);
     localJPanel1.add(localJLabel1);
     localJPanel1.add(localJLabel2);
     localJPanel1.add(localJLabel3);
     localJPanel1.add(localJLabel4);
     localJPanel1.add(localJLabel5);
     localJPanel1.add(localJLabel6);
     localJTabbedPane.addTab("Player Details", localJPanel1);
 
     int j = 0;
     while (j < i) {
       String[] arrayOfString2 = (String[])paramArrayOfObject[j];
       JPanel localJPanel2 = new JPanel();
       localJPanel2.setLayout(new BoxLayout(localJPanel2, 1));
       JLabel localJLabel7 = new JLabel("Game ID: " + arrayOfString2[0]);
       JLabel localJLabel8 = new JLabel("Winner: " + arrayOfString2[1] + " Loser: " + arrayOfString2[2]);
       JLabel localJLabel9 = new JLabel("Final Score: " + arrayOfString2[3]);
       JLabel localJLabel10 = new JLabel("Played on " + arrayOfString2[4]);
       JLabel localJLabel11 = new JLabel("Game commenced ar " + arrayOfString2[5]);
       JLabel localJLabel12 = new JLabel("Game finished at " + arrayOfString2[6]);
       JLabel localJLabel13 = new JLabel("Forfeit: " + arrayOfString2[7]);
       localJPanel2.add(localJLabel7);
       localJPanel2.add(localJLabel8);
       localJPanel2.add(localJLabel9);
       localJPanel2.add(localJLabel10);
       localJPanel2.add(localJLabel11);
       localJPanel2.add(localJLabel12);
       localJPanel2.add(localJLabel13);
       localJTabbedPane.addTab("" + (j + 1), localJPanel2);
       j++;
     }
     localContainer.add(localJTabbedPane);
     pack();
     localJDialog.addWindowListener(new WindowAdapter()
     {
       public void windowClosing(WindowEvent paramAnonymousWindowEvent)
       {
         localJDialog.setVisible(false);
         localJDialog.dispose();
         GameGUI.this.setDialogClose();
       }
     });
     localJDialog.pack();
     localJDialog.setLocationRelativeTo(this);
     this.progressbar.setIndeterminate(false);
     this.progressbar.setVisible(false);
     localJDialog.setVisible(true);
   }
 
   public void showProgress(String paramString)
   {
     this.backImage.add(this.progressbar);
     this.progressbar.setLocation(new Point(150, 163));
     this.progressbar.setSize(200, 30);
     this.progressbar.setVisible(true);
     this.progressbar.setString(paramString);
     this.progressbar.setIndeterminate(true);
   }
 
   public void displayError(String paramString)
   {
     JOptionPane.showInternalMessageDialog(this.cPane, paramString, "Error!", 0);
     if (this.progressbar.isIndeterminate()) {
       this.progressbar.setIndeterminate(false);
       this.progressbar.setVisible(false);
     }
   }
 
   public void displayNewPlayerID(String paramString1, String paramString2)
   {
     JOptionPane.showInternalMessageDialog(this.cPane, paramString2 + ", your new player ID is [" + paramString1 + "]", "New Player ID!", 1);
   }
 
   public void showHelp()
   {
     JFrame localJFrame = new JFrame("The Matrix Card Game help!");
     localJFrame.setIconImage(appImage);
     Container localContainer = localJFrame.getContentPane();
     try {
       JEditorPane localJEditorPane = new JEditorPane(GameGUI.class.getResource("manual/main.html"));
       JScrollPane localJScrollPane = new JScrollPane(localJEditorPane);
       localJEditorPane.setEditable(false);
       localContainer.add(localJScrollPane);
       localJFrame.setSize(600, 500);
       localJFrame.setVisible(true);
       localJFrame.toFront();
     }
     catch (IOException localIOException) {
       displayError("Help file missing");
     }
   }
 
   public static GameGUI createGUI(Frame paramFrame)
   {
     GameGUI localGameGUI = new GameGUI(paramFrame);
     return localGameGUI;
   }
 
   private class TopTenScores extends JFrame
   {
     Object[] allScores;
 
     public TopTenScores(Object[] arg2)
     {
       super();
       setIconImage(GameGUI.appImage);
       
       
       this.allScores = arg2;
       Container localContainer = getContentPane();
       JLabel localJLabel = new JLabel(tMCGToolKit.getGuiImage("code.jpg"));
       localContainer.add(localJLabel);
       pack();
       setLocation((GameGUI.screenDimension.width - 500) / 2, (GameGUI.screenDimension.height - 300) / 2);
 
       addWindowListener(new WindowAdapter()
       {
         public void windowClosing(WindowEvent paramAnonymousWindowEvent)
         {
           GameGUI.this.setDialogClose();
           GameGUI.TopTenScores.this.dispose();
         }
       });
       setDefaultCloseOperation(0);
     }
 
     public void update(Graphics paramGraphics)
     {
       paint(paramGraphics);
     }
 
     public void paint(Graphics paramGraphics)
     {
       super.paint(paramGraphics);
       Graphics2D localGraphics2D = (Graphics2D)paramGraphics;
       localGraphics2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
       localGraphics2D.setColor(new Color(0, 245, 0));
       localGraphics2D.setFont(GameGUI.this.MatrixFont);
       int i = this.allScores.length;
       int j = 0;
       int k = 50;
       int m = 0;
       while (j < i) {
         k += 25;
 
         String[] arrayOfString = (String[])this.allScores[j];
 
         m = 150;
         localGraphics2D.drawString(j + 1 + ".", m, k);
         if (arrayOfString[0].length() == 3) {
           m = 185;
         }
         else {
           m = 180;
         }
         localGraphics2D.drawString(">" + arrayOfString[0] + "<", m, k);
         m = 255;
         localGraphics2D.drawString(arrayOfString[1] + "[id:" + arrayOfString[2] + "]", m, k);
 
         j++;
       }
     }
   }
 
   private class gameWinner extends JFrame
   {
     int shift;
     String winnerName;
     String winnerScore;
 
     public gameWinner(String paramString1, String arg3)
     {
       super();
       setIconImage(GameGUI.appImage);
       this.winnerName = paramString1;
       this.winnerScore = arg3;
       Container localContainer = getContentPane();
       JLabel localJLabel = new JLabel(tMCGToolKit.getGuiImage("winner.jpg"));
       localContainer.add(localJLabel);
       pack();
       setLocation((GameGUI.screenDimension.width - 500) / 2, (GameGUI.screenDimension.height - 300) / 2);
       this.shift = (250 - (this.winnerName.length() * 10 + 200) / 2);
       setVisible(true);
       toFront();
     }
 
     public void update(Graphics paramGraphics)
     {
       paint(paramGraphics);
     }
 
     public void paint(Graphics paramGraphics)
     {
       super.paint(paramGraphics);
       Graphics2D localGraphics2D = (Graphics2D)paramGraphics;
       localGraphics2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
       localGraphics2D.setColor(new Color(0, 240, 0));
       localGraphics2D.setFont(GameGUI.this.MatrixFont);
       localGraphics2D.drawString(this.winnerName + " won the game with " + this.winnerScore + " points!", this.shift, 240);
     }
   }
 
   private class GameMainMenuListener
     implements ActionListener
   {
     private GameMainMenuListener()
     {
     }
 
     public void actionPerformed(ActionEvent paramActionEvent)
     {
       int i = Integer.valueOf(paramActionEvent.getActionCommand()).intValue();
       switch (i)
       {
       case 1:
         GameGUI.this.setGame(1);
         break;
       case 2:
         GameGUI.this.showProgress("Fetching high scores information...");
         GameGUI.this.setGame(2);
         break;
       case 3:
         GameGUI.this.showHelp();
         break;
       case 4:
         GameGUI.this.setGame(4);
         break;
       case 5:
         GameGUI.this.setGame(5);
         break;
       case 6:
         GameGUI.this.setNames();
         break;
       case 7:
         GameGUI.this.setPlayChoice(0);
         break;
       case 8:
         GameGUI.this.setPlayChoice(1);
         break;
       case 9:
         GameGUI.this.setPlayChoice(2);
         break;
       case 10:
         GameGUI.this.setPlayChoice(3);
         break;
       case 11:
         GameGUI.this.setPlayChoice(4);
         break;
       case 12:
         GameGUI.this.setPlayChoice(5);
         break;
       case 13:
         GameGUI.this.setPlayChoice(6);
         break;
       case 14:
         GameGUI.this.setPlayChoice(77);
       }
     }
   }
 }

/* Location:           \\tumo.lab\UserStore\Staff\hayk.galstyan\Desktop\test\
 * Qualified Name:     GameGUI
 * JD-Core Version:    0.6.2
 */