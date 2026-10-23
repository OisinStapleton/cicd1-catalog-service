package ie.atu.catalog.service;

import ie.atu.catalog.dto.CreateProductRequest;
import ie.atu.catalog.model.Category;
import ie.atu.catalog.model.Product;
import ie.atu.catalog.repository.CategoryRepository;
import ie.atu.catalog.repository.ProductRepository;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductService {

    private final ProductRepository repository;
    private final CategoryRepository categoryRepository;

    public ProductService(ProductRepository repository, CategoryRepository categoryRepository, CategoryRepository categoryRepository1) {
        this.repository = repository;
        this.categoryRepository = categoryRepository;
    }

    public List<Product> getAll() {
        return repository.findAll();
    }

    public Product createProduct(CreateProductRequest request) {
        if (request.categoryId() == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "categoryId is required");
        }
        Category category = categoryRepository
                .findById(request.categoryId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.BAD_REQUEST, "Category does not exist"));
        Product product = new Product();
        product.setName(request.name());
        product.setPrice(request.price());
        product.setCategory(category);
        return repository.save(product);
    }


    public Product getById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Product not found"));
    }
    public List<Product> getByMinPrice(BigDecimal minPrice) {
        return repository.findByMinPrice(minPrice);
    }

    public List<Product> getByCategoryName(String categoryName) {
        return repository.findByCategoryName(categoryName);
    }
}
