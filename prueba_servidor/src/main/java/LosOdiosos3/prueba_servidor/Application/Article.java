package LosOdiosos3.prueba_servidor.Application;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
public class Article {
	// identificador de la entidad
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	// usuario que escribió el artículo
	@ManyToOne
	private User user;
	
	// comentarios referentes al artículo
	@OneToMany(mappedBy="article", cascade=CascadeType.REMOVE)
	private List<Comment> comments = new ArrayList<Comment>();
	
	// atributos principales de la entidad
	private String title;
	private String head;	
	private String article;
	private String image;
	
	// constructor de la entidad
	public Article () {}
	
	// constructor del objeto Artículo
	public Article (User user, String title, String head, String article, String image) {
		this.user = user;
		this.title = title;
		this.head = head;		
		this.article = article;
		this.image = image;
	}
	
	// ------------------------------ GETTERS Y SETTERS ----------------------------------
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getHead() {
		return head;
	}

	public void setHead(String head) {
		this.head = head;
	}

	public String getArticle() {
		return article;
	}

	public void setArticle(String article) {
		this.article = article;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
	public void setComment(Comment c) {
		comments.add(c);
		
	}

	public List<Comment> getComment() {
		return this.comments;
	}
	
	@Override
	public String toString() {
		return "Article [id=" + id + ", user=" + user + ", comments=" + comments + ", head=" + head + ", title=" + title
				+ ", article=" + article + ", image=" + image + "]";
	}
	// ------------------------------ FIN GETTERS Y SETTERS -------------------------------
}

