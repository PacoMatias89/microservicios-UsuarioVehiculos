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

import com.pacomolina.entities.Coche;
import com.pacomolina.service.CocheService;

@RestController
@RequestMapping("/coche")
public class CocheController {

	@Autowired
	private CocheService cocheService;
	
	@GetMapping
	public ResponseEntity<List<Coche>> ListCoche(){
		List<Coche> coches = cocheService.getAllCoche();
		if (coches.isEmpty()) {
			// obtenemos el código 204
			return ResponseEntity.noContent().build();
		}
		// obtenemos el código 200
		return ResponseEntity.ok(coches);
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<Coche> getCoche(@PathVariable("id") long id){
		Coche coche = cocheService.getCocheById(id);
		
		if (coche == null) {
			// obtenemos el código 404
			return ResponseEntity.notFound().build();
		}
		// obtenemos el código 200
		return ResponseEntity.ok(coche);
		
	}
	
	@PostMapping
	public ResponseEntity<Coche> guardarCocheController(@RequestBody Coche coche){
		Coche cocheNuevo = cocheService.guardar(coche);
		
		// obtenemos el código 200
		return ResponseEntity.ok(cocheNuevo);
	}
	
	@GetMapping("/usuario/{usuarioId}")
	public ResponseEntity<List<Coche>> ListCochePorUsuarioId(@PathVariable("usuarioId") long id){
		
		List<Coche> coches = cocheService.byUsuarioId(id);
		if (coches.isEmpty()) {
			// obtenemos el código 204
			return ResponseEntity.noContent().build();
		}
		// obtenemos el código 200
		return ResponseEntity.ok(coches);
		
	}
}
