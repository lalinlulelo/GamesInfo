package LosOdiosos3.prueba_servidor.Application;

import java.util.ArrayList;
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
public class mylistController {	
	// ----------------------------- INYECCIONES --------------------------------------	
	// repositorio de la tabla usuarios 
	@Autowired
	private UserRepository userRepository;	
	
	//repositorio de la tabla listas
	@Autowired
	private MyListRepository mylistRepository;
	// ----------------------------- FIN INYECCIONES ----------------------------------


	// ----------------------------- MY LISTS -----------------------------------------
	@RequestMapping("/my_lists")
	public String my_lists (Model model, HttpSession usuario, HttpServletRequest request) {
		model.addAttribute("added", " ");
		// Obtengo el nombre del usuario
		String name = (String) usuario.getAttribute("name");
		
		// Accedo al repositorio de usuarios con el nombre obtenido
		List <User> users = userRepository.findByName(name);
		
		// Cojo las listas de juegos		
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
		
		//Para activar admin
		model.addAttribute("admin", usuario.getAttribute("admin"));
		
		// atributos del token
		CsrfToken token = (CsrfToken) request.getAttribute("_csrf");
		model.addAttribute("token", token.getToken());
		
		return "my_lists";
	}
	
	@RequestMapping("my_lists_settings/{option}")
	public String my_lists_option (Model model, @PathVariable String option, @RequestParam String list,  HttpSession usuario, HttpServletRequest request) {
		// cojo el usuario 
		User user = userRepository.findByName((String)usuario.getAttribute("name")).get(0);
		MyList mylist = null;
		boolean added = false;
		// escojo la opción añadir
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
		
		//Para activar admin
		model.addAttribute("admin", usuario.getAttribute("admin"));
		
		// atributos del token
		CsrfToken token = (CsrfToken) request.getAttribute("_csrf");
		model.addAttribute("token", token.getToken());
		
		return "my_lists";
	}
	// ----------------------------- FIN MY LISTS -------------------------------------
	
	
}
