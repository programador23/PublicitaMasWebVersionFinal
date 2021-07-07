package cl.christian.demo.interfaceService;

import java.util.List;
import java.util.Optional;

import cl.christian.demo.modelo.CartelPublicitario;

public interface ICartelPublicitarioService {

	public List<CartelPublicitario>listarcarteles();
	public CartelPublicitario listarIdcartel(int id);
	public int savecartel(CartelPublicitario c);
	public void deletecartel (int id);
	
	public List<CartelPublicitario>listarIdcartelUsuario(int idusuario);

	
}
