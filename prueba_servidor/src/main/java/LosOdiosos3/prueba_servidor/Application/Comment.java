package LosOdiosos3.prueba_servidor.Application;
import LosOdiosos3.prueba_servidor.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
public class Comment {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@ManyToOne
	private User user;	
	@OneToOne
	private Game game;	
	@OneToOne
	private Company company;	
	@OneToOne
	private Event event;	
	@OneToOne
	private Article article;

	private String text;
	private Date date;

	protected Comment() {};

	public Comment(User user, String text, Date d) {		
		this.user = user;
		this.text = text;
		this.date =d;
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

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}
	
	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}


	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Comment [id=" + id + ", user=" + user + ", game=" + game + ", company=" + company + ", event=" + event
				+ ", article=" + article + ", text=" + text + ", date=" + date + "]";
	}
}
