package com.wallace.ecommerce.product;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ProductPurchaseRequest(
        @NotNull(message = "Product is required")
        Long productId,
        @Positive(message = "Quantity must be higher than zero")
        @NotNull(message = "Quantity is required")
        double quantity
) {
}
