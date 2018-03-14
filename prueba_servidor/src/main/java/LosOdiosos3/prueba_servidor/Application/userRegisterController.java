package LosOdiosos3.prueba_servidor.Application;
import java.util.Arrays;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

@Controller
public class userRegisterController {
	// ----------------------------- INYECCIONES --------------------------------------	
	// repositorio de la tabla usuarios 
	@Autowired
	private UserRepository userRepository;		
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
			
			// atributos del token
			CsrfToken token = (CsrfToken) request.getAttribute("_csrf");
			model.addAttribute("token", token.getToken());
			
			return "/new_user";
		}
		usur = userRepository.findByEmail(user.getEmail());
		if(usur.size() > 0) {
			model.addAttribute("alert", "<script type=\"text/javascript\">" + "alert('email alredy getted');" + "window.location = 'new_user.html'; " + "</script>");		
			
			// atributos del token
			CsrfToken token = (CsrfToken) request.getAttribute("_csrf");
			model.addAttribute("token", token.getToken());
			
			return "/new_user";
		}
		// iconos disponibles
		List<String> icons = Arrays.asList("https://mir-s3-cdn-cf.behance.net/project_modules/disp/bb3a8833850498.56ba69ac33f26.png",
				"https://mir-s3-cdn-cf.behance.net/project_modules/disp/1bdc9a33850498.56ba69ac2ba5b.png", "https://mir-s3-cdn-cf.behance.net/project_modules/disp/bf6e4a33850498.56ba69ac3064f.png",
				"https://mir-s3-cdn-cf.behance.net/project_modules/disp/64623a33850498.56ba69ac2a6f7.png", "https://mir-s3-cdn-cf.behance.net/project_modules/disp/e70b1333850498.56ba69ac32ae3.png",
				"https://mir-s3-cdn-cf.behance.net/project_modules/disp/84c20033850498.56ba69ac290ea.png", "http://blogs.studentlife.utoronto.ca/lifeatuoft/files/2015/02/FullSizeRender.jpg",
				"https://i.pinimg.com/474x/c3/53/7f/c3537f7ba5a6d09a4621a77046ca926d--soccer-quotes-lineman.jpg");	
		
		// se da por registrado al usuario
		User nuevo = new User(user.getName(), user.getPassword(), user.getDate(), user.getEmail(),"ROLE_USER");
		nuevo.setIcon(icons.get((int)Math.random()*6));
		
		// ------------------------------------- MAIL SERVICE ------------------------------------------
		//Envio mensaje rest al MailGamesInfo
		// numero de caracteres hasta la arroba
		int arroba = user.getEmail().indexOf("@");
		// se coge la primera parte de la dirección
		String uMail = user.getEmail().substring(0, arroba);
		// se coge la extensión del correo
		String ext = user.getEmail().substring(arroba+1, user.getEmail().length());
		
		// numero de caracteres que ocupa la empresa de correo
		int punto = ext.indexOf(".");
		// se coge el servidor de correo
		String server = ext.substring(0, punto);
		// se coge la terminacion (.es, .com, ...)
		ext = ext.substring(punto + 1, ext.length());
		
		// se manda al puerto del mail service la información
		String urlFinal = "http://localhost:8080/" + "/user/" + user.getName() + "/mail/" + uMail + "/"+ server +"/" + ext;
		System.out.println("Usuario con url: " + urlFinal);

		//Aqui es donde exactamente se comunica con el restcontroller
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getForObject(urlFinal, String.class);
		System.out.println("Enviado al MailService.");
		//Fin de comunicacion
		// ------------------------------------- FIN MAIL SERVICE --------------------------------------
		
		// se guarda al nuevo usuario en el repositorio
		userRepository.save(nuevo);
		
		// se cargan los atributos de index
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

}
