package LosOdiosos3.prueba_servidor.Entities;
import LosOdiosos3.prueba_servidor.*;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
	
	// busqueda por nombre
	List<Company> findByName(String name);

	// Enabling static ORDER BY for a query
	List<Company> findByNameOrderByYearAsc(String name);
	
	// busqueda con nombre parcial
	List<Company> findByNameContaining(String name);
}
