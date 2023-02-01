package d2s.analyser.data;

import org.springframework.data.jpa.repository.JpaRepository;

import d2s.analyser.domain.Hero;


public interface HeroRepository extends JpaRepository<Hero, Integer>{

}
