package com.api.modelos;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection= "Plan")
public class Plan {

	@Id
	private String id;
	private Date fechaInicio;
	private Date fechaFin;
	private int clasesDisponibles;
	@DBRef(lazy = true)
	private List<Sesion> sesionesReservadas;
	@DBRef(lazy = true)
	private List<Sesion> sesionesAsistidas;
	@DBRef
	private TipoPlan tipoPlan;
	private boolean activo;
	
	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	
	public List<Sesion> getSesionesAsistidas() {
		return sesionesAsistidas;
	}
	public void setSesionesAsistidas(List<Sesion> sesionesAsistidas) {
		this.sesionesAsistidas = sesionesAsistidas;
	}
	public List<Sesion> getSesionesReservadas() {
		return sesionesReservadas;
	}
	public void setSesionesReservadas(List<Sesion> sesionesReservadas) {
		this.sesionesReservadas = sesionesReservadas;
	}
	public Date getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public Date getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}
	public int getClasesDisponibles() {
		return clasesDisponibles;
	}
	public void setClasesDisponibles(int clasesDisponibles) {
		this.clasesDisponibles = clasesDisponibles;
	}
	public TipoPlan getTipoPlan() {
		return tipoPlan;
	}
	public void setTipoPlan(TipoPlan tipoPlan) {
		this.tipoPlan = tipoPlan;
	}
	
	
}