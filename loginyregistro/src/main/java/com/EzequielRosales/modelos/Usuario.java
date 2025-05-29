package com.EzequielRosales.modelos;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="usuarios")
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "Por favor, proporciona un nombre")
	@Size(min=3, max=15, message = "Tu nombre debe contener entre 3 y 15 caracteres.")
	private String nombreUsuario;
	
	@NotBlank(message = "Por favor, proporciona una contraseña.")
	@Size(min = 8, message = "El password necesita tener al menos 8 catacteres.")
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}$", 
				message = "El password necesita incluir al menos una letra mayúscula, una letra minúscula y un número")
	private String contraseña;
	
	@Transient
	private String confirmarContraseña;
	
	@NotBlank(message = "Por favor, proporciona un correo.")
	@Email(message = "Por favor, proporciona un correo valido.")
	private String correo;
	
	@NotBlank(message = "Por favor, proporciona un nombre")
	@Size(min=3, message = "Por favor, proporciona un nombre mas largo.")
	@Pattern(regexp = "^[A-Za-zÁÉÍÓÚáéíóúÑñ ]+$", message = "El nombre no debe contener números, ni caracteres especiales.")
	private String nombre;
	
	@NotBlank(message = "Por favor, proporciona un apellido.")
	@Size(min=3, message = "Por favor, proporciona un apellido mas largo.")
	@Pattern(regexp = "^[A-Za-zÁÉÍÓÚáéíóúÑñ ]+$", message = "El apellido no debe contener números, ni caracteres especiales.")
	private String apellido;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Past(message = "Tu fecha de nacimiento debe ser del pasado.")
	@Temporal(TemporalType.DATE)
	private Date fechaNacimiento;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecha_creacion")
	private Date fechaCreacion;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecha_actualizacion")
	private Date fechaActualizacion;

	public Usuario() {
	}
	
	 public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
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



	public String getConfirmarContraseña() {
		return confirmarContraseña;
	}



	public void setConfirmarContraseña(String confirmarContraseña) {
		this.confirmarContraseña = confirmarContraseña;
	}



	public String getCorreo() {
		return correo;
	}



	public void setCorreo(String correo) {
		this.correo = correo;
	}



	public String getNombre() {
		return nombre;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public String getApellido() {
		return apellido;
	}



	public void setApellido(String apellido) {
		this.apellido = apellido;
	}



	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}



	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}



	public Date getFechaCreacion() {
		return fechaCreacion;
	}



	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}



	public Date getFechaActualizacion() {
		return fechaActualizacion;
	}



	public void setFechaActualizacion(Date fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}

	 @PrePersist
	   protected void onCreate() {
	       this.fechaCreacion = new Date();
	       this.fechaActualizacion = this.fechaCreacion;
	   }
	 @PreUpdate
	 	protected void onUpdate() {
		 	this.fechaActualizacion = new Date();
	   }
}
