package cl.christian.demo.interfaceService;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.query.Param;

import cl.christian.demo.modelo.CampaniaPublicitaria;



public interface ICampaniaPublicitariaService {

	public List<CampaniaPublicitaria>listarcampania();
	public CampaniaPublicitaria listarId(int id);
	public void savecampania(CampaniaPublicitaria c);
	public void delete(int id);
	
	
	
	public List<CampaniaPublicitaria>BuscarPordusuario(String idusuario);
	
	public List<CampaniaPublicitaria>BuscarPornombre(String nombre);
	
	public List<CampaniaPublicitaria>findCampaniaPublicitariaById(@Param("id") int id);
}
