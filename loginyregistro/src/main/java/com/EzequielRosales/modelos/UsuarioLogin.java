package com.EzequielRosales.modelos;

public class UsuarioLogin {
	
	private String nombreUsuario;
	private String contraseña;
	
	
	public UsuarioLogin() {
	}
	
	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getContraseña() {
		return contraseña;
	}
	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}
	
	
}