package vzap.losh.game;

import vzap.losh.enums.PlayerStatus;
import vzap.losh.gamePlayers.Player;

public class PlayerScore {

	private int player;
	private PlayerStatus playerStatus; //active, folded
	private int    score;
	private int    turnsPlayed;
	
	
	
	public PlayerScore(int player, PlayerStatus status, int score, int turns) {
		super();
		this.player = player;
		this.playerStatus = status;
		this.score = score;
		this.turnsPlayed = turns;
	}
	
	public PlayerScore(int player) {
		this.player = player;
		this.playerStatus = PlayerStatus.ACTIVE;
		this.score = 0;
		this.turnsPlayed = 0;
	}


	public PlayerStatus getStatus() {
		return playerStatus;
	}

	public void setStatus(PlayerStatus status) {
		this.playerStatus = status;
	}

	
	public int getPlayer() {
		return player;
	}

	public void setPlayer(int player) {
		this.player = player;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
	public void addScore(int score) {
		this.score += score;
	}

	public int getTurns() {
		return turnsPlayed;
	}

	public void setTurns(int turns) {
		this.turnsPlayed = turns;
	}

	public void addTurns(int turns) {
		this.turnsPlayed += turns;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

}
