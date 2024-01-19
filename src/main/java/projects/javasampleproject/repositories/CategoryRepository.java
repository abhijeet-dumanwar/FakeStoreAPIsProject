package projects.javasampleproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projects.javasampleproject.models.Category;
@Repository
public interface CategoryRepository
extends JpaRepository<Category, Long> {
}
