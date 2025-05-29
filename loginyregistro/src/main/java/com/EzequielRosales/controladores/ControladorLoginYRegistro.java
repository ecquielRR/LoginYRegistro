package com.EzequielRosales.controladores;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.EzequielRosales.modelos.Usuario;
import com.EzequielRosales.modelos.UsuarioLogin;
import com.EzequielRosales.servicios.ServicioUsuarios;

import jakarta.validation.Valid;

@Controller
public class ControladorLoginYRegistro {
	@Autowired
	private final ServicioUsuarios servicioUsuarios;
	
	public ControladorLoginYRegistro(ServicioUsuarios servicioUsuarios) {
		this.servicioUsuarios = servicioUsuarios;
	}

	@GetMapping("/")
	public String loginYRegistro(@ModelAttribute("usuario") Usuario usuario, @ModelAttribute("usuarioLogin") Usuario usuarioLogin) {
		return "Index.jsp";
	}
	
	@GetMapping("/inicio")
	public String inicio() {
		return "Inicio.jsp";
	}
	
	@PostMapping("/procesa/registro")
	public String procesarRegistro(@Valid @ModelAttribute("usuario") Usuario nuevoUsuario, BindingResult validacion, @ModelAttribute("usuarioLogin") UsuarioLogin loginUsuario) {
		if (! nuevoUsuario.getContraseña().equals(nuevoUsuario.getConfirmarContraseña())) {
			validacion.rejectValue("confirmarContraseña", "contraseñasDistintas", "Las contraseñas no son iguales.");
		}
		if (validacion.hasErrors()) {
			return "Index.jsp";
		}
		String contraseñaEncriptada = BCrypt.hashpw(nuevoUsuario.getContraseña(), BCrypt.gensalt());
		nuevoUsuario.setContraseña(contraseñaEncriptada);
		this.servicioUsuarios.agregarUsuario(nuevoUsuario);
		
		return "redirect:/inicio";
	}
	
	@PostMapping("/procesa/login")
	public String procesarLogin(@Valid @ModelAttribute("usuarioLogin") UsuarioLogin loginUsuario, BindingResult validacion, @ModelAttribute("usuario") Usuario nuevoUsuario) {
		Usuario usuarioActual = this.servicioUsuarios.obtenerUsuarioPorNombreUsuario(loginUsuario.getNombreUsuario());
		if (usuarioActual == null) {
			validacion.rejectValue("nombreUsuario", "usuarioNoExistente", "Por favor, ingrese un usuario existente.");
		} else {
			if (! BCrypt.checkpw(loginUsuario.getContraseña(), usuarioActual.getContraseña())) {
				validacion.rejectValue("contraseña", "credencialIncorrecta", "Credenciales Incorrectas.");
			}
		}
		if (validacion.hasErrors()) {
			return "Index.jsp";
		}
		
		return "redirect:/inicio";
	}
}

