package LosOdiosos3.prueba_servidor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

// se indica que es un componente

@Component
@SessionScope
@Entity
public class User {
	// id de la entidad user
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	// atributos del usuario
	private String name;
	private String password;
	private String date;
	
	// setters
	public void setName (String name) {
		this.name = name;
	}
	
	public void setPassword (String password) {
		this.password = password;
	}
	
	public void setDate (String date) {
		this.date = date;
	}
	
	// getters
	public String getName () {
		return name;
	}
	
	public String getPassword () {
		return password;
	}
	
	public String getDate () {
		return date;
	}
}
