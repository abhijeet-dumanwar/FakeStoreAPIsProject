package projects.javasampleproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestTemplate;
import projects.javasampleproject.dto.FakeStoreProductDTO;
import projects.javasampleproject.models.Category;
import projects.javasampleproject.models.Product;

import java.util.ArrayList;
import java.util.List;

@Service("fakeStoreProductService")
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

    @Override
    public List<Product> getAllProducts(){
        FakeStoreProductDTO[] response= restTemplate.getForObject( "https://fakestoreapi.com/products", FakeStoreProductDTO[].class);
        List<Product> answer = new ArrayList<>();
        for(FakeStoreProductDTO a : response){
            answer.add(convertFakeStoreProductDTOtoProduct(a));
        }
        return answer;
    }

    @Override
    public Product updateExistingProduct(Product product, Long id){
        FakeStoreProductDTO fakeStoreProductDto = new FakeStoreProductDTO();
        fakeStoreProductDto.setTitle(product.getTitle());
        fakeStoreProductDto.setPrice(product.getPrice());
        fakeStoreProductDto.setImage(product.getDescription());
        fakeStoreProductDto.setImage(product.getImageUrl());

        RequestCallback requestCallback = restTemplate.httpEntityCallback(fakeStoreProductDto, FakeStoreProductDTO.class);
        HttpMessageConverterExtractor<FakeStoreProductDTO> responseExtractor = new HttpMessageConverterExtractor(FakeStoreProductDTO.class, restTemplate.getMessageConverters());
        FakeStoreProductDTO response = restTemplate.execute("https://fakestoreapi.com/products/"+id, HttpMethod.PUT, requestCallback, responseExtractor);

       // FakeStoreProductDTO response= restTemplate.patchForObject("https://fakestoreapi.com/products/"+id, fakeStoreProductDto, FakeStoreProductDTO.class);
        return convertFakeStoreProductDTOtoProduct(response);
    }

    public Product addNewProduct(Product product){
        FakeStoreProductDTO fakeStoreProductDto = new FakeStoreProductDTO();
        fakeStoreProductDto.setTitle(product.getTitle());
        fakeStoreProductDto.setPrice(product.getPrice());
        fakeStoreProductDto.setDescription(product.getDescription());
        fakeStoreProductDto.setImage(product.getImageUrl());
        fakeStoreProductDto.setCategory(product.getCategory().getName());
        FakeStoreProductDTO apiResponse= restTemplate.postForObject("https://fakestoreapi.com/products", fakeStoreProductDto, FakeStoreProductDTO.class);
        return convertFakeStoreProductDTOtoProduct(apiResponse);
    }

    @Override
    public Product deleteProduct(Long id) {
        RequestCallback requestCallback = restTemplate.httpEntityCallback( FakeStoreProductDTO.class);
        HttpMessageConverterExtractor<FakeStoreProductDTO> responseExtractor = new HttpMessageConverterExtractor(FakeStoreProductDTO.class, restTemplate.getMessageConverters());
        FakeStoreProductDTO apiResponse=  restTemplate.execute("https://fakestoreapi.com/products/"+id, HttpMethod.DELETE, requestCallback, responseExtractor);
        return convertFakeStoreProductDTOtoProduct(apiResponse);
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

    public List<Category> getAllCategories(){
        return null;
    }

    public List<Product> getProductsByCategory(String category){
        return null;
    }

}
