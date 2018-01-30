package LosOdiosos3.prueba_servidor;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class InitController {
	// tag de la pagina: localhost:8080/init
	@RequestMapping("/init")
	public String init (Model model) {
		model.addAttribute("name","World");
		
		return "init_template";
	}
	/*
	// inicio de sesion
	@RequestMapping("/ruta_controlador")
	public String Formulario (HttpSession session, ) {
		session.setAttribute("info", info);
		return "template";
	}*/
}
