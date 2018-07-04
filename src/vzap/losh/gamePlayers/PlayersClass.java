package vzap.losh.gamePlayers;

import java.util.Vector;

public class PlayersClass 
{

	private Vector<Player> registeredPlayers ;
	/**
  getter method name
  @reutrn
	 */
	public PlayersClass()
	{
		registeredPlayers = returnRegisteredPlayers();
		// new Player ??
		// assignment to load the values
	}

	protected Vector<Player> getRegisteredPlayers() {
		return registeredPlayers;
	}

	private Vector<Player> returnRegisteredPlayers()
	{
		Vector<Player> output = new Vector<Player>(); 
		output.addElement(new Player("losh", "Dare Devil","resources/piggy.jpg",1)); //player id last will be auto
																					//generated on registration
		output.addElement(new Player ("Kaiden","Batman","resources/piggy.jpg",2));
		return output;
	}

	public Player getPlayerObject(int playerId)
	{
		// sql prepared statement to return the value
		if (playerId == 2)
			return new Player("losh", "Dare Devil","resources/piggy.jpg",1);
		else 
			return	new Player("kaiden", "Batman","resources/piggy.jpg",2);
	} 

	public boolean addPlayerName(Player goOnPlayer)
	{
		return true;
	}

	@Override
	public String toString() {
		return "PlayersClass [registeredPlayers=" + registeredPlayers + "]";
	}


}//end of class
