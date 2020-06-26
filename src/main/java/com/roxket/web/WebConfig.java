package com.roxket.web;

import java.util.Locale;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

/**
 *
 * @author Roxket
 */

@Configuration //bean de configuracion de Spring
public class WebConfig implements WebMvcConfigurer{
	
	//Configuración de internacionalización
	@Bean //Se agrega al contexto de Spring
	public SessionLocaleResolver localeResolver(){
		var slr = new SessionLocaleResolver();
		slr.setDefaultLocale(new Locale("es"));
		return slr;
	}
	
	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor(){
		var lci = new LocaleChangeInterceptor();
		lci.setParamName("lang");
		return lci;
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registro){
		registro.addInterceptor(localeChangeInterceptor()); 
	}
	
	//Configuración del path de acceso con Seguridad activada
	@Override
	public void addViewControllers(ViewControllerRegistry registro){ //mapeo directamente a la vista sin pasar por el controlador
		registro.addViewController("/").setViewName("index");
		registro.addViewController("/login");
		registro.addViewController("/errores/403").setViewName("/errores/403");
	}
	
	
}
