package br.com.emanuelgabriel.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.emanuelgabriel.dtos.GameDTO;
import br.com.emanuelgabriel.services.GameService;

@RestController
@RequestMapping(value = "/games", produces = MediaType.APPLICATION_JSON_VALUE)
public class GameResource {

	@Autowired
	private GameService gameService;

	@GetMapping
	public ResponseEntity<List<GameDTO>> findAll() {
		List<GameDTO> games = gameService.findAll();
		return games != null ? ResponseEntity.ok().body(games) : ResponseEntity.notFound().build();
	}

}
