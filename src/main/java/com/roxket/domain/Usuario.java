package com.roxket.domain;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import lombok.Data;

/**
 *
 * @author Roxket
 */

@Entity //JPA standard
@Data //Setters y getter de lombok
@Table(name="usuario") //mapeo de la tabla
public class Usuario implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id //primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) //standard JPA
	private Long idUsuario;
	
	@NotEmpty //standard JPA
	private String username;
	@NotEmpty //standard JPA
	private String password;
	
	@OneToMany
	@JoinColumn(name = " id_usuario")
	private List<Rol> roles;
	
}
