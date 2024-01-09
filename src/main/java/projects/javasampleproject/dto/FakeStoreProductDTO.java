package projects.javasampleproject.dto;

import lombok.Getter;
import lombok.Setter;
import projects.javasampleproject.models.Product;

@Getter
@Setter
public class FakeStoreProductDTO {
    private Long id;
    private String title;
    private double price;
    private String category;
    private String description;
    private String image;
}
