
package com.roxket.service;

import com.roxket.dao.IPersonaDAO;
import com.roxket.domain.Persona;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Roxket
 */

@Service //Parte del contendor de Spring, Permite inyectar esta implementacion
public class PersonaServiceImpl implements IPersonaService{
	
	@Autowired //Utilizará esta implementación, y no el DAO. Estamos desacoplando la capa de datos 
	private IPersonaDAO personaDao;
	
	
	@Override
	@Transactional(readOnly = true) //commit or rollback
	public List<Persona> listarPersonas() {
		return (List<Persona>) personaDao.findAll();
	}

	@Override
	@Transactional
	public void guardar(Persona persona) {
		personaDao.save(persona);
	}

	@Override
	@Transactional
	public void eliminar(Persona persona) {
		personaDao.delete(persona);
	}

	@Override
	@Transactional(readOnly = true)
	public Persona encontrarPersona(Persona persona) {
		return personaDao.findById(persona.getIdPersona()).orElse(null);
	}
	
}
