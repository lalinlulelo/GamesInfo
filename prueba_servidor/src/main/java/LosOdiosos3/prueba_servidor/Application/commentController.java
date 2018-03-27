package LosOdiosos3.prueba_servidor.Application;

import java.util.Date;

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
	// ----------------------------- INYECCIONES --------------------------------------		
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
			// se guarda el comentario dentro del juego y se guarda el juego en la BBDD
			game.setComment(c);
			gameRepository.save(game);
			// se retorna al juego
			ret= "/game/"+game.getName();
		// comentario de compañia
		}else if(page.equals("company")) {				
			Comment c = new Comment((User)usuario.getAttribute("Usuario"),text,new Date());			
			// se coge la compania donde se ha realizado el comentario
			Company company= companyRepository.findByName(name).get(0);		
			// se guarda la compania dentro del objeto comentario y se guarda el comentario en la BBDD
			c.setCompany(company);
			commentRepository.save(c);
			// se guarda el comentario dentro de la compania y se guarda la compania en la BBDD
			company.setComment(c);
			companyRepository.save(company);
			// se retorna a la compania
			ret="/company/"+company.getName();
		// comentario de evento
		}else if(page.equals("event")) {
			Comment c = new Comment((User)usuario.getAttribute("Usuario"),text,new Date());				
			// se coge el evento donde se ha realizado el comentario
			Event event= eventRepository.findByName(name).get(0);		
			// se guarda el evento dentro del objeto comentario y se guarda el comentario en la BBDD
			c.setEvent(event);
			commentRepository.save(c);
			// se guarda el comentario dentro del evento y se guarda el evento en la BBDD
			event.setComment(c);
			eventRepository.save(event);
			// se retorna al evento
			ret="/event/"+event.getName();
		// comentario de articulo
		}else if(page.equals("article")) {
			Comment c = new Comment((User)usuario.getAttribute("Usuario"),text,new Date());				
			// se coge el articulo donde se ha realizado el comentario
			Article article= articleRepository.findByTitle(name).get(0);		
			// se guarda el articulo dentro del objeto comentario y se guarda el comentario en la BBDD
			c.setArticle(article);
			commentRepository.save(c);
			// se guarda el comentario dentro del articulo y se guarda el articulo en la BBDD
			article.setComment(c);
			articleRepository.save(article);
			// se retorna al articulo
			ret="/article/"+article.getTitle();
		}				
		
		// se pasan los atributos del navbar al modelo
		model.addAttribute("registered", usuario.getAttribute("registered"));
		boolean aux = !(Boolean) usuario.getAttribute("registered");
		model.addAttribute("unregistered", aux);
		model.addAttribute("name", usuario.getAttribute("name"));
		model.addAttribute("profile_img",String.format("<img src=\"%s\" class=\"profile_img\">",(String) usuario.getAttribute("icon")));

		//Para activar admin
		model.addAttribute("admin", usuario.getAttribute("admin"));
		
		// atributos del index
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
