package com.api.dto;

import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class TipoSesionDTO {
    @Id
    private String id;

    @NotEmpty(message = "Nombre de tipo vacio")
    private String nombre;

    @NotNull(message = "Número de cupos vacio")
    private int cupos;

    @NotNull(message = "Duracion vacia")
    private int duracion;


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

    public int getCupos() {
        return cupos;
    }
    public void setCupos(int cupos) {
        this.cupos = cupos;
    }

    public int getDuracion() {
    	return duracion; 
    }
    
    public void setDuracion(int duracion) { 
    	this.duracion = duracion; 
    }
}
