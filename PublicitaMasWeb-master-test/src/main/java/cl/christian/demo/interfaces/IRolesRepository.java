package cl.christian.demo.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.christian.demo.modelo.Roles;

@Repository
public interface IRolesRepository extends JpaRepository<Roles, Integer>{

}
