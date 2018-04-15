package LosOdiosos3.prueba_servidor.Application;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;


@CacheConfig(cacheNames="games")
public interface GameRepository extends JpaRepository<Game, Long> {
	
	@CacheEvict(allEntries=true)
	Game save(Game game);
	
	@CacheEvict(allEntries=true)
	void delete(Game game);
	
	// busqueda por nombre
	@Cacheable
	List<Game> findByName (String name);
	
	//@Cacheable
	List<Game> findAll ();
		
	// busqueda con nombre parcial
	@Cacheable
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
	
	// busqueda por compañia
	@Query("SELECT game FROM Game game ORDER BY game.company ASC")
	List<Game> findByCompanyAsc();
}
