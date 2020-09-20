package br.com.emanuelgabriel.dtos;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

public class GameInputCodigoDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull(message = "GameId não pode ser nulo ou vazio")
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
