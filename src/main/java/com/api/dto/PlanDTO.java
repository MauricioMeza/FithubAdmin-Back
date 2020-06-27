package com.api.dto;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotEmpty;

import com.api.modelos.Sesion;
import com.api.modelos.TipoPlan;

public class PlanDTO {
	
	@NotEmpty(message = "Campo de Fecha de inicio vacío")
	private Date fechaInicio;
	@NotEmpty(message = "Campo de Fecha de fin vacío")
	private Date fechaFin;
	@NotEmpty(message = "Campo de clases disponibles vacío")
	private int clasesDisponibles;
	private String id;
	@NotEmpty(message = "Campo de tipo de plan vacío")
	private String tipoPlan;

	private TipoPlan tipo;

	public TipoPlan getTipo() { return tipo; }
	public void setTipo(TipoPlan tipo) { this.tipo = tipo; }

	private List<Sesion> sesionesReservadas;
	private List<Sesion> sesionesAsistidas;
	private  boolean activo;

	public boolean isActivo() { return activo; }
	public void setActivo(boolean activo) { this.activo = activo; }

	private int sesionReservada;
	private int sesionAsistida;
	
	
	public List<Sesion> getSesionesReservadas() {
		return sesionesReservadas;
	}
	public void setSesionesReservadas(List<Sesion> sesionesReservadas) {
		this.sesionesReservadas = sesionesReservadas;
	}

	public List<Sesion> getSesionesAsistidas() {
		return sesionesAsistidas;
	}
	public void setSesionesAsistidas(List<Sesion> sesionesAsistidas) {
		this.sesionesAsistidas = sesionesAsistidas;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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

	public String getTipoPlan() {
		return tipoPlan;
	}
	public void setTipoPlan(String tipoPlan) {
		this.tipoPlan = tipoPlan;
	}

	public int getSesionReservada() { return sesionReservada; }
	public void setSesionReservada(int sesionReservada) { this.sesionReservada = sesionReservada; }

	public int getSesionAsistida() { return sesionAsistida; }

	public void setSesionAsistida(int sesionAsistida) { this.sesionAsistida = sesionAsistida; }
}
