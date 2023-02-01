package d2s.analyser.data;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import d2s.analyser.domain.Hero;
import d2s.analyser.domain.HeroMatchup;
import d2s.analyser.domain.Selection;

@Service
public class Matchups {
	
	@Autowired
	private MatchupRepository matchupsDB;
	
	public void add(HeroMatchup match) {
		matchupsDB.save(match);
	}
	
	public HeroMatchup findById(String id) {
		var selection = matchupsDB.findById(id);
		return selection.get();
	}

	public double againstEnemy(Hero target, Hero enemy, String selection) {
		return matchupsDB.findById(selection + "[" + target.getName() + " : " + enemy.getName() + "]").get().getAgainstEachOther();
	}

	public double withAlly(Hero target, Hero ally, String selection) {
		return matchupsDB.findById(selection + "[" + target.getName() + " : " + ally.getName() + "]").get().getInOneTeam();
	}
	
//	public double withAlly(int target, int ally) {
//		return matchups.getById(target + "-" + ally).getWith();
//	}
//	
//	public double againstEnemy(int target, int enemy) {
//		return matchups.getById(target + "-" + enemy).getAgainst();
//	}

}
