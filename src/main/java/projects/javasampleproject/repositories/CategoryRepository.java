package projects.javasampleproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projects.javasampleproject.models.Category;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository
extends JpaRepository<Category, Long> {

    Optional<Category> findByName(String Name);

    List<Category> findAll();

}
