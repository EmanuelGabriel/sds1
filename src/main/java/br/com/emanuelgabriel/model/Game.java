package br.com.emanuelgabriel.model;

import java.io.Serializable;

import br.com.emanuelgabriel.model.enums.Plataforma;

public class Game implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String titulo;
	private Plataforma plataforma;

	private Genero genero;

	public Game() {
	}

	public Game(Long id, String titulo, Plataforma plataforma, Genero genero) {
		this.id = id;
		this.titulo = titulo;
		this.plataforma = plataforma;
		this.genero = genero;
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

	public Genero getGenero() {
		return genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
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
		Game other = (Game) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Game [id=" + id + ", titulo=" + titulo + ", plataforma=" + plataforma + ", genero=" + genero + "]";
	}

}
