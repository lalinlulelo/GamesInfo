package LosOdiosos3.prueba_servidor;

import javax.persistence.Entity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

// se indica que es un componente

@Component
@SessionScope
@Entity
public class User {
	private String name;
	private String password;
	private String date;
	
	public void setName (String name) {
		this.name = name;
	}
	
	public void setPassword (String password) {
		this.password = password;
	}
	
	public void setDate (String date) {
		this.date = date;
	}
	
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
