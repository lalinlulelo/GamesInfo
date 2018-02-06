package LosOdiosos3.prueba_servidor.Entities;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
	
	List<Company> findByName(String name);

	// Enabling static ORDER BY for a query
	List<Company> findByNameOrderByYearAsc(String name);
}
