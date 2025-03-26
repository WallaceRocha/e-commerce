package com.wallace.ecommerce.order;

import com.wallace.ecommerce.product.PurchaseRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.List;

public record OrderRequest (
        Long id,
        String reference,
        @Positive(message="The should be positive")
        BigDecimal amount,
        @NotNull(message="Payment method should be provided")
        PaymentMethod paymentMethod,
        @NotNull(message="Customer id should be provided")
        @NotEmpty(message="Customer id should be provided")
        @NotBlank(message="Customer id should be provided")
        String customerId,
        @NotEmpty(message="You need to select at least one product")
        List<PurchaseRequest> products
) {
}
