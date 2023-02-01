package d2s.analyser.domain;

public class PredictRequest {

	private Hero[] radiant;
	private Hero[] dire;
	
	private String selection;
	
	public PredictRequest() {
		
	}

	public Hero[] getRadiant() {
		return radiant;
	}

	public Hero[] getDire() {
		return dire;
	}

	public String getSelection() {
		return selection;
	}

	public void setRadiant(Hero[] radiant) {
		this.radiant = radiant;
	}

	public void setDire(Hero[] dire) {
		this.dire = dire;
	}

	public void setSelection(String selection) {
		this.selection = selection;
	}
	
}
