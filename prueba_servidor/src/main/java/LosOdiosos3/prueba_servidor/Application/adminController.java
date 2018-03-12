package LosOdiosos3.prueba_servidor.Application;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class adminController {
		@RequestMapping("/admin")
		public String addGame (Model model, HttpSession usuario, HttpServletRequest request) {
			
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

