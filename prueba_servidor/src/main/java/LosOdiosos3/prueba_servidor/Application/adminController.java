package LosOdiosos3.prueba_servidor.Application;



import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class adminController {
	
	// repositorio de la tabla compañias
			

			// repositorio de la tabla juegos
			@Autowired
			private GameRepository gameRepository;
			
			@Autowired
			private CompanyRepository companyRepository;
			
			@Autowired
			private EventRepository eventRepository;
			// ----------------------------- FIN INYECCIONES ----------------------------------
	
		@RequestMapping("/admin")
		public String addGame (Model model, HttpSession usuario, HttpServletRequest request) {
			
			
			
		fillModel(model,usuario,request);
			
			return "admin";
			
		}
		
		
		@RequestMapping("/deleteGame")
		public String deleteGame (Model model, HttpSession usuario, HttpServletRequest request, @RequestParam String Game) {
			
			List<Game> list =gameRepository.findByName(Game);
			
			gameRepository.delete(list.get(0));
			
			fillModel(model,usuario,request);
			
			return "admin";
		}
		
		@RequestMapping("/deleteCompany")
		public String deleteCompany (Model model, HttpSession usuario, HttpServletRequest request, @RequestParam String Company) {
			
			List<Company> list =companyRepository.findByName(Company);
			
			companyRepository.delete(list.get(0));
			
			fillModel(model,usuario,request);
			
			return "admin";
		}
		
		@RequestMapping("/deleteEvent")
		public String deleteEvent (Model model, HttpSession usuario, HttpServletRequest request, @RequestParam String Event) {
			
			List<Event> list =eventRepository.findByName(Event);
			
			eventRepository.delete(list.get(0));
			
			fillModel(model,usuario,request);
			
			return "admin";
		}
		
		public void fillModel(Model model,HttpSession usuario,HttpServletRequest request) {
			List<Game> games=gameRepository.findAll();
			List<Company> companies=companyRepository.findAll();
			List<Event> events=eventRepository.findAll();
			List<String>listGames=new ArrayList<String>();
			List<String>listCompanies=new ArrayList<String>();
			List<String>listEvents=new ArrayList<String>();

			
		
			for(Game g:games) {
				String name=g.getName();
				
				String aux=String.format("<option value=\"%s\">%s</option>", name,name);
				listGames.add(aux);
			}
			
			model.addAttribute("listGames", listGames);
			
			
			for(Company c:companies) {
				String name=c.getName();
				
				String aux=String.format("<option value=\"%s\">%s</option>", name,name);
				listCompanies.add(aux);
			}
			
			model.addAttribute("listCompanies", listCompanies);
			
			for(Event e:events) {
				String name=e.getName();
				
				String aux=String.format("<option value=\"%s\">%s</option>", name,name);
				listEvents.add(aux);
			}
			
			model.addAttribute("listEvents", listEvents);
			
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

