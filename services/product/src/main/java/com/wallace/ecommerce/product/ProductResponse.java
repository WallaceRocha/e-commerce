package com.wallace.ecommerce.product;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ProductResponse(
        Long id,
        String name,
        String description,
        double quantityInStock,
        BigDecimal price,
        Long categoryId,
        String categoryName,
        String categoryDescription
) {
}
