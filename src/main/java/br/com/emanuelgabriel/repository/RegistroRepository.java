package br.com.emanuelgabriel.repository;

import java.time.Instant;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.emanuelgabriel.model.Registro;

@Repository
public interface RegistroRepository extends JpaRepository<Registro, Long> {

	@Query("SELECT obj FROM Registro obj WHERE " + "(coalesce(:min, null) IS NULL OR obj.moment >= :min) AND"
			+ "(coalesce(:max, null) IS NULL OR  obj.moment <= :max)")
	Page<Registro> findByMoments(Instant min, Instant max, Pageable pageable);

	Registro findByNome(String nome);

}
