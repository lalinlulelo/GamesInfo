package LosOdiosos3.prueba_servidor;
import LosOdiosos3.prueba_servidor.Entities.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class webController {	
	// ----------------------------- VARIABLES DEL SERVIDOR --------------------------
	// iconos de usuario
	private List<String> icons = Arrays.asList("https://mir-s3-cdn-cf.behance.net/project_modules/disp/bb3a8833850498.56ba69ac33f26.png", "https://mir-s3-cdn-cf.behance.net/project_modules/disp/1bdc9a33850498.56ba69ac2ba5b.png", "https://mir-s3-cdn-cf.behance.net/project_modules/disp/bf6e4a33850498.56ba69ac3064f.png", "https://mir-s3-cdn-cf.behance.net/project_modules/disp/64623a33850498.56ba69ac2a6f7.png", "https://mir-s3-cdn-cf.behance.net/project_modules/disp/e70b1333850498.56ba69ac32ae3.png", "https://mir-s3-cdn-cf.behance.net/project_modules/disp/84c20033850498.56ba69ac290ea.png", "http://blogs.studentlife.utoronto.ca/lifeatuoft/files/2015/02/FullSizeRender.jpg", "https://i.pinimg.com/474x/c3/53/7f/c3537f7ba5a6d09a4621a77046ca926d--soccer-quotes-lineman.jpg");
	// ----------------------------- FIN VARIABLES DEL SERVIDOR ----------------------
	
	// ----------------------------- INYECCIONES -------------------------------------	
	// repositorio de la tabla usuarios
	@Autowired
	private UserRepository userRepository;	
	
	// repositorio de la tabla compañias
	@Autowired
	private CompanyRepository companyRepository;
	
	// repositorio de la tabla eventos
	@Autowired
	private EventRepository eventRepository;
	
	// repositorio de la tabla juegos
	@Autowired
	private GameRepository gameRepository;
	
	// repositorio de la tabla comentarios
	@Autowired
	private CommentRepository commentRepository;
	// ------------------------------ FIN INYECCIONES --------------------------------

	// ----------------------------- PAGINA INICIO -----------------------------------
	@RequestMapping("/")
	public String inicio (Model model, HttpSession usuario) throws ParseException {
		
		// Datos cargados inicialmente
		// Repositorio para usuarios
		userRepository.save(new User("Juan", "123", "20/11/85", "Juan@gmail.com"));
		userRepository.save(new User("Pedro", "456", "15/6/92", "Pedro@hotmail.com"));
		userRepository.save(new User("Guille", "789", "25/2/96", "Guille@hotmail.com"));
		userRepository.save(new User("Sergio", "1011", "4/2/95", "Sergio@hotmail.com"));
		userRepository.save(new User("Agus", "1213", "14/10/96", "Agus@hotmail.com"));
		
		// Repositorio para compnias
		Company Naughty_Dog = new Company("Naughty Dog", "EEUU", "PlayStation fisrt party",1984,"http://www.naughtydog.com","https://www.naughtydog.com");
		companyRepository.save(Naughty_Dog);		
		Company Nintendo = new Company("Nintendo", "Japon", "Nintendo Company", 1889, "https://www.nintendo.es/", "https://www.nintendo.es/");
		companyRepository.save(Nintendo);

		// Repositorio para juegos
		Game TLOU = new Game("The last of us", Naughty_Dog, "survival horror", "Good game", 2013, new Date(2018, 2, 1), 9.5,
				"https://en.wikipedia.org/wiki/The_Last_of_Us","http://www.thelastofus.playstation.com/");
		gameRepository.save(TLOU);		
		Game LOZBTW = new Game("Legend of Zelda Breath of Wild", Nintendo, "adventure", "Game of the Year 2017", 2017, new Date(2018, 2, 1), 10,
				"http://polvar.ir/wp-content/uploads/2018/02/Nintendo_Switch_ESRB_cover.jpg", "https://es.wikipedia.org/wiki/The_Legend_of_Zelda:_Breath_of_the_Wild");
		gameRepository.save(LOZBTW);		
		
		// Repositorio para eventos
		eventRepository.save(new Event("E3", "Los Angeles", new Date(2018, 6, 10), 286, "muy chachi", "..." ));
		eventRepository.save(new Event("GameGen", "Madrid", new Date(2018, 2, 21), 0, "a jugar", "..." ));
		eventRepository.save(new Event("GDC", "Berlin", new Date(2018, 4, 26), 100, "ja!", "..." ));
		eventRepository.save(new Event("Fun&Serious", "Bilbao", new Date(2018, 11, 21), 30, "txangurro", "..." ));
		eventRepository.save(new Event("PGW", "Paris", new Date(2018, 1, 30), 18, "croisant", "..." ));
		
		// Variables iniciales del usuario
		usuario.setAttribute("alert", "  ");
		usuario.setAttribute("registered", false);
		
		
		
		// comentarios de prueba de la pagina html
		model.addAttribute("Titulo", "Juegos Nuevos");
		model.addAttribute("Cuerpo", "Proximamente");
		
		// condicionales para mostrar o ocultar contenido		
		model.addAttribute("registered", usuario.getAttribute("registered"));
		boolean aux = !(Boolean) usuario.getAttribute("registered");	
		if(aux == false) {
			model.addAttribute("name", usuario.getAttribute("name"));
		}else {
			model.addAttribute("name", " ");
		}
		model.addAttribute("unregistered", aux);
		
		// deshabilitacion del comando alert que saluda al usuario		
		model.addAttribute("alert", usuario.getAttribute("alert"));
		model.addAttribute("hello", " ");
		
		// se accede a la pagina principal
		return "index";
	}
	
	// si se retorna a inicio
	@RequestMapping("/index")
	public String index (Model model, HttpSession usuario) {						
		// comentarios de prueba de la pagina html
		model.addAttribute("Titulo", "Juegos Nuevos");
		model.addAttribute("Cuerpo", "Proximamente");
		
		// se muestra el link de iniciar/registrar usuario si es false
		model.addAttribute("registered", usuario.getAttribute("registered"));
		boolean aux = !(Boolean) usuario.getAttribute("registered");	
		if(aux == false) {
			model.addAttribute("name", usuario.getAttribute("name"));
		}else {
			model.addAttribute("name", " ");
		}
		model.addAttribute("unregistered", aux);		
		
		// valor 
		model.addAttribute("alert", usuario.getAttribute("alert"));
		model.addAttribute("hello", " ");
		
		// se accede a la pagina principal
		return "index";
	}
	// ----------------------------- FIN PAGINA INICIO -------------------------------
	
	// ----------------------------- REGISTRAR NUEVO USUARIO -------------------------
	@RequestMapping("/new_user")
	public String new_user (Model model) {
		model.addAttribute("alert", " ");
		return "new_user";
	}
	
	@PostMapping(value = "/register")
	public String registrar(Model model, User user, HttpSession usuario) {
		
		// se inicializa el usuario con los datos del formulario
		usuario.setAttribute("name", user.getName());
		usuario.setAttribute("password", user.getPassword());
		usuario.setAttribute("date", user.getDate());
		usuario.setAttribute("icon",  icons.get((int)Math.random()*6));
		
		// se comprueba la existencia del nombre
		List<User> usur = userRepository.findByName(user.getName());
		if(usur.size() > 0) {
			System.out.println(usur.get(0).toString());
			model.addAttribute("alert", "<script type=\"text/javascript\">" + "alert('nombre ya existente');" + "window.location = 'new_user.html'; " + "</script>");		
			return "new_user";
		}
		
		// se da por registrado al usuario
		userRepository.save(new User(user.getName(), user.getPassword(), user.getDate(), user.getEmail()));
		model.addAttribute("Titulo", "Juegos Nuevos");
		model.addAttribute("Cuerpo", "Proximamente");
		model.addAttribute("alert", " ");
		
		// se muestra el link de iniciar/registrar usuario si es false
		model.addAttribute("registered", usuario.getAttribute("registered"));
		boolean aux = !(Boolean) usuario.getAttribute("registered");
		if(aux == false) {
			model.addAttribute("name", usuario.getAttribute("name"));
		}else {
			model.addAttribute("name", " ");
		}
		model.addAttribute("unregistered", aux);
		
		return "index";
	}
	// ----------------------------- FIN REGISTRAR NUEVO USUARIO ---------------------
	
	// ----------------------------- INICIO DE SESION --------------------------------
	@RequestMapping("/login")
	public String iniciar_sesion (Model model, User user, HttpSession usuario) {
		// recopilamos la lista de usuarios que contienen el nombre
		List<User> usur = userRepository.findByName(user.getName());
		
		// si la lista no esta vacía, la recorro comparando la contraseña introducida,
		// con las disponibles
		if(usur.size() > 0) {
			for(int i = 0; i < usur.size(); i++) {
				if(user.getPassword().equals(usur.get(i).getPassword())) {
					// se registra el usuario
					usuario.setAttribute("registered", true);
					usuario.setAttribute("name", usur.get(i).getName());
					usuario.setAttribute("password", usur.get(i).getPassword());
					usuario.setAttribute("date", usur.get(i).getDate());
					
					// se deshabilita el alert
					model.addAttribute("alert", "  ");	
					model.addAttribute("Titulo", "Juegos Nuevos");
					model.addAttribute("Cuerpo", "Proximamente");
					
					// se muestra el link de iniciar/registrar usuario si es false
					model.addAttribute("registered", usuario.getAttribute("registered"));
					boolean aux = !(Boolean) usuario.getAttribute("registered");
					model.addAttribute("unregistered", aux);
					model.addAttribute("name", usuario.getAttribute("name"));
					model.addAttribute("hello", "<script type=\"text/javascript\">" + "alert('welcome " + usuario.getAttribute("name") + "!');"  + "</script>");

					return "index";
				}
			}
		}
		
		// se guardan los atributos en el modelo
		model.addAttribute("Titulo", "Juegos Nuevos");
		model.addAttribute("Cuerpo", "Proximamente");
		model.addAttribute("alert", "<script type=\"text/javascript\">" + "alert('User or password incorrect');" + "window.location = '/'; " + "</script>");		
		model.addAttribute("name", " ");
		// se dirige a la pagina como iniciado
		return "index";
	}
	// ----------------------------- FIN INICIO DE SESION ----------------------------
	
	// ----------------------------- PERFIL DE USUARIO -------------------------------
	// no disponible por ahora
	@RequestMapping("/profile")
	public String init (Model model, HttpSession usuario) {		
		// se cogen del usuario los atributos
		String name = (String) usuario.getAttribute("name");
		String password = (String) usuario.getAttribute("password");
		String date = (String) usuario.getAttribute("date");
		String icon = (String) usuario.getAttribute("icon");
		
		// se mandan los datos al modelo
		model.addAttribute("name", name);
		model.addAttribute("password", password);
		model.addAttribute("date", date);	
		model.addAttribute("icon", icon);
		
		// se muestra el link de iniciar/registrar usuario si es false
		model.addAttribute("registered", usuario.getAttribute("registered"));
		boolean aux = !(Boolean) usuario.getAttribute("registered");
		model.addAttribute("unregistered", aux);
		model.addAttribute("name", usuario.getAttribute("name"));
		model.addAttribute("lists", " ");
		
		// se devuelve el nombre de la lista, siendo el PERFIL del usuario
		return "profile";
	}
	// ----------------------------- FIN PERFIL DE USUARIO ---------------------------
	
	// ----------------------------- JUEGO -------------------------------------------
	@RequestMapping("/game")
	public String Game (Model model, HttpSession usuario) {		
		List <Game> games = gameRepository.findByName("Legend of Zelda Breath of Wild");
		System.out.println(games.get(0).toString());
		
		String name = games.get(0).getName();
		Company company = games.get(0).getCompany();
		String genre = games.get(0).getGenre();
		String description = games.get(0).getDescription();
		int year = games.get(0).getYear();
		Date addGame = games.get(0).getAddGame();
		int pts = (int)games.get(0).getScore();
		String image = games.get(0).getImage();
		String url = games.get(0).getUrl();
		
		String score = "";
		switch(pts/2) {
			case 0:
				score = "<span class=\"fa fa-star\"></span>" + "<span class=\"fa fa-star\"></span>" + "<span class=\"fa fa-star\"></span>" + "<span class=\"fa fa-star\"></span>" + "<span class=\"fa fa-star\"></span>";
				break;
			case 1:
				score = "<span class=\"fa fa-star checked\"></span>" + "<span class=\"fa fa-star\"></span>" + "<span class=\"fa fa-star\"></span>" + "<span class=\"fa fa-star\"></span>" + "<span class=\"fa fa-star\"></span>";
				break;
			case 2:
				score = "<span class=\"fa fa-star checked\"></span>" + "<span class=\"fa fa-star checked\"></span>" + "<span class=\"fa fa-star\"></span>" + "<span class=\"fa fa-star\"></span>" + "<span class=\"fa fa-star\"></span>";
				break;
			case 3:
				score = "<span class=\"fa fa-star checked\"></span>" + "<span class=\"fa fa-star checked\"></span>" + "<span class=\"fa fa-star checked\"></span>" + "<span class=\"fa fa-star\"></span>" + "<span class=\"fa fa-star\"></span>";
				break;
			case 4:
				score = "<span class=\"fa fa-star checked\"></span>" + "<span class=\"fa fa-star checked\"></span>" + "<span class=\"fa fa-star checked\"></span>" + "<span class=\"fa fa-star checked\"></span>" + "<span class=\"fa fa-star\"></span>";
				break;
			case 5:
				System.out.println("etro");
				score = "<span class=\"fa fa-star checked\"></span>" + "<span class=\"fa fa-star checked\"></span>" + "<span class=\"fa fa-star checked\"></span>" + "<span class=\"fa fa-star checked\"></span>" + "<span class=\"fa fa-star checked\"></span>";
				break;
		}
		
		model.addAttribute("name_g", name);
		model.addAttribute("company", company.getName());
		model.addAttribute("genre", genre);
		model.addAttribute("description", description);
		model.addAttribute("year", year);
		model.addAttribute("addGame", addGame.toString());
		model.addAttribute("score", score);
		model.addAttribute("image", image);
		model.addAttribute("url", url);
		/*
		model.addAttribute("name_g", " ");
		model.addAttribute("company", " ");
		model.addAttribute("genre", " ");
		model.addAttribute("description", " ");
		model.addAttribute("year", " ");
		model.addAttribute("addGame", " ");
		model.addAttribute("score", " ");
		model.addAttribute("image", " ");
		model.addAttribute("url", " ");*/
		// se muestra el link de iniciar/registrar usuario si es false
		model.addAttribute("registered", usuario.getAttribute("registered"));
		boolean aux = !(Boolean) usuario.getAttribute("registered");
		model.addAttribute("unregistered", aux);
		model.addAttribute("name", usuario.getAttribute("name"));
		
		return "game";
	}
	// ----------------------------- FIN JUEGO ---------------------------------------
	
	// ----------------------------- COMPAÑIA ----------------------------------------
	@RequestMapping("/company")
	public String company (Model model, HttpSession usuario){
		
		model.addAttribute("TituloP", "Bugisoft");
		model.addAttribute("paragraph", "Juego frances");
		model.addAttribute("Title", "abscdef");
		model.addAttribute("image", "https://www.instant-gaming.com/images/products/1842/271x377/1842.jpg");
		
		// se muestra el link de iniciar/registrar usuario si es false
		model.addAttribute("registered", usuario.getAttribute("registered"));
		boolean aux = !(Boolean) usuario.getAttribute("registered");
		model.addAttribute("unregistered", aux);
		model.addAttribute("name", usuario.getAttribute("name"));
				
		return "company";
	}
	// ----------------------------- FIN COMPAÑIA -------------------------------------
	
	// ----------------------------- EVENTO -------------------------------------------
	@RequestMapping("/event_calendar")
	public String event_calendar (Model model, HttpSession usuario) {
		// se muestra el link de iniciar/registrar usuario si es false
				model.addAttribute("registered", usuario.getAttribute("registered"));
				boolean aux = !(Boolean) usuario.getAttribute("registered");
				model.addAttribute("unregistered", aux);
				model.addAttribute("name", usuario.getAttribute("name"));
				
				return "event_calendar";
	}
	
	@RequestMapping("/event")
	public String event (Model model, HttpSession usuario) {
		
		// se muestra el link de iniciar/registrar usuario si es false
		model.addAttribute("registered", usuario.getAttribute("registered"));
		boolean aux = !(Boolean) usuario.getAttribute("registered");
		model.addAttribute("unregistered", aux);
		model.addAttribute("name", usuario.getAttribute("name"));
		
		return "event";
	}	
	// ---------------------------------- FIN EVENTO ----------------------------------
	
	// ---------------------------------- MY LISTS ------------------------------------
	@RequestMapping("/my_lists")
	public String my_lists (Model model) {		
		return "my_lists";
	}
	// ---------------------------------- FIN MY LISTS --------------------------------

}
