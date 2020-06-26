
package com.roxket.service;

import com.roxket.dao.UsuarioDao;
import com.roxket.domain.Rol;
import com.roxket.domain.Usuario;
import java.util.ArrayList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Roxket
 */

@Service("userDetailsService") //Clase bean de servicio, para spring Security
@Slf4j //manejo de log
public class UsuarioService implements UserDetailsService {
	
	@Autowired //Inyectamos una instancia de usuarioDao
	private UsuarioDao usuarioDao;
	
	@Override
	@Transactional(readOnly=true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = usuarioDao.findByUsername(username);
		
		if(usuario == null){
			throw new UsernameNotFoundException(username);
		}
		
		var roles = new ArrayList<GrantedAuthority>(); //requisito de Spring Security. SpringSecurity con implementacion de JPA
		
		for(Rol rol: usuario.getRoles()){ // .getRoles() viene de la autogeneracion por @Data en Usuario
			roles.add(new SimpleGrantedAuthority(rol.getNombre()));
		}
		
		return new User(usuario.getUsername(), usuario.getPassword(), roles); //Clase de Spring
	}
	
}
