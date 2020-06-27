package com.api.repositorios;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.api.modelos.Sesion;

import java.util.List;

@Repository
public interface SesionRepositorio extends MongoRepository<Sesion,Integer>{
	
	Sesion findById(String id);
	List<Sesion> findAll();
}
