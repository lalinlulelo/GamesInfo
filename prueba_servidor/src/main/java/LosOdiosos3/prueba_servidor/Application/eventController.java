package LosOdiosos3.prueba_servidor.Application;

import java.util.ArrayList;
import java.util.Arrays;
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
public class eventController {	
	// ----------------------------- INYECCIONES --------------------------------------	
	// repositorio de la tabla eventos
	@Autowired
	private EventRepository eventRepository;
	// ----------------------------- FIN INYECCIONES ----------------------------------

	// ----------------------------- CALENDARIO DE EVENTOS  ---------------------------
	@RequestMapping("/event_calendar")
	public String event_calendar (Model model, HttpSession usuario, HttpServletRequest request) {
		// lista con todos los eventos disponibles
		List<Event> event_list = eventRepository.findAll();
		
		// se crea una cadena cuya funcion será de script
		// para ello mediante JSON disponemos de las fechas a colocar en el calendario
		String events = "var events = [ ";
		String parcial = "";
		for(int i = 0; i < event_list.size(); i++) {			
			if(i != event_list.size() -1 ) {
				parcial += "{'Date': new Date(" + event_list.get(i).getYear() + "," + (event_list.get(i).getMonth() -1) + "," + event_list.get(i).getDay() + "), 'Title': '" + event_list.get(i).getName() + "', 'Link': '" + "/event/" + event_list.get(i).getName() + "'}, ";
			}else {
				parcial += "{'Date': new Date(" +  event_list.get(i).getYear() + "," + (event_list.get(i).getMonth() -1) + "," + event_list.get(i).getDay() + "), 'Title': '" + event_list.get(i).getName() + "', 'Link': '" + "/event/" + event_list.get(i).getName() + "'}];";
			}
		}
		events += parcial;
		// finalmente creamos la cadena que contendrá el script
		String script = "<script>" + events + "var settings = {};\r\n" + "      var element = document.getElementById('caleandar');\r\n" + 	"      caleandar(element, events, settings);\r\n" + "    </script>";
		model.addAttribute("eventos", script);	
		
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
		
		// se retorna a la pagina del calendario de eventos
		return "event_calendar";
	}
	// ----------------------------- FIN CALENDARIO DE EVENTOS  -----------------------
	
	// ----------------------------- EVENTO  ------------------------------------------
	@RequestMapping("/event/{event_name}")
	public String event (Model model, HttpSession usuario, @PathVariable String event_name, HttpServletRequest request) {
		// lista que contiene los eventos con el nombre introudcido por url
		List<Event> events = eventRepository.findByName(event_name);		
		
		List<String> list=new ArrayList<String>();
		String div="<div class=\"com\"><div class=\"commentsUser \"><img class=\"comment_img\" src=\"%s\"></img>%s</div><div class=\"Date\">%s</div></div>\r\n" +  "     <div class=\"comments \">%s</div>"	+ "</div><br>";
		
		// si hay comentarios en el juego
		if(events.get(0).getComment().size()>0) {
			List<Comment> list_comments=events.get(0).getComment();					
			for(int i=0;i<list_comments.size();i++) {
				//Aqui accederiamos a la base de datos para cambiar en cada iteracion las variables
				String User=list_comments.get(i).getUser().getName();
				String Text=list_comments.get(i).getText();						
				Date d=list_comments.get(i).getDate();
				String img=list_comments.get(i).getUser().getIcon();				//Copiamos el div que queremos poner en el documento html en una variable auxiliar
				//Le damos formato a la variable auxiliar y la añadimos a la lista
				String aux=String.format(div, img,User,d.toString(), Text);				
				list.add(aux);				
			}
			Collections.reverse(list);
			model.addAttribute("comments", list);
		}else {
			model.addAttribute("comments"," ");
		}		
		
		// se pasan a plantilla los atributos del evento
		model.addAttribute("name_g", events.get(0).getName());
		model.addAttribute("place", events.get(0).getPlace());
		model.addAttribute("image", events.get(0).getImage());
		model.addAttribute("fee", events.get(0).getFee());
		model.addAttribute("date", events.get(0).getDay() + ", " + events.get(0).getMonth() + ", " + events.get(0).getYear());
		model.addAttribute("description", events.get(0).getDescription());
		
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
		
		// se accede al html
		return "event";
	}	
	// ---------------------------------- FIN EVENTO ----------------------------------
	
}
