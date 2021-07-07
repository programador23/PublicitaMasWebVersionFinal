package cl.christian.demo.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import cl.christian.demo.modelo.CampaniaPublicitaria;
@Repository
public interface ICampaniaPublicitaria extends CrudRepository<CampaniaPublicitaria, Integer>{

	List<CampaniaPublicitaria>findByIdusuario(String idusuario);
	List<CampaniaPublicitaria>findByNombre(String nombre);
	
	@Query("FROM CampaniaPublicitaria c WHERE c.id = :id")
	public List<CampaniaPublicitaria>findCampaniaPublicitariaById(@Param("id") int id);
	
}

