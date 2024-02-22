package projects.javasampleproject.services;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import projects.javasampleproject.Exceptions.ProductNotFoundException;
import projects.javasampleproject.models.Category;
import projects.javasampleproject.models.Product;
import projects.javasampleproject.repositories.CategoryRepository;
import projects.javasampleproject.repositories.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service("selfProductService")
public class SelfProductService implements ProductService{

    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    @Autowired
    public SelfProductService(ProductRepository productRepository, CategoryRepository categoryRepository){
        this.productRepository=productRepository;
        this.categoryRepository=categoryRepository;

    }
    @Override
    public Product getSingleProduct(Long id) throws ProductNotFoundException{
        Optional<Product> productOptional= productRepository.findById(id);
        if(productOptional.isEmpty()){
            throw new ProductNotFoundException("Product with ID: "+id+" does not exist.");
        }else{
            return productOptional.get();
        }
    }

    @Override
    public List<Product> getAllProducts() {
         return productRepository.findAll();
    }

    @Override
    public Product updateExistingProduct(Product product, Long id) throws ProductNotFoundException {
        Optional<Product> optionalProduct= productRepository.findById(id);
        if(optionalProduct.isEmpty()){
            throw new ProductNotFoundException("Product does not exist for given id");
        }
        Product fetchedProduct=new Product();
        fetchedProduct.setId(optionalProduct.get().getId());
        fetchedProduct.setPrice(optionalProduct.get().getPrice());
        fetchedProduct.setTitle(optionalProduct.get().getTitle());
        Category fetchedCategory=new Category();
        fetchedCategory.setName(optionalProduct.get().getCategory().getName());
        fetchedCategory.setId(optionalProduct.get().getCategory().getId());
        fetchedProduct.setCategory(fetchedCategory);
        fetchedProduct.setDescription(optionalProduct.get().getDescription());
        fetchedProduct.setImageUrl(optionalProduct.get().getImageUrl());

        if(product.getCategory()!=null){
            fetchedProduct.getCategory().setId(product.getCategory().getId());
        }
        if(!product.getTitle().isEmpty()){
            fetchedProduct.setTitle(product.getTitle());
        }
        if(product.getPrice()!=0.0){
            fetchedProduct.setPrice(product.getPrice());
        }
        if(!product.getDescription().isEmpty()){
            fetchedProduct.setDescription(product.getDescription());
        }
        if(!product.getImageUrl().isEmpty()){
            fetchedProduct.setImageUrl(product.getImageUrl());
        }

        return productRepository.save(fetchedProduct);
    }

    @Override
    public Product addNewProduct(Product product) {
        Optional<Category> optionalCategory=categoryRepository.findByName(product.getCategory().getName());
        if(optionalCategory.isEmpty()){
            product.setCategory(categoryRepository.save(product.getCategory()));
        }else{
            product.setCategory(optionalCategory.get());
        }
        return productRepository.save(product);
    }

    @Transactional
    public Product deleteProduct(Long id) throws ProductNotFoundException{
        Product deletedProduct = productRepository.deleteProductById(id);
        if (deletedProduct != null) {
            return deletedProduct;
        } else {
            throw new ProductNotFoundException("Product with id " + id + " not found");
        }
    }

    public List<Category> getAllCategories(){
        return categoryRepository.findAll();
    }

    public List<Product> getProductsByCategory(String category){
        return productRepository.findByCategoryName(category);
    }
}
