package LosOdiosos3.prueba_servidor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class InitController {
	boolean registrado = false;
	boolean sin_registrar = false;
	@Autowired
	private Usuario usuario;
	
	// pagina principal
	@RequestMapping("/")
	public String inicio (Model model) {
		sin_registrar = true;
		model.addAttribute("sin_registrar", sin_registrar);
		model.addAttribute("registrado", registrado);
		return "index";
	}
	
	// pagina de registro
	
	@PostMapping(value = "/registrar")
	public String registrar(Model model, Inicio usur) {
		sin_registrar = false;
		registrado = true;
		usuario.setNombre(usur.getNombre());
		usuario.setPassword(usur.getPassword());
		usuario.setFecha(usur.getFecha());
		model.addAttribute("sin_registrar", sin_registrar);
		model.addAttribute("registrado", registrado);
		return "index";
	}
	
	
	
	
	// recopila la informacion del usuario y se la pasa a la pagina perfil
	@RequestMapping("/perfil")
	public String init (Model model) {
		String nombre = usuario.getNombre();
		String password = usuario.getPassword();
		String fecha = usuario.getFecha();
		model.addAttribute("nombre", nombre);
		model.addAttribute("password", password);
		model.addAttribute("fecha", fecha);		
		return "perfil";
	}
}
