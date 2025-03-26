package com.wallace.ecommerce.product;


public record ProductPurchaseResponse(
        Long productId,
        String name,
        String description,
        double quantity
) {
}
