package cl.christian.demo.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idusuario;
	private String username;
	private String password;
	private int enabled;
	
	private String nombre;
	private String apellido;
	private String correo;
	private int celular;
	
	private String infopresentacion;
	
	
	public int getIdusuario() {
		return idusuario;
	}
	public void setIdusuario(int idusuario) {
		this.idusuario = idusuario;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getEnabled() {
		return enabled;
	}
	public void setEnabled(int enabled) {
		this.enabled = enabled;
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
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public int getCelular() {
		return celular;
	}
	public void setCelular(int celular) {
		this.celular = celular;
	}
	
	public String getInfopresentacion() {
		return infopresentacion;
	}
	public void setInfopresentacion(String infopresentacion) {
		this.infopresentacion = infopresentacion;
	}
	@Override
	public String toString() {
		return "User [idusuario=" + idusuario + ", username=" + username + ", password=" + password + ", enabled="
				+ enabled + ", nombre=" + nombre + ", apellido=" + apellido + ", correo=" + correo + ", celular="
				+ celular + ", infopresentacion=" + infopresentacion + "]";
	}
	

	
	
}
