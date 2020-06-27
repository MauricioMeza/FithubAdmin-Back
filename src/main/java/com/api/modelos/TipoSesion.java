package com.api.modelos;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Document(collection = "TipoSesion")
public class TipoSesion {

    @Id
    private String id;
    private String nombre;
    private int cupos;
    private int duracion;

    public TipoSesion(){};

    public TipoSesion(String nombre, int cupos, int duracion) {
        super();
        this.nombre = nombre;
        this.cupos = cupos;
        this.duracion = duracion;
    }

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

    public int getDuracion() { return duracion; }
    public void setDuracion(int duracion) { this.duracion = duracion; }


    @Override
    public String toString() {
        return "TipoSesion{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", cupos=" + cupos +
                ", duracion=" + duracion +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TipoSesion)) return false;
        TipoSesion that = (TipoSesion) o;
        return cupos == that.cupos &&
                duracion == that.duracion &&
                id.equals(that.id) &&
                nombre.equals(that.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, cupos, duracion);
    }
}
