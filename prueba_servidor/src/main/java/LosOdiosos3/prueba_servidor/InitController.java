package LosOdiosos3.prueba_servidor;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class InitController {
	
	// ----------------------------- INYECCIONES -------------------------------------	
	// repositorio de la tabla usuarios
	@Autowired
	private UserRepository repository;	
	// ------------------------------ FIN INYECCIONES --------------------------------
	
	// ----------------------------- PAGINA INICIO -----------------------------------
	@RequestMapping("/")
	public String inicio (Model model, HttpSession usuario) {
		repository.save(new User("Juan", "123", "rojo"));
		repository.save(new User("francisco", "aupa", "amarillo"));
		// comentarios de prueba de la pagina html
		model.addAttribute("Titulo", "Agus guapo");
		model.addAttribute("Cuerpo", "Susa idiota");
		
		// se muestra el link de iniciar/registrar usuario si es false
		model.addAttribute("registered", usuario.getAttribute("registered"));
		
		return "index";
	}
	// ----------------------------- FIN PAGINA INICIO -------------------------------
	
	// ----------------------------- REGISTRAR NUEVO USUARIO -------------------------
	@PostMapping(value = "/register")
	public String registrar(Model model, User user, HttpSession usuario) {
		// se inicializa el usuario con los datos del formulario
		usuario.setAttribute("name", user.getName());
		usuario.setAttribute("password", user.getPassword());
		usuario.setAttribute("date", user.getDate());
		
		// se comprueba la existencia del nombre
		List<User> usur = repository.findByName(user.getName());
		if(usur.size() > 0) {
			System.out.println("error, usuario existente");
			// --- buscar forma de notificar el error
			return "index";
		}
		
		// se da por registrado al usuario
		usuario.setAttribute("registered", true);
		repository.save(new User(user.getName(), user.getPassword(), user.getDate()));
		
		// se muestra MOSTRAR PERFIL si es true
		model.addAttribute("registered", usuario.getAttribute("registered"));
		model.addAttribute("Titulo", "Agus guapo");
		model.addAttribute("Cuerpo", "Susa idiota");
		return "index";
	}
	// ----------------------------- FIN REGISTRAR NUEVO USUARIO ---------------------
	
	// ----------------------------- INICIO DE SESION --------------------------------
	@RequestMapping("/login")
	public String iniciar_sesion (Model model, User user, HttpSession usuario) {
		System.out.println(user.getName());
		// recopilamos la lista de usuarios que contienen el nombre
		// --- en teoria no puede haber varios con el mismo nombre ---
		List<User> usur = repository.findByName(user.getName());
		// usuario auxiliar que recogera el usuario correspondiente a iniciar sesion
		User aux;
		
		// si la lista no esta vacía, la recorro comparando la contraseña introducida,
		// con las disponibles
		if(usur.size() > 0) {
			for(int i = 0; i < usur.size(); i++) {
				System.out.println(usur.get(i).toString());
				if(user.getPassword().equals(usur.get(i).getPassword())) {
					aux = usur.get(i);
					usuario.setAttribute("registered", true);
					break;
				}
			}
			usuario.setAttribute("registered", false);
		}else {
			// en caso de no existir el usuario, se ha de notificar de algun modo
			// --- procesar cual seria el correcto ---
			System.out.println("user or password wrong");
			usuario.setAttribute("registered", false);
		}
		model.addAttribute("loged", usuario.getAttribute("registered"));
		// se dirige a la pagina como iniciado
		return "loged";
	}
	// ----------------------------- FIN INICIO DE SESION ----------------------------
	
	// ----------------------------- PERFIL DE USUARIO -------------------------------
	@RequestMapping("/profile")
	public String init (Model model, HttpSession usuario) {
		
		// se cogen del usuario los atributos
		String name = (String) usuario.getAttribute("name");
		String password = (String) usuario.getAttribute("password");
		String date = (String) usuario.getAttribute("date");
		
		// se mandan los datos al modelo
		model.addAttribute("name", name);
		model.addAttribute("password", password);
		model.addAttribute("date", date);	
		
		// se devuelve el nombre de la lista, siendo el PERFIL del usuario
		return "profile";
	}
	// ----------------------------- FIN PERFIL DE USUARIO ---------------------------
	
	// ----------------------------- JUEGO -------------------------------------------
	@RequestMapping("/game")
	public String Game (Model model) {
		return "game";
	}
	// ----------------------------- FIN JUEGO ---------------------------------------
	
	// ----------------------------- COMPAÑIA ----------------------------------------
	@RequestMapping("/comany")
	public String company (Model model){
		return "comapny";
	}
	// ----------------------------- FIN COMPAÑIA -------------------------------------
	
	// ----------------------------- EVENTO -------------------------------------------
	@RequestMapping("/event")
	public String event (Model model) {
		return "event";
	}	
	// ---------------------------------- FIN EVENTO ----------------------------------
}
