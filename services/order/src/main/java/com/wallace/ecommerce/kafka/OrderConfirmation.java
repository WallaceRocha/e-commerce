package com.wallace.ecommerce.kafka;

import com.wallace.ecommerce.customer.CustomerResponse;
import com.wallace.ecommerce.order.PaymentMethod;
import com.wallace.ecommerce.product.PurchaseResponse;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(
        String orderReference,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        CustomerResponse customer,
        List<PurchaseResponse> products
) {
}
