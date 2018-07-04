package vzap.losh.game;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import vzap.losh.enums.PlayerStatus;
import vzap.losh.gamePlayers.Player;

public class GameSheetDAO {



	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public Vector<PlayerScore> loadNewGameSheet(Connection con, int currentGame, Vector<Player> players) {
		// TODO Auto-generated method stub

		try {

			String playerStatus= PlayerStatus.ACTIVE.toString();

			int score=0, turnsPlayer=0;
			boolean affects;
			ResultSet rs;


			String insertSQL = "insert into gameSheet  (gameid, playerid, playerstatus)values(?,?,?);" ;

			PreparedStatement pStmt  = con.prepareStatement(insertSQL);

			Vector<PlayerScore> playerScoresOut = new Vector<PlayerScore>();

			for (Player p: players)
			{
				pStmt.setInt(1, currentGame);//gameid
				pStmt.setInt(2, p.getPlayerID());
				pStmt.setString(3, playerStatus);

				System.out.println(" Statement creation " + pStmt.toString() );

				affects = pStmt.execute();

				playerScoresOut.addElement( new PlayerScore(p.getPlayerID()) );

			}
			return playerScoresOut;

		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

}
