package LosOdiosos3.prueba_servidor;

/*
 *  Clase auxiliar que contiene un usuario imaginario que luego trasladará su información a un Usuario real
 */

public class User_Aux {
	// atributos de la persona
	private String name;
	private String password;
	private String date;
	// constructor para String
	public User_Aux() {}
	// devuelve el nombre de la persona
	public String getName () {
		return name;
	}
	// devuelve la contraseña de la persona
	public String getPassword () {
		return password;
	}
	// devuelve la fecha de nacimiento de la persona
	public String getDate () {
		return date;
	}
	// cambia el nombre de la persona
	public void setName (String name) {
		this.name = name;
	}
	// cambia la contraseña de la persona
	public void setPassword (String pass) {
		password = pass;
	}
	// cambia la fecha de nacimiento de la persona
	public void setDate (String date) {
		this.date = date;
	}
}
