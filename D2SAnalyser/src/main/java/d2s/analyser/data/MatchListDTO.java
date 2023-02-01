package d2s.analyser.data;

import java.util.ArrayList;
import java.util.List;

import d2s.analyser.domain.Match;

public class MatchListDTO {

	private ArrayList<Match> matches;
	
	public MatchListDTO(ArrayList<Match> list) {
		matches = list;
	}
	
	public ArrayList<Match> getList() {
		return matches;
	}
	
}