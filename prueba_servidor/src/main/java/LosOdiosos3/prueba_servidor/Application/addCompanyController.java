package LosOdiosos3.prueba_servidor.Application;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class addCompanyController {

	// repositorio de la tabla compañias
			@Autowired
			private CompanyRepository companyRepository;
			// ----------------------------- FIN INYECCIONES ----------------------------------
		
		
		@RequestMapping("/addCompany")
		public String addGame (Model model, HttpSession usuario, @RequestParam String name,@RequestParam String country,
		@RequestParam String description,@RequestParam int date,@RequestParam String img ,@RequestParam String url, HttpServletRequest request) {
		
		
			List<Company> listc=companyRepository.findAll();
			
			for(Company c:listc) {
				
				if(c.getName().equals(name)) {
					fillModel(model,usuario,request);
					return "admin";
				}
				}
			
			
			
			Company newCompany=new Company(name,country,description,date,img,url);
			companyRepository.save(newCompany);
			
			fillModel(model,usuario,request);
			return "admin";
		
		}
		
	
public void fillModel(Model model,HttpSession usuario,HttpServletRequest request) {		
	// se pasan los atributos de la barra de navegacion
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
	
}
}
