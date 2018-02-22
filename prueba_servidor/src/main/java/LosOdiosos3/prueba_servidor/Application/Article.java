package LosOdiosos3.prueba_servidor.Application;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import LosOdiosos3.prueba_servidor.*;

@Entity
public class Article {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@ManyToOne
	private User user;
	
	@OneToMany(mappedBy="article", cascade=CascadeType.REMOVE)
	private List<Comment> comments = new ArrayList<Comment>();

	private String head;
	private String title;
	private String article;
	private String image;
	
	public Article () {}
	
	public Article (User user, String head, String title, String article, String image) {
		this.user = user;
		this.head = head;
		this.title = title;
		this.article = article;
		this.image = image;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getHead() {
		return head;
	}

	public void setHead(String head) {
		this.head = head;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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
		// TODO Auto-generated method stub
		return this.comments;
	}
	
	@Override
	public String toString() {
		return "Article [id=" + id + ", user=" + user + ", comments=" + comments + ", head=" + head + ", title=" + title
				+ ", article=" + article + ", image=" + image + "]";
	}
}

