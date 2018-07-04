package vzap.losh.gamePlayers;

public class Player 
{

	private int     playerID;
	private String playerName;

	private String playerAlias;
	private String avatar;

	public Player(String playerName, String alias, String avatar, int playerID)
	{
		this.playerName = playerName;
		this.playerAlias = alias;
		this.avatar = avatar;
	 
		// connect to the DB and get the autoincrement
		
		this.playerID = playerID;
		
		
		// load DAO

	}
	

	public Player(String playerName, String alias)
	{
		
	}


	public String getPlayerName()
	{
		return this.playerName;
	} 


	@Override
	public String toString() {
		return "Player [playerName=" + playerName + ", playerAlias=" + playerAlias + ", avatar=" + avatar + "]";
	}

	public void setPlayerName(String playerName)
	{
		this.playerName = playerName;
	}

	public String getPlayerAlias() {
		return playerAlias;
	}

	public void setPlayerAlias(String playerAlias) {
		this.playerAlias = playerAlias;
	}

	public int getPlayerID() {
		return playerID;
	}

	public void setPlayerID(int playerID) {
		this.playerID = playerID;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
}//end of class

