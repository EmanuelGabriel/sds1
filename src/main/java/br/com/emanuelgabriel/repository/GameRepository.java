package br.com.emanuelgabriel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.emanuelgabriel.model.Game;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {

	
	
}
