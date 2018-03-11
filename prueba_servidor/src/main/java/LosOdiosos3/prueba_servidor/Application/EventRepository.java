package LosOdiosos3.prueba_servidor.Application;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
	List<Event> findByName(String name);
	
	// busqueda con nombre parcial
	List<Event> findByNameContaining(String name);
}
