package LosOdiosos3.prueba_servidor;
import LosOdiosos3.prueba_servidor.Entities.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
	//Plantilla para añadir html de las listas
	 
	
	
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
		User Juan = new User("Juan", "123", "20/11/85", "Juan@gmail.com");
		userRepository.save(Juan);
		
		userRepository.save(new User("Pedro", "456", "15/6/92", "Pedro@hotmail.com"));
		userRepository.save(new User("Guille", "789", "25/2/96", "Guille@hotmail.com"));
		userRepository.save(new User("Sergio", "1011", "4/2/95", "Sergio@hotmail.com"));
		userRepository.save(new User("Agus", "1213", "14/10/96", "Agus@hotmail.com"));
		
		// Repositorio para companias
		Company Naughty_Dog = new Company("Naughty Dog", "EEUU", "PlayStation fisrt party",1984,"https://ih1.redbubble.net/image.37514287.0124/sticker,220x200-bg,ffffff-pad,220x200,ffffff.u2.jpg","https://www.naughtydog.com");
		companyRepository.save(Naughty_Dog);		
		Company Nintendo = new Company("Nintendo", "Japon", "Nintendo Company", 1889, "https://www.nintendo.com/images/social/fb-400x400.jpg", "https://www.nintendo.es/");
		companyRepository.save(Nintendo);
		Company Activision = new Company("Activision", "EEUU", "AAA Company", 1979, "https://static.blog.playstation.com/wp-content/uploads/avatars//tvj-D5G4_400x400.jpg?m=1475608799", "https://www.activision.com/es/home");
		companyRepository.save(Activision);
		Company Platinum_Games = new Company("Platinum Games", "Japan", "Witches Company", 2007, "https://pbs.twimg.com/profile_images/474105828337676288/IhP1K1eG_400x400.png", "https://www.platinumgames.com/");
		companyRepository.save(Platinum_Games);
		Company Square_Enix = new Company("Square Enix", "Japan", "Only make FF", 1975, "https://na.square-enix.com/sites/default/files/imagecache/post-image/image_gallery/587/6477ee99579631b75080a480f63952e8.jpg", "http://www.square-enix.com/");
		companyRepository.save(Square_Enix);
		
		// Repositorio para juegos
		Game TLOU = new Game("The last of us", Naughty_Dog, "survival horror", "Good game", 2013, new Date(2018, 2, 1), 9.5,
				"https://media.vandal.net/m/23887/the-last-of-us-remastered-201449185610_1.jpg","http://www.thelastofus.playstation.com/");
		gameRepository.save(TLOU);		
		Game LOZBTW = new Game("Legend of Zelda Breath of Wild", Nintendo, "adventure", "Game of the Year 2017", 2017, new Date(2018, 2, 1), 10,
				"http://polvar.ir/wp-content/uploads/2018/02/Nintendo_Switch_ESRB_cover.jpg", "https://es.wikipedia.org/wiki/The_Legend_of_Zelda:_Breath_of_the_Wild");
		gameRepository.save(LOZBTW);
		Game SMO = new Game("Super Mario Odyssey", Nintendo, "platform", "Great Mario Game", 2017, new Date(2018, 2, 4), 9.5,
				"https://www.virginmegastore.ae/medias/sys_master/root/h9f/h76/9104301326366/Super-Mario-Odyssey-375469-Detail.png", "https://www.nintendo.com/games/detail/super-mario-odyssey-switch");
		gameRepository.save(SMO);
		Game CB = new Game("Crash Bandicoot N Sane Trilogy", Activision, "platform", "Hard Game", 2017, new Date(2018, 2, 3), 8, 
				"http://www.eliteguias.com/img/caratulas/_og_/playstation4/crash-bandicoot-n-sane-trilogy.jpg", "https://es.wikipedia.org/wiki/Crash_Bandicoot");	
		gameRepository.save(CB);
		Game BYNT = new Game("Bayonetta 2", Platinum_Games, "Hack n Slash", "witch hunting angels", 2018, new Date(2018, 2, 5), 8.5, 
				"https://gocdkeys.com/images/games/bayonetta-2-nintendo-switch.jpg", "https://www.nintendo.es/Juegos/Nintendo-Switch/Bayonetta-2-1313750.html");
		gameRepository.save(BYNT);
		
		// Repositorio para eventos
		eventRepository.save(new Event("E3", "Los Angeles", new Date(2018, 6, 10), 286, "muy chachi", "..." ));
		eventRepository.save(new Event("GameGen", "Madrid", new Date(2018, 2, 21), 0, "a jugar", "..." ));
		eventRepository.save(new Event("GDC", "Berlin", new Date(2018, 4, 26), 100, "ja!", "..." ));
		eventRepository.save(new Event("Fun&Serious", "Bilbao", new Date(2018, 11, 21), 30, "txangurro", "..." ));
		eventRepository.save(new Event("PGW", "Paris", new Date(2018, 1, 30), 18, "croisant", "..." ));
		
		//Guardar juegos para un usuario
		ArrayList<Game> ninGameList = new ArrayList<Game>();
		ninGameList.add(SMO);
		ninGameList.add(LOZBTW);
		Juan.addList(ninGameList);
		
		
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

		// se muestra el link de iniciar/registrar usuario si es false
		model.addAttribute("registered", usuario.getAttribute("registered"));
		boolean aux = !(Boolean) usuario.getAttribute("registered");
		model.addAttribute("unregistered", aux);
		model.addAttribute("name", usuario.getAttribute("name"));
		
		return "game";
	}
	
	@RequestMapping("/game_list")
	public String game_list (Model model, HttpSession usuario) {
		
		List<String> list=new ArrayList<String>();
		
		String div="<div class=\"col-md-3\">\r\n" + 
				"    <div class=\"Game\">\r\n" + 
				"<img src=\"%s\" class=\"imagen\">\r\n" + 
				"      <p style=\"text-align:center; color:  rgb(33, 73, 138);\">%s</p>\r\n" + 
				"     \r\n" + 
				"    </div>\r\n" + 
				"  </div>";
		
		
		List<Game> list_games=gameRepository.findAll();
		
		//variable auxiliar
		for(int i=0;i<list_games.size();i++) {
			//Aqui accederiamos a la base de datos para cambiar en cada iteracion las variables
			String Url=list_games.get(i).getImage();
			String Titulo=list_games.get(i).getName();		
			
			//Copiamos el div que queremos poner en el documento html en una variable auxiliar
			
			//Le damos formato a la variable auxiliar y la añadimos a la lista
			String aux=String.format(div, Url,Titulo);
			
			list.add(aux);
			
		}
		
		
		model.addAttribute("games", list);
		
		// se muestra el link de iniciar/registrar usuario si es false
		model.addAttribute("registered", usuario.getAttribute("registered"));
		boolean aux = !(Boolean) usuario.getAttribute("registered");
		model.addAttribute("unregistered", aux);
		model.addAttribute("name", usuario.getAttribute("name"));
		
		return "game_list";
	}
	// ----------------------------- FIN JUEGO ---------------------------------------
	
	// ----------------------------- COMPAÑIA ----------------------------------------
	@RequestMapping("/company")
	public String company (Model model, HttpSession usuario){
		List <Company> companies = companyRepository.findByName("Nintendo");
		
		String name = companies.get(0).getName();
		String country = companies.get(0).getCountry();
		String description = companies.get(0).getDescription();
		int year = companies.get(0).getYear();
		String image = companies.get(0).getImage();
		String url = companies.get(0).getUrl();
		
		model.addAttribute("name_g", name);
		model.addAttribute("country", country);
		model.addAttribute("description", description);
		model.addAttribute("year", year);
		model.addAttribute("image", image);
		model.addAttribute("url", url);
		
		// se muestra el link de iniciar/registrar usuario si es false
		model.addAttribute("registered", usuario.getAttribute("registered"));
		boolean aux = !(Boolean) usuario.getAttribute("registered");
		model.addAttribute("unregistered", aux);
		model.addAttribute("name", usuario.getAttribute("name"));
				
		return "company";
	}
	

	@RequestMapping("/company_list")
	public String company_list (Model model, HttpSession usuario) {
		
		List<String> list=new ArrayList<String>();
		
		String div="<div class=\"col-md-3\">\r\n" + 
				"    <div class=\"Game\">\r\n" + 
				"<img src=\"%s\" class=\"imagen\">\r\n" + 
				"      <p style=\"text-align:center;\">%s</p>\r\n" + 
				"     \r\n" + 
				"    </div>\r\n" + 
				"  </div>";
		
		
		List<Company> list_company=companyRepository.findAll();
		
		//variable auxiliar
		for(int i=0;i<list_company.size();i++) {
			//Aqui accederiamos a la base de datos para cambiar en cada iteracion las variables
			String Url=list_company.get(i).getImage();
			String Titulo=list_company.get(i).getName();		
			
			//Copiamos el div que queremos poner en el documento html en una variable auxiliar
			
			//Le damos formato a la variable auxiliar y la añadimos a la lista
			String aux=String.format(div, Url,Titulo);
			
			list.add(aux);
			
		}
		
		
		model.addAttribute("company", list);
		
		// se muestra el link de iniciar/registrar usuario si es false
				model.addAttribute("registered", usuario.getAttribute("registered"));
				boolean aux = !(Boolean) usuario.getAttribute("registered");
				model.addAttribute("unregistered", aux);
				model.addAttribute("name", usuario.getAttribute("name"));
				
		
		return "company_list";
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
	public String my_lists (Model model, HttpSession usuario) {
		
		List <User> users = userRepository.findByName("Juan");
		
		//Accedo a la lista de lista de juego
		ArrayList<ArrayList<Game>> lists = users.get(0).getMyLists();
		//Accedo a la sublista de juegos
		ArrayList<Game> ninList = lists.get(0);
		
		String name = ninList.get(0).getName();
		Company company = ninList.get(0).getCompany();
		String genre = ninList.get(0).getGenre();
		String description = ninList.get(0).getDescription();
		int year = ninList.get(0).getYear();
		Date addGame = ninList.get(0).getAddGame();
		int pts = (int)ninList.get(0).getScore();
		String image = ninList.get(0).getImage();
		String url = ninList.get(0).getUrl();
		
		
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
		
		model.addAttribute("lists", lists);
		
		model.addAttribute("name_g", name);
		model.addAttribute("company", company.getName());
		model.addAttribute("genre", genre);
		model.addAttribute("description", description);
		model.addAttribute("year", year);
		model.addAttribute("addGame", addGame.toString());
		model.addAttribute("score", score);
		model.addAttribute("image", image);
		model.addAttribute("url", url);
		
		// se muestra el link de iniciar/registrar usuario si es false
		model.addAttribute("registered", usuario.getAttribute("registered"));
		boolean aux = !(Boolean) usuario.getAttribute("registered");
		model.addAttribute("unregistered", aux);
		model.addAttribute("name", usuario.getAttribute("name"));
		
		return "my_lists";
	}
	// ---------------------------------- FIN MY LISTS --------------------------------

}
