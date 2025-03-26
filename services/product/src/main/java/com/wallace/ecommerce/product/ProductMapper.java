package com.wallace.ecommerce.product;

import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    public Product toProduct(ProductRequest request) {
        return Product.builder()
                .name(request.name())
                .description(request.description())
                .price(request.price())
                .quantityInStock(request.quantityInStock())
                .build();
    }

    public ProductResponse toProductResponse(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getQuantityInStock(),
                product.getPrice(),
                product.getCategory().getId(),
                product.getCategory().getName(),
                product.getCategory().getDescription()
        );
    }

    public ProductPurchaseResponse toProductPurchaseResponse(
            Product productInStock,
            double quantity) {
        return new ProductPurchaseResponse(
                productInStock.getId(),
                productInStock.getName(),
                productInStock.getDescription(),
                quantity);
    }
}
