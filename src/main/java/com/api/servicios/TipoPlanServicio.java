package com.api.servicios;

import com.api.dto.TipoPlanDTO;
import com.api.modelos.TipoPlan;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface TipoPlanServicio {

	TipoPlan getTipoPlanById(String id);
	TipoPlan getTipoPlanByName(String name);
    List<TipoPlan> getAllTypePlans();
    TipoPlan addTipoPlan(TipoPlanDTO tipoPlanDTO);
    ResponseEntity<String> deleteTipoPlan(TipoPlan tipoPlan);
    //void cambiarTipoPlan(TipoPlanDTO tipoPlanDTO);

}
