package LosOdiosos3.prueba_servidor.Application;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.security.web.csrf.CsrfToken;

@Controller
public class webController {	
	// repositorio de la tabla compañias
	@Autowired
	private CompanyRepository companyRepository;
	
	// repositorio de la tabla eventos
	@Autowired
	private EventRepository eventRepository;
	
	// repositorio de la tabla juegos
	@Autowired
	private GameRepository gameRepository;
	
	// repositorio de la tabla anuncios
	@Autowired
	private ArticleRepository articleRepository;
	
	// ----------------------------- PAGINA INICIO ------------------------------------
	@RequestMapping("/")
	public String inicio (Model model, HttpSession usuario, HttpServletRequest request) throws ParseException {
		// en caso de no estar inicializada la variable, se inicializa
		
		if(usuario.getAttribute("registered") == null) {
			usuario.setAttribute("registered", false);			
		}
		
		// comentarios de abajo del html
		model.addAttribute("Titulo", "Latest News");		
		
		// articulos relevantes
		model.addAttribute("news", articles());
		
		// condicionales para mostrar u ocultar contenido		
		model.addAttribute("registered", usuario.getAttribute("registered"));		
		boolean aux = !(Boolean) usuario.getAttribute("registered");	
		//boolean aux = false;
		if(aux == false) {
			model.addAttribute("name", usuario.getAttribute("name"));
			model.addAttribute("profile_img",String.format("<img src=\"%s\" class=\"profile_img\">",(String) usuario.getAttribute("icon")));
		}else {
			model.addAttribute("name", " ");
		}		
		model.addAttribute("unregistered", aux);
		
		//Para activar admin
		model.addAttribute("admin", usuario.getAttribute("admin"));
		
		// deshabilitacion del comando alert que saluda al usuario		
		usuario.setAttribute("alert", " "); 
		model.addAttribute("alert", usuario.getAttribute("alert"));
		//model.addAttribute("alert", " ");
		model.addAttribute("hello", " ");
		
		// atributos del token
		CsrfToken token = (CsrfToken) request.getAttribute("_csrf");
		model.addAttribute("token", token.getToken());

		// se accede a la pagina principal
		return "index";
	}
	// ----------------------------- FIN PAGINA INICIO --------------------------------

	// ----------------------------- BUSQUEDAS ----------------------------------------
	@RequestMapping("/search")
	public String search (Model model, HttpSession usuario, @RequestParam String text, HttpServletRequest request) {
		
		// se adquieren las listas de juego, compañia y evento
		List<Game> games = gameRepository.findByNameContaining(text);
		List<Company> companies = companyRepository.findByNameContaining(text);
		List<Event> events = eventRepository.findByNameContaining(text);
		
		// se crea una lista de cadenas por cada categoria
		List<String> list_games = new ArrayList<String>();
		List<String> list_companies = new ArrayList<String>();	
		List<String> list_events = new ArrayList<String>();	
		
		// booleanos que controlan la presencia de elementos de cada categoría
		boolean n_games = false;
		boolean n_companies = false;
		boolean n_events = false;
		boolean no_lists = true;

		if(games.size() > 0) {
			no_lists = false;
			n_games = true;
			String div="<div class=\"col-md-3\">\r\n" + "<div class=\"Game\">\r\n" + "<img src=\"%s\" class=\"imagen\">\r\n" + 	" <a href=\"%s\" style=\"text-align:center;display:block; color:  rgb(33, 73, 138);\">%s</a>\r\n" + "     \r\n" + "    </div>\r\n" + "  </div>";
			// lista de todas las compañias disponibles		
			for(int i=0; i < games.size(); i++) {
				//Aqui accederiamos a la base de datos para cambiar en cada iteracion las variables
				String Url = games.get(i).getImage();
				String Titulo = games.get(i).getName();
				String link = "/game/" + Titulo;			
				//Copiamos el div que queremos poner en el documento html en una variable auxiliar
				//Le damos formato a la variable auxiliar y la añadimos a la lista
				String aux = String.format(div, Url, link, Titulo);
				list_games.add(aux);			
			}			
			// se transmite la lista a la plantilla
			model.addAttribute("games", list_games);
		}
		
		if(companies.size() > 0) {
			no_lists = false;
			n_companies = true;
			String div="<div class=\"col-md-3\">\r\n" + "<div class=\"Game\">\r\n" + "<img src=\"%s\" class=\"imagen\">\r\n" + 	" <a href=\"%s\" style=\"text-align:center;display:block; color:  rgb(33, 73, 138);\">%s</a>\r\n" + "     \r\n" + "    </div>\r\n" + "  </div>";
			// lista de todas las compañias disponibles		
			for(int i=0; i < companies.size(); i++) {
				//Aqui accederiamos a la base de datos para cambiar en cada iteracion las variables
				String Url = companies.get(i).getImage();
				String Titulo = companies.get(i).getName();
				String link = "/company/" + Titulo;			
				//Copiamos el div que queremos poner en el documento html en una variable auxiliar
				//Le damos formato a la variable auxiliar y la añadimos a la lista
				String aux=String.format(div, Url, link, Titulo);
				list_companies.add(aux);			
			}			
			// se transmite la lista a la plantilla
			model.addAttribute("companies", list_companies);
		}
		
		if(events.size() > 0) {
			no_lists = false;
			n_events = true;
			String div="<div class=\"col-md-3\">\r\n" + "<div class=\"Game\">\r\n" + "<img src=\"%s\" class=\"imagen\">\r\n" + 	" <a href=\"%s\" style=\"text-align:center;display:block; color:  rgb(33, 73, 138);\">%s</a>\r\n" + "     \r\n" + "    </div>\r\n" + "  </div>";
			// lista de todas las compañias disponibles		
			for(int i=0; i < events.size(); i++) {
				//Aqui accederiamos a la base de datos para cambiar en cada iteracion las variables
				String Url = events.get(i).getImage();
				String Titulo = events.get(i).getName();
				String link = "/event/" + Titulo;			
				//Copiamos el div que queremos poner en el documento html en una variable auxiliar
				//Le damos formato a la variable auxiliar y la añadimos a la lista
				String aux=String.format(div, Url, link, Titulo);
				list_events.add(aux);			
			}			
			// se transmite la lista a la plantilla
			model.addAttribute("events", list_events);
		}
		
		// se mandan las listas a pantalla
		model.addAttribute("no_lists", no_lists);
		model.addAttribute("n_games", n_games);
		model.addAttribute("n_companies", n_companies);
		model.addAttribute("n_events", n_events);
		
		// se pasan los atributos de la barra de navegacion
		model.addAttribute("registered", usuario.getAttribute("registered"));
		boolean aux = !(Boolean) usuario.getAttribute("registered");
		model.addAttribute("unregistered", aux);
		model.addAttribute("name", usuario.getAttribute("name"));
		model.addAttribute("profile_img",String.format("<img src=\"%s\" class=\"profile_img\">",(String) usuario.getAttribute("icon")));
		
		//Para activar admin
		model.addAttribute("admin", usuario.getAttribute("admin"));
				
		model.addAttribute("alert"," ");
		model.addAttribute("hello", " ");
		model.addAttribute("Titulo", " ");
		model.addAttribute("Cuerpo", " ");	
		
		// atributos del token
		CsrfToken token = (CsrfToken) request.getAttribute("_csrf");
		model.addAttribute("token", token.getToken());
		
		return "search";
	}
	// ----------------------------- FIN BUSQUEDAS ------------------------------------
	
	// ---------------------------- METODOS AUXILIARES --------------------------------
	public String articles () {
		String article = "";
		List<Article> articles = articleRepository.findAll();
		if(articles.size() > 0) {			
			String div ="<div class=\"row\">\r\n" + "<div class=\"col-sm-1 col-md-1\"> </div>	"  +  "<div class=\"card p-3\">\r\n"  + 	"<div class=\"card-wrapper\">\r\n" + 	"                <div class=\"card-img  col-xs-12 col-sm-5 col-md-5\">\r\n" + "                    <img src=\"  %s  \" alt=\"Mobirise\" title=\"\" media-simple=\"true\">\r\n" + "                </div>\r\n" + 	"                <div class=\"card-box col-xs-12 col-sm-4 col-md-4\">\r\n" + 	"                    <h4 class=\"card-title pb-3 mbr-fonts-style display-7\">  %s  </h4>\r\n" + 	"                    <p class=\"mbr-text mbr-fonts-style display-7\">\r\n" + 	"                        %s  <a href=\"  %s  \">   Read more...</a>\r\n" + 	"                    </p>\r\n" + 		"                </div>\r\n" + 		"            </div>\r\n" +   "            </div>\r\n"	+ 	"        </div>";			
			for(int i = 0; i < articles.size(); i++) {
				String Url= articles.get(i).getImage();
				String Titulo = articles.get(i).getTitle();	
				String Head = articles.get(i).getHead();
				String link="/article/" + Titulo;								

				String aux = String.format(div, Url, Titulo, Head, link);			
				article += aux;			
			}	
		}
		return article;
	}
	// ---------------------------- FIN METODOS AUXILIARES ----------------------------	
}
	


