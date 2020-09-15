package br.com.emanuelgabriel.dtos;

import java.io.Serializable;
import java.time.Instant;

import javax.validation.constraints.NotBlank;

import br.com.emanuelgabriel.model.Registro;
import br.com.emanuelgabriel.model.enums.Plataforma;

public class RegistroDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private Instant moment;

	@NotBlank(message = "Nome não pode ser vazio")
	private String nome;

	@NotBlank(message = "Idade não pode ser vazio")
	private Integer idade;

	@NotBlank(message = "Título do Game não pode ser vazio")
	private String tituloGame;

	private Plataforma plataformaGame;

	@NotBlank(message = "Nome do Gênero não pode ser vazio")
	private String nomeGenero;

	public RegistroDTO() {
	}

	public RegistroDTO(Registro registro) {
		this.id = registro.getId();
		this.moment = registro.getMoment();
		this.nome = registro.getNome();
		this.idade = registro.getIdade();
		this.tituloGame = registro.getGame().getTitulo();
		this.plataformaGame = registro.getGame().getPlataforma();
		this.nomeGenero = registro.getGame().getGenero().getNome();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Instant getMoment() {
		return moment;
	}

	public void setMoment(Instant moment) {
		this.moment = moment;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getIdade() {
		return idade;
	}

	public void setIdade(Integer idade) {
		this.idade = idade;
	}

	public String getTituloGame() {
		return tituloGame;
	}

	public void setTituloGame(String tituloGame) {
		this.tituloGame = tituloGame;
	}

	public Plataforma getPlataformaGame() {
		return plataformaGame;
	}

	public void setPlataformaGame(Plataforma plataformaGame) {
		this.plataformaGame = plataformaGame;
	}

	public String getNomeGenero() {
		return nomeGenero;
	}

	public void setNomeGenero(String nomeGenero) {
		this.nomeGenero = nomeGenero;
	}

}
