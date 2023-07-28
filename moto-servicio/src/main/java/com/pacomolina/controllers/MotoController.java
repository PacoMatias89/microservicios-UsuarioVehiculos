package com.pacomolina.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pacomolina.entities.Moto;
import com.pacomolina.service.MotoService;



@RestController
@RequestMapping("/moto")
public class MotoController {
	
	@Autowired
	private MotoService motoService;
	
	@GetMapping
	public ResponseEntity<List<Moto>> ListMotos(){
		List<Moto> motos = motoService.getAllMoto();
		if (motos.isEmpty()) {
			// obtenemos el código 204
			return ResponseEntity.noContent().build();
		}
		// obtenemos el código 200
		return ResponseEntity.ok(motos);
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<Moto> getMoto(@PathVariable("id") long id){
		Moto moto = motoService.getMotoById(id);
		
		if (moto == null) {
			// obtenemos el código 404
			return ResponseEntity.notFound().build();
		}
		// obtenemos el código 200
		return ResponseEntity.ok(moto);
		
	}
	
	@PostMapping
	public ResponseEntity<Moto> guardarMotoController(@RequestBody Moto moto){
		Moto motoNueva = motoService.guardar(moto);
		
		// obtenemos el código 200
		return ResponseEntity.ok(motoNueva);
	}
	
	@GetMapping("/usuario/{usuarioId}")
	public ResponseEntity<List<Moto>> ListMotosPorUsuarioId(@PathVariable("usuarioId") long id){
		
		List<Moto> motos = motoService.byUsuarioId(id);
		if (motos.isEmpty()) {
			// obtenemos el código 204
			return ResponseEntity.noContent().build();
		}
		// obtenemos el código 200
		return ResponseEntity.ok(motos);
		
	}

}
