package br.com.emanuelgabriel.dtos;

import java.io.Serializable;

import br.com.emanuelgabriel.model.Game;
import br.com.emanuelgabriel.model.enums.Plataforma;

public class GameDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String titulo;
	private Plataforma plataforma;

	public GameDTO() {
	}

	/**
	 * converter um GameDTO em um objeto GAME
	 * 
	 * @param game
	 */
	public GameDTO(Game game) {
		this.id = game.getId();
		this.titulo = game.getTitulo();
		this.plataforma = game.getPlataforma();
	}

	public GameDTO(Long id, String titulo, Plataforma plataforma) {
		this.id = id;
		this.titulo = titulo;
		this.plataforma = plataforma;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Plataforma getPlataforma() {
		return plataforma;
	}

	public void setPlataforma(Plataforma plataforma) {
		this.plataforma = plataforma;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GameDTO other = (GameDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "GameDTO [id=" + id + ", titulo=" + titulo + ", plataforma=" + plataforma + "]";
	}

}
