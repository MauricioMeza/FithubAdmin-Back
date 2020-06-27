package com.api.servicios;

import com.api.dto.TipoSesionDTO;
import com.api.modelos.Sesion;
import com.api.modelos.TipoSesion;
import com.api.repositorios.TipoSesionRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TipoSesionServicioImpl implements TipoSesionServicio{

    @Autowired
    TipoSesionRepositorio repositorio;
    @Autowired
    SesionServicio servicioSesion;


    @Override
    public TipoSesion addTipoSesion(TipoSesionDTO tipoSesionDTO) {
        TipoSesion nuevoTipoSesion = new TipoSesion();

        nuevoTipoSesion.setNombre(tipoSesionDTO.getNombre());
        nuevoTipoSesion.setCupos(tipoSesionDTO.getCupos());
        nuevoTipoSesion.setDuracion(tipoSesionDTO.getDuracion());

        return repositorio.save(nuevoTipoSesion);
    }

    @Override
    public List<TipoSesion> findAllTipos() {
        return repositorio.findAll();
    }

    @Override
    public TipoSesion getTipoSesionByName(String nombre) {
        return repositorio.findByNombre(nombre);
    }

    @Override
    public TipoSesion getTipoSesionById(String id) {
        return repositorio.findById(id);
    }

    @Override
    public ResponseEntity<String> deleteTipoSesion(TipoSesion tipoSesion) {
    	List<Sesion> sesiones = servicioSesion.findAllFutureSesionsByDate();
    	for(Sesion ses: sesiones) {
    		if(ses.getTipo().getId().equals(tipoSesion.getId()))
    			return ResponseEntity.badRequest().body("Hay sesiones ligadas a éste tipo de sesion");
    	}
        repositorio.delete(tipoSesion);
        return ResponseEntity.ok().body("Tipo de sesión eliminado con éxito");
    }

	/*
	 * @Override public void cambiarTipoSesion(TipoSesionDTO tipoSesionDTO) {
	 * TipoSesion nuevoTipoSesion = new TipoSesion();
	 * 
	 * nuevoTipoSesion.setId(tipoSesionDTO.getId());
	 * nuevoTipoSesion.setNombre(tipoSesionDTO.getNombre());
	 * nuevoTipoSesion.setCupos(tipoSesionDTO.getCupos());
	 * nuevoTipoSesion.setDuracion(tipoSesionDTO.getDuracion());
	 * 
	 * repositorio.save(nuevoTipoSesion); }
	 */
}
