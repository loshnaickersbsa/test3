package vzap.losh.gamePlayers;

public class PlayersClass2DAO 
{

  private String playerName;
  private String playerAlias;
  private int playerScore;

  /**
  getter method name
  @reutrn
  */
  public PlayersClass2DAO(String playerName, String alias)
  {
      this.playerName = playerName;
      playerScore =0;
  }
  
  public String getPlayerName()
  {
      return this.playerName;
  } 
  
  
  public void setPlayerName(String playerName)
  {
      this.playerName = playerName;
  }

  public int getPlayerScore()
  {
      return this.playerScore;
  } 
  
  
  public void setPlayerScore(int playerScroe)
  {
      this.playerScore = playerScore;
  }
  
  public void addToPlayerScore(int currentScore)
  {
      this.playerScore +=  currentScore;
  }

protected String getPlayerAlias() {
	return playerAlias;
}

protected void setPlayerAlias(String playerAlias) {
	this.playerAlias = playerAlias;
}

@Override
public String toString() {
	return "Player [playerName=" + playerName + ", playerAlias=" + playerAlias + ", playerScore=" + playerScore + "]";
}

}//end of class
