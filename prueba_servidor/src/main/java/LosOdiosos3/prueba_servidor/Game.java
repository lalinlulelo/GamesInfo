package LosOdiosos3.prueba_servidor;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;


//@Entity
public class Game {
	/*
	@Id	
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	*/
	private String name;
	/*
	@ManyToOne
	private Company company;
	*/
	private String companyName;
	private String genre;
	private int year;
	private Date addGame;
	/*
	@OneToOne(cascade=CascadeType.REMOVE)
	private Image image;
	*/
	private String url;
	
	public Game(String name, Company company,String companyName, String genre, int year, Date addGame,
			Image image, String url) {		
		
		this.name = name;
		//this.company = company;
		this.companyName= companyName;
		this.genre = genre;
		this.year = year;
		this.addGame = addGame;
		//this.image = image;
		this.url = url;
	}

}
