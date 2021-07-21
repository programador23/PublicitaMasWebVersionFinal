package cl.christian.demo.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="campaniapublicitaria")
public class CampaniaPublicitaria {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String nombre;
	private String area;
	private int valor;
	private String duracion;
	private String detalle;
	private String image;
	private String idusuario;
	
	public CampaniaPublicitaria() {
		super();
	}

	public CampaniaPublicitaria(int id, String nombre, String area, int valor, String duracion, String detalle,
			String image, String idusuario) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.area = area;
		this.valor = valor;
		this.duracion = duracion;
		this.detalle = detalle;
		this.image = image;
		this.idusuario = idusuario;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

	public String getDuracion() {
		return duracion;
	}

	public void setDuracion(String duracion) {
		this.duracion = duracion;
	}

	public String getDetalle() {
		return detalle;
	}

	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getIdusuario() {
		return idusuario;
	}

	public void setIdusuario(String idusuario) {
		this.idusuario = idusuario;
	}

	




	
}
