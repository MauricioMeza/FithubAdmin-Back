package com.api.modelos;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection= "TipoPlan")
public class TipoPlan {

	@Id
	private String id;
	private String nombre;
	private int cantDias;
	private int cantSesiones;
	private int precio;
	
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
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
	
	
	
	
}
