package com.api.controladores;

import java.util.ArrayList;
import java.util.List;

import com.api.dto.TipoPlanDTO;
import com.api.dto.SesionDTO;
import com.api.dto.TipoSesionDTO;
import com.api.modelos.Sesion;
import com.api.modelos.TipoPlan;
import com.api.modelos.TipoSesion;
import com.api.modelos.Usuario;
import com.api.servicios.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.api.modelos.Instructor;

import javax.validation.Valid;

@RestController
@RequestMapping("/Admin")
public class InstructorControlador {
	
	@Autowired
	private InstructorServicio servicioInstructor;
	@Autowired
	private SesionServicio servicioSesion;
	@Autowired
	private TipoPlanServicio servicioTipoPlan;
	@Autowired
	private TipoSesionServicio servicioTipoSesion;
	@Autowired
	private UsuarioServicio servicioUsuario;

	// ----------- Controladores Instructor ----------------

	@GetMapping("/encontrarTodosLosInstructores")
	public List<Instructor> buscarInstructores(){
		return servicioInstructor.getAllInstructors();
	}

	@ResponseBody
	@GetMapping("/instructoresNombres")
	public List<String> buscarInstructoresNombres(){
		List<Instructor> instructores = servicioInstructor.getAllInstructors();
		ArrayList<String> insNombres = new ArrayList<>();
		for (Instructor ins: instructores) {
			insNombres.add(ins.getNombre());
		}
		return insNombres;
	}

	@PostMapping("/agregarInstructor")
	public ResponseEntity<String> agregarInstructor(@RequestBody Instructor instructor) {
		servicioInstructor.addInstructor(instructor);
		return ResponseEntity.ok().body("Instructor añadido con id: "+ instructor.getCedula());
	}
	
	@DeleteMapping("/eliminarInstructor")
	public ResponseEntity<String> eliminarInstructor(@RequestBody String cedula) {
		Instructor instructor = servicioInstructor.getInstructorById(cedula);
		if (instructor != null) {
			servicioInstructor.deleteInstructor(instructor);
			return ResponseEntity.ok().body("Instructor eliminado");
		} else {
			return ResponseEntity.badRequest().body("No existe ningún instructor con este id");
		}
	}


	// ---------------- Controladores Usuario ----------------------

	@GetMapping("/encontrarTodosLosUsuarios")
	public List<Usuario> buscarUsuarios(){
		return servicioUsuario.getAllUsers();
	}


	// ---------------- Controladores Sesion -----------------------

	@ResponseBody
	@GetMapping("/buscarTodasSesiones")
	public List<SesionDTO> buscarSesiones( ) {
		ArrayList<SesionDTO> sesionFormat = new ArrayList<>();
		List<Sesion> sesiones = servicioSesion.findAllSesionsByDate();
		for (Sesion ses: sesiones) {
			SesionDTO sesionData = new SesionDTO();
			sesionData.setFecha(ses.getFecha_hora());
			sesionData.setTipo(ses.getTipo());
			sesionData.setInstructor(ses.getInstructor().getNombre());
			sesionData.setId(ses.getId());
			sesionData.setCupos(ses.getCupos());
			List<String> nombres = new ArrayList<>();
			for(int i = 0; i < ses.getAsistentes().size(); i++ ) {
				nombres.add(ses.getAsistentes().get(i).getNombre());
			}

			sesionData.setNombresAsistentes(nombres);
			sesionFormat.add(sesionData);
		}
		return sesionFormat;
	}

	@PostMapping("/agregarSesion")
	public ResponseEntity<String> agregarSesion(@RequestBody @Valid SesionDTO sesion) {
		return servicioSesion.addSesion(sesion);
	}

	@DeleteMapping("/eliminarSesion")
	public ResponseEntity<String> eliminarSesion(@RequestBody String id) {
		Sesion sesion = servicioSesion.getSesionById(id);
		if (sesion != null) {
			return servicioSesion.deleteSesion(sesion);
		} else {
			return ResponseEntity.badRequest().body("No existe ninguna sesion con este id");
		}
	}

	@PutMapping("/actualizarSesion")
	public ResponseEntity<String> actualizarSesion(@Valid @RequestBody SesionDTO sesion) {
		servicioSesion.updateSesion(sesion);
		return ResponseEntity.ok().body("Sesion Actualizada");
	}


	// ---------------- Controladores TipoPlan -----------------------

	@PostMapping("/crearTipoPlan")
	public ResponseEntity<String> crearTipoPlan(@RequestBody TipoPlanDTO tipoPlanDTO) {
		servicioTipoPlan.addTipoPlan(tipoPlanDTO);
		return ResponseEntity.ok().body("Tipo de Plan añadido con Nombre: "+ tipoPlanDTO.getNombre());
	}
	@DeleteMapping("/eliminarTipoPlan")
	public ResponseEntity<String> eliminarTipoPlan(@RequestBody String nombre) {
		TipoPlan tipoPlan = servicioTipoPlan.getTipoPlanByName(nombre);
		if (tipoPlan != null) {
			return servicioTipoPlan.deleteTipoPlan(tipoPlan);
		} else {
			return ResponseEntity.badRequest().body("No existe ningún tipo de plan con este nombre");
		}
	}


	// ---------------- Controladores TipoSesion -----------------------

	@PostMapping("/agregarTipoSesion")
	public ResponseEntity<String> guardarTipoSesion(@RequestBody @Valid TipoSesionDTO tipoSesionDTO) {
		servicioTipoSesion.addTipoSesion(tipoSesionDTO);
		return ResponseEntity.ok().body("Tipo de Sesion añadida");
	}

	@DeleteMapping("/eliminarTipoSesion")
	public ResponseEntity<String> eliminarTipoSesion(@RequestBody String id) {
		TipoSesion tipoSesion = servicioTipoSesion.getTipoSesionById(id);
		if (tipoSesion != null) {
			return servicioTipoSesion.deleteTipoSesion(tipoSesion);
		} else {
			return ResponseEntity.badRequest().body("No existe ningún tipo de sesion con este nombre");
		}
	}

	@ResponseBody
	@GetMapping("/buscarTodosTiposSesiones")
	public List<TipoSesionDTO> buscarTipoSesiones( ) {
		List<TipoSesion> tipoSesiones = servicioTipoSesion.findAllTipos();
		ArrayList<TipoSesionDTO> tipoSesionFormat = new ArrayList<>();
		for (TipoSesion tSes: tipoSesiones) {
			TipoSesionDTO tipoSesionData = new TipoSesionDTO();
			tipoSesionData.setDuracion(tSes.getDuracion());
			tipoSesionData.setNombre(tSes.getNombre());
			tipoSesionData.setCupos(tSes.getCupos());
			tipoSesionData.setId(tSes.getId());
			tipoSesionFormat.add(tipoSesionData);
		}
		return tipoSesionFormat;
	}
}
