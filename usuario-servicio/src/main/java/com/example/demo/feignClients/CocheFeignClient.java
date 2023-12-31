package com.example.demo.feignClients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.models.Coche;

@FeignClient(name="coche-servicio", url="http://localhost:8082")
@RequestMapping("/coche")
public interface CocheFeignClient {
	@PostMapping()
	public Coche guardar(@RequestBody Coche coche);
	
	@GetMapping("/usuario/{usuarioId}")
	public List<Coche> getCoches(@PathVariable("usuarioId") long usuarioId);
}
