package com.api.servicios;

import com.api.dto.UsuarioDTO;
import com.api.modelos.Sesion;
import com.api.modelos.Usuario;
import java.util.List;
import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Service;

@Service
public interface UsuarioServicio {

    Usuario getUserByEmail(String email);
    Usuario getUserById(String id);
    Usuario getUserByName(String name);
    List<Usuario> getAllUsers();
    Usuario addUser(Usuario user);
    void addUsuario(UsuarioDTO usuarioDTO);
    public Usuario updateUser(Usuario user);
    boolean signedUser(Sesion sesion, Usuario user);
    Claims infoJWT(String jwt);

}