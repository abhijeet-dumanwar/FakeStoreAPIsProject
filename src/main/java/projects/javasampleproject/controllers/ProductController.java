package projects.javasampleproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import projects.javasampleproject.models.Product;
import projects.javasampleproject.services.FakeStoreproductService;
import projects.javasampleproject.services.ProductService;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {

    private ProductService productService;
    private RestTemplate restTemplate;

    @Autowired
    public ProductController(@Qualifier("selfProductService") ProductService productService, RestTemplate restTemplate){
        this.restTemplate=restTemplate;
        this.productService=productService;
    }
    @GetMapping("/products")
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }

    @GetMapping("/product/{id}")
    public Product getSingleProduct(@PathVariable("id") Long id){
        return productService.getSingleProduct(id);
    }

    @PostMapping("/addNewProduct")
    public Product addNewProduct(@RequestBody Product product){
        return productService.addNewProduct(product);
    }

    @PutMapping("/updateProduct/{id}")
    public Product updateExistingProduct(@RequestBody Product product, @PathVariable Long id){
        return productService.updateExistingProduct(product, id);
    }

    @DeleteMapping("/deleteProduct/{id}")
    public Product deleteProduct( @PathVariable("id") Long id){
        return productService.deleteProduct(id);
    }
}
