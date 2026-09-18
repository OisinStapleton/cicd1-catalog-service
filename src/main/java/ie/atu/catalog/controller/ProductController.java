package ie.atu.catalog.controller;

import ie.atu.catalog.model.Product;
import ie.atu.catalog.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }
@GetMapping
    public List<Product> getProducts() {
        return productService.getAll();
    }
@PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productService.create(product);
    }
}
