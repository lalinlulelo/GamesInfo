package LosOdiosos3.prueba_servidor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class InitController {
	// identifica la sesion de un usuario
	@Autowired
	private User usuario;
	
	// repositorio de la tabla usuarios
	@Autowired
	private UserRepository repository;
	
	// pagina principal
	@RequestMapping("/")
	public String inicio (Model model) {
		model.addAttribute("Titulo", "Agus guapo");
		model.addAttribute("Cuerpo", "Susa idiota");
		model.addAttribute("registered", usuario.getRegistered());
		//model.addAttribute("sin_registrar", sin_registrar);
		//model.addAttribute("registrado", registrado);
		return "index";
	}
	
	// pagina de registro	
	@PostMapping(value = "/registrar")
	public String registrar(Model model, User_Aux usur) {
		usuario.setName(usur.getName());
		usuario.setPassword(usur.getPassword());
		usuario.setDate(usur.getDate());
		usuario.setRegistered(true);
		repository.save(usuario);
		model.addAttribute("registered", usuario.getRegistered());
		return "index";
	}
	
	// recopila la informacion del usuario y se la pasa a la pagina perfil
	@RequestMapping("/perfil")
	public String init (Model model) {
		String nombre = usuario.getName();
		String password = usuario.getPassword();
		String fecha = usuario.getDate();
		model.addAttribute("nombre", nombre);
		model.addAttribute("password", password);
		model.addAttribute("fecha", fecha);		
		return "perfil";
	}
	
	// juego
	@RequestMapping("/game")
	public String juego (Model model) {
		return "juego";
	}
	
	// compañia
	@RequestMapping("/comany")
	public String company (Model model){
		return "comapny";
	}
	
	// event
	@RequestMapping("/event")
	public String event (Model model) {
		return "event";
	}
	
	
}
