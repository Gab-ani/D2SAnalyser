package d2s.analyser.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;

@Entity
public class HeroMatchup {
	
	@Id
	private String id;

	@OneToOne
	private Hero forHero;
	@OneToOne
	private Hero withHero;
	
	private double inOneTeam;
	private double againstEachOther;
	
	private String selectionName;
	
	public HeroMatchup() {
		
	}
	
	public HeroMatchup(Hero forHero, Hero withHero, double inOneTeam, double againstEachOther, String selection) {
		this.id = selection + "[" + forHero.getName() + " : " + withHero.getName() + "]";
		this.selectionName = selection;
		this.forHero = forHero;
		this.withHero = withHero;
		this.inOneTeam = inOneTeam;
		this.againstEachOther = againstEachOther;
	}

	public Hero getForHero() {
		return forHero;
	}
	
	public Hero getWithHero() {
		return withHero;
	}
	
	public double getInOneTeam() {
		return inOneTeam;
	}
	
	public double getAgainstEachOther() {
		return againstEachOther;
	}
	
	public String getSelectionName() {
		return selectionName;
	}
	
	public void setForHero(Hero forHero) {
		this.forHero = forHero;
	}
	
	public void setWithHero(Hero withHero) {
		this.withHero = withHero;
	}
	
	public void setInOneTeam(double inOneTeam) {
		this.inOneTeam = inOneTeam;
	}
	
	public void setAgainstEachOther(double againstEachOther) {
		this.againstEachOther = againstEachOther;
	}
	
	public void setSelection(String selection) {
		this.selectionName = selection;
	}
	
}
