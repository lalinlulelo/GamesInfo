package LosOdiosos3.prueba_servidor;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

// se indica que es un componente
@Component
@SessionScope
public class Usuario {
	private String nombre;
	private String password;
	private String fecha;
	
	public void setNombre (String name) {
		nombre = name;
	}
	
	public void setPassword (String pass) {
		password = pass;
	}
	
	public void setFecha (String date) {
		fecha = date;
	}
	
	public String getNombre () {
		return nombre;
	}
	
	public String getPassword () {
		return password;
	}
	
	public String getFecha () {
		return fecha;
	}
}
