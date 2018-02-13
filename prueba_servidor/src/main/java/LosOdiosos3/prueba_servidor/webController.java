package LosOdiosos3.prueba_servidor;
import LosOdiosos3.prueba_servidor.Entities.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class webController {	
	// ----------------------------- VARIABLES DEL SERVIDOR --------------------------
	// iconos de usuario
	private List<String> icons = Arrays.asList("https://mir-s3-cdn-cf.behance.net/project_modules/disp/bb3a8833850498.56ba69ac33f26.png", "https://mir-s3-cdn-cf.behance.net/project_modules/disp/1bdc9a33850498.56ba69ac2ba5b.png", "https://mir-s3-cdn-cf.behance.net/project_modules/disp/bf6e4a33850498.56ba69ac3064f.png", "https://mir-s3-cdn-cf.behance.net/project_modules/disp/64623a33850498.56ba69ac2a6f7.png", "https://mir-s3-cdn-cf.behance.net/project_modules/disp/e70b1333850498.56ba69ac32ae3.png", "https://mir-s3-cdn-cf.behance.net/project_modules/disp/84c20033850498.56ba69ac290ea.png", "http://blogs.studentlife.utoronto.ca/lifeatuoft/files/2015/02/FullSizeRender.jpg", "https://i.pinimg.com/474x/c3/53/7f/c3537f7ba5a6d09a4621a77046ca926d--soccer-quotes-lineman.jpg");
	// variable de inicio de controlador
	boolean comienzo = false;
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
		if(comienzo == false) {
			// Datos de la Base de Datos cargados inicialmente
			// usuarios
			User Juan = new User("Juan", "123", "20/11/85", "Juan@gmail.com");
			Juan.setIcon(icons.get(3));
			userRepository.save(Juan);
			User Pedro = new User("Pedro", "456", "15/6/92", "Pedro@hotmail.com");
			Pedro.setIcon(icons.get((int)Math.random()*6));
			userRepository.save(Pedro);
			User Guille = new User("Guille", "789", "25/2/96", "Guille@hotmail.com");
			Guille.setIcon(icons.get((int)Math.random()*6));
			userRepository.save(Guille);
			User Sergio = new User("Sergio", "1011", "4/2/95", "Sergio@hotmail.com");
			Sergio.setIcon(icons.get((int)Math.random()*6));
			userRepository.save(Sergio);
			User Agus = new User("Agus", "1213", "14/10/96", "Agus@hotmail.com");
			Agus.setIcon(icons.get((int)Math.random()*6));
			userRepository.save(Agus);
			
			// compañias
			Company Naughty_Dog = new Company("Naughty Dog", "EEUU", "PlayStation fisrt party",1984,"https://ih1.redbubble.net/image.37514287.0124/sticker,220x200-bg,ffffff-pad,220x200,ffffff.u2.jpg","https://www.naughtydog.com");
			companyRepository.save(Naughty_Dog);		
			Company Nintendo = new Company("Nintendo", "Japan", "Nintendo Company", 1889, "https://www.nintendo.com/images/social/fb-400x400.jpg", "https://www.nintendo.es/");
			companyRepository.save(Nintendo);
			Company Activision = new Company("Activision", "EEUU", "AAA Company", 1979, "https://static.blog.playstation.com/wp-content/uploads/avatars//tvj-D5G4_400x400.jpg?m=1475608799", "https://www.activision.com/es/home");
			companyRepository.save(Activision);
			Company Platinum_Games = new Company("Platinum Games", "Japan", "Witches Company", 2007, "https://pbs.twimg.com/profile_images/474105828337676288/IhP1K1eG_400x400.png", "https://www.platinumgames.com/");
			companyRepository.save(Platinum_Games);
			Company Square_Enix = new Company("Square Enix", "Japan", "Only make FF", 1975, "https://na.square-enix.com/sites/default/files/imagecache/post-image/image_gallery/587/6477ee99579631b75080a480f63952e8.jpg", "http://www.square-enix.com/");
			companyRepository.save(Square_Enix);
			Company Santa_Monica = new Company("Santa Monica", "EEUU", "Only God of War", 1999, "https://static.giantbomb.com/uploads/original/0/1992/1292491-sce_santa_monica_logo.png", "http://sms.playstation.com/");
			companyRepository.save(Santa_Monica);
			Company Epic_Games = new Company("Epic Games", "EEUU", "Caotic", 2003, "https://upload.wikimedia.org/wikipedia/commons/thumb/3/31/Epic_Games_logo.svg/1200px-Epic_Games_logo.svg.png", "https://es.wikipedia.org/wiki/Epic_Games");
			companyRepository.save(Epic_Games);
			
			// juegos
			Game TLOU = new Game("The last of us", Naughty_Dog, "survival horror", "Good game", 2013, 9.5, "https://media.vandal.net/m/23887/the-last-of-us-remastered-201449185610_1.jpg","http://www.thelastofus.playstation.com/");
			gameRepository.save(TLOU);		
			Game LOZBTW = new Game("Legend of Zelda Breath of Wild", Nintendo, "adventure", "Game of the Year 2017", 2017, 10, "http://polvar.ir/wp-content/uploads/2018/02/Nintendo_Switch_ESRB_cover.jpg", "https://es.wikipedia.org/wiki/The_Legend_of_Zelda:_Breath_of_the_Wild");
			gameRepository.save(LOZBTW);
			Game SMO = new Game("Super Mario Odyssey", Nintendo, "platform", "Great Mario Game", 2017, 9.5,	"https://www.virginmegastore.ae/medias/sys_master/root/h9f/h76/9104301326366/Super-Mario-Odyssey-375469-Detail.png", "https://www.nintendo.com/games/detail/super-mario-odyssey-switch");
			gameRepository.save(SMO);
			Game CB = new Game("Crash Bandicoot N Sane Trilogy", Activision, "platform", "Hard Game", 2017, 8, "http://www.eliteguias.com/img/caratulas/_og_/playstation4/crash-bandicoot-n-sane-trilogy.jpg", "https://es.wikipedia.org/wiki/Crash_Bandicoot");	
			gameRepository.save(CB);
			Game BYNT = new Game("Bayonetta 2", Platinum_Games, "Hack n Slash", "witch hunting angels", 2017, 8.5, "https://gocdkeys.com/images/games/bayonetta-2-nintendo-switch.jpg", "https://www.nintendo.es/Juegos/Nintendo-Switch/Bayonetta-2-1313750.html");
			gameRepository.save(BYNT);
			Game TMBR = new Game("Rise of the Tomb Raider", Square_Enix, "adventure", "Tomb Raider Aniversary", 2016, 8, "https://images-na.ssl-images-amazon.com/images/I/51Hyk3IIfwL.jpg", "https://es.wikipedia.org/wiki/Rise_of_the_Tomb_Raider");
			gameRepository.save(TMBR);
			Game TLOU2 = new Game("The last of us 2", Naughty_Dog, "survival horror", "Good game", 2018, 9.5, "https://images-na.ssl-images-amazon.com/images/I/511VhhJg%2BbL.jpg","https://www.playstation.com/es-es/games/the-last-of-us-part-ii-ps4/");
			gameRepository.save(TLOU2);
			Game GOW = new Game("God of War", Santa_Monica, "adventure", "Human vs Gods", 2018, 9.5, "https://i11d.3djuegos.com/juegos/11552/god_of_war_ps4__nombre_temporal_/fotos/ficha/god_of_war_ps4__nombre_temporal_-3754795.jpg", "https://www.playstation.com/es-es/games/god-of-war-ps4/");
			gameRepository.save(GOW);
			Game GOWX = new Game("Gears of War", Epic_Games, "adventure", "apocalipsis", 2016, 7, "https://i11c.3djuegos.com/juegos/1444/gears_of_war/fotos/ficha/gears_of_war-1681066.jpg", "https://es.wikipedia.org/wiki/Gears_of_War");
			gameRepository.save(GOWX);
			
			// eventos
			Event E3 = new Event("E3", "Los Angeles", 2018, 6, 15, 286, "muy chachi", "http://media.comicbook.com/2018/02/e3-2018-1080845.jpeg" );
			eventRepository.save(E3);
			Event GameGen = new Event("GameGen", "Madrid", 2018, 2, 22, 0, "a jugar", "https://upload.ticketing.events/event-logo/event-logo-2373.png");
			eventRepository.save(GameGen);
			Event GDC = new Event("GDC", "Berlin", 2018, 4, 15, 100, "ja!", "http://www.gdconf.com/img/fb.png");
			eventRepository.save(GDC);
			Event FS = new Event("Fun&Serious", "Bilbao", 2018, 11, 21, 30, "txangurro", "https://www.republica.com/deportes-electronicos/wp-content/uploads/sites/48/2016/11/logo-funserious-fondo-transparente.png");
			eventRepository.save(FS);
			Event PGW = new Event("PGW", "Paris", 2018, 1, 30, 18, "croisant", "http://www.nintenderos.com/wp-content/uploads/2016/09/Paris-Games-Week.jpg");
			eventRepository.save(PGW);
			
			// listas de juegos
			Juan.addGame(TLOU);
			Juan.addGame(LOZBTW);
			userRepository.save(Juan);
			Pedro.addGame(CB);
			Pedro.addGame(SMO);
			userRepository.save(Pedro);
			TLOU.addUser(Juan);
			LOZBTW.addUser(Juan);
			CB.addUser(Pedro);
			SMO.addUser(Pedro);
			gameRepository.save(TLOU);
			gameRepository.save(LOZBTW);
			gameRepository.save(SMO);
			gameRepository.save(CB);
			
			// Variables iniciales del usuario
			usuario.setAttribute("alert", "  ");
			
			// se fija el no retorno por esta fase
			comienzo = true;
		}		
		if(usuario.getAttribute("registered") == null) {
			usuario.setAttribute("registered", false);
		}
		// comentarios de abajo del html
		model.addAttribute("Titulo", "Juegos Nuevos");
		model.addAttribute("Cuerpo", "Proximamente");
		
		// condicionales para mostrar u ocultar contenido		
		model.addAttribute("registered", usuario.getAttribute("registered"));
		
		boolean aux = !(Boolean) usuario.getAttribute("registered");	
		if(aux == false) {
			model.addAttribute("name", usuario.getAttribute("name"));
			model.addAttribute("profile_img",String.format("<img src=\"%s\" class=\"profile_img\">",(String) usuario.getAttribute("icon")));

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
		usuario.setAttribute("email", user.getEmail());
		usuario.setAttribute("icon",  user.getIcon());
		
		List<User> usur = null;
		// se comprueba la existencia del nombre
		usur = userRepository.findByName(user.getName());
		if(usur.size() > 0) {
			model.addAttribute("alert", "<script type=\"text/javascript\">" + "alert('name alredy getted');" + "window.location = 'new_user.html'; " + "</script>");		
			return "new_user";
		}
		usur = userRepository.findByEmail(user.getEmail());
		if(usur.size() > 0) {
			model.addAttribute("alert", "<script type=\"text/javascript\">" + "alert('email alredy getted');" + "window.location = 'new_user.html'; " + "</script>");		
			return "new_user";
		}
		
		// se da por registrado al usuario
		userRepository.save(new User(user.getName(), user.getPassword(), user.getDate(), user.getEmail()));
		model.addAttribute("Titulo", "Juegos Nuevos");
		model.addAttribute("Cuerpo", "Proximamente");
		model.addAttribute("alert", " ");
		model.addAttribute("profile_img",String.format("<img src=\"%s\" class=\"profile_img\">",(String) usuario.getAttribute("icon")));

		
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
					usuario.setAttribute("icon", usur.get(i).getIcon());
					usuario.setAttribute("email", usur.get(i).getEmail());
					
					// se guarda un objeto User
					User newUser=userRepository.findByName((String)usuario.getAttribute("name")).get(0);
					usuario.setAttribute("Usuario", newUser);
					
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
					model.addAttribute("profile_img",String.format("<img src=\"%s\" class=\"profile_img\">",(String) usuario.getAttribute("icon")));
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
	
	@RequestMapping("/log_out")
	public String log_out (Model model, HttpSession usuario) {
		usuario.setAttribute("registered", false);
		// se guardan los atributos en el modelo
		model.addAttribute("Titulo", "Juegos Nuevos");
		model.addAttribute("Cuerpo", "Proximamente");
		model.addAttribute("alert", "Good Bye");		
		model.addAttribute("name", " ");
		
		return "index";
	}
	// ----------------------------- FIN INICIO DE SESION ----------------------------
	
	// ----------------------------- PERFIL DE USUARIO -------------------------------
	@RequestMapping("/profile")
	public String init (Model model, HttpSession usuario) {		
		
		// se cogen del usuario los atributos
		String name = (String) usuario.getAttribute("name");
		String password = (String) usuario.getAttribute("password");
		String date = (String) usuario.getAttribute("date");
		String icon = (String) usuario.getAttribute("icon");
		String email = (String) usuario.getAttribute("email");
		
		// se mandan los datos al modelo
		model.addAttribute("name", name);
		model.addAttribute("password", password);
		model.addAttribute("date", date);	
		model.addAttribute("icon", icon);
		model.addAttribute("email", email);
		
		// se muestra el link de iniciar/registrar usuario si es false
		model.addAttribute("registered", usuario.getAttribute("registered"));
		boolean aux = !(Boolean) usuario.getAttribute("registered");
		model.addAttribute("unregistered", aux);
		model.addAttribute("name", usuario.getAttribute("name"));
		model.addAttribute("profile_img",String.format("<img src=\"%s\" class=\"profile_img\">",(String) usuario.getAttribute("icon")));

		model.addAttribute("lists", " ");
		
		// se devuelve el nombre de la lista, siendo el PERFIL del usuario
		return "profile";
	}
	// ----------------------------- FIN PERFIL DE USUARIO ---------------------------
	
	// ----------------------------- JUEGO -------------------------------------------
	@RequestMapping("/game/{game_name}")
	public String Game (Model model, HttpSession usuario, @PathVariable String game_name) {
		// se coge de la BBDD la lista de juegos con dicho nombre recibido por url
		List <Game> games = gameRepository.findByName(game_name);		
		
		// se adquieren los atributos del juego
		String name = games.get(0).getName();
		Company company = games.get(0).getCompany();
		String genre = games.get(0).getGenre();
		String description = games.get(0).getDescription();
		int year = games.get(0).getYear();
		int pts = (int)games.get(0).getScore();
		String image = games.get(0).getImage();
		String url = games.get(0).getUrl();
		
		// se carga la puntuacion
		String score = "";
		switch(pts/2) {
			case 0: score = "<span class=\"fa fa-star\"></span>" + "<span class=\"fa fa-star\"></span>" + "<span class=\"fa fa-star\"></span>" + "<span class=\"fa fa-star\"></span>" + "<span class=\"fa fa-star\"></span>"; break;
			case 1:	score = "<span class=\"fa fa-star checked\"></span>" + "<span class=\"fa fa-star\"></span>" + "<span class=\"fa fa-star\"></span>" + "<span class=\"fa fa-star\"></span>" + "<span class=\"fa fa-star\"></span>"; break;
			case 2: score = "<span class=\"fa fa-star checked\"></span>" + "<span class=\"fa fa-star checked\"></span>" + "<span class=\"fa fa-star\"></span>" + "<span class=\"fa fa-star\"></span>" + "<span class=\"fa fa-star\"></span>"; break;
			case 3:	score = "<span class=\"fa fa-star checked\"></span>" + "<span class=\"fa fa-star checked\"></span>" + "<span class=\"fa fa-star checked\"></span>" + "<span class=\"fa fa-star\"></span>" + "<span class=\"fa fa-star\"></span>"; break;
			case 4:	score = "<span class=\"fa fa-star checked\"></span>" + "<span class=\"fa fa-star checked\"></span>" + "<span class=\"fa fa-star checked\"></span>" + "<span class=\"fa fa-star checked\"></span>" + "<span class=\"fa fa-star\"></span>"; break;
			case 5:	score = "<span class=\"fa fa-star checked\"></span>" + "<span class=\"fa fa-star checked\"></span>" + "<span class=\"fa fa-star checked\"></span>" + "<span class=\"fa fa-star checked\"></span>" + "<span class=\"fa fa-star checked\"></span>"; break;
		}
		
		// gestion de commentarios del juego
		List<String> list=new ArrayList<String>();
		String div="<div class=\"com\"><div class=\"commentsUser \"><img class=\"comment_img\" src=\"%s\"></img>%s</div><div class=\"Date\">%s</div></div>\r\n" +  "     <div class=\"comments \">%s</div>"	+ "</div><br>";
		
		// si hay comentarios en el juego
		if(games.get(0).getComment().size()>0) {
			List<Comment> list_comments=games.get(0).getComment();			

			for(int i=0;i<list_comments.size();i++) {
				//Aqui accederiamos a la base de datos para cambiar en cada iteracion las variables
				String User=list_comments.get(i).getUser().getName();
				String Text=list_comments.get(i).getText();						
				Date d=list_comments.get(i).getDate();	
				String img=list_comments.get(i).getUser().getIcon();
				//Copiamos el div que queremos poner en el documento html en una variable auxiliar
				//Le damos formato a la variable auxiliar y la añadimos a la lista
				String aux=String.format(div, img,User,d.toString(), Text);				
				list.add(aux);				
			}
			Collections.reverse(list);
			model.addAttribute("comments", list);
		}else {
			model.addAttribute("comments"," ");
		}
		
		// se pasan los datos a la plantilla
		model.addAttribute("name_g", name);
		model.addAttribute("company", company.getName());
		model.addAttribute("genre", genre);
		model.addAttribute("description", description);
		model.addAttribute("year", year);
		model.addAttribute("score", score);
		model.addAttribute("image", image);
		model.addAttribute("url", url);

		// datos referentes a la barra de navegacion
		model.addAttribute("registered", usuario.getAttribute("registered"));
		boolean aux = !(Boolean) usuario.getAttribute("registered");
		model.addAttribute("unregistered", aux);
		model.addAttribute("name", usuario.getAttribute("name"));
		model.addAttribute("profile_img",String.format("<img src=\"%s\" class=\"profile_img\">",(String) usuario.getAttribute("icon")));
		
		return "game";
	}
	// ----------------------------- FIN JUEGO ---------------------------------------
	
	// ----------------------------- LISTA DE JUEGOS ---------------------------------	
	@RequestMapping("/game_list/{modo}")
	public String game_list (Model model, HttpSession usuario, @PathVariable String modo) {
		List<String> list=new ArrayList<String>();		
		String div="<div class=\"col-md-3\">\r\n" + "<div class=\"Game\">\r\n" + "<img src=\"%s\" class=\"imagen\">\r\n" + "      <a href=\"%s\" style=\"text-align:center;display:block; color:  rgb(33, 73, 138);\">%s</a>\r\n" + "     \r\n" + "    </div>\r\n" + "  </div>";
		List<Game> list_games_2 = gameRepository.findAll();
		//List<Game> list_games = gameRepository.findByNameContainingOrderByScoreAsc("");
		
		List<Game> list_games = null;
		switch(modo) {
			case "n":
				list_games = gameRepository.findAll();
				break;
			case "score_up":
				list_games = gameRepository.findByScoreAsc();
				break;
			case "score_down":
				list_games = gameRepository.findByScoreDesc();
				break;
			case "letter_up":
				list_games = gameRepository.findByNameAsc();
				break;
			case "letter_down":
				list_games = gameRepository.findByNameDesc();
				break;
			case "year_up":
				list_games = gameRepository.findByYearDesc();
				break;
			case "year_down":
				list_games = gameRepository.findByYearAsc();
				break;
			case "companies":
				list_games = gameRepository.findByCompanyAsc();
				break;
			
		}
		for(int i=0;i<list_games.size();i++) {
			//Aqui accederiamos a la base de datos para cambiar en cada iteracion las variables
			String Url=list_games.get(i).getImage();
			String Titulo=list_games.get(i).getName();		
			String link="/game/" + Titulo;
			//Copiamos el div que queremos poner en el documento html en una variable auxiliar
			//Le damos formato a la variable auxiliar y la añadimos a la lista
			String aux=String.format(div, Url, link, Titulo);			
			list.add(aux);			
		}		
		
		// se pasa la lista de juegos a la plantilla
		model.addAttribute("games", list);
		
		// se muestra el link de iniciar/registrar usuario si es false
		model.addAttribute("registered", usuario.getAttribute("registered"));
		boolean aux = !(Boolean) usuario.getAttribute("registered");
		model.addAttribute("unregistered", aux);
		model.addAttribute("name", usuario.getAttribute("name"));
		model.addAttribute("profile_img",String.format("<img src=\"%s\" class=\"profile_img\">",(String) usuario.getAttribute("icon")));

		
		return "game_list";
	}
	// ----------------------------- FIN LISTA DE JUEGOS -----------------------------
	
	// ----------------------------- COMPAÑIA ----------------------------------------
	@RequestMapping("/company/{company_name}")
	public String company (Model model, HttpSession usuario, @PathVariable String company_name){
				
		// se adquiere la lista de juegos que contiene el nombre
		List <Company> companies = companyRepository.findByName(company_name);
		
		// se adquieren sus atributos
		String name = companies.get(0).getName();
		String country = companies.get(0).getCountry();
		String description = companies.get(0).getDescription();
		int year = companies.get(0).getYear();
		String image = companies.get(0).getImage();
		String url = companies.get(0).getUrl();		
				
			// gestion de commentarios del juego
			List<String> list=new ArrayList<String>();
			String div="<div class=\"com\"><div class=\"commentsUser \"><img class=\"comment_img\" src=\"%s\"></img>%s</div><div class=\"Date\">%s</div></div>\r\n" +  "     <div class=\"comments \">%s</div>"	+ "</div><br>";
			
			// si hay comentarios en el juego
			if(companies.get(0).getComment().size()>0) {
				List<Comment> list_comments=companies.get(0).getComment();			
					
				for(int i=0;i<list_comments.size();i++) {
					//Aqui accederiamos a la base de datos para cambiar en cada iteracion las variables
					String User=list_comments.get(i).getUser().getName();
					String Text=list_comments.get(i).getText();
					Date d=list_comments.get(i).getDate();	
					String img=list_comments.get(i).getUser().getIcon();
					//Copiamos el div que queremos poner en el documento html en una variable auxiliar
					//Le damos formato a la variable auxiliar y la añadimos a la lista
					String aux=String.format(div, img,User,d.toString(), Text);						
					list.add(aux);				
				}
				Collections.reverse(list);
				model.addAttribute("comments", list);
			}else {
				model.addAttribute("comments"," ");
			}		
		
		// se transmiten los atributos a la plantilla
		model.addAttribute("name_g", name);
		model.addAttribute("country", country);
		model.addAttribute("description", description);
		model.addAttribute("year", year);
		model.addAttribute("image", image);
		model.addAttribute("url", url);
		
		// variables referentes a la barra de navegacion
		model.addAttribute("registered", usuario.getAttribute("registered"));
		boolean aux = !(Boolean) usuario.getAttribute("registered");
		model.addAttribute("unregistered", aux);
		model.addAttribute("name", usuario.getAttribute("name"));
		model.addAttribute("profile_img",String.format("<img src=\"%s\" class=\"profile_img\">",(String) usuario.getAttribute("icon")));

		
		//se accede a compañia
		return "company";
	}
	// ----------------------------- FIN COMPAÑIA -------------------------------------
	
	// ----------------------------- LISTA DE COMPAÑIAS -------------------------------
	@RequestMapping("/company_list/{mode}")
	public String company_list (Model model, HttpSession usuario, @PathVariable String mode) {
		List<String> list=new ArrayList<String>();		
		String div="<div class=\"col-md-3\">\r\n" + "<div class=\"Game\">\r\n" + "<img src=\"%s\" class=\"imagen\">\r\n" + 	"      <a href=\"%s\" style=\"text-align:center;display:block; color:  rgb(33, 73, 138);\">%s</a>\r\n" + "     \r\n" + "    </div>\r\n" + "  </div>";
		
		// lista de todas las compañias disponibles
		List<Company> list_company = null;
		switch(mode) {
			case "n":
				list_company=companyRepository.findAll();
				break;
			case "letter_up":
				list_company = companyRepository.findByNameAsc();
				break;
			case "letter_down":
				list_company = companyRepository.findByNameDesc();
				break;
			case "year_down":
				list_company = companyRepository.findByYearAsc();
				break;
			case "year_up":
				list_company = companyRepository.findByYearDesc();
				break;
		}
		
		for(int i=0;i<list_company.size();i++) {
			//Aqui accederiamos a la base de datos para cambiar en cada iteracion las variables
			String Url=list_company.get(i).getImage();
			String Titulo=list_company.get(i).getName();
			String link="/company/" + Titulo;			
			//Copiamos el div que queremos poner en el documento html en una variable auxiliar
			//Le damos formato a la variable auxiliar y la añadimos a la lista
			String aux=String.format(div, Url, link, Titulo);
			list.add(aux);			
		}
		
		// se transmite la lista a la plantilla
		model.addAttribute("company", list);
		
		// se muestra el link de iniciar/registrar usuario si es false
		model.addAttribute("registered", usuario.getAttribute("registered"));
		boolean aux = !(Boolean) usuario.getAttribute("registered");
		model.addAttribute("unregistered", aux);
		model.addAttribute("name", usuario.getAttribute("name"));
		model.addAttribute("profile_img",String.format("<img src=\"%s\" class=\"profile_img\">",(String) usuario.getAttribute("icon")));

				
		// se accede a la pagina que expone todas las comapañias
		return "company_list";
	}	
	// ----------------------------- FIN LISTA DE COMPAÑIAS -------------------------------------
	
	// ----------------------------- CALENDARIO DE EVENTOS  -------------------------------------
	@RequestMapping("/event_calendar")
	public String event_calendar (Model model, HttpSession usuario) {
		// lista con todos los eventos disponibles
		List<Event> event_list = eventRepository.findAll();
		
		// se crea una cadena cuya funcion será de script
		// para ello mediante JSON disponemos de las fechas a colocar en el calendario
		String events = "var events = [ ";
		String parcial = "";
		for(int i = 0; i < event_list.size(); i++) {			
			if(i != event_list.size() -1 ) {
				parcial += "{'Date': new Date(" + event_list.get(i).getYear() + "," + (event_list.get(i).getMonth() -1) + "," + event_list.get(i).getDay() + "), 'Title': '" + event_list.get(i).getName() + "', 'Link': '" + "/event/" + event_list.get(i).getName() + "'}, ";
			}else {
				parcial += "{'Date': new Date(" +  event_list.get(i).getYear() + "," + (event_list.get(i).getMonth() -1) + "," + event_list.get(i).getDay() + "), 'Title': '" + event_list.get(i).getName() + "', 'Link': '" + "/event/" + event_list.get(i).getName() + "'}];";
			}
		}
		events += parcial;
		// finalmente creamos la cadena que contendrá el script
		String script = "<script>" + events + "var settings = {};\r\n" + 
				"      var element = document.getElementById('caleandar');\r\n" + 
				"      caleandar(element, events, settings);\r\n" + 
				"    </script>";
		model.addAttribute("eventos", script);	
		
		// variables referentes a la barra de navegacion
		model.addAttribute("registered", usuario.getAttribute("registered"));
		boolean aux = !(Boolean) usuario.getAttribute("registered");
		model.addAttribute("unregistered", aux);
		model.addAttribute("name", usuario.getAttribute("name"));
		model.addAttribute("profile_img",String.format("<img src=\"%s\" class=\"profile_img\">",(String) usuario.getAttribute("icon")));

		
		return "event_calendar";
	}
	// ----------------------------- FIN CALENDARIO DE EVENTOS  ----------------------------------
	
	// ----------------------------- EVENTO  -----------------------------------------------------
	@RequestMapping("/event/{event_name}")
	public String event (Model model, HttpSession usuario, @PathVariable String event_name) {
		// lista que contiene los eventos con el nombre introudcido por url
		List<Event> events = eventRepository.findByName(event_name);		
		
		List<String> list=new ArrayList<String>();
		String div="<div class=\"com\"><div class=\"commentsUser \"><img class=\"comment_img\" src=\"%s\"></img>%s</div><div class=\"Date\">%s</div></div>\r\n" +  "     <div class=\"comments \">%s</div>"	+ "</div><br>";
		
		// si hay comentarios en el juego
		if(events.get(0).getComment().size()>0) {
			List<Comment> list_comments=events.get(0).getComment();			
				
			for(int i=0;i<list_comments.size();i++) {
				//Aqui accederiamos a la base de datos para cambiar en cada iteracion las variables
				String User=list_comments.get(i).getUser().getName();
				String Text=list_comments.get(i).getText();						
				Date d=list_comments.get(i).getDate();
				String img=list_comments.get(i).getUser().getIcon();				//Copiamos el div que queremos poner en el documento html en una variable auxiliar
				//Le damos formato a la variable auxiliar y la añadimos a la lista
				String aux=String.format(div, img,User,d.toString(), Text);				
				list.add(aux);				
			}
			Collections.reverse(list);
			model.addAttribute("comments", list);
		}else {
			model.addAttribute("comments"," ");
		}		
		
		// se pasan a plantilla los atributos del evento
		model.addAttribute("name_g", events.get(0).getName());
		model.addAttribute("place", events.get(0).getPlace());
		model.addAttribute("image", events.get(0).getImage());
		model.addAttribute("fee", events.get(0).getFee());
		model.addAttribute("date", events.get(0).getDay() + ", " + events.get(0).getMonth() + ", " + events.get(0).getYear());
		model.addAttribute("description", events.get(0).getDescription());
		
		// variables referentes a la barra de navegacion
		model.addAttribute("registered", usuario.getAttribute("registered"));
		boolean aux = !(Boolean) usuario.getAttribute("registered");
		model.addAttribute("unregistered", aux);
		model.addAttribute("name", usuario.getAttribute("name"));
		model.addAttribute("profile_img",String.format("<img src=\"%s\" class=\"profile_img\">",(String) usuario.getAttribute("icon")));

		
		// se accede al html
		return "event";
	}	
	// ---------------------------------- FIN EVENTO ----------------------------------
	
	// ---------------------------------- MY LISTS ------------------------------------
	@RequestMapping("/my_lists")
	public String my_lists (Model model, HttpSession usuario) {		
		//Obtengo el nombre del usuario
		String name = (String) usuario.getAttribute("name");
		//Accedo al repositorio de usuarios con el nombre obtenido
		List <User> users = userRepository.findByName(name);		
		
		//Cojo la lista de juegos de ese usuario			
		List<Game> list_games = users.get(0).getGames();
		
		List<String> list=new ArrayList<String>();		
		String div="<div class=\"col-md-3\">\r\n" + "<div class=\"Game\">\r\n" + "<img src=\"%s\" class=\"imagen\">\r\n" + "      <a href=\"%s\" style=\"text-align:center;display:block; color:  rgb(33, 73, 138);\">%s</a>\r\n" + "     \r\n" + "    </div>\r\n" + "  </div>";

		for(int i=0;i<list_games.size();i++) {
			//Aqui accederiamos a la base de datos para cambiar en cada iteracion las variables
			String Url=list_games.get(i).getImage();
			String Titulo=list_games.get(i).getName();		
			String link="/game/" + Titulo;
			//Copiamos el div que queremos poner en el documento html en una variable auxiliar
			//Le damos formato a la variable auxiliar y la añadimos a la lista
			String aux=String.format(div, Url, link, Titulo);			
			list.add(aux);			
		}		
		
		// se pasa la lista de juegos a la plantilla
		model.addAttribute("games", list);
		
		// se muestra el link de iniciar/registrar usuario si es false
		model.addAttribute("registered", usuario.getAttribute("registered"));
		boolean aux = !(Boolean) usuario.getAttribute("registered");
		model.addAttribute("unregistered", aux);
		model.addAttribute("name", usuario.getAttribute("name"));
		model.addAttribute("profile_img",String.format("<img src=\"%s\" class=\"profile_img\">",(String) usuario.getAttribute("icon")));
		
		return "my_lists";
	}
	// ---------------------------------- FIN MY LISTS --------------------------------
	
	
	// ----------------------------- addgame  -------------------------------------
	@RequestMapping("/addList/{page}")
	public String addList (Model model, HttpSession usuario, @RequestParam String name, @PathVariable String page) {	
		String ret = null;
		
		if(page.equals("game")) {
		
			//Obtengo el nombre del usuario
			String name2 = (String) usuario.getAttribute("name");
			//Accedo al repositorio de usuarios con el nombre obtenido
			List <User> users = userRepository.findByName(name2);
			
			boolean repetido = false;
			
			// se coge el juego donde me encuentro
			Game gameAux = gameRepository.findByName(name).get(0);
			
			//Coge la lista de juegos del usuario en caso de que sea repetido lo convierte en true
			for(int i=0; i<users.get(0).getGames().size(); i++) {
				if(gameAux == users.get(0).getGames().get(i)) {
					repetido = true;
				}				
			}
			
			//En caso de que no este en la lista lo mete en la lista
			if(repetido == false) {
				// se guarda el juego dentro del de la lista de juegos de usuario
				users.get(0).addGame(gameAux);
				userRepository.save(users.get(0));
				
				// se guarda el usuario que tiene el juego
				gameAux.addUser(users.get(0));
				gameRepository.save(gameAux);
				//model.addAttribute("alerta", " ");
			}else {
				//model.addAttribute("alerta", "<script type=\"text/javascript\">" + "alert('Game is already added.');" + "window.location = '/'; " + "</script>");		
			}
				
			// se retorna al juego
			ret= "/game/"+gameAux.getName();
		}
		
		model.addAttribute("registered", usuario.getAttribute("registered"));
		boolean aux = !(Boolean) usuario.getAttribute("registered");
		model.addAttribute("unregistered", aux);
		model.addAttribute("name", usuario.getAttribute("name"));
		model.addAttribute("profile_img",String.format("<img src=\"%s\" class=\"profile_img\">",(String) usuario.getAttribute("icon")));

		model.addAttribute("alert"," ");
		model.addAttribute("hello", " ");
		model.addAttribute("Titulo", " ");
		model.addAttribute("Cuerpo", " ");		
	
		return ret;
	}
	// ----------------------------- FIN addgame  --------------------------------------------------
	
	
	// ----------------------------- COMENTARIOS  -------------------------------------
	@RequestMapping("/comment/{page}")
	public String comment (Model model, HttpSession usuario,@RequestParam String text, @RequestParam String name,@PathVariable String page) {	
		String ret = null;
		
		if(page.equals("game")) {
			
			Comment c = new Comment((User)usuario.getAttribute("Usuario"),text,new Date());
			// se coge el juego donde se ha realizado el comentario
			Game game= gameRepository.findByName(name).get(0);		
			// se guarda el juego dentro del objeto comentario y se guarda el comentario en la BBDD
			c.setGame(game);
			commentRepository.save(c);
			// se guarda el comentario dentro del juego y se guarda el juego em la BBDD
			game.setComment(c);
			gameRepository.save(game);
			
			// se pasan los atributos de la barra de navegacion
				
			
			// se retorna al juego
			ret= "/game/"+game.getName();
		}else {
			if(page.equals("company")) {
				
				Comment c = new Comment((User)usuario.getAttribute("Usuario"),text,new Date());
				
				// se coge el juego donde se ha realizado el comentario
				Company company= companyRepository.findByName(name).get(0);		
				// se guarda el juego dentro del objeto comentario y se guarda el comentario en la BBDD
				c.setCompany(company);
				commentRepository.save(c);
				// se guarda el comentario dentro del juego y se guarda el juego em la BBDD
				company.setComment(c);
				companyRepository.save(company);
				
				// se pasan los atributos de la barra de navegacion
						
			
				// se retorna al juego
				ret="/company/"+company.getName();
		}else {
			if(page.equals("event")) {
				Comment c = new Comment((User)usuario.getAttribute("Usuario"),text,new Date());
				
				// se coge el juego donde se ha realizado el comentario
				Event event= eventRepository.findByName(name).get(0);		
				// se guarda el juego dentro del objeto comentario y se guarda el comentario en la BBDD
				c.setEvent(event);
				commentRepository.save(c);
				// se guarda el comentario dentro del juego y se guarda el juego em la BBDD
				event.setComment(c);
				eventRepository.save(event);
				
				// se pasan los atributos de la barra de navegacion
						
			
				// se retorna al juego
				ret="/event/"+event.getName();
			}
			
		}
				
			}
			
			model.addAttribute("registered", usuario.getAttribute("registered"));
			boolean aux = !(Boolean) usuario.getAttribute("registered");
			model.addAttribute("unregistered", aux);
			model.addAttribute("name", usuario.getAttribute("name"));
			model.addAttribute("profile_img",String.format("<img src=\"%s\" class=\"profile_img\">",(String) usuario.getAttribute("icon")));

			model.addAttribute("alert"," ");
			model.addAttribute("hello", " ");
			model.addAttribute("Titulo", " ");
			model.addAttribute("Cuerpo", " ");		
		
		return ret;
		// se crea un comentario con el usuario y el texto introducido
		
	}
	// ----------------------------- FIN COMENTARIOS  --------------------------------------------------
	
	// -------------------------------- BUSQUEDAS ------------------------------------------------------
	@RequestMapping("/search")
	public String search (Model model, HttpSession usuario, @RequestParam String text) {
		System.out.println(text);
		List<Game> games = gameRepository.findByNameContaining(text);
		List<Company> companies = companyRepository.findByNameContaining(text);
		List<Event> events = eventRepository.findByNameContaining(text);
		
		List<String> list_games = new ArrayList<String>();
		List<String> list_companies = new ArrayList<String>();	
		List<String> list_events = new ArrayList<String>();	
		
		boolean n_games = false;
		boolean n_companies = false;
		boolean n_events = false;
		boolean no_lists = true;
		System.out.println(games.size());
		System.out.println(companies.size());
		System.out.println(events.size());
		if(games.size() > 0) {
			no_lists = false;
			n_games = true;
			String div="<div class=\"col-md-3\">\r\n" + "<div class=\"Game\">\r\n" + "<img src=\"%s\" class=\"imagen\">\r\n" + 	" <a href=\"%s\" style=\"text-align:center;display:block; color:  rgb(33, 73, 138);\">%s</a>\r\n" + "     \r\n" + "    </div>\r\n" + "  </div>";
			// lista de todas las compañias disponibles		
			for(int i=0; i < games.size(); i++) {
				//Aqui accederiamos a la base de datos para cambiar en cada iteracion las variables
				String Url = games.get(i).getImage();
				System.out.println(Url);
				String Titulo = games.get(i).getName();
				System.out.println(Titulo);
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

		model.addAttribute("alert"," ");
		model.addAttribute("hello", " ");
		model.addAttribute("Titulo", " ");
		model.addAttribute("Cuerpo", " ");	
		
		return "search";
	}

}	

	


