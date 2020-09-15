package br.com.emanuelgabriel.resources;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.emanuelgabriel.dtos.RegistroDTO;
import br.com.emanuelgabriel.dtos.RegistroInsertDTO;
import br.com.emanuelgabriel.services.RegistroService;

@RestController
@RequestMapping(value = "/registros", produces = MediaType.APPLICATION_JSON_VALUE)
public class RegistroResource {

	@Autowired
	private RegistroService registroService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<RegistroDTO> criar(@Valid @RequestBody RegistroInsertDTO dto) {
		RegistroDTO novoRegistro = registroService.criar(dto);
		return ResponseEntity.ok().body(novoRegistro);
	}

	private URI getUri(Long id) {
		return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
	}
}
