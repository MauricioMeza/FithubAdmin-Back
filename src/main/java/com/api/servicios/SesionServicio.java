package com.api.servicios;

import com.api.dto.SesionDTO;
import com.api.modelos.Sesion;
import com.api.modelos.Usuario;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface SesionServicio {

	ResponseEntity<String> addSesion(SesionDTO sesionDTO);
    Sesion updateSesion(SesionDTO sesionDTO);
    List<Sesion> findAllSesionsByDate();
    Sesion getSesionById(String idSesion);
    ResponseEntity<String> deleteSesion(Sesion sesion);
    void updateSesion(Sesion sesion);
    ResponseEntity<String> deleteUserFromSesion(Sesion sesion, Usuario user);
    ResponseEntity<String> addUserToSesion(String idSesion, String idUser);
    public List<Sesion> findAllFutureSesionsByDate();

}
