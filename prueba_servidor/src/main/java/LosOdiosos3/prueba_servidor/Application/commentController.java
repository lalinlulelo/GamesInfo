package LosOdiosos3.prueba_servidor.Application;

import java.util.Arrays;
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
public class commentController {
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

	// ----------------------------- COMENTARIOS  -------------------------------------
	@RequestMapping("/comment/{page}")
	public String comment (Model model, HttpSession usuario,@RequestParam String text, @RequestParam String name, @PathVariable String page, HttpServletRequest request) {	
		String ret = null;		
		// comentario de juego, 
		if(page.equals("game")) {			
			Comment c = new Comment((User)usuario.getAttribute("Usuario"),text,new Date());
			// se coge el juego donde se ha realizado el comentario
			Game game= gameRepository.findByName(name).get(0);		
			// se guarda el juego dentro del objeto comentario y se guarda el comentario en la BBDD
			c.setGame(game);
			commentRepository.save(c);
			// se guarda el comentario dentro del juego y se guarda el juego em la BBDD
			game.setComment(c);
			gameRepository.save(game);
			// se retorna al juego
			ret= "/game/"+game.getName();
		// comentario de compañia
		}else if(page.equals("company")) {				
			Comment c = new Comment((User)usuario.getAttribute("Usuario"),text,new Date());			
			// se coge el juego donde se ha realizado el comentario
			Company company= companyRepository.findByName(name).get(0);		
			// se guarda el juego dentro del objeto comentario y se guarda el comentario en la BBDD
			c.setCompany(company);
			commentRepository.save(c);
			// se guarda el comentario dentro del juego y se guarda el juego em la BBDD
			company.setComment(c);
			companyRepository.save(company);
			// se retorna al juego
			ret="/company/"+company.getName();
		// comentario de evento
		}else if(page.equals("event")) {
			Comment c = new Comment((User)usuario.getAttribute("Usuario"),text,new Date());				
			// se coge el juego donde se ha realizado el comentario
			Event event= eventRepository.findByName(name).get(0);		
			// se guarda el juego dentro del objeto comentario y se guarda el comentario en la BBDD
			c.setEvent(event);
			commentRepository.save(c);
			// se guarda el comentario dentro del juego y se guarda el juego em la BBDD
			event.setComment(c);
			eventRepository.save(event);
			// se retorna al juego
			ret="/event/"+event.getName();
		}			
			
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
		
		return ret;
		// se crea un comentario con el usuario y el texto introducido
		
	}
	// ----------------------------- FIN COMENTARIOS  ---------------------------------

}
