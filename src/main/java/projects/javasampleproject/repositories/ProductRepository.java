package projects.javasampleproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;
import projects.javasampleproject.models.Product;
@Repository
public interface ProductRepository
        extends JpaRepository<Product, Long> {
}
