package com.pacomolina.entities;

import javax.persistence.*;

@Entity
public class Coche {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String marca;
	private String modelo;
	private long usuarioId;
	
	public Coche() {
			
	}
	
	public long getUsuarioId() {
		return usuarioId;
	}
	public void setUsuarioId(long usuarioId) {
		this.usuarioId = usuarioId;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	
	
	
	
}
