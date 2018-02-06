package LosOdiosos3.prueba_servidor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Event {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String name;	
	private String place;	
	private Date date;
	private String description;

	@OneToOne(cascade=CascadeType.REMOVE)
	private Image image;
	
	@ManyToMany(mappedBy="events")
	private List<Game> games;
	
	@ManyToMany(mappedBy="events")
	private List<Company> companies;

	protected Event() {
	}

	public Event(String name, String place, Date date, String description, Image image) {
		this.name = name;
		this.place = place;
		this.date = date;
		this.description = description;
		this.image = image;	
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
	
	public void setTitle(String name) {
		this.name = name;
	}
	
	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}
	
	@Override
	public String toString() {
		return "Event [id: " + id + ", name: " + name + ", place: " + place +
				", date: " + date + ", description: " + description + 
				", image: " + image + ", games: " + games + ", companies: " + companies + "]";
	}
}
