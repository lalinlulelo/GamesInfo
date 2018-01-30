package LosOdiosos3.prueba_servidor;

/*
 *  Clase auxiliar que contiene un usuario imaginario que luego trasladará su información a un Usuario real
 */

public class Persona {
	// atributos de la persona
	private String nombre;
	private String password;
	private String fecha;
	// constructor para String
	public Persona () {}
	// constructor normal
	public Persona (String name, String pass, String date) {
		nombre = name;
		password = pass;
		fecha = date;
	}
	// devuelve el nombre de la persona
	public String getNombre () {
		return nombre;
	}
	// devuelve la contraseña de la persona
	public String getPassword () {
		return password;
	}
	// devuelve la fecha de nacimiento de la persona
	public String getFecha () {
		return fecha;
	}
	// cambia el nombre de la persona
	public void setNombre (String name) {
		nombre = name;
	}
	// cambia la contraseña de la persona
	public void setPassword (String pass) {
		password = pass;
	}
	// cambia la fecha de nacimiento de la persona
	public void setFecha (String date) {
		fecha = date;
	}
}
