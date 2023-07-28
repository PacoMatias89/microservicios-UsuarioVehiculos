package com.pacomolina.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pacomolina.entities.Coche;

@Repository
public interface CocheRepository extends JpaRepository<Coche, Long>{
	List<Coche> findByUsuarioId(long usuarioId);

}
