package LosOdiosos3.prueba_servidor.Application;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
public class Event {
	// identificador de la entidad
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	// atributos principales de la entidad
	private String name;	
	private String place;	
	private int year;
	private int month;
	private int day;
	private double fee;
	private String description;
	private String image;
	
	// comentarios publicados en el evento
	@OneToMany(mappedBy="event", cascade=CascadeType.REMOVE)
	private List<Comment> comments=new ArrayList<Comment>();
	
	// construcor de la entidad
	protected Event() {
	}
	
	// constructor del objeto Evento
	public Event(String name, String place, int year, int month, int day, double fee, String description, String image) {
		this.name = name;
		this.place = place;
		this.year = year;
		this.month = month;
		this.day = day;
		this.fee = fee;
		this.description = description;
		this.image = image;	
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
	
	public void setTitle(String name) {
		this.name = name;
	}
	
	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}
	
	public void setYear (int year) {
		this.year = year;
	}
	
	public void setMonth (int month) {
		this.month = month;
	}
	
	public void setDay (int day) {
		this.day = day;
	}
	
	public int getYear () {
		return year;
	}
	
	public int getMonth () {
		return month;
	}
	
	public int getDay () {
		return day;
	}
	
	public double getFee() {
		return fee;
	}

	public void setFee(double fee) {
		this.fee = fee;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
	public List<Comment> getComment() {
		return this.comments;
	}
	public void setComment(Comment c) {
		this.comments.add(c);
	}
	
	@Override
	public String toString() {
		return "Event [id: " + id + ", name: " + name + ", place: " + place +
				", date: " + day + ", " + month + ", " + year + ", fee: " + fee + ", description: " + description + 
				", image: " + image + ", games: ]";
	}
	// ------------------------------ FIN GETTERS Y SETTERS ------------------------------
}
