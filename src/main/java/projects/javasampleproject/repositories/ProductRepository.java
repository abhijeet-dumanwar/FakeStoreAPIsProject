package projects.javasampleproject.repositories;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projects.javasampleproject.models.Category;
import projects.javasampleproject.models.Product;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository
        extends JpaRepository<Product, Long> {

    List<Product> findByTitleContaining(String word);
    long deleteByTitleIgnoreCase(String title);
    Product findByIdAndPriceOrderById(Long Id, Double price );

    List<Product> findByPriceBetween(double startRange, double endRange);

    List<Product> findByCategory(Category category);

    List<Product> findByCategoryName(String name);
    List<Product> findAll();
    Optional<Product> findById(Long id);
    Product save(Product product);

    void deleteById(Long id) ;

    @Transactional
    Product removeById(Long id);

    Product deleteProductById(Long id);

}


