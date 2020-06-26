
package com.roxket.dao;

import com.roxket.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Roxket
 */
public interface UsuarioDao extends JpaRepository<Usuario, Long> { //Tambien es valido CrudRepository de Spring, aunque JpaRepository tiene algunas mejoras
	Usuario findByUsername(String username); //metodo del standard Spring Security
}
