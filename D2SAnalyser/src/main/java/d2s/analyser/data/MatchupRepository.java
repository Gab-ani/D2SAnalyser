package d2s.analyser.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import d2s.analyser.domain.HeroMatchup;
import d2s.analyser.domain.Selection;

public interface MatchupRepository extends JpaRepository<HeroMatchup, String> {

	public Optional<HeroMatchup> findById(String id);
	
}
