package br.com.emanuelgabriel.resources;

import java.net.URI;
import java.time.Instant;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.emanuelgabriel.dtos.RegistroDTO;
import br.com.emanuelgabriel.dtos.RegistroInsertDTO;
import br.com.emanuelgabriel.model.Registro;
import br.com.emanuelgabriel.services.RegistroService;

@RestController
@RequestMapping(value = "/registros", produces = MediaType.APPLICATION_JSON_VALUE)
public class RegistroResource {

	@Autowired
	private RegistroService registroService;

	@Autowired
	private ModelMapper modelMapper;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<RegistroDTO> criar(@Valid @RequestBody RegistroInsertDTO dto) {
		RegistroDTO novoRegistro = registroService.criar(dto);
		return ResponseEntity.ok().body(novoRegistro);
	}

	@GetMapping
	public ResponseEntity<Page<RegistroDTO>> findAll(@RequestParam(value = "min", defaultValue = "") String min,
			@RequestParam(value = "max", defaultValue = "") String max,
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "0") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "moment") String orderBy,
			@RequestParam(value = "direction", defaultValue = "DESC") String direction) {

		Instant dataInicial = ("".equals(min)) ? null : Instant.parse(min);
		Instant dataFinal = ("".equals(max)) ? null : Instant.parse(max);

		if (linesPerPage == 0) {
			linesPerPage = Integer.MAX_VALUE;
		}

		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);

		Page<RegistroDTO> listaGames = registroService.findByMoments(dataInicial, dataFinal, pageRequest);
		return ResponseEntity.ok().body(listaGames);
	}

	private Registro toDTO(RegistroInsertDTO registroInsertDTO) {
		return modelMapper.map(registroInsertDTO, Registro.class);
	}

	private RegistroDTO toModel(Registro registro) {
		return modelMapper.map(registro, RegistroDTO.class);
	}

	private URI getUri(Long id) {
		return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
	}
}
