package com.api.servicios;

import com.api.dto.UsuarioDTO;
import com.api.modelos.Sesion;
import com.api.modelos.TipoPlan;
import com.api.modelos.Usuario;
import com.api.repositorios.UsuarioRepositorio;
import com.api.seguridad.JwtProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UsuarioServicioImpl implements UsuarioServicio{

    @Autowired
    UsuarioRepositorio repositorio;
    @Autowired
    TipoPlanServicio servicioTipoPlan;
    @Autowired
    PlanServicio servicioPlan;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public Usuario getUserByEmail(String correo){
        return repositorio.findByCorreo(correo);
    }

    @Override
    public Usuario getUserById(String cedula){
        return repositorio.findByCedula(cedula);
    }

    @Override
    public Usuario getUserByName(String nombre){
        return  repositorio.findByNombre(nombre);
    }

    @Override
    public List<Usuario> getAllUsers(){
        return repositorio.findAll();
    }

    @Override
    public Usuario addUser(Usuario user) {
        Usuario usuario = user;
        usuario.setContrasena(passwordEncoder.encode(usuario.getContrasena()));
        return repositorio.save(usuario);
    }
    
    @Override 
    public Usuario updateUser(Usuario user) {
        Usuario usuario = user;
        return repositorio.save(usuario);
    }

    @Override
    public void addUsuario(UsuarioDTO usuarioDTO){
        Usuario user = new Usuario();

        user.setNombre(usuarioDTO.getNombre());
        user.setContrasena(usuarioDTO.getContrasena());
        user.setCorreo(usuarioDTO.getCorreo());
        user.setCedula(usuarioDTO.getCedula());
        user.setRole("USER");
        addUser(user);

        TipoPlan planPrueba = servicioTipoPlan.getTipoPlanByName("Plan de Prueba");
        servicioPlan.addNewPlan(planPrueba, user);
        this.updateUser(user);
    };

    @Override
    public Claims infoJWT(String jwt){
        Claims info = Jwts.parser().setSigningKey(JwtProperties.SECRET)
                .parseClaimsJws(jwt)
                .getBody();

        return info;
    }

    @Override
	public boolean signedUser(Sesion sesion, Usuario usuario) {
		
		boolean inscrito = false;
		List<Usuario> usuariosInscritos = sesion.getAsistentes();
		for(int i = 0; i < usuariosInscritos.size(); i++)
		{
			String cedulaUsuarioLista = usuariosInscritos.get(i).getCedula();
			String cedulaUsuario = usuario.getCedula();
			if(usuariosInscritos.size()== 0) break;
			else if(cedulaUsuarioLista.equals(cedulaUsuario))
				inscrito = true; 
		}
		return inscrito;  
	}

}