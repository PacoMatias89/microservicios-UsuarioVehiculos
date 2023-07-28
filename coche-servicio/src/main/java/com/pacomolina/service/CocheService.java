package com.pacomolina.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pacomolina.entities.Coche;
import com.pacomolina.repository.CocheRepository;

@Service
public class CocheService {

	@Autowired
	private CocheRepository cocheRepository;

	public List<Coche> getAllCoche() {
		return cocheRepository.findAll();
	}

	public Coche getCocheById(long id) {
		return cocheRepository.findById(id).orElse(null);
	}

	public Coche guardar(Coche coche) {
		Coche cocheNuevo = cocheRepository.save(coche);
		return cocheNuevo;
	}

	public List<Coche> byUsuarioId(long idUsuario) {
		return cocheRepository.findByUsuarioId(idUsuario);
	}

}
