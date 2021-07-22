package cl.christian.demo.interfaces;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import cl.christian.demo.modelo.CartelPublicitario;
@Repository
public interface ICartelPublicitario extends CrudRepository<CartelPublicitario, Integer> {

	List<CartelPublicitario> findByIdusuario(String idusuario);
}
