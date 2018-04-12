package LosOdiosos3.prueba_servidor.Application;

import java.util.List;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

@CacheConfig(cacheNames="games")
public interface CompanyRepository extends JpaRepository<Company, Long> {
	
	@CacheEvict(allEntries=true)
	Company save(Company company);
	
	@CacheEvict(allEntries=true)
	void delete(Company company);
	
	// busqueda por nombre
	@Cacheable
	List<Company> findByName(String name);
	
	// busqueda con nombre parcial
	@Cacheable
	List<Company> findByNameContaining(String name);
	
	// busqueda nombre en orden ascendente
	@Query("SELECT company FROM Company company ORDER BY company.name ASC")
	List<Company> findByNameAsc();
	
	// busqueda nombre en orden descendente
	@Query("SELECT company FROM Company company ORDER BY company.name DESC")
	List<Company> findByNameDesc();
	
	// busqueda anio en orden ascendente
	@Query("SELECT company FROM Company company ORDER BY company.year ASC")
	List<Company> findByYearAsc();
	
	// busqueda anio en orden descendente
	@Query("SELECT company FROM Company company ORDER BY company.year DESC")
	List<Company> findByYearDesc();
}
