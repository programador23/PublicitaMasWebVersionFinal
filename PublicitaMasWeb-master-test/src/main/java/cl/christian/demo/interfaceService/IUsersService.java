package cl.christian.demo.interfaceService;

import java.util.List;

import cl.christian.demo.modelo.User;

public interface IUsersService {
	void guardar (User user);
	public User listaPorUser(String username);
}

