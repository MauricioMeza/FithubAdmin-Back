package com.api.modelos;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;

@Getter
@Setter
@ToString

@Document(collection= "Instructor")
public class Instructor {
	
	@Id
	private String cedula;
	private String nombre;
	private String correo;
	private String contrasena;
	private String role;
	 
	
	
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getCedula() {
		return cedula;
	}
	public void setCedula(String string) {
		this.cedula = string;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getContrasena() {
		return contrasena;
	}
	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}


	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Instructor)) return false;
		Instructor that = (Instructor) o;
		return cedula.equals(that.cedula) &&
				nombre.equals(that.nombre) &&
				correo.equals(that.correo) &&
				contrasena.equals(that.contrasena) &&
				role.equals(that.role);
	}

	@Override
	public int hashCode() {
		return Objects.hash(cedula, nombre, correo, contrasena, role);
	}

	@Override
	public String toString() {
		return "Instructor{" +
				"cedula='" + cedula + '\'' +
				", nombre='" + nombre + '\'' +
				", correo='" + correo + '\'' +
				", contrasena='" + contrasena + '\'' +
				", role='" + role + '\'' +
				'}';
	}
}
