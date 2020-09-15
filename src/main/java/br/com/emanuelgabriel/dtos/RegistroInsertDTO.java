package br.com.emanuelgabriel.dtos;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

public class RegistroInsertDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotBlank(message = "Nome não pode ser vazio")
	private String nome;

	private Integer idade;

	private Long gameId;

	public RegistroInsertDTO() {
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

	public Long getGameId() {
		return gameId;
	}

	public void setGameId(Long gameId) {
		this.gameId = gameId;
	}

	@Override
	public String toString() {
		return "RegistroInsertDTO [nome=" + nome + ", idade=" + idade + ", gameId=" + gameId + "]";
	}

}
