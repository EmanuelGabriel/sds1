package br.com.emanuelgabriel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.emanuelgabriel.model.Game;

public interface GameRepository extends JpaRepository<Game, Long> {

	
	
}
