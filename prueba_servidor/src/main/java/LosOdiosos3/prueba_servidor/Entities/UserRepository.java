package LosOdiosos3.prueba_servidor;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
	// busqueda por nombre
	List<User> findByName(String name);
	// busqueda por password
	List<User> findByPassword(String pass);
}
