package cl.christian.demo.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.christian.demo.modelo.User;

@Repository
public interface IUsersRepository extends JpaRepository<User, Integer> {
	User findByUsername(String username);
	
}