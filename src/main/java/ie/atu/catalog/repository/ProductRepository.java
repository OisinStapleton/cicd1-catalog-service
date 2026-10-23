package ie.atu.catalog.repository;


import ie.atu.catalog.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.math.BigDecimal;
import org.springframework.data.repository.query.Param;

public interface ProductRepository
        extends JpaRepository<Product, Long> {
    @Query("SELECT p FROM Product p WHERE p.price >= :minprice ORDER BY p.price ASC")
    List<Product> findByMinPrice(@Param("minprice") BigDecimal minprice);

}
