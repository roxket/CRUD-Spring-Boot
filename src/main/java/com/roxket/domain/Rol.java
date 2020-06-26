
package com.roxket.domain;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import lombok.Data;

/**
 *
 * @author Roxket
 */

@Entity //JPA standard
@Data //Setters y getter de lombok
@Table(name="rol") //mapeo de la tabla
public class Rol implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id //primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) //standard JPA
	private Long idRol;
	
	@NotEmpty //standard JPA
	private String nombre;
	
}
