package LosOdiosos3.prueba_servidor.Application;

import java.util.List;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

@CacheConfig(cacheNames="games")
public interface EventRepository extends JpaRepository<Event, Long> {
	// busqueda con nombre
	@Cacheable
	List<Event> findByName(String name);
	
	// busqueda con nombre parcial
	@Cacheable
	List<Event> findByNameContaining(String name);
}
