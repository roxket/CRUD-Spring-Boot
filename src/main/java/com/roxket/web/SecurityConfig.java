package com.roxket.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 *
 * @author Roxket
 */
@Configuration //Configuracion Spring
@EnableWebSecurity // Security Settings
public class SecurityConfig extends WebSecurityConfigurerAdapter { // se extiende para admitir la configuracion de usuarios

	@Autowired
	private UserDetailsService userDetailsService;
	
	@Bean //Se declara la instancia dentro del container de Spring (IoC) de Spring para posterior uso global
	public BCryptPasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}
	
	@Autowired //Inyectamos una instancia AuthenticationManagerBuilder
	public void configurerGlobal(AuthenticationManagerBuilder build) throws Exception{
		//Se inyecta una instancia de userDetailsService de UsuarioService 
		build.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	} 
	
	@Override
	//Metodo de configuracion de accesos - AUTORIZACIÓN
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/editar/**", "/agregar/**", "/eliminar") //configuramos los paths
				.hasRole("ADMIN")
				.antMatchers("/")
				.hasAnyRole("USER", "ADMIN")
				.and()
				.formLogin()
				.loginPage("/login")
				.and()
				.exceptionHandling().accessDeniedPage("/errores/403");
				

	}
}
