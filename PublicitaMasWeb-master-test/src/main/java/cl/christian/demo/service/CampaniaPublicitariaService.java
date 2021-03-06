package cl.christian.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import cl.christian.demo.interfaceService.ICampaniaPublicitariaService;
import cl.christian.demo.interfaces.ICampaniaPublicitaria;
import cl.christian.demo.modelo.CampaniaPublicitaria;


@Service
public class CampaniaPublicitariaService implements ICampaniaPublicitariaService{

	@Autowired
	private ICampaniaPublicitaria data;
	@Override
	public List<CampaniaPublicitaria> listarcampania() {
		
		return (List<CampaniaPublicitaria>)data.findAll();
	}



	@Override
	public void savecampania(CampaniaPublicitaria c) {
		//guarda 
		int res=0;
		CampaniaPublicitaria campaniapublicitaria=data.save(c);
		if(!campaniapublicitaria.equals(null)) {
			res=1;
		}
		return;
	}

	@Override
	public void delete(int id) {
		data.deleteById(id);
		
		
	}


	@Override
	public List<CampaniaPublicitaria> BuscarPordusuario(String idusuario) {
		// TODO Auto-generated method stub
		return data.findByIdusuario(idusuario);
	}



	@Override
	public List<CampaniaPublicitaria> findCampaniaPublicitariaById(int id) {
		
		return data.findCampaniaPublicitariaById(id);
	}



	@Override
	public CampaniaPublicitaria listarId(int id) {
		// TODO Auto-generated method stub
		return data.findById(id).orElse(null);
	}



	@Override
	public List<CampaniaPublicitaria> BuscarPornombre(String nombre) {
		// TODO Auto-generated method stub
		return data.findByNombre(nombre);
	}












	



}
