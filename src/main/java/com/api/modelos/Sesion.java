package com.api.modelos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection= "Sesion")
public class Sesion {

	@Id
	private String id;
	private Date fecha_hora;
	@DBRef
	private TipoSesion tipo;
	@DBRef
	private Instructor instructor;
	@DBRef
	private List<Usuario> asistentes;
	private int cupos;

	public Sesion(){
		this.asistentes = new ArrayList<Usuario>();
	};


	public Sesion(String id, Date fecha_hora, TipoSesion tipo, Instructor instructor, int cupos, List<Usuario> asistentes) {
		super();
		this.id = id;
		this.fecha_hora = fecha_hora;
		this.tipo = tipo;
		this.instructor = instructor;
		this.cupos = cupos;
		this.asistentes = asistentes;
	}


	public List<Usuario> quitarAsistente(Usuario usuario){
		List<Usuario> asistentes = this.asistentes;
		List<Usuario> asistentes_actualizado = new ArrayList<>();
		for(int i = 0; i < asistentes.size(); i++) {
			if(asistentes.get(i).getCedula() == usuario.getCedula())
				asistentes_actualizado.add(asistentes.get(i));
		}
		return asistentes_actualizado;
	}

	public Date getFecha_hora() {
		return fecha_hora;
	}
	public void setFecha_hora(Date fecha_hora) {
		this.fecha_hora = fecha_hora;
	}

	public Instructor getInstructor() {
		return instructor;
	}
	public void setInstructor(Instructor instructor) {
		this.instructor = instructor;
	}

	public List<Usuario> getAsistentes() {
		return asistentes;
	}
	public void setAsistentes(List<Usuario> asistentes) {
		this.asistentes = asistentes;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public TipoSesion getTipo() {
		return tipo;
	}

	public void setTipo(TipoSesion tipo) {
		this.tipo = tipo;
	}

	public int getCupos() {
		return cupos;
	}

	public void setCupos(int cupos) {
		this.cupos = cupos;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Sesion)) return false;
		Sesion sesion = (Sesion) o;
		return cupos == sesion.cupos &&
				id.equals(sesion.id) &&
				fecha_hora.equals(sesion.fecha_hora) &&
				tipo.equals(sesion.tipo) &&
				instructor.equals(sesion.instructor) &&
				Objects.equals(asistentes, sesion.asistentes);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, fecha_hora, tipo, instructor, asistentes, cupos);
	}

	@Override
	public String toString() {
		return "Sesion{" +
				"id='" + id + '\'' +
				", fecha_hora=" + fecha_hora +
				", tipo=" + tipo +
				", instructor=" + instructor +
				", asistentes=" + asistentes +
				", cupos=" + cupos +
				'}';
	}
}
