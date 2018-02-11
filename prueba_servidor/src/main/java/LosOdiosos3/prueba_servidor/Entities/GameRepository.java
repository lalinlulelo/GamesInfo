package LosOdiosos3.prueba_servidor.Entities;
import LosOdiosos3.prueba_servidor.*;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface GameRepository extends JpaRepository<Game, Long> {
	// busqueda por nombre
	List<Game> findByName (String name);
	
	// busqueda con nombre parcial
	List<Game> findByNameContaining(String name);
	
	// busqueda puntuacion en orden ascendente
	@Query("SELECT game FROM Game game ORDER BY game.score ASC")
	List<Game> findByScoreAsc();
	
	// busqueda puntuacion en orden descendente
	@Query("SELECT game FROM Game game ORDER BY game.score DESC")
	List<Game> findByScoreDesc();
	
	// busqueda nombre en orden ascendente
	@Query("SELECT game FROM Game game ORDER BY game.name ASC")
	List<Game> findByNameAsc();
	
	// busqueda nombre en orden descendente
	@Query("SELECT game FROM Game game ORDER BY game.name DESC")
	List<Game> findByNameDesc();
	
	// busqueda anio en orden ascendente
	@Query("SELECT game FROM Game game ORDER BY game.year ASC")
	List<Game> findByYearAsc();
	
	// busqueda anio en orden descendente
	@Query("SELECT game FROM Game game ORDER BY game.year DESC")
	List<Game> findByYearDesc();
}
