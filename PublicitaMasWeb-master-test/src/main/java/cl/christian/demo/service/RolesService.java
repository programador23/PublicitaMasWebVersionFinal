package cl.christian.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.christian.demo.interfaceService.IRolesService;
import cl.christian.demo.interfaces.IRolesRepository;
import cl.christian.demo.modelo.Roles;

@Service
public class RolesService implements IRolesService{
	@Autowired
	private IRolesRepository rolesRepo;
	@Override
	public void guardar(Roles roles) {
		rolesRepo.save(roles);
		
	}

}
