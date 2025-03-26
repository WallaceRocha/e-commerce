package com.wallace.ecommerce.product;

import com.wallace.ecommerce.product.exceptions.ProductPurchaseExpection;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository repository;
    private final ProductMapper mapper;

    public Long createProduct(@Valid ProductRequest request) {
        var product = mapper.toProduct(request);
        return repository.save(product).getId();
    }

    public List<ProductPurchaseResponse> purchaseProducts(@Valid List<ProductPurchaseRequest> productPurchaseRequest) {
        var productIds = productPurchaseRequest.stream()
                .map(ProductPurchaseRequest::productId)
                .toList();

        var productsInStock = repository.findAllByIdInOrderById(productIds);
        // if the number of products fetched is different from the number of required products
        if (!Objects.equals(productIds.size(), productsInStock.size())) {
            throw new ProductPurchaseExpection("Some products were not found");
        }
        // create a map of products in storage
        var mapOfProductsInStock = productsInStock.stream()
                .collect(Collectors.toMap(Product::getId, Function.identity()));

        var purchasedProducts = new ArrayList<ProductPurchaseResponse>();


        for (var productRequest : productPurchaseRequest) {
            var productInStock = mapOfProductsInStock.get(productRequest.productId());
            if (productInStock.getQuantityInStock() < productRequest.quantity()) {
                throw new ProductPurchaseExpection(format("Product %d has only %f in stock", productRequest.productId(), productInStock.getQuantityInStock()));
            }
            var newNumberOfProductsInStock = productInStock.getQuantityInStock() - productRequest.quantity();
            productInStock.setQuantityInStock(newNumberOfProductsInStock);
            // update the product with the new stock
            repository.save(productInStock);
            purchasedProducts.add(mapper.toProductPurchaseResponse(productInStock, productRequest.quantity()));
        }


        return purchasedProducts;
    }

    public ProductResponse findById(Long productId) {
        return repository.findById(productId)
                .map(mapper::toProductResponse)
                .orElseThrow(
                () -> new EntityNotFoundException("Cannot find this product:: "+ productId)
        );
    }

    public List<ProductResponse> findAll() {
        return repository.findAll().stream()
                .map(mapper::toProductResponse)
                .toList();
    }
}
