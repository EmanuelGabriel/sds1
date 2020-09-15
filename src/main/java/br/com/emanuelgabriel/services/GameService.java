package br.com.emanuelgabriel.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.emanuelgabriel.dtos.GameDTO;
import br.com.emanuelgabriel.model.Game;
import br.com.emanuelgabriel.repository.GameRepository;

@Service
public class GameService {

	@Autowired
	private GameRepository gameRepository;

	@Transactional(readOnly = true)
	public List<GameDTO> findAll() {
		List<Game> listaGames = gameRepository.findAll();
		return listaGames.stream().map(g -> new GameDTO(g)).collect(Collectors.toList());
	}

}
