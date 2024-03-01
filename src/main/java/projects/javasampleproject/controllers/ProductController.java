package projects.javasampleproject.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import projects.javasampleproject.Exceptions.ProductNotFoundException;
import projects.javasampleproject.commons.AuthenticationCommons;
import projects.javasampleproject.dto.Role;
import projects.javasampleproject.dto.SendEmailEventDTO;
import projects.javasampleproject.dto.UserDto;
import projects.javasampleproject.models.Category;
import projects.javasampleproject.models.Product;
import projects.javasampleproject.services.FakeStoreproductService;
import projects.javasampleproject.services.KafkaMessagePublisherService;
import projects.javasampleproject.services.ProductService;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class ProductController {

    private ProductService productService;
    private RestTemplate restTemplate;
    private AuthenticationCommons authenticationCommons;
    private KafkaMessagePublisherService kafkaMessagePublisherService;

    @Autowired
    public ProductController(@Qualifier("selfProductService") ProductService productService, RestTemplate restTemplate, AuthenticationCommons authenticationCommons, KafkaMessagePublisherService kafkaMessagePublisherService){
        this.restTemplate=restTemplate;
        this.productService=productService;
        this.authenticationCommons=authenticationCommons;
        this.kafkaMessagePublisherService=kafkaMessagePublisherService;
    }
    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts(@RequestHeader("AuthenticationToken") String token){

        UserDto userDto=authenticationCommons.validateToken(token);
        if(userDto==null){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        boolean isAdmin = false;
        for(Role role: userDto.getRoles()){
            if(role.getName().equals("ADMIN")){
                isAdmin=true;
                break;
            }
        }
        if(!isAdmin){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        List<Product> products = productService.getAllProducts(); // o p q

        List<Product> finalProducts = new ArrayList<>();

        for (Product p: products) { // o  p q
            p.setTitle("Hello" + p.getTitle());
            finalProducts.add(p);
        }

        ResponseEntity<List<Product>> response = new ResponseEntity<>(
                finalProducts, HttpStatus.FORBIDDEN
        );
        return response;
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getSingleProduct(@PathVariable("id") Long id) throws ProductNotFoundException {
        return new ResponseEntity<>(
                productService.getSingleProduct(id), HttpStatus.OK
        );
    }

    @PostMapping("/addNewProduct")
    public ResponseEntity<Product> addNewProduct(@RequestBody Product product){

        return new ResponseEntity<>(
                productService.addNewProduct(product), HttpStatus.ACCEPTED
        );
    }

    @PutMapping("/updateProduct/{id}")
    public Product updateExistingProduct(@RequestBody Product product, @PathVariable Long id) throws ProductNotFoundException{
        return productService.updateExistingProduct(product, id);
    }

    @DeleteMapping("/deleteProduct/{id}")
    public Product deleteProduct( @PathVariable("id") Long id) throws ProductNotFoundException{
        return productService.deleteProduct(id);
    }

    @GetMapping("/products/categories")
    public List<Category> getAllCategory(){
        return productService.getAllCategories();
    }

    @GetMapping("/products/categories/{category}")
    public List<Product> getProductsByCategory(@PathVariable("category") String category){
        return productService.getProductsByCategory(category);
    }

    @PostMapping("/publishMessage")
    public ResponseEntity<String> publishMessage(@RequestBody SendEmailEventDTO message) throws JsonProcessingException {

        String response = kafkaMessagePublisherService.publishMessage(message);
        return new ResponseEntity<>(
                restTemplate.postForObject("http://localhost:8080/publish", message, String.class), HttpStatus.OK
        );
    }
}
