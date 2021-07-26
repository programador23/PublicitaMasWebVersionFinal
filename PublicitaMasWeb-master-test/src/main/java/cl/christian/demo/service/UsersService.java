package cl.christian.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.christian.demo.interfaceService.IUsersService;
import cl.christian.demo.interfaces.IUsersRepository;
import cl.christian.demo.modelo.User;
@Service
public class UsersService implements IUsersService{
	@Autowired
	private IUsersRepository usersRepo;
	@Override
	public void guardar(User user) {
		usersRepo.save(user);
		
	}
	@Override
	public User listaPorUser(String username) {
		
	return usersRepo.findByUsername(username);
	}


	
	

}
