package cl.christian.demo.interfaces;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import cl.christian.demo.modelo.CartelPublicitario;
@Repository
public interface ICartelPublicitario extends CrudRepository<CartelPublicitario, Integer> {

}
