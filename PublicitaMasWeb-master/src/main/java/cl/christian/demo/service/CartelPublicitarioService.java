package cl.christian.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.christian.demo.interfaceService.ICartelPublicitarioService;
import cl.christian.demo.interfaces.ICartelPublicitario;

import cl.christian.demo.modelo.CartelPublicitario;

@Service
public class CartelPublicitarioService implements ICartelPublicitarioService {

	@Autowired
	private ICartelPublicitario data;
	@Override
	public List<CartelPublicitario> listarcarteles() {
		return (List<CartelPublicitario>)data.findAll();
		
	}

	@Override
	public CartelPublicitario listarIdcartel(int id) {
		
		return data.findById(id).orElse(null);
	}

	@Override
	public int savecartel(CartelPublicitario c) {
		int res=0;
		CartelPublicitario cartelPublicitario=data.save(c);
		if(!cartelPublicitario.equals(null)) {
			
		}
		return 0;
	}

	@Override
	public void deletecartel(int id) {
		data.deleteById(id);
		
	}

	@Override
	public List<CartelPublicitario> listarIdcartelUsuario(int idusuario) {
		// TODO Auto-generated method stub
		return null;
	}





	

}
