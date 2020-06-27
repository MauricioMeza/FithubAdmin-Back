package com.api.servicios;

import com.api.dto.TipoPlanDTO;
import com.api.modelos.TipoPlan;
import com.api.modelos.Usuario;
import com.api.repositorios.TipoPlanRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class TipoPlanServicioImpl implements TipoPlanServicio {

    @Autowired
    TipoPlanRepositorio repositorio; 
    @Autowired
    UsuarioServicio servicioUsuario;

	@Override
	public TipoPlan getTipoPlanById(String id) {
		return repositorio.findTipoPlanById(id);
	}

	@Override
	public List<TipoPlan> getAllTypePlans() {
		return repositorio.findAll();
	}

	@Override
	public TipoPlan addTipoPlan(TipoPlanDTO tipoPlanDTO) {
		TipoPlan tipoPlan = new TipoPlan();
		tipoPlan.setId(tipoPlanDTO.getId());
		tipoPlan.setCantDias(tipoPlanDTO.getCantDias());
		tipoPlan.setNombre(tipoPlanDTO.getNombre());
		tipoPlan.setPrecio(tipoPlanDTO.getPrecio());
		tipoPlan.setCantSesiones(tipoPlanDTO.getCantSesiones());
		repositorio.save(tipoPlan);
		return tipoPlan;
	}

	@Override
	public TipoPlan getTipoPlanByName(String nombre) {
		return repositorio.findTipoPlanByNombre(nombre);
	}

	@Override
    public ResponseEntity<String> deleteTipoPlan(TipoPlan tipoPlan) {
		List<Usuario> usuarios = servicioUsuario.getAllUsers();
		for(Usuario user: usuarios) {
			if(user.getPlan().getTipoPlan().getId().equals(tipoPlan.getId()))
				return ResponseEntity.badRequest().body("Hay usuarios con éste tipo de plan inscrito");
		}
        repositorio.delete(tipoPlan);
        return ResponseEntity.ok().body("Tipo de plan borrado con éxito");
    }

	/*
	 * @Override public void cambiarTipoPlan(TipoPlanDTO tipoPlanDTO) { TipoPlan
	 * nuevoTipoPlan = new TipoPlan();
	 * 
	 * nuevoTipoPlan.setId(tipoPlanDTO.getId());
	 * nuevoTipoPlan.setNombre(tipoPlanDTO.getNombre());
	 * nuevoTipoPlan.setCantDias(tipoPlanDTO.getCantDias());
	 * nuevoTipoPlan.setPrecio(tipoPlanDTO.getPrecio());
	 * nuevoTipoPlan.setCantSesiones(tipoPlanDTO.getCantSesiones());
	 * repositorio.save(nuevoTipoPlan); }
	 */
}
