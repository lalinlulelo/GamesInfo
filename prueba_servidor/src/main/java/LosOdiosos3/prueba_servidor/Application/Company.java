package LosOdiosos3.prueba_servidor.Application;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;



@Entity
public class Company {
	// identificador de la entidad
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	// atributos principales de la entidad
    private String name;
    private String country;
    private String description;
    private int year;
    private String image;    
    private String url;
    
    // lista de juegos que pertenecen a la compañía
    // evita error de la cache, pero la hace petar
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany (mappedBy="company", cascade=CascadeType.REMOVE)
    @Column(nullable = true)
    @JsonIgnore
    private List<Game> games = new ArrayList<Game>();
        
    // lista de comentarios que pertenecen a la compañía
    @OneToMany(mappedBy="company", cascade=CascadeType.REMOVE)
	private List<Comment> comments = new ArrayList<Comment>();
    
    // constructor de la entidad
    protected Company (){};

    // constructor del objeto Compañía
    public Company(String name, String country, String description, int year, String image, String url) {
    	this.name = name;
		this.country = country;
		this.description = description;
		this.year = year;
		this.image = image;
		this.url = url;
	}

	// ------------------------------ GETTERS Y SETTERS ----------------------------------
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

	public List<Game> getGames() {
		return games;
	}

	public void setGames(List<Game> games) {
		this.games = games;
	}
	
	@Override
	public String toString() {
		return "Company [id: " + id + ", name: " + name + ", country: " + country +
				", description: " + description + ", year: " + year + ", image: " + image + 
				", url: " + url + ", games: " + games + "]";
	}

	public void setComment(Comment c) {
		comments.add(c);
		
	}

	public List<Comment> getComment() {
		// TODO Auto-generated method stub
		return this.comments;
	}
	// ------------------------------ FIN GETTERS Y SETTERS ------------------------------
}
