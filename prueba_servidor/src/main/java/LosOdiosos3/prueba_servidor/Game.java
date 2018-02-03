package LosOdiosos3.prueba_servidor;
import java.util.Date;
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
	
	private String companyName;
	private String genre;
	private int year;
	private Date addGame;
	
	@OneToOne(cascade=CascadeType.REMOVE)
	private Image image;
	
	private String url;
	
	@ManyToMany
	private List<Event> events;
	
	public Game(String name, Company company,String companyName, String genre, int year, 
			Date addGame, Image image, String url) {		
		
		this.name = name;
		this.company = company;
		this.companyName= companyName;
		this.genre = genre;
		this.year = year;
		this.addGame = addGame;
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

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public Date getAddYear() {
		return addYear;
	}

	public void setAddYear(Date addYear) {
		this.addYear = addYear;
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
}
