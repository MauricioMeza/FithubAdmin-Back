package com.api.dto;

import javax.validation.constraints.NotEmpty;


public class TipoPlanDTO {
	
	@NotEmpty(message = "Campo de Nombre vacío")
	private String nombre;
	@NotEmpty(message = "Campo de Cantidad de Dias vacío")
	private int cantDias;
	@NotEmpty(message = "Campo de Cantidad de Sesiones vacío")
	private int cantSesiones;
	@NotEmpty(message = "Campo de Precio vacío")
	private int precio;
	private String id;
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getCantDias() {
		return cantDias;
	}
	public void setCantDias(int cantDias) {
		this.cantDias = cantDias;
	}
	public int getCantSesiones() {
		return cantSesiones;
	}
	public void setCantSesiones(int cantSesiones) {
		this.cantSesiones = cantSesiones;
	}
	public int getPrecio() {
		return precio;
	}
	public void setPrecio(int precio) {
		this.precio = precio;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
	
}
