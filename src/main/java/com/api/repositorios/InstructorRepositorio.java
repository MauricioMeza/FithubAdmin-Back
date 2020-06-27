package com.api.repositorios;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.api.modelos.Instructor;

@Repository
public interface InstructorRepositorio extends MongoRepository<Instructor,Integer>{

	Instructor findByCorreo(String correo);
	Instructor findByCedula(String cedula);
	Instructor findByNombre(String nombre);
}
