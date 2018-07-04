package vzap.losh;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.AbstractButton;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JSlider;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JList;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.border.TitledBorder;

import vzap.losh.enums.GameStatus;
import vzap.losh.game.GameDAO;
import vzap.losh.game.GameSheet;
import vzap.losh.game.PlayerScore;
import vzap.losh.gamePieces.Dice;
import vzap.losh.gamePlayers.Player;
import vzap.losh.gamePlayers.PlayersClass;

import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;

public class GameFrame extends JFrame implements ActionListener{

	/**
	 * 
	 */
	Connection con;
	private static final long serialVersionUID = 1L;
	private static final int WINVALUE = 20;
	private JPanel contentPane;
	private int numberOfPlayers;
	private  int numberOfDice;
	private JPanel p1Panel;
	private JScrollPane scrollPane_2;
	private JScrollPane scrollPane_3;
	private JScrollPane scrollPane_4;
	private JScrollPane scrollPane_5;
	private JLabel p1LblScore;
	private JLabel p1Lbl1ScoreValue;
	private JButton p1BtnDice;
	private JButton p1BtnFold;
	private JLabel p2LblScore;
	private JButton p2BtnDice;
	private JButton p2ButtonFold;
	private JPanel p2Panel;
	private JScrollPane scrollPane;
	private JLabel p2LblScoreValue;
	private Player p1;
	private Player p2;
	private Player p3;
	private Player p4;
	private Player p5;
	private Player p6;
	private int diceInPlay;
	private PlayersClass registerPlayerList;
	private JMenuItem playGameMenuItem;
	private JMenuItem mntmRegisterNewPlayer;
	private JMenu mnPlayGame;
	private JCheckBoxMenuItem chckbxmntmUseDice;
	private JMenuItem mntmTop;
	private JMenuItem mntmStatsForSpecific;
	private JMenuItem mntmScoreForA;
	private JMenu settingsMenu;
	private GameSheet newGameSheet;
	private Statement stmt;
	private ResultSet rs;
	private Vector<Player> playersPlaying;
	private JMenu mnStats;
	private JLabel p2LblPlayerName;
	private JLabel p1LblPlayerName;
	private Vector<PlayerScore> playerScores;
	private int p2Score;
	private int p2ID;
	private Player p2Player;
	private int p1ID;
	private Player p1Player;
	private JMenuBar menuBar;
	private JScrollPane scrollPane_1;
	private JLabel lblGameIdDisplay;
	private JLabel lblGameId;
	private JLabel lblWhoTurnValue;
	private int playersTurn;
	private JButton btnConcedeGame;
	private JSlider sliderMenu;
	private PlayerScore winningPlayer;
	private int personWhoWon;
	private Player winningPlayerObject;
	private GameDAO x;
	private boolean nextTurn;
	private JButton p3BtnDice;
	private JButton p4BtnDice;
	private JButton p5BtnDice;
	private JButton p6BtnDice;
	private JButton p3ButtonFold;
	private JButton p4ButtonFold;
	private JButton p5ButtonFold;
	private JButton p6ButtonFold;
	private JLabel p6LblScoreValue;
	private JTextArea jTextarea0;
	private JTextArea jTextarea1;
	private JTextArea jTextarea2;
	private JTextArea jTextarea3;
	private JTextArea jTextarea4;
	private JTextArea jTextarea5;
	private JLabel label_1;
	private JLabel label2Turn;
	private JLabel label3Turn;
	private JLabel label_5;
	private JLabel label4Turn;
	private JLabel label_8;
	private JLabel label5Turn;
	private JLabel label_11;
	private JLabel label6Turn;
	private JLabel label_13;
	private JLabel label1Turns;
	private JPanel panel5;
	private JPanel panel4;
	private JLabel label_4;
	private JLabel p4LblScoreValue;
	private JLabel p4LblPlayerName;
	private JLabel p5LblScoreValue;
	private JLabel p5LblPlayerName;
	private JPanel panel6;
	private JLabel p6LblPlayerName;
	private JLabel picturelblNewLabel;
	private JPanel panel3;
	private JLabel p3LblScoreValue;
	private JLabel p3LblPlayerName;
	private JButton[] arrayOfButtons;



	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameFrame frame = new GameFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// on the player add we check for duplicates not during the game!!!!! from menu

	/*
	public static connection getConnection()
	{

	}
	 */

	//this with a list of , seperate
	// gameId 
	// in scoresheet create method, static one to retrieve the incomplete games

	public void defineConnection()
	{

		int gameId;

		try {

			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("driver registered");
			String url = "JDBC:mysql://localhost:3306/pigbase";
			String username="root";
			String password="root";
			con = DriverManager.getConnection(url,username,password);
			//con.setAutoCommit(false);
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY); //letting the connection make the object
			rs = null;
			String querySQL = "select gameid from game;";
			rs =stmt.executeQuery(querySQL);
			while (rs.next())
			{
				gameId = rs.getInt(1);
				System.out.println("game id display :"  + gameId);
			}

			System.out.println("connection opened and tested");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}



	}

	public void gamePlay ()
	{

		// turn of game settings menu and Play GAME!!!

		// show panes

		// 2 registered players so far losh , alias dare devil
		// 							   kaiden, batman 



		//To be converted to a dialog with combo box to select and set the users
		playersPlaying = new Vector<Player>(); 


		// set all the buttons to disabled 


		//  ............**********other buttons to follow

		numberOfPlayers=sliderMenu.getValue();
		numberOfDice = (chckbxmntmUseDice.isSelected())?2:1;

		arrayOfButtons = new JButton[] {p1BtnDice, p2BtnDice,p3BtnDice,p4BtnDice,p5BtnDice,p6BtnDice};

		//create a new gameid, auto increment your gameID

		switch(numberOfPlayers)
		{
		case 6:
			//p6 on 

			break;
		case 5:

			scrollPane_5.setVisible(false);
			panel6.setVisible(false);
			p6BtnDice.setEnabled(false);


		case 4:
			scrollPane_5.setVisible(false);
			panel6.setVisible(false);
			p6BtnDice.setEnabled(false);

			scrollPane_4.setVisible(false);
			panel5.setVisible(false);
			p5BtnDice.setEnabled(false);



		case 3:
			scrollPane_5.setVisible(false);
			panel6.setVisible(false);
			p6BtnDice.setEnabled(false);

			scrollPane_4.setVisible(false);
			panel5.setVisible(false);
			p5BtnDice.setEnabled(false);

			scrollPane_3.setVisible(false);
			panel4.setVisible(false);
			p4BtnDice.setEnabled(false);


		case 2:
			p1= new Player("Kaiden","Batman","Cool.pic",1);
			p2= new Player("Loshen","Dare Devil","Cool.pic",2);
			playersPlaying.addElement(p1); //batman , 0
			playersPlaying.addElement(p2); //dare devil , 1

			scrollPane_5.setVisible(false);
			panel6.setVisible(false);
			p6BtnDice.setEnabled(false);

			scrollPane_4.setVisible(false);
			panel5.setVisible(false);
			p5BtnDice.setEnabled(false);

			scrollPane_3.setVisible(false);
			panel4.setVisible(false);
			p4BtnDice.setEnabled(false);

			scrollPane_2.setVisible(false);
			panel3.setVisible(false);
			p3BtnDice.setEnabled(false);

			scrollPane_1.setVisible(true);
			p2Panel.setVisible(true);
			p2BtnDice.setEnabled(true);
			p2ButtonFold.setEnabled(true);

			
			scrollPane.setVisible(true);
			p1Panel.setVisible(true);
			p1BtnDice.setEnabled(true);
			p1BtnFold.setEnabled(true);
			
			
			
			break;

		case 1:	

			p1= new Player("Kaiden","Batman","Cool.pic",1);
			playersPlaying.addElement(p1); //batman

			scrollPane_5.setVisible(false);
			panel6.setVisible(false);
			p6BtnDice.setEnabled(false);

			scrollPane_4.setVisible(false);
			panel5.setVisible(false);
			p5BtnDice.setEnabled(false);

			scrollPane_3.setVisible(false);
			panel4.setVisible(false);
			p4BtnDice.setEnabled(false);

			scrollPane_2.setVisible(false);
			panel3.setVisible(false);
			p3BtnDice.setEnabled(false);


			scrollPane_1.setVisible(false);
			p2Panel.setVisible(false);
			p2BtnDice.setEnabled(false);

			scrollPane.setVisible(true);
			p1Panel.setVisible(true);
			p1BtnDice.setEnabled(true);
			p1BtnFold.setEnabled(true);


			break;
		}
		
		try {
			newGameSheet = new GameSheet(con, playersPlaying);
			playerScores = newGameSheet.getPlayerScores();
			lblGameId.setText(Integer.toString(this.newGameSheet.getCurrentGame()));
			this.getContentPane().setVisible(true);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		switch(numberOfPlayers)
		{
		case 6:
			//p6 on 
		case 5:

		case 4:

		case 3:

		case 2:
			p2ID = playerScores.get(1).getPlayer();
			//store p2 name data , alias, picture
//			playersPlaying.addElement(p1); //batman , 0
	//		playersPlaying.addElement(p2); //dare devil , 1
			p2Player=registerPlayerList.getPlayerObject(p2ID);
			p2LblPlayerName.setText(p2Player.getPlayerAlias());

		case 1:
			//store p2 score 
			p1ID = playerScores.get(0).getPlayer();
			//store p2 name data , alias, picture
			p1Player=registerPlayerList.getPlayerObject(p1ID);
			p1LblPlayerName.setText(p1Player.getPlayerAlias());
		}

		playersTurn=1;
		displayPlayersTurn(0);

		this.getContentPane().repaint();



	}






	private void displayPlayersTurn(int i) {
		// TODO Auto-generated method stub
		lblWhoTurnValue.setText(playersPlaying.elementAt(i).getPlayerAlias());
	}

	public GameFrame() {

		// create our SQL connection
		defineConnection();
		gamePanelUI();
		gamePanelUIActionListener();
		registerPlayerList = new PlayersClass(); //load the players db into registered players
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing (WindowEvent we)
			{
				if (con!=null)
				{
					try {
						con.close();
					} catch (SQLException e) {
						System.out.println("connection closed");
						e.printStackTrace();
					}
				}
			}
		});

		this.getContentPane().setVisible(false);

		diceInPlay =2;
		numberOfPlayers=2;

		//play gets call from the action 


		// on exit close the connection

		/*
		 *  Start a loop for the game
		 *  Read the settings
		 *  
		 */
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object source;
		source=e.getSource();


		if (source == playGameMenuItem)
		{
			this.gamePlay();
			this.menuBar.setVisible(false); // menu hide exit current game displayed
		}

		if (source==btnConcedeGame)//exit button presses then save the game she
		{
			concedeTheGameAndHideMenu();
		}


		if (source == p1BtnDice)
		{
			if (playersTurn==1)
			{
				playRoutine(0, jTextarea0, label1Turns,p1Lbl1ScoreValue);
				if (playerScores.elementAt(0).getScore()  >= WINVALUE)
				{
					p1BtnDice.setEnabled(false);
					p1BtnFold.setEnabled(false);
					checkIfGameHasWinnerAndCanBeEnded();
					}
			}
			else
			{
				JOptionPane.showMessageDialog(this, "Not the players turn");
			}
		}

		if (source == p1BtnFold)
		{
			setToNextPlayer();
			refreshTheFrame();
		}

		if (source == p2BtnDice)
		{
			if (playersTurn==2)
			{
				playRoutine(1, jTextarea1, label2Turn,p2LblScoreValue);
				if (playerScores.elementAt(1).getScore()  >= WINVALUE)
				{
					p2BtnDice.setEnabled(false);
					p2ButtonFold.setEnabled(false);
					checkIfGameHasWinnerAndCanBeEnded();
				}
			}
			else
			{
				JOptionPane.showMessageDialog(this, "Not the players turn");
			}
		}

		if (source== p2ButtonFold)
		{
			setToNextPlayer();
			refreshTheFrame();
		}		

		return;

	}
	private boolean checkIfGameHasWinnerAndCanBeEnded() {
		// TODO Auto-generated method stub
		boolean done=determineIfTurnsDoneCheckFinalScores();
		if (done)
		{
			winningPlayer = decideWhoWon();
			personWhoWon = winningPlayer.getPlayer();
			winningPlayerObject= registerPlayerList.getPlayerObject(personWhoWon);
			JOptionPane.showMessageDialog(this, "Winner is " + winningPlayerObject.getPlayerName());
			newGameSheet.savePlayerScores(newGameSheet.getCurrentGame(), playerScores);
			try {
				newGameSheet.saveGameWinning(con, winningPlayerObject.getPlayerID(), GameStatus.WON);
				closeShowMenu();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				System.out.println("Problem saving the game");
			}
		}
		else
		{
			refreshTheFrame();
		}


		return done;

	}

	private boolean determineIfTurnsDoneCheckFinalScores() {
		// TODO Auto-generated method stub
		switch ( numberOfPlayers)
		{
		case 6:
			//if (p1BtnDice = )
			if (p1BtnDice.isEnabled() ||
					p2BtnDice.isEnabled() ||
					p3BtnDice.isEnabled() ||
					p4BtnDice.isEnabled() ||
					p5BtnDice.isEnabled() ||
					p6BtnDice.isEnabled()
					)
			{
				return false;
			}
			return true;
		case 5:
			if (p1BtnDice.isEnabled() ||
					p2BtnDice.isEnabled() ||
					p3BtnDice.isEnabled() ||
					p4BtnDice.isEnabled() ||
					p5BtnDice.isEnabled() 
					)
			{
				return false;			
			}
			return true;

		case 4:
			if (p1BtnDice.isEnabled() ||
					p2BtnDice.isEnabled() ||
					p3BtnDice.isEnabled() ||
					p4BtnDice.isEnabled() 
					)
			{
				return false;			
			}
			return true;
		case 3:
			if (p1BtnDice.isEnabled() ||
					p2BtnDice.isEnabled() ||
					p3BtnDice.isEnabled() 
					)
			{
				return false;			
			}
			return true;
		case 2:
			if (p1BtnDice.isEnabled() ||
					p2BtnDice.isEnabled() 
					)
			{
				return false;			
			}
			return true;
		default:
			return true;
		}
	}

	private void playRoutine(int index , JTextArea listString, JLabel turnsLabel,JLabel scoreValue) {
		int[] value = playDice(numberOfDice, listString);
		playerScores.elementAt(index).addTurns(1);
		nextTurn=calcScore(value,index);
		if (playerScores.elementAt(index).getScore() >= WINVALUE)
		{
			nextTurn=true;	
		}
		scoreValue.setText(new Integer(playerScores.elementAt(index).getScore()).toString());
		if (nextTurn)
		{
			setToNextPlayer();
		}
		turnsLabel.setText(new Integer(playerScores.elementAt(index).getTurns()).toString());
	}

	private void setToNextPlayer() {

		while (true)
		{	

			applyincrement();
			if (arrayOfButtons[playersTurn-1].isEnabled())
			{
				break;
			}
		}	
		
		displayPlayersTurn(playersTurn-1);

	}




	private void applyincrement() {
		// TODO Auto-generated method stub
		if (playersTurn==numberOfPlayers)
		{
			playersTurn=1;
		}
		else
		{
			playersTurn++;
		}	
	}

	private boolean calcScore(int[] value, int index) {
		// TODO Auto-generated method stub

		if (numberOfDice == 1) {
			return calcScoreOneDice(value[0],index);
		} else if (numberOfDice == 2) {
			return calcScoreTwoDice(value[0],value[1],index);
		}
		return true;//next players turn 

	}

	private boolean calcScoreTwoDice(int dice1, int dice2, int index) {

		// TODO Auto-generated method stub
		if ((dice1==1 && dice2!=1) || (dice2==1 && dice1!=1))
		{
			playerScores.elementAt(index).setScore(0);
			return true;
		}
		if (dice1==1 && dice2==1)
		{
			playerScores.elementAt(index).setScore(25);
			return true;
		}

		if (dice1==dice2)
		{
			playerScores.elementAt(index).addScore(dice1*4);
			return false;
		}

		playerScores.elementAt(index).addScore(dice1 + dice2);
		return false;

	}

	private boolean calcScoreOneDice(int inValue, int index) {
		// TODO Auto-generated method stub
		if 	(inValue == 1)
		{
			return true;
		}

		playerScores.elementAt(index).addScore(inValue);
		return false;
	}

	private int[] playDice(int numberOfDice2 , JTextArea listStrings) {
		// TODO Auto-generated method stub
		int[] values = {0,0};
		Dice dice1= new Dice();
		Dice dice2= new Dice();

		switch (numberOfDice2)
		{
		case 1:
			dice1.rollDice();
			values[0]=dice1.getFaceValue();
			listStrings.append("Dice1 : " +  values[0] + "\n" );
			break;
		default:
			dice1.rollDice();
			values[0]=dice1.getFaceValue();
			listStrings.append("Dice1 : " +  values[0]  + "\n"  );

			dice2.rollDice();
			values[1]=dice2.getFaceValue();
			listStrings.append("Dice2 : " +  values[1]  + "\n" );
			break;
		
		}
		return values;
	}



	private void concedeTheGameAndHideMenu() {
		// TODO Auto-generated method stub
		winningPlayer = decideWhoWon();
		if (winningPlayer != null)
		{
			personWhoWon = winningPlayer.getPlayer();
			winningPlayerObject= registerPlayerList.getPlayerObject(personWhoWon);
			JOptionPane.showMessageDialog(this, "Winner is " + winningPlayerObject.getPlayerName());
			newGameSheet.savePlayerScores(newGameSheet.getCurrentGame(), playerScores);
			try {
				newGameSheet.saveGameWinning(con, winningPlayerObject.getPlayerID(), GameStatus.WON);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				System.out.println("Problem saving the game");
			}
		}
		else
		{
			JOptionPane.showMessageDialog(this, "No Winner  ");
			newGameSheet.savePlayerScores(newGameSheet.getCurrentGame(), playerScores);
			try {
				newGameSheet.saveAbandonedGame(con,  GameStatus.ABANDONED);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				System.out.println("Problem saving the game");
			}

		}
		closeShowMenu();
		//	game
		return;

	}
	
	private void closeShowMenu()
	{
		menuBar.setVisible(true);
		this.getContentPane().setVisible(false);
		refreshTheFrame();
	}

	private void refreshTheFrame() {
		// TODO Auto-generated method stub
		this.getContentPane().repaint();
	}

	private PlayerScore decideWhoWon() {
		// TODO Auto-generated method stub

		PlayerScore highestPlayersScore=playerScores.get(0);

		Vector<PlayerScore> storeGt100= new Vector<PlayerScore>() ;

		for (PlayerScore ps :  playerScores)
		{
			if ((ps.getScore() >= highestPlayersScore.getScore()) && (ps.getScore()>=WINVALUE)) 
			{
				highestPlayersScore=ps;
				if (highestPlayersScore.getScore() >= WINVALUE)
				{
					storeGt100.addElement(highestPlayersScore);
				}
			}
		}

		if (storeGt100.isEmpty() ) // no one won this game
		{

			return null;
		}

		if (storeGt100.size() > 1)
		{
			highestPlayersScore = storeGt100.elementAt(0);
			for (PlayerScore ps :  storeGt100)
			{
				if (ps.getTurns() > highestPlayersScore.getTurns())
				{
					highestPlayersScore=ps;
				}
			}
			return highestPlayersScore;
		}
		else 
		{
			return highestPlayersScore;
		}

	}


	public void gamePanelUIActionListener()
	{
		playGameMenuItem.addActionListener(this);
		p2BtnDice.addActionListener(this);
		p2ButtonFold.addActionListener(this);
		btnConcedeGame.addActionListener(this);
		p1BtnDice.addActionListener(this);
		p1BtnFold.addActionListener(this);
		p3BtnDice.addActionListener(this);
		p3ButtonFold.addActionListener(this);
		p4BtnDice.addActionListener(this);
		p4ButtonFold.addActionListener(this);
		p5BtnDice.addActionListener(this);
		p5ButtonFold.addActionListener(this);
		p6BtnDice.addActionListener(this);
		p6ButtonFold.addActionListener(this);

	}
	public void gamePanelUI()
	{

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1040, 828);

		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		mnPlayGame = new JMenu("Play Game");
		menuBar.add(mnPlayGame);

		playGameMenuItem = new JMenuItem("Play Game");
		playGameMenuItem.setIcon(null);
		mnPlayGame.add(playGameMenuItem);


		mntmRegisterNewPlayer = new JMenuItem("Register New Player");
		mnPlayGame.add(mntmRegisterNewPlayer);

		settingsMenu = new JMenu("settings");
		menuBar.add(settingsMenu);

		chckbxmntmUseDice = new JCheckBoxMenuItem("use 2 dice");
		chckbxmntmUseDice.setSelected(true);
		settingsMenu.add(chckbxmntmUseDice);
		

		sliderMenu = new JSlider();
		sliderMenu.setValue(2);
		sliderMenu.setMajorTickSpacing(1);
		sliderMenu.setPaintTicks(true);
		sliderMenu.setSnapToTicks(true);
		sliderMenu.setPaintLabels(true);
		sliderMenu.setMinimum(1);
		sliderMenu.setMaximum(6);
		settingsMenu.add(sliderMenu);

		mnStats = new JMenu("Stats");
		menuBar.add(mnStats);

		mntmTop = new JMenuItem("Top 10");
		mnStats.add(mntmTop);

		mntmStatsForSpecific = new JMenuItem("Stats for specific player");
		mnStats.add(mntmStatsForSpecific);

		mntmScoreForA = new JMenuItem("Score for a specific Game");
		mnStats.add(mntmScoreForA);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

		scrollPane_1 = new JScrollPane();
		scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

		scrollPane_2 = new JScrollPane();
		scrollPane_2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

		scrollPane_3 = new JScrollPane();
		scrollPane_3.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_3.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

		scrollPane_4 = new JScrollPane();
		scrollPane_4.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_4.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

		scrollPane_5 = new JScrollPane();
		scrollPane_5.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_5.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);


		lblGameIdDisplay = new JLabel("Game ID");

		lblGameId = new JLabel("999999999");

		p1Panel = new JPanel();
		p1Panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Player one", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));

		p2Panel = new JPanel();
		p2Panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Player two", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		p2LblScore = new JLabel("Score2");
		p2LblScoreValue = new JLabel("0");

		p2BtnDice = new JButton("Dice");



		p2ButtonFold = new JButton("Fold");



		p2LblPlayerName = new JLabel("New label");

		label_1 = new JLabel("Turns");

		label2Turn = new JLabel("0");

		GroupLayout gl_p2Panel = new GroupLayout(p2Panel);
		gl_p2Panel.setHorizontalGroup(
				gl_p2Panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_p2Panel.createSequentialGroup()
						.addGroup(gl_p2Panel.createParallelGroup(Alignment.LEADING)
								.addGroup(Alignment.TRAILING, gl_p2Panel.createSequentialGroup()
										.addComponent(p2LblScore)
										.addPreferredGap(ComponentPlacement.RELATED, 56, Short.MAX_VALUE)
										.addComponent(p2LblScoreValue, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE))
								.addGroup(Alignment.TRAILING, gl_p2Panel.createSequentialGroup()
										.addContainerGap()
										.addComponent(p2BtnDice, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE))
								.addGroup(Alignment.TRAILING, gl_p2Panel.createSequentialGroup()
										.addContainerGap(15, Short.MAX_VALUE)
										.addComponent(p2ButtonFold, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE))
								.addGroup(Alignment.TRAILING, gl_p2Panel.createSequentialGroup()
										.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED, 82, Short.MAX_VALUE)
										.addComponent(label2Turn, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE))
								.addComponent(p2LblPlayerName))
						.addContainerGap())
				);
		gl_p2Panel.setVerticalGroup(
				gl_p2Panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_p2Panel.createSequentialGroup()
						.addContainerGap()
						.addComponent(p2LblPlayerName)
						.addGap(15)
						.addGroup(gl_p2Panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(p2LblScoreValue)
								.addComponent(p2LblScore))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(gl_p2Panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(label2Turn)
								.addComponent(label_1))
						.addGap(83)
						.addComponent(p2BtnDice)
						.addPreferredGap(ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
						.addComponent(p2ButtonFold)
						.addGap(33))
				);
		p2Panel.setLayout(gl_p2Panel);



		JLabel lblTurnLabel = new JLabel("Who's  pig is it anyway :");


		picturelblNewLabel = new JLabel(new ImageIcon("resources/236abf6f16fb64272b6249756d01e187[1].jpg"));

		lblWhoTurnValue = new JLabel("Player1");

		btnConcedeGame = new JButton("Player Concedes Game"); // Only switched on after 1 player reaches 100


		panel3 = new JPanel();
		panel3.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Player two", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));

		JLabel label = new JLabel("Score2");

		p3LblScoreValue = new JLabel("0");

		p3BtnDice = new JButton("Dice");

		p3LblPlayerName = new JLabel("New label");

		p3ButtonFold = new JButton("Fold");

		label3Turn = new JLabel("0");

		label_5 = new JLabel("Turns");
		GroupLayout gl_panel3 = new GroupLayout(panel3);
		gl_panel3.setHorizontalGroup(
				gl_panel3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel3.createSequentialGroup()
						.addGroup(gl_panel3.createParallelGroup(Alignment.LEADING)
								.addGroup(Alignment.TRAILING, gl_panel3.createSequentialGroup()
										.addComponent(label)
										.addPreferredGap(ComponentPlacement.RELATED, 68, Short.MAX_VALUE)
										.addComponent(p3LblScoreValue, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE))
								.addComponent(p3LblPlayerName, Alignment.TRAILING)
								.addGroup(Alignment.TRAILING, gl_panel3.createSequentialGroup()
										.addContainerGap()
										.addComponent(p3BtnDice, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE))
								.addGroup(Alignment.TRAILING, gl_panel3.createSequentialGroup()
										.addContainerGap(27, Short.MAX_VALUE)
										.addComponent(p3ButtonFold, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE))
								.addGroup(Alignment.TRAILING, gl_panel3.createSequentialGroup()
										.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(label_5, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
										.addGap(56)
										.addComponent(label3Turn, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)))
						.addContainerGap())
				);
		gl_panel3.setVerticalGroup(
				gl_panel3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel3.createSequentialGroup()
						.addGap(15)
						.addComponent(p3LblPlayerName)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(gl_panel3.createParallelGroup(Alignment.BASELINE)
								.addComponent(p3LblScoreValue)
								.addComponent(label))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_panel3.createParallelGroup(Alignment.LEADING)
								.addComponent(label_5)
								.addComponent(label3Turn))
						.addGap(75)
						.addComponent(p3BtnDice)
						.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(p3ButtonFold)
						.addGap(33))
				);
		panel3.setLayout(gl_panel3);

		panel4 = new JPanel();
		panel4.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Player Four", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));

		label_4 = new JLabel("Score2");

		p4LblScoreValue = new JLabel("0");

		p4BtnDice = new JButton("Dice");

		p4LblPlayerName = new JLabel("New label");

		p4ButtonFold = new JButton("Fold");

		label4Turn = new JLabel("0");

		label_8 = new JLabel("Turns");
		GroupLayout gl_panel4 = new GroupLayout(panel4);
		gl_panel4.setHorizontalGroup(
				gl_panel4.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel4.createSequentialGroup()
						.addGroup(gl_panel4.createParallelGroup(Alignment.LEADING)
								.addGroup(Alignment.TRAILING, gl_panel4.createSequentialGroup()
										.addComponent(label_4)
										.addPreferredGap(ComponentPlacement.RELATED, 68, Short.MAX_VALUE)
										.addComponent(p4LblScoreValue, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE))
								.addComponent(p4LblPlayerName, Alignment.TRAILING)
								.addGroup(Alignment.TRAILING, gl_panel4.createSequentialGroup()
										.addContainerGap()
										.addComponent(p4BtnDice, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE))
								.addGroup(Alignment.TRAILING, gl_panel4.createSequentialGroup()
										.addContainerGap(27, Short.MAX_VALUE)
										.addComponent(p4ButtonFold, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel4.createSequentialGroup()
										.addComponent(label_8, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED, 56, Short.MAX_VALUE)
										.addComponent(label4Turn, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)))
						.addContainerGap())
				);
		gl_panel4.setVerticalGroup(
				gl_panel4.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel4.createSequentialGroup()
						.addGap(15)
						.addComponent(p4LblPlayerName)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(gl_panel4.createParallelGroup(Alignment.BASELINE)
								.addComponent(p4LblScoreValue)
								.addComponent(label_4))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(gl_panel4.createParallelGroup(Alignment.LEADING)
								.addComponent(label_8)
								.addComponent(label4Turn))
						.addGap(81)
						.addComponent(p4BtnDice)
						.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(p4ButtonFold)
						.addGap(33))
				);
		panel4.setLayout(gl_panel4);

		panel5 = new JPanel();
		panel5.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Player Five", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));

		JLabel label_7 = new JLabel("Score2");

		p5LblScoreValue = new JLabel("0");

		p5BtnDice = new JButton("Dice");

		p5LblPlayerName = new JLabel("New label");

		p5ButtonFold = new JButton("Fold");

		label5Turn = new JLabel("0");

		label_11 = new JLabel("Turns");
		GroupLayout gl_panel5 = new GroupLayout(panel5);
		gl_panel5.setHorizontalGroup(
				gl_panel5.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel5.createSequentialGroup()
						.addGroup(gl_panel5.createParallelGroup(Alignment.LEADING)
								.addGroup(Alignment.TRAILING, gl_panel5.createSequentialGroup()
										.addComponent(label_7)
										.addPreferredGap(ComponentPlacement.RELATED, 56, Short.MAX_VALUE)
										.addComponent(p5LblScoreValue, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE))
								.addComponent(p5LblPlayerName, Alignment.TRAILING)
								.addGroup(Alignment.TRAILING, gl_panel5.createSequentialGroup()
										.addContainerGap()
										.addComponent(p5BtnDice, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE))
								.addGroup(Alignment.TRAILING, gl_panel5.createSequentialGroup()
										.addContainerGap(15, Short.MAX_VALUE)
										.addComponent(p5ButtonFold, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE))
								.addGroup(Alignment.TRAILING, gl_panel5.createSequentialGroup()
										.addContainerGap()
										.addComponent(label_11, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED, 56, Short.MAX_VALUE)
										.addComponent(label5Turn, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)))
						.addContainerGap())
				);
		gl_panel5.setVerticalGroup(
				gl_panel5.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel5.createSequentialGroup()
						.addGap(15)
						.addComponent(p5LblPlayerName)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(gl_panel5.createParallelGroup(Alignment.BASELINE)
								.addComponent(p5LblScoreValue)
								.addComponent(label_7))
						.addGap(18)
						.addGroup(gl_panel5.createParallelGroup(Alignment.LEADING)
								.addComponent(label_11)
								.addComponent(label5Turn))
						.addGap(76)
						.addComponent(p5BtnDice)
						.addPreferredGap(ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
						.addComponent(p5ButtonFold)
						.addGap(33))
				);
		panel5.setLayout(gl_panel5);

		panel6 = new JPanel();
		panel6.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Player six", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));

		JLabel label_10 = new JLabel("Score2");

		p6LblScoreValue = new JLabel("0");

		p6BtnDice = new JButton("Dice");

		p6LblPlayerName = new JLabel("New label");

		p6ButtonFold = new JButton("Fold");

		label6Turn = new JLabel("0");

		label_13 = new JLabel("Turns");
		GroupLayout gl_panel6 = new GroupLayout(panel6);
		gl_panel6.setHorizontalGroup(
				gl_panel6.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel6.createSequentialGroup()
						.addGroup(gl_panel6.createParallelGroup(Alignment.LEADING)
								.addGroup(Alignment.TRAILING, gl_panel6.createSequentialGroup()
										.addComponent(label_10)
										.addPreferredGap(ComponentPlacement.RELATED, 56, Short.MAX_VALUE)
										.addComponent(p6LblScoreValue, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE))
								.addComponent(p6LblPlayerName, Alignment.TRAILING)
								.addGroup(Alignment.TRAILING, gl_panel6.createSequentialGroup()
										.addContainerGap()
										.addComponent(p6BtnDice, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE))
								.addGroup(Alignment.TRAILING, gl_panel6.createSequentialGroup()
										.addContainerGap(15, Short.MAX_VALUE)
										.addComponent(p6ButtonFold, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE))
								.addGroup(Alignment.TRAILING, gl_panel6.createSequentialGroup()
										.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(label_13, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
										.addGap(56)
										.addComponent(label6Turn, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)))
						.addContainerGap())
				);
		gl_panel6.setVerticalGroup(
				gl_panel6.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel6.createSequentialGroup()
						.addGap(15)
						.addComponent(p6LblPlayerName)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(gl_panel6.createParallelGroup(Alignment.BASELINE)
								.addComponent(p6LblScoreValue)
								.addComponent(label_10))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(gl_panel6.createParallelGroup(Alignment.LEADING)
								.addComponent(label_13)
								.addComponent(label6Turn))
						.addGap(81)
						.addComponent(p6BtnDice)
						.addPreferredGap(ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
						.addComponent(p6ButtonFold)
						.addGap(33))
				);
		panel6.setLayout(gl_panel6);

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
				gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPane.createSequentialGroup()
										.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
												.addGroup(gl_contentPane.createSequentialGroup()
														.addComponent(lblGameIdDisplay, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
														.addGap(50)
														.addComponent(lblGameId, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
												.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 158, GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(ComponentPlacement.RELATED))
								.addComponent(p1Panel, GroupLayout.PREFERRED_SIZE, 158, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
										.addComponent(p2Panel, GroupLayout.PREFERRED_SIZE, 158, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(panel3, GroupLayout.PREFERRED_SIZE, 158, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(panel4, GroupLayout.PREFERRED_SIZE, 158, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addComponent(panel5, GroupLayout.PREFERRED_SIZE, 158, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(panel6, GroupLayout.PREFERRED_SIZE, 158, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
										.addGap(72)
										.addComponent(picturelblNewLabel, GroupLayout.PREFERRED_SIZE, 222, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 158, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(scrollPane_2, GroupLayout.PREFERRED_SIZE, 158, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(scrollPane_3, GroupLayout.PREFERRED_SIZE, 158, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(scrollPane_4, GroupLayout.PREFERRED_SIZE, 158, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(scrollPane_5, GroupLayout.PREFERRED_SIZE, 176, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
										.addGap(37)
										.addComponent(lblTurnLabel)
										.addGap(18)
										.addComponent(lblWhoTurnValue)
										.addGap(52)
										.addComponent(btnConcedeGame)))
						.addGap(43))
				);
		gl_contentPane.setVerticalGroup(
				gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblGameIdDisplay)
								.addComponent(lblGameId)
								.addComponent(lblTurnLabel)
								.addComponent(lblWhoTurnValue)
								.addComponent(btnConcedeGame))
						.addGap(20)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
										.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 267, GroupLayout.PREFERRED_SIZE)
										.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 267, GroupLayout.PREFERRED_SIZE)
										.addComponent(scrollPane_2, GroupLayout.PREFERRED_SIZE, 267, GroupLayout.PREFERRED_SIZE)
										.addComponent(scrollPane_3, GroupLayout.PREFERRED_SIZE, 267, GroupLayout.PREFERRED_SIZE)
										.addComponent(scrollPane_4, GroupLayout.PREFERRED_SIZE, 267, GroupLayout.PREFERRED_SIZE))
								.addComponent(scrollPane_5, GroupLayout.PREFERRED_SIZE, 267, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPane.createSequentialGroup()
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(panel6, GroupLayout.PREFERRED_SIZE, 248, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(panel5, GroupLayout.PREFERRED_SIZE, 248, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(panel4, GroupLayout.PREFERRED_SIZE, 248, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
										.addGap(27)
										.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
												.addComponent(p1Panel, GroupLayout.DEFAULT_SIZE, 248, Short.MAX_VALUE)
												.addComponent(p2Panel, GroupLayout.DEFAULT_SIZE, 248, Short.MAX_VALUE)))
								.addGroup(gl_contentPane.createSequentialGroup()
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(panel3, GroupLayout.PREFERRED_SIZE, 248, GroupLayout.PREFERRED_SIZE)))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(picturelblNewLabel, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE)
						.addContainerGap())
				);

		jTextarea5 = new JTextArea();
		jTextarea5.setEditable(false);
		scrollPane_5.setViewportView(jTextarea5);

		jTextarea4 = new JTextArea();
		jTextarea4.setEditable(false);
		scrollPane_4.setViewportView(jTextarea4);

		jTextarea3 = new JTextArea();
		jTextarea3.setEditable(false);
		scrollPane_3.setViewportView(jTextarea3);

		jTextarea2 = new JTextArea();
		jTextarea2.setEditable(false);
		scrollPane_2.setViewportView(jTextarea2);

		jTextarea1 = new JTextArea();
		jTextarea1.setEditable(false);
		scrollPane_1.setViewportView(jTextarea1);

		jTextarea0 = new JTextArea();
		jTextarea0.setEditable(false);
		scrollPane.setViewportView(jTextarea0);
		p1LblScore = new JLabel("Score1");
		p1Lbl1ScoreValue = new JLabel("0");

		p1BtnDice = new JButton("Dice");


		p1BtnFold = new JButton("Fold");


		p1LblPlayerName = new JLabel("New label");

		JLabel labelTurnsLiteral = new JLabel("Turns");

		label1Turns = new JLabel("0");

		GroupLayout gl_p1Panel = new GroupLayout(p1Panel);
		gl_p1Panel.setHorizontalGroup(
				gl_p1Panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_p1Panel.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_p1Panel.createParallelGroup(Alignment.LEADING)
								.addComponent(p1BtnDice, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE)
								.addComponent(p1BtnFold, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE)
								.addComponent(p1LblPlayerName)
								.addGroup(gl_p1Panel.createSequentialGroup()
										.addGroup(gl_p1Panel.createParallelGroup(Alignment.LEADING)
												.addComponent(p1LblScore)
												.addComponent(labelTurnsLiteral, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
										.addGap(70)
										.addGroup(gl_p1Panel.createParallelGroup(Alignment.LEADING)
												.addComponent(label1Turns, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
												.addComponent(p1Lbl1ScoreValue, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE))))
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				);
		gl_p1Panel.setVerticalGroup(
				gl_p1Panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_p1Panel.createSequentialGroup()
						.addContainerGap()
						.addComponent(p1LblPlayerName)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_p1Panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(p1Lbl1ScoreValue)
								.addComponent(p1LblScore))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(gl_p1Panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(labelTurnsLiteral)
								.addComponent(label1Turns))
						.addGap(29)
						.addComponent(p1BtnDice)
						.addPreferredGap(ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
						.addComponent(p1BtnFold)
						.addGap(32))
				);
		p1Panel.setLayout(gl_p1Panel);
		contentPane.setLayout(gl_contentPane);

	}
}
