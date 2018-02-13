package LosOdiosos3.prueba_servidor.Entities;
import LosOdiosos3.prueba_servidor.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.*;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import com.mysql.fabric.xmlrpc.base.Array;

// se indica que es un componente
@Entity
public class User {	
	// id de la entidad user	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	// atributos del usuario
	private String name = "...";
	private String password = "...";
	private String email;
	private String date = "...";
	private String icon = "https://mir-s3-cdn-cf.behance.net/project_modules/disp/64623a33850498.56ba69ac2a6f7.png";
	
	//private ArrayList<ArrayList<Game>> my_lists = new ArrayList<ArrayList<Game>>();
	@ManyToMany(mappedBy="users")
	private List<Game> games = new ArrayList<Game>();
	
	@OneToMany(mappedBy="user", cascade=CascadeType.REMOVE)
	private List<Comment> comments = new ArrayList<Comment>();
	
	@OneToMany(mappedBy="user", cascade=CascadeType.REMOVE)
	private List<Article> articles = new ArrayList<Article>();

	// lista de juegos
	// suscripcion juego
	// suscripcion compañia
	// suscripcion evento
	
	// constructor
	protected User () {}
	
	// contructor
	public User (String name, String password, String date, String email) {
		this.name = name;
		this.password = password;
		this.date = date;
		this.email = email;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
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
	
	public void setEmail (String email) {
		this.email = email;
	}
	
	public void setIcon (String url) {
		icon = url;
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
	
	public String getEmail () {
		return email;
	}
	
	public String getIcon () {
		return icon;
	}
	
	// toString
	public String toString () {
		return "User [name: " + name + "/npassword: " + password + "/nbirthday: " + date + "/nemail" + email + "]";
	}

	public List<Game> getGames() {
		return games;
	}

	public void setGames(List<Game> games) {
		this.games = games;
	}
	
	public void addGame(Game game){
		games.add(game);
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	
	// metodos auxiliares
	/*
	public void addList (ArrayList<Game> list) {
		my_lists.add(list);
	}
	
	public void removeList(ArrayList<Game> list) {
		my_lists.remove(list);
	}
	
	public ArrayList<ArrayList<Game>> getMyLists(){
		return my_lists;
	}
	*/
	
}
