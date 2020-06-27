package com.api.repositorios;

import com.api.modelos.TipoSesion;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TipoSesionRepositorio extends MongoRepository<TipoSesion, Integer> {

    TipoSesion findById(String id);
    TipoSesion findByNombre(String nombre);
    List<TipoSesion> findAll();
}
