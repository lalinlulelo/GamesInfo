package LosOdiosos3.prueba_servidor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Image {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String url;

	protected Image(){
		
	}
	
	public Image(String url){
		this.url = url;
	}
	
	public String getUrl(){
		return url;
	}
	
	public void setUrl(String url){
		this.url = url;
	}
	
	@Override
	public String toString() {
		return "Image [id: " + id + ", url: " + url + "]";
	}	

}
