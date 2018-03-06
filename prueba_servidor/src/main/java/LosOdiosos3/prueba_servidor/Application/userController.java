package LosOdiosos3.prueba_servidor.Application;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import Socket.MailSender;

@Controller
public class userController {
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

	// ----------------------------- REGISTRAR NUEVO USUARIO --------------------------
	@RequestMapping("/new_user")
	public String new_user (Model model, HttpServletRequest request) {
		model.addAttribute("alert", " ");
		// atributos del token
		CsrfToken token = (CsrfToken) request.getAttribute("_csrf");
		model.addAttribute("token", token.getToken());
		
		return "new_user";
	}
	
	@PostMapping(value = "/register")
	public String registrar(Model model, User user, HttpSession usuario, HttpServletRequest request) {
		MailSender.mailSender((String)usuario.getAttribute("name"), (String)usuario.getAttribute("email"));
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
		model.addAttribute("Titulo", "Latest News");

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
				
		// atributos del token
		CsrfToken token = (CsrfToken) request.getAttribute("_csrf");
		model.addAttribute("token", token.getToken());
		
		return "/";
	}
	// ----------------------------- FIN REGISTRAR NUEVO USUARIO ----------------------
	
	// ----------------------------- INICIO DE SESION ---------------------------------
	@RequestMapping("/login/{loged}")
	public String iniciar_sesion (Model model, UsernamePasswordAuthenticationToken user, HttpSession usuario, @PathVariable String loged, HttpServletRequest request) {
		if(loged.equals("true")) {
			User usur = userRepository.findByName(user.getName()).get(0);
			// se registra el usuario
			usuario.setAttribute("registered", true);
			usuario.setAttribute("name", usur.getName());
			usuario.setAttribute("password", usur.getPassword());
			usuario.setAttribute("date", usur.getDate());
			usuario.setAttribute("icon", usur.getIcon());
			usuario.setAttribute("email", usur.getEmail());
			
			// se guarda un objeto User
			User newUser=userRepository.findByName((String)usuario.getAttribute("name")).get(0);
			usuario.setAttribute("Usuario", newUser);
			
			// se deshabilita el alert
			model.addAttribute("alert", "  ");	
			model.addAttribute("Titulo", "Latest News");
			
			// articulos relevantes
			model.addAttribute("news", articles ());
			
			
			// se muestra el link de iniciar/registrar usuario si es false										
			model.addAttribute("registered", usuario.getAttribute("registered"));
			boolean aux = !(Boolean) usuario.getAttribute("registered");
			model.addAttribute("unregistered", aux);
			model.addAttribute("name", usuario.getAttribute("name"));
			model.addAttribute("hello", "<script type=\"text/javascript\">" + "alert('welcome " + usuario.getAttribute("name") + "!');"  + "</script>");
			model.addAttribute("profile_img",String.format("<img src=\"%s\" class=\"profile_img\">",(String) usuario.getAttribute("icon")));
			
			// atributos del token
			CsrfToken token = (CsrfToken) request.getAttribute("_csrf");
			model.addAttribute("token", token.getToken());
			
			return "index";
		}
		// se guardan los atributos en el modelo
		model.addAttribute("Titulo", "Latest News");
		// articulos relevantes
		List<Article> articles = articleRepository.findAll();
		String news = "";
		if(articles.size() > 0) {
			String div ="<div class=\"card p-3 col-12 col-md-6 col-lg-4\">\r\n" + 	"<div class=\"card-wrapper\">\r\n" + 	"                <div class=\"card-img\">\r\n" + "                    <img src=\"  %s  \" alt=\"Mobirise\" title=\"\" media-simple=\"true\">\r\n" + "                </div>\r\n" + 	"                <div class=\"card-box\">\r\n" + 	"                    <h4 class=\"card-title pb-3 mbr-fonts-style display-7\">  %s  </h4>\r\n" + 	"                    <p class=\"mbr-text mbr-fonts-style display-7\">\r\n" + 	"                        %s  <a href=\"  %s  \">   Learn more...</a>\r\n" + 	"                    </p>\r\n" + 		"                </div>\r\n" + 		"            </div>\r\n" + 		"        </div>";			
			for(int j = 0; j < articles.size(); j++) {
				String Url= articles.get(j).getImage();
				String Titulo = articles.get(j).getTitle();	
				String Head = articles.get(j).getHead();
				String link="/article/" + Titulo;
	
	
				String art = String.format(div, Url, Titulo, Head, link);			
				news += art;			
			}	
		}
		model.addAttribute("news", news);
	
		model.addAttribute("alert", "<script type=\"text/javascript\">" + "alert('User or password incorrect');" + "window.location = '/'; " + "</script>");		
		model.addAttribute("name", " ");		
		model.addAttribute("hello", " ");
		
		// atributos del token
		CsrfToken token = (CsrfToken) request.getAttribute("_csrf");
		model.addAttribute("token", token.getToken());
		
		// se dirige a la pagina como iniciado
		return "index";
	}
	/*
	@RequestMapping("/log_out")
	public String log_out (Model model, HttpSession usuario, HttpServletRequest request) {
		usuario.setAttribute("registered", false);
		// se guardan los atributos en el modelo
		model.addAttribute("Titulo", "Latest News");
		model.addAttribute("alert", "Good Bye");		
		model.addAttribute("name", " ");
		
		// articulos relevantes	
		model.addAttribute("news", articles ());
		
		// se muestra el link de iniciar/registrar usuario si es false
		model.addAttribute("registered", usuario.getAttribute("registered"));
		boolean aux = !(Boolean) usuario.getAttribute("registered");
		model.addAttribute("unregistered", aux);
		model.addAttribute("name", usuario.getAttribute("name"));
		model.addAttribute("profile_img",String.format("<img src=\"%s\" class=\"profile_img\">",(String) usuario.getAttribute("icon")));
		model.addAttribute("hello", "<script type=\"text/javascript\">" + "alert('See you soon " + usuario.getAttribute("name") + "!');"  + "</script>");
		
		// atributos del token
		CsrfToken token = (CsrfToken) request.getAttribute("_csrf");
		model.addAttribute("token", token.getToken());
		
		return "/";
	}*/
	// ----------------------------- FIN INICIO DE SESION -----------------------------
	
	// ----------------------------- PERFIL DE USUARIO --------------------------------
	@RequestMapping("/profile")
	public String init (Model model, HttpSession usuario, HttpServletRequest request) {		
		//MailSender.mailSender((String)usuario.getAttribute("name"), (String)usuario.getAttribute("email"));
		// se cogen del usuario los atributos
		String name = (String) usuario.getAttribute("name");
		String password = (String) usuario.getAttribute("password");
		String date = (String) usuario.getAttribute("date");
		String icon = (String) usuario.getAttribute("icon");
		String email = (String) usuario.getAttribute("email");
		
		// se mandan los datos al modelo
		model.addAttribute("name", name);
		model.addAttribute("password", "*******");
		model.addAttribute("date", date);	
		model.addAttribute("icon", icon);
		model.addAttribute("email", email);
		model.addAttribute("alert"," ");	

		
		// se muestra el link de iniciar/registrar usuario si es false
		model.addAttribute("registered", usuario.getAttribute("registered"));
		boolean aux = !(Boolean) usuario.getAttribute("registered");
		model.addAttribute("unregistered", aux);
		model.addAttribute("name", usuario.getAttribute("name"));
		model.addAttribute("profile_img",String.format("<img src=\"%s\" class=\"profile_img\">",(String) usuario.getAttribute("icon")));

		model.addAttribute("lists", " ");
		
		// atributos del token
		CsrfToken token = (CsrfToken) request.getAttribute("_csrf");
		model.addAttribute("token", token.getToken());
		
		// se devuelve el nombre de la lista, siendo el PERFIL del usuario
		return "profile";
	}
	// ----------------------------- FIN PERFIL DE USUARIO ----------------------------	

	// ----------------------------- AJUSTES PERFIL -----------------------------------
	@RequestMapping("/change/{field}")
	public String change (Model model, HttpSession usuario, @PathVariable String field, @RequestParam String text, @RequestParam String text_1, @RequestParam String text_2, HttpServletRequest request) {		
		List<User> usurs = userRepository.findByName((String)usuario.getAttribute("name"));		
		User usur=null; 
		if(usurs.size() > 0){
			usur=usurs.get(0);
			switch(field) {	
				case "name":		
					// se comprueba que no se repita
					List<User> list=(List<User>) userRepository.findAll();		
					if(list.size() > 0){		
						for(int i=0;i<list.size();i++) {					
							if(list.get(i).getName().equals(text)) {
								model.addAttribute("alert", "<script type=\"text/javascript\">" + "alert('Error, el nombre introducido está asociado a otra cuenta');" +  "</script>");		
								
								modelAttrChange(usur,usuario,model);	
								return "profile";
							}					
						}			
						usur.setName(text);
						userRepository.save(usur);
						((User)usuario.getAttribute("Usuario")).setName(text);
						usuario.setAttribute("name", text);	
					}		
					break;
				

			
				
				case "password":
					if(!new BCryptPasswordEncoder().matches(text, usur.getPassword())) {
						System.out.println("mal metido");
						model.addAttribute("alert", "<script type=\"text/javascript\">" + "alert('one of the field was incorrect');" +  "</script>");		
						modelAttrChange(usur,usuario,model);	
						return "profile";
					}
					if(!text_1.equals(text_2)) {
						model.addAttribute("alert", "<script type=\"text/javascript\">" + "alert('one of the field was incorrect');" +  "</script>");		
						modelAttrChange(usur,usuario,model);	
						return "profile";
					}
					String password = new BCryptPasswordEncoder().encode(text_1);
					usur.setPassword(password);
					userRepository.save(usur);
					usuario.setAttribute("password", password);
					((User)usuario.getAttribute("Usuario")).setPassword(password);
					break;

				
				case "birthday":
					usur.setDate(text);
					userRepository.save(usur);
					usuario.setAttribute("date", text);
					((User)usuario.getAttribute("Usuario")).setDate(text);
					break;
				
				case "email":
					// se comprueba que no se repita
			        List<User> listus=(List<User>) userRepository.findAll();			
			        if(listus.size() > 0){		        		
						for(int i=0;i<listus.size();i++) {					
							if(listus.get(i).getEmail().equals(text)) {
								model.addAttribute("alert", "<script type=\"text/javascript\">" + "alert('Error, el email introducido está asociado a otra cuenta');" +  "</script>");		

								modelAttrChange(usur,usuario,model);	
								return "profile";
							}					
						}					
						usur.setEmail(text);
						userRepository.save(usur);
						usuario.setAttribute("email", text);
						((User)usuario.getAttribute("Usuario")).setEmail(text);
					}
					break;
			}		
			modelAttrChange(usur, usuario, model);
		}
		model.addAttribute("alert","<script type=\"text/javascript\">" + "alert('Cambio realizado con éxito');" +  "</script>");	
		
		modelAttrChange(usur,usuario,model);
			
		// atributos del token
		CsrfToken token = (CsrfToken) request.getAttribute("_csrf");
		model.addAttribute("token", token.getToken());
		
		return"profile";
	}
	
	
	
	//Metodo que añade los atributos a change
	private void modelAttrChange(User usur, HttpSession usuario, Model model) {
		String name = (String) usur.getName();
		String password = (String) usur.getPassword();
		String date = (String) usur.getDate();
		String icon = (String) usur.getIcon();
		String email = (String) usur.getEmail();
		
		// se mandan los datos al modelo
		model.addAttribute("name", name);
		model.addAttribute("password", "*******");
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
	}	
	// ----------------------------- FIN AJUSTES PERFIL --------------------------------
	
	// ---------------------------- METODOS AUXILIARES --------------------------------
	public String articles () {
		String article = "";
		List<Article> articles = articleRepository.findAll();
		if(articles.size() > 0) {
			String div ="<div class=\"row\">\r\n" + "<div class=\"col-sm-1 col-md-1\"> </div>	"  +  "<div class=\"card p-3\">\r\n"  + 	"<div class=\"card-wrapper\">\r\n" + 	"                <div class=\"card-img  col-xs-12 col-sm-4 col-md-4\">\r\n" + "                    <img src=\"  %s  \" alt=\"Mobirise\" title=\"\" media-simple=\"true\">\r\n" + "                </div>\r\n" + 	"                <div class=\"card-box col-xs-12 col-sm-3 col-md-3\">\r\n" + 	"                    <h4 class=\"card-title pb-3 mbr-fonts-style display-7\">  %s  </h4>\r\n" + 	"                    <p class=\"mbr-text mbr-fonts-style display-7\">\r\n" + 	"                        %s  <a href=\"  %s  \">   Read more...</a>\r\n" + 	"                    </p>\r\n" + 		"                </div>\r\n" + 		"            </div>\r\n" +   "            </div>\r\n"	+ 	"        </div>";			
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
	// ---------------------------- FIN METODOS AUXILIARES ----------------------------
}
