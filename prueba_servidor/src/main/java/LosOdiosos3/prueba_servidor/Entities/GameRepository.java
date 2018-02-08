package LosOdiosos3.prueba_servidor.Entities;
import LosOdiosos3.prueba_servidor.*;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Long> {
	// busqueda por nombre
	List<Game> findByName (String name);
}
