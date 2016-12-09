 import java.util.ArrayList;
 import java.util.Collections;
 
 public class MatrixCardGame
 {
     
   private Player player1;
   private Player player2;
   private Player currentPlayer;
   public CardStack cardStack;
   private CardStack drawStack;
   private boolean newGame;
   private GameGUI gui;
   private boolean compareCards;
   private String lastStat;
   private tMCG_HighScores db;
   private static final String[] attribNames = { "", "Mastery of the Matrix", "Navigational Skills", "Matrix Hacking", "Fighting Skills" };
 
   public MatrixCardGame(GameGUI paramGameGUI, tMCG_HighScores paramtMCG_HighScores)
   {
     this.db = paramtMCG_HighScores;
 
     this.lastStat = "";
 
     this.player1 = null;
     this.player2 = null;
 
     this.cardStack = new CardStack();
     this.drawStack = new CardStack();
 
     this.newGame = true;
 
     this.gui = paramGameGUI;
 
     startGame();
   }
 
   public void startGame()
   {
     this.compareCards = true;
 
     while (this.newGame)
       playGame();
   }
 
   private void playGame()
   {
     fillDeck();
     Collections.shuffle(this.cardStack);
 
     this.gui.showGameOptions();
     Object localObject3;
     int j;
     Object localObject4;
     while (this.gui.getGame() != 1) {
       int i = 0;
 
       localObject3 = null;
       int k;
       float f1;
       float f2;
       float f3;
       int i1;
       switch (this.gui.getGame())
       {
       case 2:
         this.gui.displayTopTenHighScores(this.db.getTopTenScores());
         this.gui.waitForDialogClose();
         this.gui.showGameOptions();
         break;
       case 3:
         break;
       case 4:
         localObject3 = this.gui.getPlayerName();
         if (localObject3 != null)
         {
           i = this.db.getPlayerID((String)localObject3);
           if (i != 0) {
             this.gui.showProgress("Retrieving player data...");
             j = this.db.countAllGamesWonLost(i, "winner");
             k = this.db.countAllGamesWonLost(i, "loser");
             f1 = j;
             f2 = k;
             f3 = f1 / (f1 + f2) * 100.0F;
             if (Float.isNaN(f3)) {
               this.gui.displayError((String)localObject3 + " has not played any games!");
             }
             else {
               i1 = (int)f3;
               this.gui.displayAllGamesPlayedBy((String)localObject3, "" + i, j, k, i1, this.db.getAllGamesPlayedBy(i));
               this.gui.waitForDialogClose();
             }
           } else {
             this.gui.displayError("Player name \"" + (String)localObject3 + "\" doesn't exist!");
           }
         }
         break;
       case 5:
         localObject4 = this.gui.getPlayerID();
         if (localObject4 != null)
         {
           try
           {
             i = Integer.valueOf((String)localObject4).intValue();
           }
           catch (Throwable localThrowable) {
             this.gui.displayError("'" + (String)localObject4 + "' is not a valid player ID!");
             break;
           }
           localObject3 = this.db.getPlayerName(i);
           if (localObject3 != null) {
             this.gui.showProgress("Retrieving player data...");
             j = this.db.countAllGamesWonLost(i, "winner");
             k = this.db.countAllGamesWonLost(i, "loser");
             f1 = j;
             f2 = k;
             f3 = f1 / (f1 + f2) * 100.0F;
             if (Float.isNaN(f3)) {
               this.gui.displayError((String)localObject3 + " has not played any games!");
             }
             else {
               i1 = (int)f3;
               this.gui.displayAllGamesPlayedBy((String)localObject3, "" + i, j, k, i1, this.db.getAllGamesPlayedBy(i));
               this.gui.waitForDialogClose();
             }
           } else {
             this.gui.displayError("Player ID \"" + i + "\" doesn't exist!");
           }
         }
         break;
       }
     }
 
     this.gui.getPlayerNamesDialog();
     
     this.player1 = new Player("0", "CPU");
       this.player2 = new Player("1", "Hayk");
 
     if ((this.player1 == null) && (this.player2 == null))
     {
       j = 0;
 
       Object localObject1 = "";
       String str1 = "";
       String str2 = "";
 
       while (j == 0)
       {
         localObject1 = this.gui.getName();
 
         if (((String)localObject1).startsWith("%"))
         {
           str1 = ((String)localObject1).substring(1);
 
           localObject1 = "" + this.db.newPlayer(str1);
 
           this.gui.displayNewPlayerID((String)localObject1, str1);
 
           j = 1;
 
           this.gui.playerNameRetrieved();
         }
         else if (this.db.getPlayerName(Integer.valueOf((String)localObject1).intValue()) == null) {
           this.gui.displayError("The player ID you have entered for player 1 doesn't exist!");
           this.gui.playerNameRetrievalFailed();
         }
         else {
           j = 1;
           this.gui.playerNameRetrieved();
         }
 
       }
 
       j = 0;
 
       while (j == 0)
       {
         str2 = this.gui.getName();
 
         if (str2.startsWith("%")) {
           str1 = str2.substring(1);
           str2 = "" + this.db.newPlayer(str1);
           this.gui.displayNewPlayerID(str2, str1);
           j = 1;
           this.gui.playerNameRetrieved();
         }
         else if (this.db.getPlayerName(Integer.valueOf(str2).intValue()) == null) {
           this.gui.displayError("The player ID you have for entered for player 2 doesn't exist!");
           this.gui.playerNameRetrievalFailed();
         }
         else {
           j = 1;
           this.gui.playerNameRetrieved();
         }
 
       }
 
       
     }
 
     this.gui.prepareMainGameResources();
 
     allocateCards();
 
     String str1 = tMCGToolKit.padTime();
 
     this.currentPlayer = this.player1;
 
     Player localPlayer1 = null;
     Object localObject1 = null;
     Object localObject2 = null;
     String str3 = "";
 
     while (!gameOver())
     {
       int m = 0;
       int n = 0;
       str3 = new String("");
 
       localObject2 = this.player1.getTopCard();
       localObject3 = this.player2.getTopCard();
 
       localObject4 = this.currentPlayer.getTopCard();
 
       boolean bool = ((MatrixCard)localObject4).isSpecial();
 
       int i2 = getAction(this.currentPlayer.getName(), (MatrixCard)localObject4, bool);
 
       int i3 = i2 - 2;
 
       if (bool)
       {
         if (i2 == 77) {
           i2 = 3;
         }
         else if (i2 == 3) {
           i2 += 1;
         }
 
       }
 
       switch (i2)
       {
       case 0:
         this.gui.displayWantToQuitDialog();
 
         if (this.gui.wantToQuit()) {
           m = 0;
           n = 0;
           str3 = "Quitting";
           this.currentPlayer.quit();
         }
 
         this.compareCards = false;
         break;
       case 1:
         this.gui.displayNumberOfCards(this.player1.getName(), this.player2.getName(), "" + this.player1.countCards(), "" + this.player2.countCards());
 
         this.gui.waitForDialogClose();
 
         this.compareCards = false;
         break;
       case 2:
         Object[] arrayOfObject = this.currentPlayer.getCardList();
 
         this.gui.displayCardsList(this.currentPlayer.getName(), (String[])arrayOfObject[0], (Object[])arrayOfObject[1]);
 
         this.gui.waitForDialogClose();
 
         this.compareCards = false;
         break;
       case 3:
         if (bool) {
           specialAction(this.currentPlayer, nextPlayer(this.currentPlayer), ((MatrixCard)localObject4).getSpecialAction());
           this.compareCards = false;
           str3 = "Special Action";
         }break;
       case 4:
       case 5:
       case 6:
       case 7:
       case 8:
       case 9:
       case 10:
         m = ((MatrixCard)localObject2).getAttributeValue(i3);
         n = ((MatrixCard)localObject3).getAttributeValue(i3);
         str3 = attribNames[i3];
         this.compareCards = true;
       }
 
       this.currentPlayer.useTurn();
 
       this.lastStat = str3;
 
       if (this.compareCards == true)
       {
         if (m != n) {
           int i4 = 0;
           int i5 = 0;
           int i6 = 0;
 
           if (m > n) {
             localPlayer1 = this.player1;
             i4 = m;
             localObject1 = this.player2;
             i5 = n;
             i6 = m - n;
           }
           else {
             localPlayer1 = this.player2;
             i4 = n;
             localObject1 = this.player1;
             i5 = m;
             i6 = n - m;
           }
 
           localPlayer1.addPoints(i6);
 
           this.gui.displayWinnerOfHand(localPlayer1.getTopCard().getCardWinnerImage(), ((Player)localObject1).getTopCard().getCardLoserImage(), localPlayer1.getName(), ((Player)localObject1).getName(), new String("" + i4), new String("" + i5), str3);
 
           this.gui.waitForDialogClose();
 
           localPlayer1.addCard(((Player)localObject1).getTopCard());
           ((Player)localObject1).removeTopCard();
 
           localPlayer1.addCard(localPlayer1.getTopCard());
           localPlayer1.removeTopCard();
 
           if (this.drawStack.size() > 0) {
             int i7 = this.drawStack.size();
             for (int i8 = 0; i8 < i7; i8++) {
               localPlayer1.addCard(this.drawStack.getTopCard());
               this.drawStack.removeTopCard();
             }
 
           }
 
           this.currentPlayer = localPlayer1;
         }
         else
         {
           this.drawStack.addCard(this.player1.getTopCard());
           this.drawStack.addCard(this.player2.getTopCard());
 
           this.player1.addPoints(1);
           this.player2.addPoints(1);
 
           this.gui.displayDrawOutcome(str3, this.player1.getName(), this.player2.getName(), this.player1.getTopCard().getCardWinnerImage(), this.player2.getTopCard().getCardWinnerImage(), new String("" + m), new String("" + n), new String("" + this.drawStack.size()));
 
           this.gui.waitForDialogClose();
 
           this.player1.removeTopCard();
           this.player2.removeTopCard();
 
           this.currentPlayer = nextPlayer(this.currentPlayer);
         }
       }
     }
 
     Player localPlayer2 = gameWinner();
     Player localPlayer3 = nextPlayer(localPlayer2);
 
     str3 = tMCGToolKit.padDate();
 
     localObject2 = tMCGToolKit.padTime();
 
     this.gui.displayGameWinner(localPlayer2.getName(), "" + localPlayer2.score());
 
     this.db.newGame(localPlayer2.getPlayerID(), localPlayer3.getPlayerID(), localPlayer2.score(), false, str3, str1, (String)localObject2);
 
     this.gui.displayTopTenHighScores(this.db.getTopTenScores());
 
     this.newGame = false;
   }
 
   private void allocateCards()
   {
     while (!this.cardStack.isEmpty())
     {
       if (!this.cardStack.isEmpty())
       {
         this.player1.addCard(this.cardStack.getTopCard());
         this.cardStack.removeTopCard();
       }
       if (!this.cardStack.isEmpty())
       {
         this.player2.addCard(this.cardStack.getTopCard());
         this.cardStack.removeTopCard();
       }
     }
   }
 
   public Player getCurrentPlayer()
   {
     return this.currentPlayer;
   }
 
   private boolean gameOver()
   {
     return (!this.player1.hasMoreCards()) || (!this.player2.hasMoreCards()) || (this.player1.getTurnsLeft() == 0) || (this.player2.getTurnsLeft() == 0);
   }
 
   private Player gameWinner()
   {
     Player localPlayer = null;
     if (!this.player1.hasMoreCards()) {
       localPlayer = this.player2;
     }
     else if (!this.player2.hasMoreCards()) {
       localPlayer = this.player1;
     }
     else if ((this.player1.getTurnsLeft() == 0) || (this.player2.getTurnsLeft() == 0)) {
       if (this.player1.score() > this.player2.score()) {
         localPlayer = this.player1;
       }
       else {
         localPlayer = this.player2;
       }
     }
     return localPlayer;
   }
 
   private Player nextPlayer(Player paramPlayer)
   {
     if (paramPlayer == this.player1)
     {
       return this.player2;
     }
 
     return this.player1;
   }
 
   private int getAction(String paramString, MatrixCard paramMatrixCard, boolean paramBoolean)
   {
     boolean bool = this.compareCards;
 
     if (this.lastStat.equals("Special Action")) {
       bool = true;
     }
     this.gui.playMatrixGame(bool, this.currentPlayer.getName(), paramBoolean, paramMatrixCard.getSpecialDescription(), paramMatrixCard.getName(), paramMatrixCard.getDescription(), paramMatrixCard.getCardID(), paramMatrixCard.getCardImage(), paramMatrixCard.getAttributeValue(1), paramMatrixCard.getAttributeValue(2), paramMatrixCard.getAttributeValue(3), paramMatrixCard.getAttributeValue(4), this.currentPlayer.getTurnsLeft(), this.currentPlayer.score());
 
     return this.gui.getPlayChoice();
   }
 
   private void specialAction(Player paramPlayer1, Player paramPlayer2, int paramInt)
   {
     switch (paramInt)
     {
     case 9:
       MatrixCard localMatrixCard = paramPlayer1.getTopCard();
 
       int[] arrayOfInt = new int[localMatrixCard.getNumberOfAttributes()];
 
       int i = 2;
 
       for (int j = 3; j > 0; j--) {
         for (int k = arrayOfInt.length - 1; k > 0; k--) {
           if (localMatrixCard.getAttributeValue(k) - i > 0) {
             arrayOfInt[k] = (localMatrixCard.getAttributeValue(k) - i);
           }
         }
 
         arrayOfInt[0] = 0;
 
         paramPlayer1.addCard(new MatrixCard(localMatrixCard.getName(), new String[] { localMatrixCard.getDescription() }, localMatrixCard.getCardID(), arrayOfInt));
 
         i++;
       }
 
       paramPlayer1.addCard(localMatrixCard);
       paramPlayer1.removeTopCard();
       break;
     case 8:
       ArrayList localArrayList = new ArrayList();
       localArrayList.add("101");
       localArrayList.add("002");
       localArrayList.add("303");
       paramPlayer2.moveCardsToBottom(localArrayList);
       paramPlayer1.addCard(paramPlayer1.getTopCard());
       paramPlayer1.removeTopCard();
     }
   }
 
   private void fillDeck()
   {
     this.cardStack.addCard(new MatrixCard("Neo", new String[] { "Also known as \"The One\", which is an anagram of neo.\nBecame the first nerd to get a bit o' nookie...with a fiiiiine woman, who likes to wear tight leather.\nOh yeah, he also saved the world or something." }, "101", new int[] { 0, 10, 2, 1, 9 }));
 
     this.cardStack.addCard(new MatrixCard("Morpheus", new String[] { "\"You can take the blue pill or the red pill...either way, I'm calling the cops right after you do!\"No seriously, hes a kool dude.\n(although he has a gap in his teeth)" }, "002", new int[] { 0, 9, 7, 4, 8 }));
 
     this.cardStack.addCard(new MatrixCard("Trinity", new String[] { "\"How about you sample this bullet instead!\"\nFine ass woman, #1 in the Matrix, after the 'Woman in Red of course'.Likes to kill people with a scorpion kick, but someoneto cuddle up to at night." }, "303", new int[] { 0, 8, 7, 3, 7 }));
 
     this.cardStack.addCard(new MatrixCard("Apoc", new String[] { "Pronounced Apok, but for the purposes of the next gagyou will have to read it as Apac...because I wrote his name like that when I wasmaking this game, and hence the following joke...\nHe'se missing 5 pacs (get it six pac->a pac (one pac))\nAnyway, he doesn't do much in the first film,and doesn't have a part in the other films,(he gets killed)." }, "004", new int[] { 0, 6, 1, 2, 4 }));
 
     this.cardStack.addCard(new MatrixCard("Cypher", new String[] { "\"I want to remember nothing...Nothing!\"\nThe git who handed the neba-whatsits-nezzar crew to the agents.Now he gets to rot in Matrix hell, if there is such a thing.", "Forces Morpheus, Neo and Trinity to go into hiding.\nCauses these cards go to the bottom of the opponentsdeck, if the opponent has these cards." }, "005", new int[] { 8, 3, 1, 2, 3 }));
 
     this.cardStack.addCard(new MatrixCard("Dozer", new String[] { "Tanks bro. Doesn't really do much in the film apart from say\"This goop is flled with vitamins and minerals,everything the body needs\". And then Mouse says...\"It doesn't have everything the body needs...what about booze, broads and... something else thatstarts with b\"... ok, I may have made that up,but you get the general picture of what he said." }, "006", new int[] { 0, 1, 4, 6, 1 }));
 
     this.cardStack.addCard(new MatrixCard("Link", new String[] { "Before you ask, no... he's not Link from Zelda!\nHe's the dude who says YES! when Neo savesThe KeyMaker, Morpheus and Trinity fromthe blast on the motorway scene.\"Wheres my pus---hey kids!\"" }, "007", new int[] { 0, 3, 7, 6, 1 }));
 
     this.cardStack.addCard(new MatrixCard("Mouse", new String[] { "The designer of the \"Woman in Red\"\nBetter than the architect himself IMO, I meanc'mon, the woman in red phoaw!He's a bit crap at fighting though,just gets shot to pieces." }, "008", new int[] { 0, 9, 2, 10, 5 }));
 
     this.cardStack.addCard(new MatrixCard("Switch", new String[] { "\"Not like this, not like this!\"\nAgain, one of the characters who doesn't do much in the filmapart from doing one liners and a bit of action." }, "009", new int[] { 0, 4, 2, 5, 6 }));
 
     this.cardStack.addCard(new MatrixCard("Tank", new String[] { "A kewl character who was fired in real life...\nThats why he isn't in the second film, even though he didn't die.Apparently his info was given to the FBI by Warner Bros...you didn't hear it from me though!" }, "010", new int[] { 0, 8, 8, 6, 1 }));
 
     this.cardStack.addCard(new MatrixCard("Merovingian", new String[] { "\"Cursing in french is like wiping your arse with silk!\"He is also known as 'pervy merv'...see the cake bit in the second film.He doesn't do much apart from talking alot of BS aboutcause and effect...causality!" }, "011", new int[] { 0, 7, 0, 10, 6 }));
 
     this.cardStack.addCard(new MatrixCard("Persephone", new String[] { "\"I want to sample it\"\nShe is the Merovingian's wife, although,he doesn't treat her that way.Persephone is very sly and sexay, and a bit of a dominatrix!" }, "012", new int[] { 0, 6, 0, 6, 2 }));
 
     this.cardStack.addCard(new MatrixCard("Virus Twin #1", new String[] { "Ugly looking bastard #1 wearing shiny white suit.\nYou wouldn't want to bump into these fellas,although it would be hard mind, they have the annoying habitof dematerialising and rematerialising at will!\nThey have also appeared on changing rooms,which explains the white 'overalls'." }, "013", new int[] { 0, 8, 0, 9, 7 }));
 
     this.cardStack.addCard(new MatrixCard("Virus Twin #2", new String[] { "Ugly looking bastard #2 wearing shiny white suit.\nYou wouldn't want to bump into these fellas,\nalthough it would be hard mind, they have the annoying habitof dematerialising and rematerialising at will!\nThey have also appeared on changing rooms,which explains the white 'overalls'." }, "014", new int[] { 0, 8, 0, 9, 7 }));
 
     this.cardStack.addCard(new MatrixCard("Ghost", new String[] { "Before you ask... no, he can't do ghost like thingswhilst in the Matrix, although he has a kewl name...and erm...goatee!" }, "015", new int[] { 0, 6, 4, 5, 6 }));
 
     this.cardStack.addCard(new MatrixCard("Niobe", new String[] { "Daaaaaaaammn woman, you can drive!\nYep, she can, and shes good at fighting...and attwo timing Commander Lock." }, "016", new int[] { 0, 7, 10, 7, 7 }));
 
     this.cardStack.addCard(new MatrixCard("Agent Smith", new String[] { "Meee Two/Too! (and me...to infinity)\nThis guy has a very sinister...and yet funny laugh.Likes to replicate, to keep himself company...and, he doesn't like the smell of humans.Yeah, you would stink if you were put in oneflippin tiny cubicle for you entire life...where you do your business there and then...and get fed the juice of dead bodies, erm,I think i went into a bit too much detail there,and the age rating of this game went up,(as if it hadn't already done so becauseof Trinity's and Persephone's descriptions)", "Replicate\nAgent Smith creates a random number of clonesof himself, however, every subsequent clonehas 2 points less of each ability" }, "017", new int[] { 9, 9, 0, 7, 9 }));
 
     this.cardStack.addCard(new MatrixCard("Bane", new String[] { "He is the human incarnation of Agent Smith,well, sort of...he finally decided to smell humans first hand.This guy seriosuly can't make his mind up'maybe I did, and maybe I didn't'He is also a bit of a masochist, although again'maybe I cut myself, or maybe I didn't'" }, "018", new int[] { 0, 6, 1, 3, 6 }));
   }
 
   public static void startGame(Object paramObject1, Object paramObject2)
   {
     MatrixCardGame localMatrixCardGame = new MatrixCardGame((GameGUI)paramObject1, (tMCG_HighScores)paramObject2);
   }
 }

/* Location:           \\tumo.lab\UserStore\Staff\hayk.galstyan\Desktop\test\
 * Qualified Name:     MatrixCardGame
 * JD-Core Version:    0.6.2
 */