package com.api.repositorios;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.api.modelos.Plan;

import org.springframework.stereotype.Repository;

@Repository
public interface PlanRepositorio extends MongoRepository<Plan, Integer>{

    Plan findById(String idPlan); 
    List<Plan> findAll();
}
