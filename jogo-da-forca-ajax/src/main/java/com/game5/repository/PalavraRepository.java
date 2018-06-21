package com.game5.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.game5.model.Palavra;

public interface PalavraRepository extends JpaRepository<Palavra, Long> {

	@Query(nativeQuery = true, value="SELECT * FROM palavra ORDER BY RAND() LIMIT 1")
	public Palavra randomPalavra();
	
	public Optional<Palavra> findByNomeIgnoreCase(String nome);
}
