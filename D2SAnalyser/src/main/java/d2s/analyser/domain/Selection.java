package d2s.analyser.domain;

import java.util.ArrayList;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

public class Selection {

	private long dateFrom;
	private long dateTo;
	
	private ArrayList<HeroMatchup> data;
	
	public Selection() {
		
	}

	public long getDateFrom() {
		return dateFrom;
	}

	public long getDateTo() {
		return dateTo;
	}

	public ArrayList<HeroMatchup> getData() {
		return data;
	}

	public void setDateFrom(long dateFrom) {
		this.dateFrom = dateFrom;
	}

	public void setDateTo(long dateTo) {
		this.dateTo = dateTo;
	}

	public void setData(ArrayList<HeroMatchup> data) {
		this.data = data;
	}
	
	public void addToData(HeroMatchup matchup) {
		this.data.add(matchup);
	}
}
