package LosOdiosos3.prueba_servidor.Application;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class MyList {
	// identificacion de la entidd
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	// lista de juegos 
	@ManyToMany(cascade = CascadeType.ALL,fetch=FetchType.EAGER, mappedBy="lists")
	@Fetch(FetchMode.SELECT)
	@JsonIgnore
	private List<Game> games = new ArrayList<Game>();
	
	// usuario al que pertenece la lista
	@ManyToOne
	private User user;
	
	// atributo principal de la entidad
	private String name;
	
	// constructor de la entidad
	public MyList () {}
	
	// constructor del objeto Entidad
	public MyList (String name) {
		this.name = name;
	}
	
	// metodo que vacía la lista
	public void cleanList () {
		this.games.clear();
	}
	
	// ------------------------------ GETTERS Y SETTERS ----------------------------------
	public void setName (String name) {
		this.name = name;
	}
	
	public String getName () {
		return name;
	}
	
	public void addGame (Game game) {
		games.add(game);
	}
	
	public List<Game> getList (){
		return games;
	}
	
	public void addUser(User user) {
		this.user = user;
	}
	// ------------------------------ FIN GETTERS Y SETTERS ------------------------------
}
