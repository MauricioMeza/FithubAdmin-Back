package com.api.servicios;

import com.api.modelos.Instructor;
import com.api.repositorios.InstructorRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InstructorServicioImpl implements InstructorServicio {

    @Autowired
    InstructorRepositorio repositorio;
    @Autowired
    PasswordEncoder passwordEncoder;

	/*@Override
	public Instructor getInstructorByCorreo(String correo) {
		return repositorio.findByCorreo(correo);
	}*/
	@Override
	public Instructor getInstructorById(String cedula) { return repositorio.findByCedula(cedula); }

	@Override
	public Instructor getInstructorByName(String nombre) { return repositorio.findByNombre(nombre); }

	@Override
	public List<Instructor> getAllInstructors() {
		return repositorio.findAll();
	}

	@Override
	public Instructor addInstructor(Instructor instructor) {
		Instructor inst = instructor;
		inst.setContrasena(passwordEncoder.encode(inst.getContrasena()));
		inst.setRole("ADMIN");
		return repositorio.save(inst);
	}
	@Override
	public void deleteInstructor(Instructor instructor) {
		repositorio.delete(instructor);
		
	}

}
