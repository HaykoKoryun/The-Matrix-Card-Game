 import java.io.PrintStream;
 import java.sql.Connection;
 import java.sql.DriverManager;
 import java.sql.ResultSet;
 import java.sql.SQLException;
 import java.sql.Statement;
 import com.mysql.jdbc.*;
 
 public class tMCG_HighScores
 {
   private static final String dbDriver = "com.mysql.jdbc.Driver";
   private static final String dbUser = "tumo1111210008";
   private static final String dbPass = "w8zpv0x7vdxf05dy";
   private static final String dbUrl = "mysql.student.tumo.org";
 
   public static Object createDB()
   {
     tMCG_HighScores localtMCG_HighScores = new tMCG_HighScores();
     return localtMCG_HighScores;
   }
 
   public void createTables()
   {
     try
     {
       Connection localConnection = createConnection();
       Statement localStatement = localConnection.createStatement();
       localStatement.execute("create table Players(playerID Integer,playerName Char(20));");
 
       localStatement.execute("create table Games(gameID Char(5),winner Integer,loser Integer,score Integer,playdate Char(8),startTime Char(4),endTime Char(4),forfeit Char(3));");
 
       localStatement.close();
       localConnection.close();
     }
     catch (SQLException localSQLException)
     {
       System.err.println("SQLException: " + localSQLException.getMessage());
     }
   }
 
   public int newPlayer(String paramString)
   {
     int i = 0;
     try
     {
       Connection localConnection = createConnection();
       Statement localStatement = localConnection.createStatement();
       ResultSet localResultSet = localStatement.executeQuery("select MAX(playerID) from Players");
       localResultSet.next();
       i = localResultSet.getInt(1) + 1;
       localStatement.executeUpdate("insert into Players (playerID,playerName)values(" + i + "," + "'" + paramString + "'" + ");");
 
       localStatement.close();
       localConnection.close();
     }
     catch (SQLException localSQLException) {
       System.err.println("SQLException: " + localSQLException.getMessage());
     }
     return i;
   }
 
   public void newGame(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean, String paramString1, String paramString2, String paramString3)
   {
     try
     {
       Connection localConnection = createConnection();
       Statement localStatement = localConnection.createStatement();
       ResultSet localResultSet = localStatement.executeQuery("select MAX(gameID) from Games");
       localResultSet.next();
       int i = localResultSet.getInt(1) + 1;
       String str = "";
       if (paramBoolean) {
         str = "yes";
       }
       else {
         str = "no";
       }
       localStatement.executeUpdate("insert into Games (gameID,winner,loser,score,playDate,startTime,endTime,forfeit)values(" + i + "," + paramInt1 + "," + paramInt2 + "," + paramInt3 + "," + "'" + paramString1 + "'," + "'" + paramString2 + "'," + "'" + paramString3 + "'," + "'" + str + "'" + ");");
 
       localStatement.close();
       localConnection.close();
     }
     catch (SQLException localSQLException) {
       System.err.println("SQLException: " + localSQLException.getMessage());
     }
   }
 
   public String[] getGame_byID(int paramInt)
   {
     String[] arrayOfString = new String[8];
     try
     {
       Connection localConnection = createConnection();
       Statement localStatement = localConnection.createStatement();
 
       ResultSet localResultSet = localStatement.executeQuery("select * from Games where(gameID=" + paramInt + ");");
       localResultSet.next();
 
       arrayOfString[0] = ("" + localResultSet.getInt(1));
 
       arrayOfString[3] = ("" + localResultSet.getInt(4));
 
       arrayOfString[4] = tMCGToolKit.getReadableDate(localResultSet.getString(5));
 
       arrayOfString[5] = tMCGToolKit.getReadableTime(localResultSet.getString(6));
 
       arrayOfString[6] = tMCGToolKit.getReadableTime(localResultSet.getString(7));
 
       arrayOfString[7] = localResultSet.getString(8);
 
       arrayOfString[1] = getPlayerName(localResultSet.getInt(2));
 
       arrayOfString[2] = getPlayerName(localResultSet.getInt(3));
 
       localStatement.close();
       localConnection.close();
       return arrayOfString;
     }
     catch (SQLException localSQLException) {
       System.err.println("SQLException: " + localSQLException.getMessage());
     }return null;
   }
 
   public String getPlayerName(int paramInt)
   {
     String str = "";
     try
     {
       Connection localConnection = createConnection();
       Statement localStatement = localConnection.createStatement();
       ResultSet localResultSet = localStatement.executeQuery("select playerName from Players where(playerID=" + paramInt + ");");
       localResultSet.next();
       str = localResultSet.getString(1);
       localStatement.close();
       localConnection.close();
       return str;
     }
     catch (SQLException localSQLException) {
       System.err.println("SQLException: " + localSQLException.getMessage());
     }return null;
   }
 
   public int getPlayerID(String paramString)
   {
     int i = 0;
     try
     {
       Connection localConnection = createConnection();
       Statement localStatement = localConnection.createStatement();
       ResultSet localResultSet = localStatement.executeQuery("select playerID from Players where(playerName='" + paramString + "');");
       localResultSet.next();
       i = localResultSet.getInt(1);
       localStatement.close();
       localConnection.close();
       return i;
     }
     catch (SQLException localSQLException) {
       System.err.println("SQLException: " + localSQLException.getMessage());
     }return 0;
   }
 
   public Object[] getAllGamesPlayedBy(int paramInt)
   {
     try
     {
       Connection localConnection = createConnection();
       Statement localStatement = localConnection.createStatement();
 
       Object[] arrayOfObject = new Object[countAllGamesWonLost(paramInt, "winner") + countAllGamesWonLost(paramInt, "loser")];
 
       ResultSet localResultSet = localStatement.executeQuery("select gameID from Games where(winner=" + paramInt + "or loser=" + paramInt + ");");
       for (int i = 0; localResultSet.next(); i++) {
         arrayOfObject[i] = getGame_byID(localResultSet.getInt(1));
       }
       localStatement.close();
       localConnection.close();
       return arrayOfObject;
     }
     catch (SQLException localSQLException) {
       System.err.println("SQLException: " + localSQLException.getMessage());
     }return null;
   }
 
   public int countAllGamesWonLost(int paramInt, String paramString)
   {
     try
     {
       Connection localConnection = createConnection();
       Statement localStatement = localConnection.createStatement();
       ResultSet localResultSet = localStatement.executeQuery("select count(" + paramString + ") from Games where(" + paramString + "=" + paramInt + ");");
       localResultSet.next();
       int i = localResultSet.getInt(1);
       localStatement.close();
       localConnection.close();
       return i;
     }
     catch (SQLException localSQLException) {
       System.err.println("SQLException: " + localSQLException.getMessage());
     }return 0;
   }
 
   public Object[] getTopTenScores()
   {
     try
     {
       Connection localConnection = createConnection();
       Statement localStatement = localConnection.createStatement();
       int i = 1000000;
       int j = 10;
       ResultSet localResultSet1 = localStatement.executeQuery("select count(gameID) from Games;");
       localResultSet1.next();
       int k = localResultSet1.getInt(1);
       Object[] arrayOfObject;
       if (k < 10) {
         j = k;
         arrayOfObject = new Object[j];
       }
       else {
         arrayOfObject = new Object[10];
       }
       for (int m = 0; m < j; m++) {
         ResultSet localResultSet2 = localStatement.executeQuery("select winner,score from Games where(score=(select MAX(score) from Games where(score<" + i + ")));");
         if (localResultSet2.next()) {
           int n = localResultSet2.getInt(1);
           int i1 = localResultSet2.getInt(2);
           i = i1;
           String[] arrayOfString = new String[3];
           arrayOfString[0] = ("" + i1);
           arrayOfString[2] = ("" + n);
           localResultSet2.close();
           ResultSet localResultSet3 = localStatement.executeQuery("select playerName from Players where(playerID=" + n + ");");
           localResultSet3.next();
           arrayOfString[1] = localResultSet3.getString(1);
           localResultSet3.close();
           arrayOfObject[m] = arrayOfString;
         }
       }
       localStatement.close();
       localConnection.close();
       return arrayOfObject;
     }
     catch (SQLException localSQLException) {
       System.err.println("SQLException: " + localSQLException.getMessage());
     }return null;
   }
 
   public Connection createConnection()
   {
     Connection localConnection = null;
     try
     {
       Class.forName("com.mysql.jdbc.Driver");
 
       localConnection = DriverManager.getConnection("jdbc:mysql://mysql.student.tumo.org", "tumo1111210008", "w8zpv0x7vdxf05dy");
       
       Statement localStatement = localConnection.createStatement();
       localStatement.execute("use `hayk.galstyan`;");
     }
     catch (Exception localException)
     {
       System.err.println("Exception: " + localException.getMessage());
     }
     return localConnection;
   }
 }

/* Location:           \\tumo.lab\UserStore\Staff\hayk.galstyan\Desktop\test\
 * Qualified Name:     tMCG_HighScores
 * JD-Core Version:    0.6.2
 */