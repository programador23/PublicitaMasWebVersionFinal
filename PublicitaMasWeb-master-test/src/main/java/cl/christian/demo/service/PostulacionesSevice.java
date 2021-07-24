package cl.christian.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.christian.demo.interfaceService.IPostulacionesService;
import cl.christian.demo.interfaces.IPostulacionesRepository;
import cl.christian.demo.modelo.Postulacion;
@Service
public class PostulacionesSevice implements IPostulacionesService{

	@Autowired
	private IPostulacionesRepository postulacionesRepo;
	@Override
	public void guardar(Postulacion postulacion) {
		postulacionesRepo.save(postulacion);
		
		
	}
	@Override
	public List<Postulacion> buscarPorIdusuario(String idusuario) {
		
		return postulacionesRepo.findByIdusuario(idusuario);
	}
	@Override
	public List<Postulacion> buscarPorIdCampania(int idCampania) {
		
		return postulacionesRepo.findByIdCampania(idCampania);
	}



}
