package vzap.losh.game;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import vzap.losh.enums.GameStatus;

public class GameDAO {

	public int  createGame(Connection con) {
	
		// TODO Auto-generated method stub


		try {

			ResultSet rs;
			String insertSQL = "insert into game values();" ;
			PreparedStatement stmt  = con.prepareStatement(insertSQL);
		
			boolean affects = stmt.execute();
			
			String selectSQL = "select max(gameid) as topgameid from game";
			
			stmt  = con.prepareStatement(selectSQL);

			rs = stmt.executeQuery();
			rs.next();
			//rs.getInt("topgameid");
			return rs.getInt("topgameid");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(0);;
		}

		return -1;
	}

	public boolean updateGame(Connection con, GameStatus status, int gameId, int winnerPlayerId) throws SQLException {
	
		// TODO Auto-generated method stub
		String updateSQL = "Update game set  winnerPlayerId = ? , gameStatus = ? where gameid = ?;" ;
		PreparedStatement pStmt  = con.prepareStatement(updateSQL);
		
		pStmt.setInt(1, winnerPlayerId);
		pStmt.setString(2, status.toString());
		pStmt.setInt(3, gameId);
		System.out.println("pStmt :" + pStmt.toString());
		
		
		return   pStmt.execute();
		
	}
	
	//save game
	

	
	//load game to get the winner and of the games
	


}
