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
public class addGameController {

	
	// ----------------------------- INYECCIONES --------------------------------------	
		// repositorio de la tabla compañias
		@Autowired
		private CompanyRepository companyRepository;

		// repositorio de la tabla juegos
		@Autowired
		private GameRepository gameRepository;
		// ----------------------------- FIN INYECCIONES ----------------------------------
	
	
	@RequestMapping("/addGame")
	public String addGame (Model model, HttpSession usuario, @RequestParam String name,@RequestParam String company,
	@RequestParam String description,@RequestParam String genre,@RequestParam double score,@RequestParam int date,
	@RequestParam String info,@RequestParam String img , HttpServletRequest request) {
		
		
		List<Game> listg=gameRepository.findAll();
		
		for(Game g:listg) {
			
			if(g.getName().equals(name)) {
				fillModel(model,usuario,request);
				return "admin";
			}
			}
		List<Company> listc=companyRepository.findByName(company);
		
		
		Game newGame=new Game(name,listc.get(0),genre,description,date,score,img," ");
		gameRepository.save(newGame);
		
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
