package LosOdiosos3.prueba_servidor.Entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class MyList {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	// una lista puede tener varios juegos
	@ManyToMany(mappedBy="lists")
	private List<Game> games = new ArrayList<Game>();
	
	// un usuario puede tener varias listas
	@ManyToOne
	private User user;
	
	private String name;
	
	public MyList () {}
	
	public MyList (String name) {
		this.name = name;
	}
	
	public void cleanList () {
		this.games.clear();
	}
	
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
}
