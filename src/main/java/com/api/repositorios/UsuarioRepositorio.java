package com.api.repositorios;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.api.modelos.Usuario;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepositorio extends MongoRepository<Usuario, String>{

    Usuario findByCorreo(String correo);
    Usuario findByCedula(String cedula);
    Usuario findByNombre(String nombre);
}
