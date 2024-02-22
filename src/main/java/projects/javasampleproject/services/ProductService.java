package projects.javasampleproject.services;

import projects.javasampleproject.Exceptions.ProductNotFoundException;
import projects.javasampleproject.models.Category;
import projects.javasampleproject.models.Product;

import java.util.List;

public interface ProductService {
    Product getSingleProduct(Long id) throws ProductNotFoundException;
    List<Product> getAllProducts();
    Product updateExistingProduct(Product product, Long id) throws ProductNotFoundException;
    Product addNewProduct(Product product);
    Product deleteProduct(/*Product product,*/ Long id) throws ProductNotFoundException;

    List<Category> getAllCategories();

    List<Product> getProductsByCategory(String category);
}
