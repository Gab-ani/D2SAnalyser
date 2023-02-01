package d2s.analyser.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;


@Entity
public class Hero {

	@Id
	private int id; // hero ids a count as stratz.com hero ids and those are valve's ids for heroes it seems.
	
	private String name;
	
	public Hero() {
		
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	
}
