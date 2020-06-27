package com.api.servicios;

import com.api.dto.SesionDTO;
import com.api.modelos.Instructor;
import com.api.modelos.Plan;
import com.api.modelos.Sesion;
import com.api.modelos.TipoSesion;
import com.api.modelos.Usuario;
import com.api.repositorios.InstructorRepositorio;
import com.api.repositorios.PlanRepositorio;
import com.api.repositorios.SesionRepositorio;

import com.api.repositorios.TipoSesionRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class SesionServicioImpl implements SesionServicio {

    @Autowired
    SesionRepositorio repositorio;
    @Autowired
    InstructorServicio servicioIns;
	@Autowired
	TipoSesionServicio servicioTses;
	@Autowired
	PlanServicio servicioPlan;
	@Autowired
	UsuarioServicio servicioUsuario;
	@Autowired
	SesionServicio servicioSesion;
	

    @Override
    public ResponseEntity<String> addSesion(SesionDTO sesionDTO) {
        Sesion nuevaSesion = new Sesion();
		Instructor instructor = servicioIns.getInstructorByName(sesionDTO.getInstructor());
		TipoSesion tipo = servicioTses.getTipoSesionByName(sesionDTO.getTipoSesion());
		sesionDTO.setTipo(tipo);

        long fechaSesionDTOTerminaMillis = sesionDTO.getFecha().getTime() + sesionDTO.getTipo().getDuracion() * 60 * 1000;
        Date fechaInicioSesionDTO = sesionDTO.getFecha();
        Date fechaFinSesionDTO = new Date(fechaSesionDTOTerminaMillis);

		List<Sesion> sesiones = repositorio.findAll();

		for(Sesion sesion : sesiones){
			long fechaSesionComienzaMillis = sesion.getFecha_hora().getTime();
			long fechaSesionTerminaMillis = sesion.getFecha_hora().getTime() + sesion.getTipo().getDuracion() * 60 * 1000;
			Date fechaInicioSesion = new Date(fechaSesionComienzaMillis);
			Date fechaFinSesion = new Date(fechaSesionTerminaMillis);
			if(sesion.getInstructor().equals(instructor)){
				if(sesion.getFecha_hora().equals(sesionDTO.getFecha())){
					return ResponseEntity.badRequest().body("El instructor ya tiene una sesión a esa hora");
				}else if(fechaFinSesionDTO.after(fechaInicioSesion) && fechaFinSesionDTO.before(fechaFinSesion)) {
					return ResponseEntity.badRequest().body("El instructor ya tiene una sesión a esa hora");
				}else if(fechaInicioSesionDTO.after(fechaInicioSesion) && fechaInicioSesionDTO.before(fechaFinSesion)) {
					return ResponseEntity.badRequest().body("El instructor ya tiene una sesión a esa hora");
				}else if(fechaInicioSesionDTO.after(fechaInicioSesion) && fechaFinSesionDTO.before(fechaFinSesion)) {
					return ResponseEntity.badRequest().body("El instructor ya tiene una sesión a esa hora");
				}else if(fechaInicioSesion.after(fechaInicioSesionDTO) && fechaFinSesion.before(fechaFinSesionDTO)) {
					return ResponseEntity.badRequest().body("El instructor ya tiene una sesión a esa hora");
				}
			}
		}
        nuevaSesion.setInstructor(instructor);
        nuevaSesion.setTipo(tipo);
        nuevaSesion.setFecha_hora(sesionDTO.getFecha());
		nuevaSesion.setCupos(tipo.getCupos());

        repositorio.save(nuevaSesion); 
        return ResponseEntity.ok().body("Sesion agregada");
    }

	@Override
	public Sesion updateSesion(SesionDTO sesionDTO) {
    	Sesion viejaSesion = repositorio.findById(sesionDTO.getId());
		Sesion nuevaSesion = new Sesion();

		Instructor instructor = servicioIns.getInstructorByName(sesionDTO.getInstructor());
		TipoSesion tipo = servicioTses.getTipoSesionByName(sesionDTO.getTipoSesion());
		TipoSesion tipoAnt = viejaSesion.getTipo();
		nuevaSesion.setAsistentes(viejaSesion.getAsistentes());
		nuevaSesion.setCupos(tipo.getCupos() - (tipoAnt.getCupos() - sesionDTO.getCupos()));
		nuevaSesion.setInstructor(instructor);
		nuevaSesion.setTipo(tipo);
		nuevaSesion.setFecha_hora(sesionDTO.getFecha());
		nuevaSesion.setId(sesionDTO.getId());

		return repositorio.save(nuevaSesion);
	}

    @Override
    public List<Sesion> findAllSesionsByDate() {
        return repositorio.findAll(Sort.by(Sort.Direction.ASC, "fecha_hora") );
    }
    
    @Override
    public List<Sesion> findAllFutureSesionsByDate() {
        List<Sesion> sesiones = repositorio.findAll(Sort.by(Sort.Direction.ASC, "fecha_hora") );
        List<Sesion> sesionesFuturas = new ArrayList<>();
        Date fechaActual = new Date();
        for(Sesion ses: sesiones) {
			long fechaSesionTerminaMillis = ses.getFecha_hora().getTime() + ses.getTipo().getDuracion() * 60 * 1000;
        	Date fechaFinSesion = new Date(fechaSesionTerminaMillis);
        	if(fechaFinSesion.after(fechaActual))
        		sesionesFuturas.add(ses);
        }
        return sesionesFuturas;
    }

    @Override
    public Sesion getSesionById(String idSesion) {
        return repositorio.findById(idSesion);
    }

    @Override
    public ResponseEntity<String> deleteSesion(Sesion sesion){
    	List<Usuario> usuarios = servicioUsuario.getAllUsers();
    	for(Usuario user: usuarios) {
    		List<Sesion> sesionesReservadas = user.getPlan().getSesionesReservadas();
    		for(Sesion ses: sesionesReservadas) {
    			if(ses.getId().equals(sesion.getId()))
    				return ResponseEntity.badRequest().body("Hay usuarios inscritos en ésta clase");
    		}
    	}
        repositorio.delete(sesion);
        return ResponseEntity.ok().body("Sesion eliminada con éxito");
    }

    
    @Override
	public void updateSesion(Sesion sesion) 
	{
		repositorio.save(sesion);
	}

	@Override
	public ResponseEntity<String> deleteUserFromSesion(Sesion sesion, Usuario usuario) {

		Date fecha_actual = new Date();
		long fechaActualMili = fecha_actual.getTime();
		long fechaLimiteCancelarMili = sesion.getFecha_hora().getTime() - 3600 * 2000;
		if(sesion.getFecha_hora().before(fecha_actual)) {
			return ResponseEntity.badRequest().body("No puede cancelar, la sesión ya comenzó");
		}else if(fechaActualMili > fechaLimiteCancelarMili){
			return ResponseEntity.badRequest().body("No puede cancelar, la sesión esta a punto de comenzar");
		}
		
		  boolean inscrito = servicioUsuario.signedUser(sesion, usuario); 
		  if (!inscrito)
			  return ResponseEntity.badRequest().body("El usuario no está inscrito en la sesion");
		 
		else {
			List<Usuario> inscritos = sesion.quitarAsistente(usuario);
			List<Sesion> sesionesReservadas = usuario.getPlan().getSesionesReservadas();
			Plan plan = usuario.getPlan();
			for(int i = 0; i < sesionesReservadas.size(); i++) {
				if(sesionesReservadas.get(i).getId().contentEquals(sesion.getId())) {
					sesionesReservadas.remove(i);
					plan.setSesionesReservadas(sesionesReservadas);
					plan.setClasesDisponibles(plan.getClasesDisponibles()+1);
					usuario.setPlan(plan);
				}
			}			
			sesion.setAsistentes(inscritos);
			int cupos = sesion.getCupos();
			sesion.setCupos(cupos+ 1);
			this.updateSesion(sesion);
			servicioPlan.addPlan(plan);
			return ResponseEntity.ok().body("El usuario ha cancelado su cupo en la sesion");
		}
	}

	@Override
	public ResponseEntity<String> addUserToSesion(String idSesion, String idUsuario) {
		Usuario usuario = servicioUsuario.getUserByEmail(idUsuario);
		Sesion sesion = servicioSesion.getSesionById(idSesion);
		Date fecha_actual = new Date();
		
		if(sesion.getFecha_hora().before(fecha_actual))
			return ResponseEntity.badRequest().body("La sesión ya pasó");
		
		if(servicioUsuario.signedUser(sesion, usuario)) {
			return ResponseEntity.badRequest().body("El usuario " + usuario.getNombre() + " ya está inscrito en la Sesion");
		}
		else{
			if(sesion.getCupos() <= 0) {
				return ResponseEntity.badRequest().body("La sesión no tiene cupos suficientes para realizar la inscripción");
			}
			else{	
				if(usuario.getPlan() == null) {
					return ResponseEntity.badRequest().body("El usuario no tiene ningún plan inscrito");
				}
				int cupos = sesion.getCupos() - 1;
				sesion.setCupos(cupos);
				List<Usuario> asistentes = sesion.getAsistentes();   
				asistentes.add(usuario);
				sesion.setAsistentes(asistentes);

				Plan plan = usuario.getPlan();
				if(plan.getClasesDisponibles()<=0)
					return ResponseEntity.badRequest().body("El plan del Usuario no cuenta con clases disponibles");
				List<Sesion> sesionesReservadas = usuario.getPlan().getSesionesReservadas();
				sesionesReservadas.add(sesion);
				plan.setSesionesReservadas(sesionesReservadas);
				plan.setClasesDisponibles(plan.getClasesDisponibles()-1);
				usuario.setPlan(plan);
				servicioPlan.addPlan(plan);
				servicioUsuario.updateUser(usuario);
				servicioSesion.updateSesion(sesion);
			}
		}
		return ResponseEntity.ok().body("El usuario ha reservado un cupo con éxito");
	}

}
