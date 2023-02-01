package d2s.analyser.data;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import d2s.analyser.domain.Hero;

@Service
public class Heroes {

	@Autowired
	private HeroRepository heroesDB;
	
	public List<Hero> findAll() {
		return heroesDB.findAll();
	}
	
}
