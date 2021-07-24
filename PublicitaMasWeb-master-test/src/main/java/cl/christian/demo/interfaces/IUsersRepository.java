package cl.christian.demo.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.christian.demo.modelo.User;

@Repository
public interface IUsersRepository extends JpaRepository<User, Integer> {

}
