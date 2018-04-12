package LosOdiosos3.prueba_servidor.Application;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
 
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
	
	@Autowired
	private ArticleRepository articleRepository;
	
	@Autowired
	private UserRepository userRepository;
	// ----------------------------- FIN INYECCIONES ----------------------------------
	
	// ---------------------------------- ADMIN ---------------------------------------
	@RequestMapping("/admin")
	public String addGame (Model model, HttpSession usuario, HttpServletRequest request) {						
		// se rellena el navbar
		fillModel(model,usuario,request);
		// se retorna a la pagina de admin
		return "admin";			
	}		
	// ---------------------------------- FIN ADMIN -----------------------------------
	
	// ---------------------------------- BORRAR --------------------------------------
	// función borrar juego
	@RequestMapping("/deleteGame")
	public String deleteGame (Model model, HttpSession usuario, HttpServletRequest request, @RequestParam String Game) {
		// se adquieren todos los juegos
		List<Game> list = gameRepository.findByName(Game);	
		// se borra del repositorio el juego seleccionado
		gameRepository.delete(list.get(0));		
		// se rellena el navbar
		fillModel(model,usuario,request);
		// se retorna a la pagina de admin
		return "admin";
	}
	
	// función borrar compañía
	@RequestMapping("/deleteCompany")
	public String deleteCompany (Model model, HttpSession usuario, HttpServletRequest request, @RequestParam String Company) {
		// se adquieren todas las compañías
		List<Company> list = companyRepository.findByName(Company);
		// se borra del repositorio la compañía seleccionada
		companyRepository.delete(list.get(0));
		// se rellena el navbar
		fillModel(model,usuario,request);
		// se retorna a la página de admin
		return "admin";
	}
	
	// función borrar evento
	@RequestMapping("/deleteEvent")
	public String deleteEvent (Model model, HttpSession usuario, HttpServletRequest request, @RequestParam String Event) {
		// se adquieren todos los eventos
		List<Event> list = eventRepository.findByName(Event);
		// se borra del repositorio el evento seleccionado
		eventRepository.delete(list.get(0));
		// se rellena el navbar
		fillModel(model,usuario,request);
		// se retorna a la página de admin
		return "admin";
	}
	
	// función borrar evento
	@RequestMapping("/deleteArticle")
	public String deleteArticle (Model model, HttpSession usuario, HttpServletRequest request, @RequestParam String Article) {
		// se adquieren todos los eventos
		List<Article> list = articleRepository.findByTitle(Article);
		// se borra del repositorio el evento seleccionado
		articleRepository.delete(list.get(0));
		// se rellena el navbar
		fillModel(model,usuario,request);
		// se retorna a la página de admin
		return "admin";
	}
	// ---------------------------------- FIN BORRAR ----------------------------------
	
	// --------------------------- FUNCIONES AUXILIARES -------------------------------
	// rellena el navbar de la página admin
	public void fillModel(Model model,HttpSession usuario,HttpServletRequest request) {
		// se cogen todos los elementos de juegos, compañías y eventos
		List<Game> games=gameRepository.findAll();
		List<Company> companies=companyRepository.findAll();
		List<Event> events=eventRepository.findAll();
		List<Article> articles=articleRepository.findAll();
		// se crean listas para abarcar las cajas desplegables
		List<String>listGames=new ArrayList<String>();
		List<String>listCompanies=new ArrayList<String>();
		List<String>listEvents=new ArrayList<String>();		
		List<String>listArticles=new ArrayList<String>();	
		
		// se cargan los juegos en la lista desplegable
		for(Game g:games) {
			String name=g.getName();			
			String aux=String.format("<option value=\"%s\">%s</option>", name,name);
			listGames.add(aux);
		}		
		model.addAttribute("listGames", listGames);
		
		// se cargan las compañías en la lista desplegable
		for(Company c:companies) {
			String name=c.getName();			
			String aux=String.format("<option value=\"%s\">%s</option>", name,name);
			listCompanies.add(aux);
		}		
		model.addAttribute("listCompanies", listCompanies);
		
		// se cargan los eventos en la lista desplegable
		for(Event e:events) {
			String name=e.getName();			
			String aux=String.format("<option value=\"%s\">%s</option>", name,name);
			listEvents.add(aux);
		}		
		model.addAttribute("listEvents", listEvents);
		
		// se cargan los articulos en la lista desplegable
		for(Article a:articles) {
			String name=a.getTitle();			
			String aux=String.format("<option value=\"%s\">%s</option>", name,name);
			listArticles.add(aux);
		}		
		model.addAttribute("listArticles", listArticles);
		
		// se pasan los atributos de la barra de navegacion
		model.addAttribute("registered", usuario.getAttribute("registered"));
		boolean aux = !(Boolean) usuario.getAttribute("registered");
		model.addAttribute("unregistered", aux);
		model.addAttribute("name", usuario.getAttribute("name"));
		model.addAttribute("profile_img",String.format("<img src=\"%s\" class=\"profile_img\">",(String) usuario.getAttribute("icon")));
		model.addAttribute("alert", usuario.getAttribute("alert"));
		model.addAttribute("hello", " ");
		model.addAttribute("Titulo", " ");
		model.addAttribute("Cuerpo", " ");	
		
		// para activar admin
		model.addAttribute("admin", usuario.getAttribute("admin"));			
		
		// atributos del token
		CsrfToken token = (CsrfToken) request.getAttribute("_csrf");
		model.addAttribute("token", token.getToken());		
	}
	// --------------------------- FIN FUNCIONES AUXILIARES ---------------------------
	
	// ------------------------------------ AÑADIR ------------------------------------
	// función de añadir compañía
	@RequestMapping("/addCompany")
	public String addCompany (Model model, HttpSession usuario, @RequestParam String name,@RequestParam String country,
	@RequestParam String description,@RequestParam String year,@RequestParam String img ,@RequestParam String url, HttpServletRequest request){	
		// se comprueba que ningún campo se quede vacío, sino se notifica con un alert
		if(name=="" || country=="" || description=="" || year=="" || img=="" || url=="") {
			usuario.setAttribute("alert","<script type=\"text/javascript\">" + "alert('Operation fail: One of the gap is empty');"  + "</script>");		
        	fillModel(model,usuario,request);
        	return "admin";
		}		
		
		try {					
			// se adquiere la lista de compañías
			List<Company> listc = companyRepository.findAll();
			
			// se comprueba que el objeto introducido no exista ya, sino se notifica con un alert
			for(Company c:listc) {			
				if(c.getName().equals(name)) {
					usuario.setAttribute("alert","<script type=\"text/javascript\">" + "alert('Operation fail: Name repeated');"  + "</script>");		
					fillModel(model,usuario,request);
					return "admin";
				}
			}							
			
			System.out.println("Company added fase 1");
			// se crea la compañía y se añade al repositorio
			Company newCompany=new Company(name,country,description,Integer.parseInt(year),img,url);
			companyRepository.save(newCompany);
			System.out.println("company added fase 2");
			
			// se rellena el navbar y se desactiva la alerta
			usuario.setAttribute("alert"," ");		
			fillModel(model,usuario,request);
			
			// se retorna a admin
			return "admin";			
		}catch(NumberFormatException ex){
			// se rellena el navbar
        	fillModel(model,usuario,request);    
        	// se retorna a admin
        	return "admin";
    	}		
	}
	
	// función de añadir evento
	@RequestMapping("/addEvent")
	public String addEvent (Model model, HttpSession usuario, @RequestParam String name,@RequestParam String place,
	@RequestParam String description,@RequestParam String fee,@RequestParam String day,@RequestParam String month, @RequestParam String year, @RequestParam String img, HttpServletRequest request) {
		// se comprueba que ningún campo se quede vacío, sino se notifica con un alert
		if(name=="" || place=="" || description=="" || fee=="" || img=="" || day=="" || month=="" || year=="" || img=="") {
			usuario.setAttribute("alert","<script type=\"text/javascript\">" + "alert('Operation fail: One of the gap is empty');" + "</script>");		
        	fillModel(model,usuario,request);        	
        	return "admin";
		}
		try {			
			// se adquiere la lista de eventos
			List<Event> liste=eventRepository.findAll();
			
			// se comprueba que el objeto introducido no exista ya, sino se notifica con un alert
			for(Event e:liste) {			
				if(e.getName().equals(name)) {
					usuario.setAttribute("alert","<script type=\"text/javascript\">" + "alert('Operation fail: Name repeated');" + "</script>");		
					fillModel(model,usuario,request);
					return "admin";
				}
			}		
			
			// se crea el evento y se añade al repositorio
			Event newEvent=new Event(name,place,Integer.parseInt(year),Integer.parseInt(month),Integer.parseInt(day),Double.parseDouble(fee),description,img);
			eventRepository.save(newEvent);
			
			// se rellena el navbar y se desactiva la alerta
			usuario.setAttribute("alert"," ");
			fillModel(model,usuario,request);
			
			// se retorna a admin
			return "admin";	
		}catch(NumberFormatException ex){
			// se rellena el navbar
        	fillModel(model,usuario,request);    
        	// se retorna a admin
        	return "admin";
    	}	
	}
	
	@RequestMapping("/addGame")
	public String addGame (Model model, HttpSession usuario, @RequestParam String name,@RequestParam String company,
	@RequestParam String description,@RequestParam String genre,@RequestParam String score,@RequestParam String year,
	@RequestParam String info,@RequestParam String img , HttpServletRequest request) {
		// se comprueba que ningún campo se quede vacío, sino se notifica con un alert
		if(name=="" || company=="" || description=="" || genre=="" || score=="" || year=="" || info=="" || img=="") {
			usuario.setAttribute("alert","<script type=\"text/javascript\">" + "alert('Operation fail: One of the gap is empty');" + "</script>");		
        	fillModel(model,usuario,request);        	
        	return "admin";
		}
		try {
			// se adquiere la lista de juegos
			List<Game> listg = gameRepository.findAll();
			
			// se comprueba que el objeto introducido no exista ya, sino se notifica con un alert
			for(Game g:listg) {			
				if(g.getName().equals(name)) {
					usuario.setAttribute("alert","<script type=\"text/javascript\">" + "alert('Operation fail: Name repeated');" + "</script>");		
					fillModel(model,usuario,request);
					return "admin";
				}
			}
			
			// se comprueba que la compañía del juego exista
			List<Company> listc=companyRepository.findAll();
			for(Company c:listc) {	
				System.out.println("iterando bucle");
				if(c.getName().equals(company)) {				
					Game newGame=new Game(name,c,genre,description,Integer.parseInt(year),Double.parseDouble(score),img,info);
					gameRepository.save(newGame);
					
					System.out.println("Game added");
					
					usuario.setAttribute("alert"," ");
					fillModel(model,usuario,request);
					return "admin";	
				}
			}
			
			// se rellena el navbar y se desactiva la alerta
			System.out.println("Compania no existente");
			usuario.setAttribute("alert","<script type=\"text/javascript\">" + "alert('Operation fail: Company doesn't exist');" + "</script>");		
			fillModel(model,usuario,request);
			
			// se retorna a admin
			return "admin";
		}catch(NumberFormatException ex){
			// se rellena el navbar
        	fillModel(model,usuario,request);    
        	// se retorna a admin
        	return "admin";
    	}
	}	
	
	// función de añadir evento
	@RequestMapping("/addArticle")
	public String addArticle (Model model, HttpSession usuario, UsernamePasswordAuthenticationToken user, @RequestParam String title,
	@RequestParam String head,@RequestParam String body,@RequestParam String image, HttpServletRequest request) {
		// se comprueba que ningún campo se quede vacío, sino se notifica con un alert
		if(title=="" || head=="" || body=="" || image=="") {
			usuario.setAttribute("alert","<script type=\"text/javascript\">" + "alert('Operation fail: One of the gap is empty');" + "</script>");		
        	fillModel(model,usuario,request);        	
        	return "admin";
		}
		try {			
			// se adquiere la lista de articulos
			List<Article> lista=articleRepository.findAll();
			
			// se comprueba que el objeto introducido no exista ya, sino se notifica con un alert
			for(Article a:lista) {			
				if(a.getTitle().equals(title)) {
					usuario.setAttribute("alert","<script type=\"text/javascript\">" + "alert('Operation fail: Name repeated');" + "</script>");		
					fillModel(model,usuario,request);
					return "admin";
				}
			}		
			
			User usur = userRepository.findByName(user.getName()).get(0);
			
			// se crea el articulo y se añade al repositorio
			Article newArticle=new Article(usur,title,head,body,image);
			articleRepository.save(newArticle);
			
			// se rellena el navbar y se desactiva la alerta
			usuario.setAttribute("alert"," ");
			fillModel(model,usuario,request);
			
			// se retorna a admin
			return "admin";	
		}catch(NumberFormatException ex){
			// se rellena el navbar
        	fillModel(model,usuario,request);    
        	// se retorna a admin
        	return "admin";
    	}	
	}
}
