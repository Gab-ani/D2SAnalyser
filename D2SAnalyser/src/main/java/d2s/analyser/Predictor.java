package d2s.analyser;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import d2s.analyser.data.Matchups;
import d2s.analyser.domain.Hero;
import d2s.analyser.domain.PredictRequest;

@Service
public class Predictor {

	@Autowired
	Matchups matchupsData;
	
	public String predict(PredictRequest request) {
		return predict(request.getSelection(),request.getRadiant(), request.getDire());
	}
	
	public String predict(String selectionName, Hero[] radiant, Hero[] dire) {
		
		double direCombined = teamSynergy(dire, selectionName) + advantage(dire, radiant, selectionName);
		double radiantCombined = teamSynergy(radiant, selectionName) + advantage(radiant, dire, selectionName);
		
		if(radiantCombined > direCombined)
			return "radiant: " + radiantCombined + " > " + direCombined + " difference: " + (radiantCombined - direCombined);
		
		return "dire: " + radiantCombined + " < " + direCombined + " difference: " + (direCombined - radiantCombined);
	}
	
	private double advantage(Hero[] team, Hero[] over, String selectionName) {
		double advantage = 0;
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 5; j++) {
				advantage += matchupsData.againstEnemy(team[i], over[j], selectionName);
			}
		}
		return advantage;
	}

	private double teamSynergy(Hero[] team, String selectionName) {
		double synergy = 0;
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 5; j++) {
				if(j != i){
					//System.out.println(team[i].getId() + "-" + team[j].getId() + " " + matchupData.withAlly(team[i].getId(), team[j].getId()));
					synergy += matchupsData.withAlly(team[i], team[j], selectionName);					
				}
			}
		}
		return synergy;
	}
	
}
