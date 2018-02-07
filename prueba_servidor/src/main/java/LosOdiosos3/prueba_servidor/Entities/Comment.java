package LosOdiosos3.prueba_servidor.Entities;
import LosOdiosos3.prueba_servidor.*;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;


@Entity
public class Comment {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@ManyToOne
	private User user;

	private String text;
	
	@ManyToOne
	private Game game;
	
	/*
	@ManyToOne
	private Company company;	
	@ManyToOne
	private Event event;
	*/

	protected Comment() {
	};

	public Comment(User user, String text) {		
		this.user = user;
		this.text = text;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	@Override
	public String toString() {
		return "Comment [id=" + id + ", user=" + user + ", text=" + text + ", game=" + game + "]";
	}

}
