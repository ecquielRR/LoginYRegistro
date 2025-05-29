package com.EzequielRosales.repositorios;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.EzequielRosales.modelos.Usuario;

@Repository
public interface RepositoriosUsuarios extends CrudRepository<Usuario, Long>{
	Usuario findByNombreUsuario(String nombreUsuario);
}