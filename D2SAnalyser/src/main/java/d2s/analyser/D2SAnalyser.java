package d2s.analyser;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import d2s.analyser.data.Heroes;
import d2s.analyser.data.Matchups;
import d2s.analyser.domain.Hero;
import d2s.analyser.domain.HeroMatchup;
import d2s.analyser.domain.Match;
import d2s.analyser.domain.Selection;

@Service
public class D2SAnalyser {

	@Autowired
	private Matchups selectionsDB;
	
	@Autowired
	private Heroes heroDB;
	
	public void createSelection(List<Match> matches, String name) {
		
		System.out.println("creating selection " + name);
		
		var allHeroes = heroDB.findAll();
		
		for(Hero target : allHeroes) {
			System.out.println("target : " + target.getName());
			for(Hero secondary : allHeroes) {
//				System.out.println(target.getName() + " : " + secondary.getName());
				if(target.getId() == secondary.getId())
					continue;
				HeroMatchup matchup = new HeroMatchup(target, secondary, calculateWith(target, secondary, matches), calculateAgainst(target, secondary, matches), name);
				selectionsDB.add(matchup);
			}
		}
		
	}
	
	public List<Match> selectByHero(Hero hero, List<Match> matches) {
		List<Match> subHistory = new ArrayList<>();
		for(Match match : matches) {
			if(match.played(hero)) {
				subHistory.add(match);
			}
		}
		return subHistory;
	}
	
	public List<Match> selectByEnemy(Hero hero, Hero enemy, List<Match> matches) {
		List<Match> subHistory = new ArrayList<>();
		for(Match match : matches) {
			if(match.wereOpposed(hero, enemy)) {
				subHistory.add(match);
			}
		}
		return subHistory;
	}
	
	public List<Match> selectByAlly(Hero hero, Hero ally, List<Match> matches) {
		List<Match> subHistory = new ArrayList<>();
		for(Match match : matches) {
			if(match.teamedUp(hero, ally)) {
				subHistory.add(match);
			}
		}
		return subHistory;
	}
	
	public double winrate(List<Match> subHistory, Hero hero) {
		double wins = 0;
		double games = 0;
		for(Match match : subHistory) {
			games += 1;
			if(match.heroWon(hero))
				wins += 1;
		}
		if(games == 0)
			return -1;
		return wins / games;
	}
	
	public double calculateAgainst(Hero forHero, Hero againstHero, List<Match> matches) {
//		System.out.println(selectByEnemy(forHero, againstHero).size() + " матчей " + forHero + " " + againstHero);
		return winrate( selectByEnemy(forHero, againstHero, matches) , forHero ) - baseWinrate(forHero, matches);
	}
	
	public double calculateWith(Hero forHero, Hero withHero, List<Match> matches) {
//		System.out.println(selectByAlly(forHero, withHero).size() + " матчей " + forHero + " " + withHero);
		return winrate( selectByAlly(forHero, withHero, matches) , forHero ) - baseWinrate(forHero, matches);
	}
	
	public double baseWinrate(Hero hero, List<Match> matches) {
		return winrate(selectByHero(hero, matches), hero);
	}
	
}
