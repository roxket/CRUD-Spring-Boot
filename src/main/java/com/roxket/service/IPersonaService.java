package com.roxket.service;

import com.roxket.domain.Persona;
import java.util.List;

/**
 *
 * @author Roxket
 */
public interface IPersonaService {
	
	public List<Persona> listarPersonas();
	
	public void guardar(Persona persona);
	
	public void eliminar(Persona persona);
	
	public Persona encontrarPersona(Persona persona);
	
}
