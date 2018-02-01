package LosOdiosos3.prueba_servidor;

import java.util.List;

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
		// se muestra el link de iniciar/registrar usuario si es false
		model.addAttribute("registered", usuario.getRegistered());
		//model.addAttribute("sin_registrar", sin_registrar);
		//model.addAttribute("registrado", registrado);
		return "index";
	}
	
	// pagina de registro	
	@PostMapping(value = "/registrar")
	public String registrar(Model model, User_Aux user) {
		usuario.setName(user.getName());
		usuario.setPassword(user.getPassword());
		usuario.setDate(user.getDate());
		
		// se comprueba la existencia del nombre
		List<User> usur = repository.findByName(user.getName());
		if(usur.size() > 0) {
			System.out.println("error, usuario existente");
			// --- buscar forma de notificar el error
			return "index";
		}
		
		// se da por registrado al usuario
		usuario.setRegistered(true);
		repository.save(usuario);
		
		// se muestra MOSTRAR PERFIL si es true
		model.addAttribute("registered", usuario.getRegistered());
		return "index";
	}
	
	// pagina de iniciar sesion
	@RequestMapping("/start_session")
	public String iniciar_sesion (Model model, User_Aux user) {
		// GESTION DE INICIO DE SESION
		// recopilamos la lista de usuarios que contienen el nombre
		// --- en teoria no puede haber varios con el mismo nombre ---
		List<User> usur = repository.findByName(user.getName());
		// usuario auxiliar que recogera el usuario correspondiente a iniciar sesion
		User aux;
		// si la lista no esta vacía, la recorro comparando la contraseña introducida,
		// con las disponibles
		if(usur.size() > 0) {
			for(int i = 0; i < usur.size(); i++) {
				if(user.getPassword().equals(usur.get(i).getPassword())) {
					aux = usur.get(i);
					break;
				}
			}
		}else {
			// en caso de no existir el usuario, se ha de notificar de algun modo
			// --- procesar cual seria el correcto ---
			System.out.println("user or password wrong");
		}
		return "loged";
	}
	
	// recopila la informacion del usuario y se la pasa a la pagina perfil
	@RequestMapping("/profile")
	public String init (Model model) {
		// se cogen del usuario los atributos
		String name = usuario.getName();
		String password = usuario.getPassword();
		String date = usuario.getDate();
		// se mandan los datos al modelo
		model.addAttribute("name", name);
		model.addAttribute("password", password);
		model.addAttribute("date", date);		
		// se devuelve el nombre de la lista, siendo el PERFIL del usuario
		return "profile";
	}
	
	// juego
	@RequestMapping("/game")
	public String Game (Model model) {
		return "game";
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
