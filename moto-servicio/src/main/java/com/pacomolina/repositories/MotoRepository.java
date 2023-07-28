package com.pacomolina.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pacomolina.entities.Moto;

@Repository
public interface MotoRepository extends JpaRepository<Moto, Long> {

	List<Moto> findByUsuarioId(long usuarioId);
}
