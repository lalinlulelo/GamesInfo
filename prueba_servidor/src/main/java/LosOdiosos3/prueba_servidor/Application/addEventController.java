package LosOdiosos3.prueba_servidor.Application;

import java.util.ArrayList;
import java.util.Arrays;
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
public class addEventController {

	
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
			
			
			@RequestMapping("/addEvent")
			public String addGame (Model model, HttpSession usuario, @RequestParam String name,@RequestParam String place,
			@RequestParam String description,@RequestParam double fee,@RequestParam int day,@RequestParam int month, @RequestParam int year, @RequestParam String img, HttpServletRequest request) {
			
			
				List<Event> liste=eventRepository.findAll();
				
				for(Event e:liste) {
					
					if(e.getName().equals(name)) {
						fillModel(model,usuario,request);
						return "admin";
					}
					}
				
				
				
				Event newEvent=new Event(name,place,year,month,day,fee,description,img);
				eventRepository.save(newEvent);
				
				fillModel(model,usuario,request);
				return "admin";
			
			}
			
		
	public void fillModel(Model model,HttpSession usuario,HttpServletRequest request) {		
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
		
	}
	
}
