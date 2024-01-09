package projects.javasampleproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import projects.javasampleproject.dto.FakeStoreProductDTO;
import projects.javasampleproject.models.Category;
import projects.javasampleproject.models.Product;

@Service
public class FakeStoreproductService implements ProductService{

    private RestTemplate restTemplate;

    @Autowired
    public FakeStoreproductService(RestTemplate restTemplate){
        this.restTemplate=restTemplate;
    }
    @Override
    public Product getSingleProduct(Long id){
        FakeStoreProductDTO productDTO= restTemplate.getForObject(
                "https://fakestoreapi.com/products/"+id,
                FakeStoreProductDTO.class
        );
        return convertFakeStoreProductDTOtoProduct(productDTO);
    }

    public Product convertFakeStoreProductDTOtoProduct(FakeStoreProductDTO fakeStoreProductDTO){
        Product product = new Product();
        product.setId(fakeStoreProductDTO.getId());
        product.setCategory(new Category());
        product.getCategory().setName(fakeStoreProductDTO.getCategory());
        product.setDescription(fakeStoreProductDTO.getDescription());
        product.setPrice(fakeStoreProductDTO.getPrice());
        product.setTitle(fakeStoreProductDTO.getTitle());
        product.setImageUrl(fakeStoreProductDTO.getImage());
        return product;
    }

}
