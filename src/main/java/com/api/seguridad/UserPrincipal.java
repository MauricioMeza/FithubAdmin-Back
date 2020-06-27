package com.api.seguridad;

import com.api.modelos.Instructor;
import com.api.modelos.Usuario;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserPrincipal implements UserDetails {
    private Usuario usuario;
    private Instructor instructor;

    public UserPrincipal(Usuario usuario){
        this.usuario = usuario;
    }

    public UserPrincipal(Instructor instructor){
        this.instructor = instructor;
    }
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorityList = new ArrayList<>();
        if(this.instructor!=null)
        	authorityList.add(new SimpleGrantedAuthority("ROLE_" + this.instructor.getRole()));
        else if(this.usuario!=null)
        	authorityList.add(new SimpleGrantedAuthority("ROLE_" + this.usuario.getRole()));
        return authorityList;
    }

    @Override
    public String getPassword() {
    	String mensaje = "";
    	if(this.instructor!=null)
    		mensaje = this.instructor.getContrasena();
    	else if(this.usuario!=null)
            mensaje = this.usuario.getContrasena();
		return mensaje;
    }

    @Override
    public String getUsername() {
    	String mensaje = "";
    	if(this.instructor!=null)
    		mensaje = this.instructor.getCorreo();
    	if(this.usuario!=null)
    		mensaje = this.usuario.getCorreo();
		return mensaje;
    }


    public String getName() {
    	String mensaje = "";
    	if(this.usuario!=null)
    		mensaje = this.usuario.getNombre();
    	if(this.instructor!=null)
    		mensaje = this.instructor.getNombre();
		return mensaje;
    }

    public String getRol() {
    	String mensaje = "";
    	if(this.usuario!=null)
    		mensaje = this.usuario.getRole();
    	else if(this.instructor != null)
    		mensaje = this.instructor.getRole();
    	return mensaje;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

