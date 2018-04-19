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
public class companyController {
	// ----------------------------- INYECCIONES --------------------------------------	
	// repositorio de la tabla compañias
	@Autowired
	private CompanyRepository companyRepository;
	// ----------------------------- FIN INYECCIONES ----------------------------------
	
	// ----------------------------- COMPAÑIA -----------------------------------------
	@RequestMapping("/company/{company_name}")
	public String company (Model model, HttpSession usuario, @PathVariable String company_name, HttpServletRequest request){
				
		// se adquiere la lista de juegos que contiene el nombre
		List <Company> companies = companyRepository.findByName(company_name);
		
		// se adquieren sus atributos
		String name = companies.get(0).getName();
		String country = companies.get(0).getCountry();
		String description = companies.get(0).getDescription();
		int year = companies.get(0).getYear();
		String image = companies.get(0).getImage();
		String url = companies.get(0).getUrl();		
				
		// gestion de commentarios del juego
		List<String> list=new ArrayList<String>();
		String div="<div class=\"com\"><div class=\"commentsUser \"><img class=\"comment_img\" src=\"%s\"></img>%s</div><div class=\"Date\">%s</div></div>\r\n" +  "     <div class=\"comments \">%s</div>"	+ "</div><br>";
		
		// si hay comentarios en el juego
		if(companies.get(0).getComment().size()>0) {
			List<Comment> list_comments=companies.get(0).getComment();			
				
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
		
		// se transmiten los atributos a la plantilla
		model.addAttribute("name_g", name);
		model.addAttribute("country", country);
		model.addAttribute("description", description);
		model.addAttribute("year", year);
		model.addAttribute("image", image);
		model.addAttribute("url", url);
		
		// variables referentes a la barra de navegacion
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
		
		//se accede a compañia
		return "company";
	}
	// ----------------------------- FIN COMPAÑIA -------------------------------------
	
	// ----------------------------- LISTA DE COMPAÑIAS -------------------------------
	@RequestMapping("/company_list/{mode}")
	public String company_list (Model model, HttpSession usuario, @PathVariable String mode, HttpServletRequest request) {
		List<String> list=new ArrayList<String>();		
		String div="<div class=\"col-md-3\">\r\n" + "<div class=\"Game\">\r\n" + "<img src=\"%s\" class=\"imagen\">\r\n" + 	"      <a href=\"%s\" style=\"text-align:center;display:block; color:  rgb(33, 73, 138);\">%s</a>\r\n" + "     \r\n" + "    </div>\r\n" + "  </div>";
		
		// lista de todas las compañias disponibles
		//List<Company> list_company = null;
		List<Company> list_company=companyRepository.findAll();
		switch(mode) {
			case "n":
				list_company=companyRepository.findAll();
				break;
			case "letter_up":
				list_company = companyRepository.findByNameAsc();
				break;
			case "letter_down":
				list_company = companyRepository.findByNameDesc();
				break;
			case "year_down":
				list_company = companyRepository.findByYearAsc();
				break;
			case "year_up":
				list_company = companyRepository.findByYearDesc();
				break;
		}
		
		for(int i=0;i<list_company.size();i++) {
			//Aqui accederiamos a la base de datos para cambiar en cada iteracion las variables
			String Url=list_company.get(i).getImage();
			String Titulo=list_company.get(i).getName();
			String link="/company/" + Titulo;			
			//Copiamos el div que queremos poner en el documento html en una variable auxiliar
			//Le damos formato a la variable auxiliar y la añadimos a la lista
			String aux=String.format(div, Url, link, Titulo);
			list.add(aux);			
		}
		
		// se transmite la lista a la plantilla
		model.addAttribute("company", list);
		
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
				
		// se accede a la pagina que expone todas las comapañias
		return "company_list";
	}	
	// ----------------------------- FIN LISTA DE COMPAÑIAS ---------------------------

}
