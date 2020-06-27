package com.api.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import com.api.modelos.Plan;

import javax.validation.constraints.Email;

@contrasenaIgualValidacion(message = "Contraseñas no coinciden")
public class UsuarioDTO {
    @NotEmpty
    @Pattern(regexp = "^(?!0)([0-9]{7,12})", message = "Numero de cedula no valido")
    private String cedula;

    @NotEmpty
    private String nombre;

    @NotEmpty
    @Email(message = "Formato de correo no valido")
    private String correo;

    @NotEmpty
    private String contrasena;
    private String contrasenaRep;

    private Plan plan;
    private PlanDTO planDTO;
    private String id;
    private String  role;

    public PlanDTO getPlanDTO() { return planDTO;}
    public void setPlanDTO(PlanDTO planDTO) { this.planDTO = planDTO;}

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public Plan getPlan() {
		return plan;
	}
	public void setPlan(Plan plan) {
		this.plan = plan;
	}

	public String getCedula() { return cedula; }
    public void setCedula(String cedula) { this.cedula = cedula; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getContrasena() { return contrasena; }
    public void setContrasena(String contrasena) { this.contrasena = contrasena; }

    public String getContrasenaRep() { return contrasenaRep; }
    public void setContrasenaRep(String contrasenaRep) { this.contrasenaRep = contrasenaRep; }

    public String getRole() {return role;}
    public void setRole(String role) {this.role = role;}
}
