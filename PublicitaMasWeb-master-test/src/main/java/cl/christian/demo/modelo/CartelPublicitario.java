package cl.christian.demo.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="cartel")
public class CartelPublicitario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String titulo;
	private String direccion;
	private String region;
	private String comuna;
	private String documentacion;
	private String image;
	private int nrocontacto;
	private String correo;
	private String nombre;
	private String apellido;
	private String detalle;
	private int precio;
	private String idusuario;
	public CartelPublicitario(int id, String titulo, String direccion, String region, String comuna,
			String documentacion, String image, int nrocontacto, String correo, String nombre, String apellido,
			String detalle, int precio, String idusuario) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.direccion = direccion;
		this.region = region;
		this.comuna = comuna;
		this.documentacion = documentacion;
		this.image = image;
		this.nrocontacto = nrocontacto;
		this.correo = correo;
		this.nombre = nombre;
		this.apellido = apellido;
		this.detalle = detalle;
		this.precio = precio;
		this.idusuario = idusuario;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getComuna() {
		return comuna;
	}
	public void setComuna(String comuna) {
		this.comuna = comuna;
	}
	public String getDocumentacion() {
		return documentacion;
	}
	public void setDocumentacion(String documentacion) {
		this.documentacion = documentacion;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public int getNrocontacto() {
		return nrocontacto;
	}
	public void setNrocontacto(int nrocontacto) {
		this.nrocontacto = nrocontacto;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getDetalle() {
		return detalle;
	}
	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}
	public int getPrecio() {
		return precio;
	}
	public void setPrecio(int precio) {
		this.precio = precio;
	}
	public String getIdusuario() {
		return idusuario;
	}
	public void setIdusuario(String idusuario) {
		this.idusuario = idusuario;
	}
	public CartelPublicitario() {
		super();
	}
	
	
	


	
	
	
}
