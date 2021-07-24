package cl.christian.demo.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.christian.demo.modelo.Postulacion;
@Repository
public interface IPostulacionesRepository extends JpaRepository<Postulacion, Integer>{

	List<Postulacion>findByIdusuario(String idusuario);
	List<Postulacion>findByIdCampania(int idCampania);
}
