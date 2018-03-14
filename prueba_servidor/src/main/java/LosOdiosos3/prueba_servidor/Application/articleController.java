package LosOdiosos3.prueba_servidor.Application;


import java.util.ArrayList;
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

@Controller
public class articleController {	
	// ----------------------------- INYECCIONES --------------------------------------	
	// repositorio de la tabla anuncios
	@Autowired
	private ArticleRepository articleRepository;	
	// ----------------------------- FIN INYECCIONES ----------------------------------
	
	// ----------------------------- ARTICULOS ----------------------------------------
	@RequestMapping("/article/{news}")
	public String article (Model model, HttpSession usuario, @PathVariable String news, HttpServletRequest request) {
		// se adquiere el articulo seleccionado
		List <Article> articles = articleRepository.findByTitle(news);
		// se adquieren los atributos del artículo
		String head = articles.get(0).getHead();
		String title = articles.get(0).getTitle();
		String article = articles.get(0).getArticle();
		String image = articles.get(0).getImage();
		User user = articles.get(0).getUser();
		
		// gestion de commentarios del artículo
		List<String> list=new ArrayList<String>();
		String div="<div class=\"com\"><div class=\"commentsUser \"><img class=\"comment_img\" src=\"%s\"></img>%s</div><div class=\"Date\">%s</div></div>\r\n" +  "     <div class=\"comments \">%s</div>"	+ "</div><br>";
		
		// si hay comentarios en el artículo
		if(articles.get(0).getComment().size()>0) {
			// se adquiere la lista de comentarios
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
			// se muestra del más reciente al más antiguo
			Collections.reverse(list);
			model.addAttribute("comments", list);
		}else {
			model.addAttribute("comments"," ");
		}		
		
		// se cargan los atributos del articulo en el modelo
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

		//Para activar admin
		model.addAttribute("admin", usuario.getAttribute("admin"));
		
		// atributos del token
		CsrfToken token = (CsrfToken) request.getAttribute("_csrf");
		model.addAttribute("token", token.getToken());
		
		// se retorna a la página de artículo
		return "article";
	}	
	// -------------------------- -- FIN ARTICULOS ------------------------------------

}
