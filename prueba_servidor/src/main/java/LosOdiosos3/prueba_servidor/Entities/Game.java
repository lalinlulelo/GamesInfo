package LosOdiosos3.prueba_servidor.Entities;
import LosOdiosos3.prueba_servidor.*;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.ManyToMany;

@Entity
public class Game {
	
	@Id	
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String name;
	
	@ManyToOne 
	private Company company;
	
	private String genre;
	private String description;
	private int year;
	private Date addGame;
	private double score;
	
	private String image;
	
	private String url;
	
	@ManyToMany
	private List<Event> events;
	
	public Game(String name, Company company, String genre, String description, int year, 
			Date addGame, double score, String image, String url) {		
		
		this.name = name;
		this.company = company;
		this.genre = genre;
		this.description = description;
		this.year = year;
		this.addGame = addGame;
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

	public Date getAddGame() {
		return addGame;
	}

	public void setAddGame(Date addGame) {
		this.addGame = addGame;
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
	
	public List<Event> getEvents() {
		return events;
	}
	
	public void setEvents(List<Event> events) {
		this.events = events;
	}
	
	@Override
	public String toString() {
		return "Game [id: " + id + ", name: " + name + ", company: " + company +
				", genre: " + genre + ", description: " + description + ", year: " + year + ", addGame: " + addGame + 
				", score: " + score + ", image: " + image + ", url: " + url + ", events: " + events + "]";
	}
}
