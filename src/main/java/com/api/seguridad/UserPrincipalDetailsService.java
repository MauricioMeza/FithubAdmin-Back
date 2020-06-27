package com.api.seguridad;

import com.api.modelos.Usuario;
import com.api.modelos.Instructor;
import com.api.modelos.Plan;
import com.api.repositorios.InstructorRepositorio;
import com.api.repositorios.UsuarioRepositorio;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserPrincipalDetailsService implements UserDetailsService {
	
	UserPrincipal userPrincipal;

    @Autowired
    UsuarioRepositorio repositorio;
    @Autowired
    InstructorRepositorio repositorioI;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
    	
        
        if(s.contains("@Fithub.com")) {
        	Instructor instructor = repositorioI.findByCorreo(s);
        	
        	if(instructor == null){
                throw new UsernameNotFoundException("No existe Instructor con el correo");
            }
        	
        	userPrincipal = new UserPrincipal(instructor);
        }
        else {
        	Usuario user = repositorio.findByCorreo(s);
     
        	if(user == null){
                throw new UsernameNotFoundException("No existe usuario con el correo");
            }
        	Plan plan = user.getPlan();
        	Date fecha_actual = new Date();
        	if(plan.getFechaFin().before(fecha_actual) || plan.getClasesDisponibles() <= 0) {
    			plan.setActivo(false);
    		}
        	
        	userPrincipal = new UserPrincipal(user);
        }
        	

        return  userPrincipal;
    }
}
