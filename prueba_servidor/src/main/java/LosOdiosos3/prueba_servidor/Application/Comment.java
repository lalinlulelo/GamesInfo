package LosOdiosos3.prueba_servidor.Application;

import java.util.Date;

import javax.persistence.*;

@Entity
public class Comment {
	// identificador de la entidad
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	// usuario que publicó el comentario
	@ManyToOne
	private User user;	
	// juego en el que fue publicadon el comentario
	@OneToOne
	private Game game;	
	// compañía en la que fue publicada el comentario
	@OneToOne
	private Company company;	
	// evento en el que fue publicado el comentario
	@OneToOne
	private Event event;	
	// articulo en el que fue publicado el comentario
	@OneToOne
	private Article article;

	// atributos principales de la entidad
	private String text;
	private Date date;

	// constructor de la entidad
	protected Comment() {};
	
	// constructor del objeto Comentario
	public Comment(User user, String text, Date d) {		
		this.user = user;
		this.text = text;
		this.date =d;
	}
	
	//----------------------------------- GETTER Y SETTERS ---------------------------------
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
	//----------------------------------- FIN GETTER Y SETTERS -----------------------------
}
