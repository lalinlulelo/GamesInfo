<<<<<<< HEAD
package LosOdiosos3.prueba_servidor.Entities;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {
	List<Article> findByTitle(String title);
}
=======
package LosOdiosos3.prueba_servidor.Entities;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {
	
}
>>>>>>> branch 'master' of https://github.com/lalinlulelo/GamesInfo.git
