package ie.atu.catalog.dto;

import java.math.BigDecimal;

public record CreateProductRequest(
    String name,
    BigDecimal price,
    Long categoryID
) { }
