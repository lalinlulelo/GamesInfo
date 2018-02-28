package LosOdiosos3.prueba_servidor.Application;
import LosOdiosos3.prueba_servidor.*;
import LosOdiosos3.prueba_servidor.Application.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Game {
	
	@Id	
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String name;
	
	@ManyToOne 
	private Company company;
	
	@ManyToMany 
	private List<MyList> lists = new ArrayList<MyList>();
	
	@OneToMany(mappedBy="game", cascade=CascadeType.REMOVE)
	private List<Comment> comments = new ArrayList<Comment>();
	
	private String genre;
	private String description;
	private int year;
	private double score;	
	private String image;	
	private String url;	
	
	public Game () {}
	
	public Game(String name, Company company, String genre, String description, 
			int year, double score, String image, String url) {				
		this.name = name;
		this.company = company;
		this.genre = genre;
		this.description = description;
		this.year = year;
		this.score = score;
		this.image = image;
		this.url = url;
	}	
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	public void addMyList(MyList list){
		lists.add(list);
	}
	
	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public void setComment(Comment comment){
		comments.add(comment);
	}
	public List<Comment> getComment(){		
		return this.comments;
	}
	
	public List<MyList> getLists() {
		return lists;
	}

	public void setLists(List<MyList> lists) {
		this.lists = lists;
	}

	@Override
	public String toString() {
		return "Game [id=" + id + ", name=" + name + ", company=" + company + ", genre=" + genre + ", description="
				+ description + ", year=" + year + ", score=" + score + ", image=" + image + ", url=" + url
				+ ", events=" + ", comments=" + comments + "]";
	}
}
