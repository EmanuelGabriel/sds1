package br.com.emanuelgabriel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.emanuelgabriel.model.Registro;

public interface RegistroRepository extends JpaRepository<Registro, Long> {

}
