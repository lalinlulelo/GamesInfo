package LosOdiosos3.prueba_servidor.Entities;
import LosOdiosos3.prueba_servidor.*;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;


@Entity
public class Company {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
    private String name;
    private String country;
    private String description;
    private int year;
    
    @OneToMany (mappedBy="company", cascade=CascadeType.REMOVE)
    private List<Game> games = new ArrayList<Game>();
    
    @OneToOne(cascade=CascadeType.REMOVE)
	private Image image;    
    private String url;
    
    @ManyToMany
	private List<Event> events;

    protected Company (){};

    public Company(String name, String country, String description, 
    				int year, Image image, String url) {
    	this.name = name;
		this.country = country;
		this.description = description;
		this.year = year;
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
	
	public String getCountry() {
		return country;
	}
	
	public void setCountry(String country) {
		this.country = country;
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
	
	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<Game> getGames() {
		return games;
	}

	public void setGames(List<Game> games) {
		this.games = games;
	}
	
	public List<Event> getEvents() {
		return events;
	}
	
	public void setEvents(List<Event> events) {
		this.events = events;
	}
	
	@Override
	public String toString() {
		return "Company [id: " + id + ", name: " + name + ", country: " + country +
				", description: " + description + ", year: " + year + ", image: " + image + 
				", url: " + url + ", games: " + games + ", events: " + events + "]";
	}
}
