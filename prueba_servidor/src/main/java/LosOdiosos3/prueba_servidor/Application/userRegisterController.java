package LosOdiosos3.prueba_servidor.Application;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import Socket.MailSender;

@Controller
public class userRegisterController {
	// ----------------------------- INYECCIONES --------------------------------------	
	// repositorio de la tabla usuarios 
	@Autowired
	private UserRepository userRepository;	
	
	@Value("${dad.servicio.url}")
	private String urlServicio;
	
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
			return "new_user";
		}
		usur = userRepository.findByEmail(user.getEmail());
		if(usur.size() > 0) {
			model.addAttribute("alert", "<script type=\"text/javascript\">" + "alert('email alredy getted');" + "window.location = 'new_user.html'; " + "</script>");		
			return "new_user";
		}
		List<String> icons = Arrays.asList("https://mir-s3-cdn-cf.behance.net/project_modules/disp/bb3a8833850498.56ba69ac33f26.png",
				"https://mir-s3-cdn-cf.behance.net/project_modules/disp/1bdc9a33850498.56ba69ac2ba5b.png", "https://mir-s3-cdn-cf.behance.net/project_modules/disp/bf6e4a33850498.56ba69ac3064f.png",
				"https://mir-s3-cdn-cf.behance.net/project_modules/disp/64623a33850498.56ba69ac2a6f7.png", "https://mir-s3-cdn-cf.behance.net/project_modules/disp/e70b1333850498.56ba69ac32ae3.png",
				"https://mir-s3-cdn-cf.behance.net/project_modules/disp/84c20033850498.56ba69ac290ea.png", "http://blogs.studentlife.utoronto.ca/lifeatuoft/files/2015/02/FullSizeRender.jpg",
				"https://i.pinimg.com/474x/c3/53/7f/c3537f7ba5a6d09a4621a77046ca926d--soccer-quotes-lineman.jpg");	
		
		// se da por registrado al usuario
		User nuevo = new User(user.getName(), user.getPassword(), user.getDate(), user.getEmail(),"ROLE_USER");
		nuevo.setIcon(icons.get((int)Math.random()*6));
		
		//Envio mensaje rest al MailGamesInfo
		int arroba = user.getEmail().indexOf("@");
		String uMail = user.getEmail().substring(0, arroba);
		String ext = user.getEmail().substring(arroba+1, user.getEmail().length());

		int punto = ext.indexOf(".");
		String server = ext.substring(0, punto);
		ext = ext.substring(punto + 1, ext.length());
				
		String urlFinal = urlServicio + "/user/" + user.getName() + "/mail/" + uMail + "/"+ server +"/" + ext;

		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getForObject(urlFinal, String.class);
		//Fin de comunicacion
		
		userRepository.save(nuevo);
		
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
