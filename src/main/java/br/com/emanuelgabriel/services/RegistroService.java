package br.com.emanuelgabriel.services;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.emanuelgabriel.dtos.RegistroDTO;
import br.com.emanuelgabriel.dtos.RegistroInsertDTO;
import br.com.emanuelgabriel.model.Game;
import br.com.emanuelgabriel.model.Registro;
import br.com.emanuelgabriel.repository.GameRepository;
import br.com.emanuelgabriel.repository.RegistroRepository;

@Service
public class RegistroService {

	@Autowired
	private RegistroRepository registroRepository;

	@Autowired
	private GameRepository gameRepository;

	@Transactional
	public RegistroDTO criar(RegistroInsertDTO dto) {
		Registro registro = new Registro();
		registro.setNome(dto.getNome());
		registro.setIdade(dto.getIdade());
		registro.setMoment(Instant.now()); 

		Game game = gameRepository.getOne(dto.getGameId());
		registro.setGame(game);

		registro = registroRepository.save(registro);
		return new RegistroDTO(registro);
	}

	@Transactional(readOnly = true)
	public Page<RegistroDTO> findByMoments(Instant dataInicial, Instant dataFinal, PageRequest pageRequest) {
		return registroRepository.findByMoments(dataInicial, dataFinal, pageRequest).map(p -> new RegistroDTO(p));
	}

}
