package com.api.repositorios;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.api.modelos.TipoPlan;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoPlanRepositorio extends MongoRepository<TipoPlan, String>{

	TipoPlan findTipoPlanById(String id); 
	TipoPlan findTipoPlanByNombre(String nombre);
}
