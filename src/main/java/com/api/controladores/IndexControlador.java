package com.api.controladores;

import com.api.dto.SesionDTO;
import com.api.dto.UsuarioDTO;
import com.api.modelos.Sesion;
import com.api.modelos.TipoPlan;
import com.api.servicios.SesionServicio;
import com.api.servicios.TipoPlanServicio;
import com.api.servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

@CrossOrigin(origins = { "http://localhost:3000"}, methods = {RequestMethod.GET, RequestMethod.POST})
@RestController
public class IndexControlador {

    @Autowired
    UsuarioServicio usuarioServicio;
    @Autowired
    SesionServicio servicioSes;
    @Autowired
    TipoPlanServicio tipoPlanServicio;


    // ------------------- Registro de Usuario --------------------------

    @PostMapping("/registro")
    public ResponseEntity<String> registroUsuario(@RequestBody @Valid UsuarioDTO accountDto, BindingResult result, WebRequest request, Errors errors) {
        if (!result.hasErrors()) {
            if ((usuarioServicio.getUserByEmail(accountDto.getCorreo()) == null) && (usuarioServicio.getUserById(accountDto.getCedula()) == null)){
                usuarioServicio.addUsuario(accountDto);
                return ResponseEntity.accepted().body("Usuario creado");
            } else if(!(usuarioServicio.getUserById(accountDto.getCedula())==null)) {
            	return ResponseEntity.badRequest().body("Ya existe esa cédula en BD");
        	} else {
        		return ResponseEntity.badRequest().body("Ya existe ese correo en BD");
        	}
        } else {
        	return ResponseEntity.badRequest().body(result.getAllErrors().get(0).getDefaultMessage());
        }
    }

    // ------------------- Lista de Sesiones para el Scheduler --------------------------

    
    @GetMapping("/listaSesiones")
    public List<SesionDTO> BuscarSesiones( ) {
		List<Sesion> sesiones = servicioSes.findAllSesionsByDate();
		ArrayList<SesionDTO> sesionFormat = new ArrayList<>();
		for (Sesion ses: sesiones) {
			SesionDTO sesionData = new SesionDTO();
			sesionData.setFecha(ses.getFecha_hora());
			sesionData.setTipo(ses.getTipo());
			sesionData.setInstructor(ses.getInstructor().getNombre());
			sesionData.setId(ses.getId());
            sesionData.setCupos(ses.getCupos());
			sesionFormat.add(sesionData);
		}
		return sesionFormat;
    }

    // ------------------- Lista de Planes para el Pricing --------------------------
    @ResponseBody
    @GetMapping("/listaTipoPlanes")
    public List<TipoPlan> BuscarTipoPlanes( ) {
        List<TipoPlan> planes = tipoPlanServicio.getAllTypePlans();
        planes.removeIf(tipoPlan -> ((tipoPlan.getNombre().contains("Prueba"))));
        return planes;
    }

}