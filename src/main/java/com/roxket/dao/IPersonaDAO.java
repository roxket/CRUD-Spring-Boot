
package com.roxket.dao;

import com.roxket.domain.Persona;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Roxket
 */

public interface IPersonaDAO extends CrudRepository<Persona, Long>{
	//ya no se requiere de daos y cruds para cada clase, ni implementación de esta interfaz
	
	//Aqui se añaden los metodos personalizados
}
