package com.roxket.domain;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 *
 * @author Roxket
 */
@Data // Project lombok: clase de dominio, nos genera el pojo directamente (metodos getter and setters)
@Entity //JPA annotation, clase del tipo entidad
@Table(name = "persona") //JPA: definir explicitamente la tabla
public class Persona implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id //llave primaria
	@GeneratedValue(strategy = GenerationType.IDENTITY) // AI en tabla
	private Long idPersona;

	@NotEmpty //Validaciones
	private String nombre;
	@NotEmpty //Validaciones
	private String apellido;
	@NotEmpty //Validaciones
	@Email
	private String email;

	private String telefono;
	@NotNull
	private Double saldo;

}
