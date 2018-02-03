package LosOdiosos3.prueba_servidor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Event {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String title;	
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

	public Event(String title, String place, Date date, Image image, String description) {
		this.title = title;
		this.place = place;
		this.date = date;
		this.description = description;
		this.image = image;	
	}

}
