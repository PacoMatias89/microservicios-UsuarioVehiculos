package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Usuario;
import com.example.demo.models.Coche;
import com.example.demo.models.Moto;
import com.example.demo.service.UsuarioService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	@GetMapping
	public ResponseEntity<List<Usuario>> ListUsuario() {
		List<Usuario> usuarios = usuarioService.getAllUsario();
		if (usuarios.isEmpty()) {
			// obtenemos el código 204
			return ResponseEntity.noContent().build();
		}
		// obtenemos el código 200
		return ResponseEntity.ok(usuarios);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Usuario> getUsuario(@PathVariable("id") long id) {
		Usuario usuario = usuarioService.getUsuarioById(id);

		if (usuario == null) {
			// obtenemos el código 404
			return ResponseEntity.notFound().build();
		}
		// obtenemos el código 200
		return ResponseEntity.ok(usuario);

	}

	@PostMapping
	public ResponseEntity<Usuario> guardarUsuarioController(@RequestBody Usuario usuario) {
		Usuario usuarioNuevo = usuarioService.guardar(usuario);

		// obtenemos el código 200
		return ResponseEntity.ok(usuarioNuevo);
	}

	@CircuitBreaker(name = "cochesCB", fallbackMethod = "fallBacKGetCoches")
	@GetMapping("/coches/{usuarioId}")
	public ResponseEntity<List<Coche>> getCochesPorIdUsuario(@PathVariable("usuarioId") long id) {
		Usuario usuario = usuarioService.getUsuarioById(id);
		if (usuario == null) {

			// Obtenemos el valor 404
			return ResponseEntity.notFound().build();

		}

		List<Coche> coches = usuarioService.getCoches(id);
		// obtenemos el código 200
		return ResponseEntity.ok(coches);
	}

	@CircuitBreaker(name = "motosCB", fallbackMethod = "fallBacKGetMotos")
	@GetMapping("/motos/{usuarioId}")
	public ResponseEntity<List<Moto>> getMotosPorIdUsuario(@PathVariable("usuarioId") long id) {
		Usuario usuario = usuarioService.getUsuarioById(id);
		if (usuario == null) {

			// Obtenemos el valor 404
			return ResponseEntity.notFound().build();

		}

		List<Moto> motos = usuarioService.getMotos(id);
		// obtenemos el código 200
		return ResponseEntity.ok(motos);
	}

	@CircuitBreaker(name = "cochesCB", fallbackMethod = "fallBacKSaveCoches")
	@PostMapping("/coche/{usuarioId}")
	public ResponseEntity<Coche> guardarCoche(@PathVariable("usuarioId") long usuarioId, @RequestBody Coche coche) {
		Coche nuevoCoche = usuarioService.guardarCoche(usuarioId, coche);
		// obtenemos el código 200
		return ResponseEntity.ok(nuevoCoche);
	}

	@CircuitBreaker(name = "motosCB", fallbackMethod = "fallBacKSaveMotos")
	@PostMapping("/moto/{usuarioId}")
	public ResponseEntity<Moto> guardarMoto(@PathVariable("usuarioId") long usuarioId, @RequestBody Moto moto) {
		Moto nuevoMoto = usuarioService.guardarMoto(usuarioId, moto);
		// obtenemos el código 200
		return ResponseEntity.ok(nuevoMoto);
	}

	// Obtenemos todos
	@CircuitBreaker(name = "todosCB", fallbackMethod = "fallBacKGetTodos")
	@GetMapping("/todos/{usuarioId}")
	public ResponseEntity<Map<String, Object>> ListarTodoVehiculo(@PathVariable("usuarioId") long usuarioId) {
		Map<String, Object> resultado = usuarioService.getUsuarioVehiculos(usuarioId);

		// obtenemos el código 200
		return ResponseEntity.ok(resultado);
	}

	private ResponseEntity<List<Coche>> fallBacKGetCoches(@PathVariable("usuarioId") Long id,
			RuntimeException exception) {
		return new ResponseEntity("El usuario:" + id + " tiene los coches en el taller", HttpStatus.OK);
	}

	private ResponseEntity<List<Coche>> fallBacKSaveCoches(@PathVariable("usuarioId") Long id, @RequestBody Coche coche,
			RuntimeException exception) {
		return new ResponseEntity("El usuario:" + id + " no puede comprar coches", HttpStatus.OK);
	}

	private ResponseEntity<List<Moto>> fallBacKGetMotos(@PathVariable("usuarioId") Long id,
			RuntimeException exception) {
		return new ResponseEntity("El usuario:" + id + " tiene los motos en el taller", HttpStatus.OK);
	}

	private ResponseEntity<List<Moto>> fallBacKSaveMotos(@PathVariable("usuarioId") Long id, @RequestBody Moto moto,
			RuntimeException exception) {
		return new ResponseEntity("El usuario:" + id + " no puede comprar motos", HttpStatus.OK);
	}

	private ResponseEntity<List<Coche>> fallBacKGetTodos(@PathVariable("usuarioId") long id,
			RuntimeException exception) {
		return new ResponseEntity("El usuario:" + id + " tiene los vehículos en el taller", HttpStatus.OK);
	}

}
