package projects.javasampleproject.services;

import projects.javasampleproject.models.Product;

import java.util.List;

public interface ProductService {
    Product getSingleProduct(Long id);
    List<Product> getAllProducts();
    Product updateExistingProduct(Product product, Long id);
    Product addNewProduct(Product product);
    Product deleteProduct(/*Product product,*/ Long id);

}
