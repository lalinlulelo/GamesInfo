package LosOdiosos3.prueba_servidor.Application;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import Socket.MailSender;

import org.springframework.security.web.csrf.CsrfToken;

@Controller
public class webController {	
	/*
	// ----------------------------- VARIABLES DEL SERVIDOR ---------------------------
	// iconos de usuario
	private List<String> icons = Arrays.asList("https://mir-s3-cdn-cf.behance.net/project_modules/disp/bb3a8833850498.56ba69ac33f26.png", "https://mir-s3-cdn-cf.behance.net/project_modules/disp/1bdc9a33850498.56ba69ac2ba5b.png", "https://mir-s3-cdn-cf.behance.net/project_modules/disp/bf6e4a33850498.56ba69ac3064f.png", "https://mir-s3-cdn-cf.behance.net/project_modules/disp/64623a33850498.56ba69ac2a6f7.png", "https://mir-s3-cdn-cf.behance.net/project_modules/disp/e70b1333850498.56ba69ac32ae3.png", "https://mir-s3-cdn-cf.behance.net/project_modules/disp/84c20033850498.56ba69ac290ea.png", "http://blogs.studentlife.utoronto.ca/lifeatuoft/files/2015/02/FullSizeRender.jpg", "https://i.pinimg.com/474x/c3/53/7f/c3537f7ba5a6d09a4621a77046ca926d--soccer-quotes-lineman.jpg");
	// variable de inicio de controlador
	boolean comienzo = false;
// ----------------------------- FIN VARIABLES DEL SERVIDOR ----------------------
*/
	// -----/------------------------ INYECCIONES --------------------------------------	
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
	
	// repositorio de la tabla anuncios
	@Autowired
	private ArticleRepository articleRepository;
	
	//repositorio de la tabla listas
	@Autowired
	private MyListRepository mylistRepository;	
	// ----------------------------- FIN INYECCIONES ----------------------------------

	// ----------------------------- PAGINA INICIO ------------------------------------
	@RequestMapping("/")
	public String inicio (Model model, HttpSession usuario, HttpServletRequest request) throws ParseException {
		//if(comienzo == false) {
			/*
			// Datos de la Base de Datos cargados inicialmente
			// usuarios
			User Juan = new User("Juan", "123", "20/11/85", "Juan@gmail.com", "ROLE_USER");
			Juan.setIcon(icons.get(3));
			userRepository.save(Juan);
			User Pedro = new User("Pedro", "456", "15/6/92", "Pedro@hotmail.com", "ROLE_USER");;
			Pedro.setIcon(icons.get((int)Math.random()*6));
			userRepository.save(Pedro);
			User Guille = new User("Guille", "789", "25/2/96", "guillemelmor@gmail.com", "ROLE_USER");;
			Guille.setIcon(icons.get((int)Math.random()*6));
			userRepository.save(Guille);
			User Sergio = new User("Sergio", "1011", "4/2/95", "Sergio@hotmail.com", "ROLE_USER");;
			Sergio.setIcon(icons.get((int)Math.random()*6));
			userRepository.save(Sergio);
			User Agus = new User("Agus", "1213", "14/10/96", "Agus@hotmail.com", "ROLE_USER");;
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
			MyList mylist = new MyList("played");
			mylist.addGame(TLOU);
			TLOU.addMyList(mylist);
			mylist.addGame(CB);
			CB.addMyList(mylist);
			Juan.addList(mylist);
			mylist.addUser(Juan);
			mylistRepository.save(mylist);
			userRepository.save(Juan);
			gameRepository.save(TLOU);
			gameRepository.save(CB);
			mylist = new MyList("waiting for");
			mylist.addGame(SMO);
			SMO.addMyList(mylist);
			mylist.addGame(GOW);
			GOW.addMyList(mylist);
			Juan.addList(mylist);
			mylist.addUser(Juan);
			mylistRepository.save(mylist);
			userRepository.save(Juan);
			gameRepository.save(GOW);
			gameRepository.save(SMO);
		
			// articulos
			Article article = new Article (userRepository.findByName("Agus").get(0), "Nintendo Labo", "Nintendo saca un nuevo producto!", "Nintendo Labo es una nueva gama de experiencias interactivas con las que crear, jugar y descubrir, para inspirar a las mentes más creativas y los corazones más inquietos.", "https://statics.vrutal.com/m/7193/7193c0a1bd77df5d5aa766727a187b77.jpg");
			articleRepository.save(article);
			article = new Article(userRepository.findByName("Guille").get(0), "The Seven Deadly Sins: Knights of Britannia", "Nuevo juego de PS4 a la vista", "nos encontramos ante un juego de lucha tridimensional en el que nos podemos mover con total libertad por los escenarios de forma muy parecida a lo que vimos en J-Stars Victory VS.", "http://www.vostory.com/wp-content/uploads/2018/01/maxresdefault.jpg");
			articleRepository.save(article);
			article = new Article(userRepository.findByName("Sergio").get(0), "Kingdom Hearts 3", "¡Nuevas imágenes de Kingdom Hearts 3!", "El pasado fin de semana Square Enix presentó el mundo de Monstruos S.A para Kingdom Hearts III. Ahora nos llega una nueva galería de imágenes y renders que nos ponen los dientes largos esperando conocer más detalles.", "https://statics.vrutal.com/m/9168/9168def48ee8a753b36bde6312659da5.jpg");
			articleRepository.save(article);*/
		//}
		if(usuario.getAttribute("registered") == null) {
			usuario.setAttribute("registered", false);			
		}
		usuario.setAttribute("alert", "  ");
		// comentarios de abajo del html
		model.addAttribute("Titulo", "Latest News");		
		
		// articulos relevantes
		List<Article> articles = articleRepository.findAll();
		String news = "";
		if(articles.size() > 0) {
			String div ="<div class=\"row\">\r\n" + "<div class=\"col-sm-1 col-md-1\"> </div>	"  +  "<div class=\"card p-3\">\r\n"  + 	"<div class=\"card-wrapper\">\r\n" + 	"                <div class=\"card-img  col-xs-12 col-sm-4 col-md-4\">\r\n" + "                    <img src=\"  %s  \" alt=\"Mobirise\" title=\"\" media-simple=\"true\">\r\n" + "                </div>\r\n" + 	"                <div class=\"card-box col-xs-12 col-sm-3 col-md-3\">\r\n" + 	"                    <h4 class=\"card-title pb-3 mbr-fonts-style display-7\">  %s  </h4>\r\n" + 	"                    <p class=\"mbr-text mbr-fonts-style display-7\">\r\n" + 	"                        %s  <a href=\"  %s  \">   Read more...</a>\r\n" + 	"                    </p>\r\n" + 		"                </div>\r\n" + 		"            </div>\r\n" +   "            </div>\r\n"	+ 	"        </div>";			
			for(int i = 0; i < articles.size(); i++) {
				String Url= articles.get(i).getImage();
				String Titulo = articles.get(i).getTitle();	
				String Head = articles.get(i).getHead();
				String link="/article/" + Titulo;								

				String aux = String.format(div, Url, Titulo, Head, link);			
				news += aux;			
			}	
		}
		model.addAttribute("news", news);
		
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
		
		// atributos del token
		CsrfToken token = (CsrfToken) request.getAttribute("_csrf");
		model.addAttribute("token", token.getToken());
		
		return "search";
	}
	// ----------------------------- FIN BUSQUEDAS ------------------------------------
}
	


