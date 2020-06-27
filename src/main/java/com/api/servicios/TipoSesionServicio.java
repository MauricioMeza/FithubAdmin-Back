package com.api.servicios;

import com.api.dto.TipoSesionDTO;
import com.api.modelos.TipoSesion;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TipoSesionServicio {

    TipoSesion addTipoSesion(TipoSesionDTO tipoSesionDTO);
    List<TipoSesion> findAllTipos();
    TipoSesion getTipoSesionByName(String name);
    TipoSesion getTipoSesionById(String id);
    ResponseEntity<String> deleteTipoSesion(TipoSesion tipoSesion);
    //void cambiarTipoSesion(TipoSesionDTO tipoSesionDTO);
}
