package com.EzequielRosales.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.EzequielRosales.modelos.Usuario;
import com.EzequielRosales.repositorios.RepositoriosUsuarios;

@Service
public class ServicioUsuarios {
	@Autowired
	private RepositoriosUsuarios repositorioUsuarios;

	public ServicioUsuarios(RepositoriosUsuarios repositorioUsuarios) {
		this.repositorioUsuarios = repositorioUsuarios;
	}
	
	public Usuario agregarUsuario(Usuario nuevoUsuario) {
		return this.repositorioUsuarios.save(nuevoUsuario);
	}
	
	public Usuario obtenerUsuarioPorNombreUsuario(String nombreUsuario) {
		return this.repositorioUsuarios.findByNombreUsuario(nombreUsuario);
	}
	
}
