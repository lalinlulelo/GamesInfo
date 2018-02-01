package LosOdiosos3.prueba_servidor;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


@Entity
public class Company {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
    private String name;
    private String country;
    private String description;
    
    @OneToMany (mappedBy="company", cascade=CascadeType.REMOVE)
    private List<Game> games = new ArrayList<Game>();
    
    protected Company (){};

    public Company(String name, String country, String description) {
		this.name = name;
		this.country = country;
		this.description = description;
	}

}
