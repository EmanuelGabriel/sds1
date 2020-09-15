package br.com.emanuelgabriel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.emanuelgabriel.model.Genero;

@Repository
public interface GeneroRepository extends JpaRepository<Genero, Long>{

}
