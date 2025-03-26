package com.wallace.ecommerce.product;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ProductRequest(
        Long id,
        @NotNull(message = "Name is required")
        String name,
        @NotNull(message = "Description is required")
        String description,
        @Positive(message = "The quantity must be higher than zero")
        double quantityInStock,
        @Positive(message = "The price must be higher than zero")
        BigDecimal price,
        @NotNull(message = "Category ID is required")
        Long categoryId

) {
}
