<<<<<<< HEAD
package LosOdiosos3.prueba_servidor.Controllers;
import LosOdiosos3.prueba_servidor.Entities.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import LosOdiosos3.prueba_servidor.Application.*;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class mylistController {
	// ----------------------------- VARIABLES DEL SERVIDOR ---------------------------
		// iconos de usuario
		private List<String> icons = Arrays.asList("https://mir-s3-cdn-cf.behance.net/project_modules/disp/bb3a8833850498.56ba69ac33f26.png", "https://mir-s3-cdn-cf.behance.net/project_modules/disp/1bdc9a33850498.56ba69ac2ba5b.png", "https://mir-s3-cdn-cf.behance.net/project_modules/disp/bf6e4a33850498.56ba69ac3064f.png", "https://mir-s3-cdn-cf.behance.net/project_modules/disp/64623a33850498.56ba69ac2a6f7.png", "https://mir-s3-cdn-cf.behance.net/project_modules/disp/e70b1333850498.56ba69ac32ae3.png", "https://mir-s3-cdn-cf.behance.net/project_modules/disp/84c20033850498.56ba69ac290ea.png", "http://blogs.studentlife.utoronto.ca/lifeatuoft/files/2015/02/FullSizeRender.jpg", "https://i.pinimg.com/474x/c3/53/7f/c3537f7ba5a6d09a4621a77046ca926d--soccer-quotes-lineman.jpg");
		// variable de inicio de controlador
		boolean comienzo = false;
	// ----------------------------- FIN VARIABLES DEL SERVIDOR -----------------------
	
	// ----------------------------- INYECCIONES --------------------------------------	
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


	// ----------------------------- MY LISTS -----------------------------------------
	@RequestMapping("/my_lists")
	public String my_lists (Model model, HttpSession usuario) {
		model.addAttribute("added", " ");
		//Obtengo el nombre del usuario
		String name = (String) usuario.getAttribute("name");
		
		//Accedo al repositorio de usuarios con el nombre obtenido
		List <User> users = userRepository.findByName(name);
		
		//Cojo las listas de juegos		
		List<MyList> lists_games = users.get(0).getList();
		
		// comprueba que existan listas
		if(lists_games.size() > 0) {
			List<String> list = new ArrayList<String>();
			String div_title = "<div class=\"col-md-11\" style=\"text-align: center;\"><br><br><h3> %s </h3></div>";
			String div_game =
					"<div class=\"col-md-3\">\r\n" + 
					"    <div class=\"Game\">\r\n" + 
					"      <img src=\"%s\" class=\"imagen\">\r\n" + 
					"      <a href=\"%s\" style=\"text-align:center;display:block; color:  rgb(33, 73, 138);\">%s</a>\r\n" + 
					"     \r\n" + 
					"    </div>\r\n" + 
					"</div>";
			for(int i=0;i<lists_games.size();i++) {
				String list_name = (String)lists_games.get(i).getName();
				String aux_1 = String.format(div_title, list_name);	
				List<Game> games = lists_games.get(i).getList();
				for(int j = 0; j < games.size(); j++) {
					String Url = games.get(j).getImage();
					String Titulo = games.get(j).getName();		
					String link="/game/" + Titulo;
					String aux_2 = String.format(div_game, Url, link, Titulo);
					aux_1 += aux_2;
				}
				aux_1+="<div class=\"col-md-11\"><br></div>";
				list.add(aux_1);
			}
			model.addAttribute("games", list);
		}else {
			String info = "<div class=\"col-md-11\" style=\"text-align: center;\"><br><br><h3> No lists created </h3></div>";

			model.addAttribute("games", info);
		}

		// se muestra el link de iniciar/registrar usuario si es false
		model.addAttribute("registered", usuario.getAttribute("registered"));
		boolean aux = !(Boolean) usuario.getAttribute("registered");
		model.addAttribute("unregistered", aux);
		model.addAttribute("name", usuario.getAttribute("name"));
		model.addAttribute("profile_img",String.format("<img src=\"%s\" class=\"profile_img\">",(String) usuario.getAttribute("icon")));
		
		return "my_lists";
	}
	
	@RequestMapping("my_lists_settings/{option}")
	public String my_lists_option (Model model, @PathVariable String option, @RequestParam String list,  HttpSession usuario) {
		User user = userRepository.findByName((String)usuario.getAttribute("name")).get(0);
		MyList mylist = null;
		boolean added = false;
		switch(option) {
			case "add":
				boolean repetido = false;
				for(int i = 0; i < user.getList().size(); i++) {
					if(user.getList().get(i).getName().equals(list)) {
						repetido = true;
						break;
					}
				}
				if(repetido == false) {
					mylist = new MyList(list);
					user.addList(mylist);
					mylist.addUser(user);
					userRepository.save(user);				
					mylistRepository.save(mylist);
					added = true;
				}
				break;
			/*
			case "delete":
				mylist = null;
				for(int i = 0; i < user.getList().size(); i++) {
					if(user.getList().get(i).getName().equals(list)) {
						mylist = user.getList().get(i);
					}
				}
				if(mylist != null) {
					mylist.cleanList();
					user.removeList(mylist);
					userRepository.save(user);	
				}
				break;
			*/
		}
		
		// realizo todo lo referente a my_list
		if(added == true) {
			model.addAttribute("added", "<script type=\"text/javascript\">" + "alert('list " + list + " added!'); window.location = '/my_lists';"  + "</script>");
		}
		if(added == false) {
			model.addAttribute("added", "<script type=\"text/javascript\">" + "alert('the list couldnt be added'); window.location = '/my_lists';"  + "</script>");

		}
		//Obtengo el nombre del usuario
		String name = (String) usuario.getAttribute("name");
		
		//Accedo al repositorio de usuarios con el nombre obtenido
		List <User> users = userRepository.findByName(name);
		
		//Cojo las listas de juegos		
		List<MyList> lists_games = users.get(0).getList();
		
		// comprueba que existan listas
		if(lists_games.size() > 0) {
			List<String> list_ = new ArrayList<String>();
			String div_title = "<div class=\"col-md-11\" style=\"text-align: center;\"><br><br><h3> %s </h3></div>";
			String div_game =
					"<div class=\"col-md-3\">\r\n" + 
					"    <div class=\"Game\">\r\n" + 
					"      <img src=\"%s\" class=\"imagen\">\r\n" + 
					"      <a href=\"%s\" style=\"text-align:center;display:block; color:  rgb(33, 73, 138);\">%s</a>\r\n" + 
					"     \r\n" + 
					"    </div>\r\n" + 
					"</div>";
			for(int i=0;i<lists_games.size();i++) {
				String list_name = (String)lists_games.get(i).getName();
				String aux_1 = String.format(div_title, list_name);	
				List<Game> games = lists_games.get(i).getList();
				for(int j = 0; j < games.size(); j++) {
					String Url = games.get(j).getImage();
					String Titulo = games.get(j).getName();		
					String link="/game/" + Titulo;
					String aux_2 = String.format(div_game, Url, link, Titulo);
					aux_1 += aux_2;
				}
				aux_1+="<div class=\"col-md-11\"><br></div>";
				list_.add(aux_1);
			}
			model.addAttribute("games", list_);
		}else {
			String info = "<div class=\"col-md-11\" style=\"text-align: center;\"><br><br><h3> No lists created </h3></div>";

			model.addAttribute("games", info);
		}
		
		// se muestra el link de iniciar/registrar usuario si es false
		model.addAttribute("registered", usuario.getAttribute("registered"));
		boolean aux = !(Boolean) usuario.getAttribute("registered");
		model.addAttribute("unregistered", aux);
		model.addAttribute("name", usuario.getAttribute("name"));
		model.addAttribute("profile_img",String.format("<img src=\"%s\" class=\"profile_img\">",(String) usuario.getAttribute("icon")));
		
		return "my_lists";
	}
	// ----------------------------- FIN MY LISTS -------------------------------------
	
	
}
=======
package LosOdiosos3.prueba_servidor.Controllers;
import LosOdiosos3.prueba_servidor.Entities.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class mylistController {
	// ----------------------------- VARIABLES DEL SERVIDOR ---------------------------
		// iconos de usuario
		private List<String> icons = Arrays.asList("https://mir-s3-cdn-cf.behance.net/project_modules/disp/bb3a8833850498.56ba69ac33f26.png", "https://mir-s3-cdn-cf.behance.net/project_modules/disp/1bdc9a33850498.56ba69ac2ba5b.png", "https://mir-s3-cdn-cf.behance.net/project_modules/disp/bf6e4a33850498.56ba69ac3064f.png", "https://mir-s3-cdn-cf.behance.net/project_modules/disp/64623a33850498.56ba69ac2a6f7.png", "https://mir-s3-cdn-cf.behance.net/project_modules/disp/e70b1333850498.56ba69ac32ae3.png", "https://mir-s3-cdn-cf.behance.net/project_modules/disp/84c20033850498.56ba69ac290ea.png", "http://blogs.studentlife.utoronto.ca/lifeatuoft/files/2015/02/FullSizeRender.jpg", "https://i.pinimg.com/474x/c3/53/7f/c3537f7ba5a6d09a4621a77046ca926d--soccer-quotes-lineman.jpg");
		// variable de inicio de controlador
		boolean comienzo = false;
	// ----------------------------- FIN VARIABLES DEL SERVIDOR -----------------------
	
	// ----------------------------- INYECCIONES --------------------------------------	
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


	// ----------------------------- MY LISTS -----------------------------------------
	@RequestMapping("/my_lists")
	public String my_lists (Model model, HttpSession usuario) {
		model.addAttribute("added", " ");
		//Obtengo el nombre del usuario
		String name = (String) usuario.getAttribute("name");
		
		//Accedo al repositorio de usuarios con el nombre obtenido
		List <User> users = userRepository.findByName(name);
		
		//Cojo las listas de juegos		
		List<MyList> lists_games = users.get(0).getList();
		
		// comprueba que existan listas
		if(lists_games.size() > 0) {
			List<String> list = new ArrayList<String>();
			String div_title = "<div class=\"col-md-11\" style=\"text-align: center;\"><br><br><h3> %s </h3></div>";
			String div_game =
					"<div class=\"col-md-3\">\r\n" + 
					"    <div class=\"Game\">\r\n" + 
					"      <img src=\"%s\" class=\"imagen\">\r\n" + 
					"      <a href=\"%s\" style=\"text-align:center;display:block; color:  rgb(33, 73, 138);\">%s</a>\r\n" + 
					"     \r\n" + 
					"    </div>\r\n" + 
					"</div>";
			for(int i=0;i<lists_games.size();i++) {
				String list_name = (String)lists_games.get(i).getName();
				String aux_1 = String.format(div_title, list_name);	
				List<Game> games = lists_games.get(i).getList();
				for(int j = 0; j < games.size(); j++) {
					String Url = games.get(j).getImage();
					String Titulo = games.get(j).getName();		
					String link="/game/" + Titulo;
					String aux_2 = String.format(div_game, Url, link, Titulo);
					aux_1 += aux_2;
				}
				aux_1+="<div class=\"col-md-11\"><br></div>";
				list.add(aux_1);
			}
			model.addAttribute("games", list);
		}else {
			String info = "<div class=\"col-md-11\" style=\"text-align: center;\"><br><br><h3> No lists created </h3></div>";

			model.addAttribute("games", info);
		}

		// se muestra el link de iniciar/registrar usuario si es false
		model.addAttribute("registered", usuario.getAttribute("registered"));
		boolean aux = !(Boolean) usuario.getAttribute("registered");
		model.addAttribute("unregistered", aux);
		model.addAttribute("name", usuario.getAttribute("name"));
		model.addAttribute("profile_img",String.format("<img src=\"%s\" class=\"profile_img\">",(String) usuario.getAttribute("icon")));
		
		return "my_lists";
	}
	
	@RequestMapping("my_lists_settings/{option}")
	public String my_lists_option (Model model, @PathVariable String option, @RequestParam String list,  HttpSession usuario) {
		User user = userRepository.findByName((String)usuario.getAttribute("name")).get(0);
		MyList mylist = null;
		boolean added = false;
		switch(option) {
			case "add":
				boolean repetido = false;
				for(int i = 0; i < user.getList().size(); i++) {
					if(user.getList().get(i).getName().equals(list)) {
						repetido = true;
						break;
					}
				}
				if(repetido == false) {
					mylist = new MyList(list);
					user.addList(mylist);
					mylist.addUser(user);
					userRepository.save(user);				
					mylistRepository.save(mylist);
					added = true;
				}
				break;
			/*
			case "delete":
				mylist = null;
				for(int i = 0; i < user.getList().size(); i++) {
					if(user.getList().get(i).getName().equals(list)) {
						mylist = user.getList().get(i);
					}
				}
				if(mylist != null) {
					mylist.cleanList();
					user.removeList(mylist);
					userRepository.save(user);	
				}
				break;
			*/
		}
		
		// realizo todo lo referente a my_list
		if(added == true) {
			model.addAttribute("added", "<script type=\"text/javascript\">" + "alert('list " + list + " added!'); window.location = '/my_lists';"  + "</script>");
		}
		if(added == false) {
			model.addAttribute("added", "<script type=\"text/javascript\">" + "alert('the list couldnt be added'); window.location = '/my_lists';"  + "</script>");

		}
		//Obtengo el nombre del usuario
		String name = (String) usuario.getAttribute("name");
		
		//Accedo al repositorio de usuarios con el nombre obtenido
		List <User> users = userRepository.findByName(name);
		
		//Cojo las listas de juegos		
		List<MyList> lists_games = users.get(0).getList();
		
		// comprueba que existan listas
		if(lists_games.size() > 0) {
			List<String> list_ = new ArrayList<String>();
			String div_title = "<div class=\"col-md-11\" style=\"text-align: center;\"><br><br><h3> %s </h3></div>";
			String div_game =
					"<div class=\"col-md-3\">\r\n" + 
					"    <div class=\"Game\">\r\n" + 
					"      <img src=\"%s\" class=\"imagen\">\r\n" + 
					"      <a href=\"%s\" style=\"text-align:center;display:block; color:  rgb(33, 73, 138);\">%s</a>\r\n" + 
					"     \r\n" + 
					"    </div>\r\n" + 
					"</div>";
			for(int i=0;i<lists_games.size();i++) {
				String list_name = (String)lists_games.get(i).getName();
				String aux_1 = String.format(div_title, list_name);	
				List<Game> games = lists_games.get(i).getList();
				for(int j = 0; j < games.size(); j++) {
					String Url = games.get(j).getImage();
					String Titulo = games.get(j).getName();		
					String link="/game/" + Titulo;
					String aux_2 = String.format(div_game, Url, link, Titulo);
					aux_1 += aux_2;
				}
				aux_1+="<div class=\"col-md-11\"><br></div>";
				list_.add(aux_1);
			}
			model.addAttribute("games", list_);
		}else {
			String info = "<div class=\"col-md-11\" style=\"text-align: center;\"><br><br><h3> No lists created </h3></div>";

			model.addAttribute("games", info);
		}
		
		// se muestra el link de iniciar/registrar usuario si es false
		model.addAttribute("registered", usuario.getAttribute("registered"));
		boolean aux = !(Boolean) usuario.getAttribute("registered");
		model.addAttribute("unregistered", aux);
		model.addAttribute("name", usuario.getAttribute("name"));
		model.addAttribute("profile_img",String.format("<img src=\"%s\" class=\"profile_img\">",(String) usuario.getAttribute("icon")));
		
		return "my_lists";
	}
	// ----------------------------- FIN MY LISTS -------------------------------------
	
	
}
>>>>>>> branch 'master' of https://github.com/lalinlulelo/GamesInfo.git
