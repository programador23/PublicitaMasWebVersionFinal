package cl.christian.demo.interfaceService;

import java.util.List;

import cl.christian.demo.modelo.Postulacion;

public interface IPostulacionesService {
 void guardar(Postulacion postulacion);
 List<Postulacion>buscarPorIdusuario(String idusuario);
 List<Postulacion>buscarPorIdCampania(int idCampania);
}
