package com.game5.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.game5.model.Tema;

/**
 * 
 * @author Alexandre Dias
 * 
 * Interface que lida com a CRUD de Temas, o próprio spring cria em tempo de execução
 *
 */

public interface TemaRepository extends JpaRepository<Tema, Long>{

	Optional<Tema> findByNomeIgnoreCase(String nome);

}
