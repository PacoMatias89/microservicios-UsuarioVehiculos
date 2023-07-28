package com.example.demo.feignClients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.models.Coche;
import com.example.demo.models.Moto;

@FeignClient(name="moto-servicio", url="http://localhost:8083")
@RequestMapping("/moto")
public interface MotoFeignClient {

	@PostMapping()
	public Moto guardarMotoFeignClient(@RequestBody Moto moto);
	
	@GetMapping("/usuario/{usuarioId}")
	public List<Moto> getMotos(@PathVariable("usuarioId") long usuarioId);
}
