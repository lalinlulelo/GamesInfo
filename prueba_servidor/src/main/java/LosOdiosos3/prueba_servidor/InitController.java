package LosOdiosos3.prueba_servidor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
//tag de la pagina: localhost:8080/init
@Controller
public class InitController {
	
	@RequestMapping("/init")
	public String init (Model model) {
		model.addAttribute("name","World");
		
		return "init_template";
	}
}
