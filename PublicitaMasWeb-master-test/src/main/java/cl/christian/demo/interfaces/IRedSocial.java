package cl.christian.demo.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.christian.demo.modelo.RedSocial;
@Repository
public interface IRedSocial extends JpaRepository<RedSocial, Integer> {
	
	RedSocial findByUsername(String username);
}
