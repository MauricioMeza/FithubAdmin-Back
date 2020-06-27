package com.api.dto;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;
import com.api.modelos.TipoSesion;


public class SesionDTO {
	@Future(message = "La fecha debe ser en el futuro")
    private Date fecha;

	@NotEmpty(message = "Tipo de clase vacio")
	private String tipoSesion;

    @NotEmpty(message = "Instructor vacio")
    private String instructor;

    private String id;
    private List<String> nombresAsistentes;
    private int cupos;
	private TipoSesion tipo;
    
    
	public List<String> getNombresAsistentes() {
		return nombresAsistentes;
	}
	public void setNombresAsistentes(List<String> nombresAsistentes) {
		this.nombresAsistentes = nombresAsistentes;
	}
	public Date getFecha() { return fecha; }
	public void setFecha(Date fecha_hora) { this.fecha = fecha_hora; }

	public String getInstructor() { return instructor; }
	public void setInstructor(String instructor) { this.instructor = instructor; }

	public String getId() { return id; }
	public void setId(String id) { this.id = id; }

	public String getTipoSesion() {
		return tipoSesion;
	}
	public void setTipoSesion(String tipoSesion) {
		this.tipoSesion = tipoSesion;
	}

	public TipoSesion getTipo() { return tipo; }
	public void setTipo(TipoSesion tipo) { this.tipo = tipo; }

	public int getCupos() { return cupos; }

	public void setCupos(int cupos) { this.cupos = cupos; }
}

