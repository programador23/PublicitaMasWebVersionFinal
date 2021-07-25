package cl.christian.demo.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="postulaciones")
public class Postulacion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int idCampania;

	private String idusuario;
	private String mensajePos;
	private String mensajeIns;
	private String estado;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdCampania() {
		return idCampania;
	}
	public void setIdCampania(int idCampania) {
		this.idCampania = idCampania;
	}
	public String getIdusuario() {
		return idusuario;
	}
	public void setIdusuario(String idusuario) {
		this.idusuario = idusuario;
	}
	public String getMensajePos() {
		return mensajePos;
	}
	public void setMensajePos(String mensajePos) {
		this.mensajePos = mensajePos;
	}
	public String getMensajeIns() {
		return mensajeIns;
	}
	public void setMensajeIns(String mensajeIns) {
		this.mensajeIns = mensajeIns;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	@Override
	public String toString() {
		return "Postulacion [id=" + id + ", idCampania=" + idCampania + ", idusuario=" + idusuario + ", mensajePos="
				+ mensajePos + ", mensajeIns=" + mensajeIns + ", estado=" + estado + "]";
	}
	
	
}
