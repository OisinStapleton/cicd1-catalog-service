package ie.atu.catalog.controller;

import ie.atu.catalog.dto.CreateProductRequest;
import ie.atu.catalog.model.Category;
import ie.atu.catalog.model.Product;
import ie.atu.catalog.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
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
    @ResponseStatus(HttpStatus.CREATED)
    public Product createProduct(@RequestBody CreateProductRequest request) {
        return productService.createProduct(request);
    }


    @GetMapping("/{id}")
    public Product getById(@PathVariable Long id) { return productService.getById(id); }

    @GetMapping("/min-price")
    public List<Product> getByMinPrice(@RequestParam BigDecimal minprice) {
        return productService.getByMinPrice(minprice);
    }
    @GetMapping("/by-category")
    public List<Product> getByCategory(
            @RequestParam("name") String categoryName) {
        return productService.getByCategoryName(categoryName);
    }

}
