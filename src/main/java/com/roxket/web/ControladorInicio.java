
package com.roxket.web;

import com.roxket.domain.Persona;
import com.roxket.service.IPersonaService;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author Roxket
 */

@Controller
@Slf4j
public class ControladorInicio { //implementa servlets internamente
	
	@Autowired // inyectamos la capa logica de servicio
	private IPersonaService personaService;
	
	@GetMapping("/") //path de solicitud tipo get. Ruta de inicio
	
	//@AuthenticationPrincipal User: inyeccion de dependencia para capturar el usuario login en la aplicacion
	public String inicio(Model model, @AuthenticationPrincipal User user){ //inyeccion de dependencia (principio hollywood)
		
		var personas = personaService.listarPersonas(); //Aunque instanciamos la interfaz, busca dentro del contenedor la implementación de esta con la anotacion @Service
		
		log.info("Ejecutando el controlador SpringMVC");
		log.info("usuario login: " + user);
		model.addAttribute("personas", personas);
		var saldoTotal = 0D;
		for(var p : personas){
			saldoTotal += p.getSaldo();
		}
		
		model.addAttribute("saldoTotal", saldoTotal);
		model.addAttribute("totalClientes", personas.size());
		return "index";
	}
	
	@GetMapping("/agregar")
	public String agregar(Persona persona){ //inyecta el objeto desde la fabrica de spring
		return "modificar";
	}
	
	@PostMapping("/guardar") //path de solicitud tipo Post
	public String guardar(@Valid Persona persona, Errors errores){ // Inyecta el objeto y recupera los parametros desde el formulario. Si existe el objeto en memoria, pasa la instancia 
						
		if (errores.hasErrors()) { //@Valid y Errors validación en el método
			return "modificar";
		} else{
		personaService.guardar(persona);
		return "redirect:/";}
	}
	
	@GetMapping("/editar/{idPersona}")
	public String editar(Persona persona, Model model){ // si existe, lo settea, sino lo inicializa y lo asocia al parametro. Con model pasa el objeto a la siguiente peticion
		persona = personaService.encontrarPersona(persona); //recupera el objeto desde la bbdd
		model.addAttribute("persona", persona); //setea el parametro a la vista
		return "modificar"; //return a la vista modificar
	}
	
	@GetMapping("/eliminar")
	public String eliminar(Persona persona){
		personaService.eliminar(persona);
		return "redirect:/";
	}
	
}
