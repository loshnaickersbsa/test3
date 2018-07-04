/**
 * 
 */
package vzap.losh.game;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import vzap.losh.enums.GameStatus;
import vzap.losh.gamePlayers.Player;

/**
 * @author A207266
 *
 */
public class GameSheet {

	private Vector<PlayerScore> playerScores;
	//
	private boolean affects;
	private ResultSet rs;
	private int currentGame;
	private GameSheetDAO gameSheettDAO;
	private Vector<Player> players;
	private GameDAO gameDAO;
	
	public Vector<PlayerScore> getPlayerScores() {
		return playerScores;
	}
	
	public void savePlayerScores(int gameID,Vector<PlayerScore> playerScores) {
		this.playerScores = playerScores;
	}
	
	public int getCurrentGame() {
		return currentGame;
	}
	
	public GameSheet (Connection con, Vector<Player> players) throws SQLException
	{	/*
		 bankEmployees = new ArrayList<Person>();
		bankManagementDAO = new BankManagementDAO();
		bankEmployees = bankManagementDAO.loadBankEmployees();
		
		 * 
		 */
		System.out.println(" game creation");
		this.players =  players;
		gameDAO = new GameDAO();
		currentGame =gameDAO.createGame(con);

		System.out.println(" game sheet creation" + currentGame);
		
		gameSheettDAO = new GameSheetDAO();
		
		playerScores=gameSheettDAO.loadNewGameSheet(con, currentGame, players);
		
		//	gameId
		
		
		
		//insert the players
		
		
		
	}
	
	public boolean saveGameWinning(Connection con,int winnerPlayerId,GameStatus status) throws SQLException
	{
		return gameDAO.updateGame(con,status,this.currentGame, winnerPlayerId);
	}
	public boolean saveAbandonedGame(Connection con, GameStatus status) throws SQLException
	{
		return gameDAO.updateGame(con,status,this.currentGame, 0);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
