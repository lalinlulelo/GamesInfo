package LosOdiosos3.prueba_servidor.Application;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
	// busqueda por nombre
	List<User> findByName(String name);
	// busqueda por password
	List<User> findByPassword(String pass);
	// busqueda por email
	List<User> findByEmail(String email);
}
