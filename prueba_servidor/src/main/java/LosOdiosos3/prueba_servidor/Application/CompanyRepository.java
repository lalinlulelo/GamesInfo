package LosOdiosos3.prueba_servidor.Application;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CompanyRepository extends JpaRepository<Company, Long> {
	
	// busqueda por nombre
	List<Company> findByName(String name);
	
	// busqueda con nombre parcial
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
