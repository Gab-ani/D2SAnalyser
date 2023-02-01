package d2s.analyser.domain;

import java.util.ArrayList;
import java.util.List;

public class Match {

	private long matchId;
	 
	private long date;
	
	private String winner;
	
	private ArrayList<String> teamRadiant;
	private ArrayList<String> teamDire;

	private boolean parsed;
	
	public Match() {
		
	}
	
	public boolean isParsed() {
		return parsed;
	}

	public long getMatchId() {
		return matchId;
	}

	public long getDate() {
		return date;
	}

	public String getWinner() {
		return winner;
	}

	public ArrayList<String> getTeamRadiant() {
		return teamRadiant;
	}

	public ArrayList<String> getTeamDire() {
		return teamDire;
	}

	public void setMatchId(long matchId) {
		this.matchId = matchId;
	}

	public void setDate(long date) {
		this.date = date;
	}

	public void setWinner(String winner) {
		this.winner = winner;
	}

	public void setTeamRadiant(ArrayList<String> teamRadiant) {
		this.teamRadiant = teamRadiant;
	}

	public void setTeamDire(ArrayList<String> teamDire) {
		this.teamDire = teamDire;
	}

	public void setParsed(boolean parsed) {
		this.parsed = parsed;
	}
	
	public String toString() {
		return "Dota2 match n" + matchId + " played at " + date + " winner: " + winner;
	}
	
	public boolean played(Hero target) {
		for(String hero : teamDire) {
			if(Integer.parseInt(hero) == target.getId()) {
				return true;
			}
		}
		for(String hero : teamRadiant) {
			if(Integer.parseInt(hero) == target.getId()) {
				return true;
			}
		}
		return false;
	}
	
	public boolean heroWon(Hero target) {
		if( ( forDirePlayed(target) && winner.equals("dire") ) || ( forRadiantPlayed(target) && winner.equals("radiant") ) )
			return true;
		return false;
	}

	public boolean wereOpposed(Hero heroId, Hero enemyId) {
		if( (forRadiantPlayed(heroId) && forDirePlayed(enemyId) ) || (forRadiantPlayed(enemyId) && forDirePlayed(heroId) ))
			return true;
		return false;
	}
	
	public boolean teamedUp(Hero heroId, Hero allyId) {
		if( (forRadiantPlayed(heroId) && forRadiantPlayed(allyId) ) || (forDirePlayed(allyId) && forDirePlayed(heroId) ))
			return true;
		return false;
	}

	private boolean forDirePlayed(Hero target) {
		for(String hero : teamDire) {
			if(Integer.parseInt(hero) == target.getId()) {
				return true;
			}
		}
		return false;
	}

	private boolean forRadiantPlayed(Hero target) {
		for(String hero : teamRadiant) {
			if(Integer.parseInt(hero) == target.getId()) {
				return true;
			}
		}
		return false;
	}

	public boolean older(long date) {
		if(this.date >= date) {
			return false;
		}
		return true;
	}

	public List<String> getLosers() {
		if(winner.equals("radiant")) {
			return teamDire;
		} else {
			return teamRadiant;
		}
	}

	public List<String> getWinners() {
		if(winner.equals("dire")) {
			return teamDire;
		} else {
			return teamRadiant;
		}
	}
	
	
}

