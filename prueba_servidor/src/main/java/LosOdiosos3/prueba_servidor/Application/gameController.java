package LosOdiosos3.prueba_servidor.Application;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class gameController {
	// ----------------------------- INYECCIONES --------------------------------------	
	// repositorio de la tabla usuarios 
	@Autowired
	private UserRepository userRepository;	

	// repositorio de la tabla juegos
	@Autowired
	private GameRepository gameRepository;

	// repositorio de la tabla anuncios
	@Autowired
	private ArticleRepository articleRepository;
	
	//repositorio de la tabla listas
	@Autowired
	private MyListRepository mylistRepository;
	// ----------------------------- FIN INYECCIONES ----------------------------------

	// ----------------------------- LISTA DE JUEGOS ----------------------------------	
	@RequestMapping("/game_list/{modo}")
	public String game_list (Model model, HttpSession usuario, @PathVariable String modo, HttpServletRequest request) {
		// lista de juegos
		List<String> list=new ArrayList<String>();		
		String div="<div class=\"col-md-3\">\r\n" + "<div class=\"Game\">\r\n" + "<img src=\"%s\" class=\"imagen\">\r\n" + "      <a href=\"%s\" style=\"text-align:center;display:block; color:  rgb(33, 73, 138);\">%s</a>\r\n" + "     \r\n" + "    </div>\r\n" + "  </div>";
		
		// modo de ordenación de la lista de juegos
		//List<Game> list_games = null;		
		List<Game> list_games = gameRepository.findAll();
		
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

		//Para activar admin
		model.addAttribute("admin", usuario.getAttribute("admin"));
		
		// atributos del token
		CsrfToken token = (CsrfToken) request.getAttribute("_csrf");
		model.addAttribute("token", token.getToken());
		
		return "game_list";
	}
	// ----------------------------- FIN LISTA DE JUEGOS ------------------------------
	
	// ----------------------------- JUEGO --------------------------------------------
	@RequestMapping("/game/{game_name}")
	public String Game (Model model, HttpSession usuario, @PathVariable String game_name, HttpServletRequest request) {
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
		model.addAttribute("options", " ");
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
		
		// añadir a la lista
		if(usuario.getAttribute("name") != null) {
			User user = userRepository.findByName((String)usuario.getAttribute("name")).get(0);
			List<MyList> lists = user.getList();
			String option = "<option value=\"%s \">%s</option>";
			String options = "";
			if(lists.size() > 0) {
				for(int i = 0; i < lists.size(); i++) {
					String Titulo = lists.get(i).getName();
					String aux = String.format(option, Titulo, Titulo);
					options += aux;
				}
				model.addAttribute("options", options);
			}
		}else {
			model.addAttribute("options", " ");
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
		
		//Para activar admin
		model.addAttribute("admin", usuario.getAttribute("admin"));
		
		// atributos del token
		CsrfToken token = (CsrfToken) request.getAttribute("_csrf");
		model.addAttribute("token", token.getToken());
		
		return "game";
	}
	// ----------------------------- FIN JUEGO ----------------------------------------
	
	// ----------------------------- ADD GAME  ----------------------------------------
	@RequestMapping("/addList/{page}")
	public String addList (Model model, HttpSession usuario, @RequestParam String list, @RequestParam String name, @PathVariable String page, HttpServletRequest request) {	
		String ret = null;
		boolean repetido = false;
		if(page.equals("game")) {
			//Obtengo el nombre del usuario
			String user_name = (String) usuario.getAttribute("name");
			//Accedo al repositorio de usuarios con el nombre obtenido
			User user = userRepository.findByName(user_name).get(0);
			// se coge el juego donde me encuentro
			Game game = gameRepository.findByName(name).get(0);
			// se coge las listas de juegos
			List<MyList> mylists = user.getList();
			MyList mylist = null;
			// aceedo a la lista seleccionada
			for(int i = 0; i < mylists.size(); i++) {
				if(list.equals(mylists.get(i).getName() + " ")) {
					mylist = mylists.get(i);
					break;
				}
			}
			List<Game> games = mylist.getList();
			//Coge la lista de juegos del usuario en caso de que sea repetido lo convierte en true
			for(int i = 0; i < games.size(); i++) {
				System.out.println(game.getName() +" vs "+games.get(i).getName());
				if(game == games.get(i)) {
					repetido = true;
					break;
				}				
			}
			//En caso de que no este en la lista lo mete en la lista
			if(repetido == false) {
				mylist.addGame(game);
				game.addMyList(mylist);
				gameRepository.save(game);
				mylistRepository.save(mylist);
				userRepository.save(user);
			}
			
			// atributos de la pagina
			model.addAttribute("registered", usuario.getAttribute("registered"));
			boolean aux = !(Boolean) usuario.getAttribute("registered");
			model.addAttribute("unregistered", aux);
			model.addAttribute("name", usuario.getAttribute("name"));
			model.addAttribute("profile_img",String.format("<img src=\"%s\" class=\"profile_img\">",(String) usuario.getAttribute("icon")));

			// Para activar admin
			model.addAttribute("admin", usuario.getAttribute("admin"));
			
			// atributos referentes a index
			model.addAttribute("alert"," ");
			model.addAttribute("hello", " ");
			model.addAttribute("Titulo", " ");
			model.addAttribute("Cuerpo", " ");	

			// añadir a la lista
			if(usuario.getAttribute("name") != null) {
				User user_aux = userRepository.findByName((String)usuario.getAttribute("name")).get(0);
				List<MyList> lists = user_aux.getList();
				String option = "<option value=\"%s \">%s</option>";
				String options = "";
				if(lists.size() > 0) {
					for(int i = 0; i < lists.size(); i++) {
						String Titulo = lists.get(i).getName();
						String aux_ = String.format(option, Titulo, Titulo);
						options += aux_;
					}
					model.addAttribute("options", options);
				}
			}else {
				model.addAttribute("options", " ");
			}
			
			// atributos del token
			CsrfToken token = (CsrfToken) request.getAttribute("_csrf");
			model.addAttribute("token", token.getToken());			
			
			// se retorna al juego
			ret= "/game/"+game.getName();			
			return ret;
		}
		// atributos de la pagina
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

		// articulos relevantes
		model.addAttribute("news", articles ());	
		
		// atributos del token
		CsrfToken token = (CsrfToken) request.getAttribute("_csrf");
		model.addAttribute("token", token.getToken());
		
		return "index";
	}
	// ----------------------------- FIN ADD GAME  ------------------------------------
		
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
}
