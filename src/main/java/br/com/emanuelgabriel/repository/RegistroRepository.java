package br.com.emanuelgabriel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.emanuelgabriel.model.Registro;

@Repository
public interface RegistroRepository extends JpaRepository<Registro, Long> {

}
