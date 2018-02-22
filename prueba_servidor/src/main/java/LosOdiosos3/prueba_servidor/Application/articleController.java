package LosOdiosos3.prueba_servidor.Application;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/*
 	Controlador de entidad articulo
 */
@Controller
public class articleController {
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
	
	// ----------------------------- ARTICULOS ----------------------------------------
	@RequestMapping("/article/{news}")
	public String article (Model model, HttpSession usuario, @PathVariable String news) {
		List <Article> articles = articleRepository.findByTitle(news);
		String head = articles.get(0).getHead();
		String title = articles.get(0).getTitle();
		String article = articles.get(0).getArticle();
		String image = articles.get(0).getImage();
		User user = articles.get(0).getUser();
		
		// gestion de commentarios del juego
		List<String> list=new ArrayList<String>();
		String div="<div class=\"com\"><div class=\"commentsUser \"><img class=\"comment_img\" src=\"%s\"></img>%s</div><div class=\"Date\">%s</div></div>\r\n" +  "     <div class=\"comments \">%s</div>"	+ "</div><br>";
		
		// si hay comentarios en el juego
		if(articles.get(0).getComment().size()>0) {
			List<Comment> list_comments=articles.get(0).getComment();			
				
			for(int i=0;i<list_comments.size();i++) {
				//Aqui accederiamos a la base de datos para cambiar en cada iteracion las variables
				String User=list_comments.get(i).getUser().getName();
				String Text=list_comments.get(i).getText();
				Date d=list_comments.get(i).getDate();	
				String img=list_comments.get(i).getUser().getIcon();
				//Copiamos el div que queremos poner en el documento html en una variable auxiliar
				//Le damos formato a la variable auxiliar y la añadimos a la lista
				String aux=String.format(div, img,User,d.toString(), Text);						
				list.add(aux);				
			}
			Collections.reverse(list);
			model.addAttribute("comments", list);
		}else {
			model.addAttribute("comments"," ");
		}		
		
		model.addAttribute("head", head);
		model.addAttribute("title", title);
		model.addAttribute("article", article);
		model.addAttribute("image", image);
		model.addAttribute("user", user.getName());
		
		// se muestra el link de iniciar/registrar usuario si es false
		model.addAttribute("registered", usuario.getAttribute("registered"));
		boolean aux = !(Boolean) usuario.getAttribute("registered");
		model.addAttribute("unregistered", aux);
		model.addAttribute("name", usuario.getAttribute("name"));
		model.addAttribute("profile_img",String.format("<img src=\"%s\" class=\"profile_img\">",(String) usuario.getAttribute("icon")));

		return "article";
	}	
	// -------------------------- -- FIN ARTICULOS ------------------------------------

}
