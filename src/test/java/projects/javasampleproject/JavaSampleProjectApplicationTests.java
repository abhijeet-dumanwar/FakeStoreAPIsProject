package projects.javasampleproject;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import projects.javasampleproject.models.Product;
import projects.javasampleproject.repositories.ProductRepository;

import java.util.List;

import static java.lang.Long.valueOf;

@SpringBootTest
class JavaSampleProjectApplicationTests {

   /* ProductRepository productRepository;
    @Autowired
     JavaSampleProjectApplicationTests(ProductRepository productRepository){
        this.productRepository=productRepository;
    }
    @Test
    void contextLoads() {
    }

    @Test
    @Transactional
    @Commit
    void testQueries(){
        List<Product> product= productRepository.findByTitleContaining("abhi");
        //productRepository.deleteByTitleIgnoreCase("andi");
        //List<Product> product=productRepository.findByIdIs(1L);
        product.get(0).print();

        Product product1= productRepository.findByIdAndPriceOrderById(432L,35D);
        product1.print();

    }*/

}
