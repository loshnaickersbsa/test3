package vzap.losh;

import java.util.Vector;
import vzap.losh.game.GameSheet;
import vzap.losh.gamePlayers.Player;

public class GameConsole
{


	private static Vector<Player> playersPlaying;

	public static void main (String args)
	{
		playersPlaying = new Vector<Player>(); 
		playersPlaying.addElement(p1);
		playersPlaying.addElement(p2);
		
		// players names entered 

		
		//start the 
		
		
		try 
		{
			//create a new gameid , auto increment your gameID
			newGameSheet = new GameSheet(con, playersPlaying);


			playerScores = newGameSheet.getPlayerScores();

			this.getContentPane().setVisible(true);

			switch(numberOfPlayers)
			{
			case 6:
				//p6 on 
			case 5:
			case 4:
			case 3:
			case 2:
				//p2Store=(Vector) 
				//store p2 score 
				p2ID = playerScores.get(1).getPlayer();
				//store p2 name data , alias, picture
				p2Player=registerPlayerList.getPlayerObject(p2ID);
				p2LblPlayer2Name.setText(p2Player.getPlayerName());

			case 1:

				//store p2 score 
				p1ID = playerScores.get(0).getPlayer();
				//store p2 name data , alias, picture
				p1Player=registerPlayerList.getPlayerObject(p1ID);
				p1LblPlayer2Name.setText(p2Player.getPlayerName());

			}

		}
	}

}