package com.api.servicios;

import com.api.modelos.Instructor;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface InstructorServicio {

	//Instructor getInstructorByCorreo(String correo);
	Instructor getInstructorById(String id);
    Instructor getInstructorByName(String name);
    List<Instructor> getAllInstructors();
    Instructor addInstructor(Instructor instructor);
    void deleteInstructor(Instructor instructor);

}
