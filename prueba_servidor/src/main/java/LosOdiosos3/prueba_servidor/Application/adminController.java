package LosOdiosos3.prueba_servidor.Application;



import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javassist.compiler.Parser;

@Controller
public class adminController {
	// ----------------------------- INYECCIONES --------------------------------------	
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
		
		//Para activar admin
		model.addAttribute("admin", usuario.getAttribute("admin"));
		
		model.addAttribute("alert", usuario.getAttribute("alert"));
		model.addAttribute("hello", " ");
		model.addAttribute("Titulo", " ");
		model.addAttribute("Cuerpo", " ");			
		
		// atributos del token
		CsrfToken token = (CsrfToken) request.getAttribute("_csrf");
		model.addAttribute("token", token.getToken());		
	}
	
	@RequestMapping("/addCompany")
	public String addCompany (Model model, HttpSession usuario, @RequestParam String name,@RequestParam String country,
	@RequestParam String description,@RequestParam String date,@RequestParam String img ,@RequestParam String url, HttpServletRequest request){	
		if(name=="" || country=="" || description=="" || date=="" || img=="" || url=="") {
			System.out.println("One of the gap is empty");
			
			usuario.setAttribute("alert","<script type=\"text/javascript\">" + "alert('Operation fail: One of the gap is empty');" + "window.location = '/admin'; " + "</script>");		
        	fillModel(model,usuario,request);
        	
        	return "admin";
		}		
		
		try {					
			List<Company> listc=companyRepository.findAll();
			
			for(Company c:listc) {			
				if(c.getName().equals(name)) {
					System.out.println("Name repeated");
					usuario.setAttribute("alert","<script type=\"text/javascript\">" + "alert('Operation fail: Name repeated');" + "window.location = '/admin'; " + "</script>");		
					fillModel(model,usuario,request);
					return "admin";
				}
			}							
			
			Company newCompany=new Company(name,country,description,Integer.parseInt(date),img,url);
			companyRepository.save(newCompany);
			
			usuario.setAttribute("alert"," ");		
			fillModel(model,usuario,request);
			return "admin";	
		
		}catch(NumberFormatException ex){
        	System.out.println("NumberFormatException");
        	fillModel(model,usuario,request);
        	
        	return "admin";
    	}		
	}
	
	@RequestMapping("/addEvent")
	public String addEvent (Model model, HttpSession usuario, @RequestParam String name,@RequestParam String place,
	@RequestParam String description,@RequestParam String fee,@RequestParam String day,@RequestParam String month, @RequestParam String year, @RequestParam String img, HttpServletRequest request) {
		if(name=="" || place=="" || description=="" || fee=="" || img=="" || day=="" || month=="" || year=="" || img=="") {
			usuario.setAttribute("alert","<script type=\"text/javascript\">" + "alert('Operation fail: One of the gap is empty');" + "window.location = '/admin'; " + "</script>");		
        	fillModel(model,usuario,request);
        	
        	return "admin";
		}
	
		List<Event> liste=eventRepository.findAll();
		
		for(Event e:liste) {			
			if(e.getName().equals(name)) {
				usuario.setAttribute("alert","<script type=\"text/javascript\">" + "alert('Operation fail: Name repeated');" + "window.location = '/admin'; " + "</script>");		
				fillModel(model,usuario,request);
				return "admin";
			}
		}				
		
		Event newEvent=new Event(name,place,Integer.parseInt(year),Integer.parseInt(month),Integer.parseInt(day),Double.parseDouble(fee),description,img);
		eventRepository.save(newEvent);
		
		usuario.setAttribute("alert"," ");
		fillModel(model,usuario,request);
		return "admin";	
	}
	
	@RequestMapping("/addGame")
	public String addGame (Model model, HttpSession usuario, @RequestParam String name,@RequestParam String company,
	@RequestParam String description,@RequestParam String genre,@RequestParam String score,@RequestParam String date,
	@RequestParam String info,@RequestParam String img , HttpServletRequest request) {
		if(name=="" || company=="" || description=="" || genre=="" || score=="" || date=="" || info=="" || img=="") {
			System.out.println("One of the gap is empty");
			usuario.setAttribute("alert","<script type=\"text/javascript\">" + "alert('Operation fail: One of the gap is empty');" + "window.location = '/admin'; " + "</script>");		
        	fillModel(model,usuario,request);
        	
        	return "admin";
		}
		
		List<Game> listg=gameRepository.findAll();
		
		for(Game g:listg) {			
			if(g.getName().equals(name)) {
				System.out.println("Name repeated");
				usuario.setAttribute("alert","<script type=\"text/javascript\">" + "alert('Operation fail: Name repeated');" + "window.location = '/admin'; " + "</script>");		
				fillModel(model,usuario,request);
				return "admin";
			}
		}
		
		List<Company> listc=companyRepository.findAll();
		for(Company c:listc) {			
			if(c.getName().equals(company)) {				
				Game newGame=new Game(name,c,genre,description,Integer.parseInt(date),Double.parseDouble(score),img,info);
				gameRepository.save(newGame);
				
				usuario.setAttribute("alert"," ");
				fillModel(model,usuario,request);
				return "admin";	
			}
		}
		System.out.println("Company doesn't exist");
		usuario.setAttribute("alert","<script type=\"text/javascript\">" + "alert('Operation fail: Company doesn't exist');" + "window.location = '/admin'; " + "</script>");		
		fillModel(model,usuario,request);
		return "admin";
	}	
}
