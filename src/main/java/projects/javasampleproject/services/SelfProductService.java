package projects.javasampleproject.services;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import projects.javasampleproject.models.Product;
import projects.javasampleproject.repositories.CategoryRepository;
import projects.javasampleproject.repositories.ProductRepository;

import java.util.List;
@Service("selfProductService")
public class SelfProductService implements ProductService{

    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    public SelfProductService(ProductRepository productRepository, CategoryRepository categoryRepository){
        this.productRepository=productRepository;
        this.categoryRepository=categoryRepository;

    }
    @Override
    public Product getSingleProduct(Long id) {
        return null;
    }

    @Override
    public List<Product> getAllProducts() {
        return null;
    }

    @Override
    public Product updateExistingProduct(Product product, Long id) {
        return null;
    }

    @Override
    public Product addNewProduct(Product product) {
        return null;
    }

    @Override
    public Product deleteProduct(Long id) {
        return null;
    }
}
