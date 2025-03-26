package com.wallace.ecommerce.order;

import com.wallace.ecommerce.customer.CustomerClient;
import com.wallace.ecommerce.kafka.OrderConfirmation;
import com.wallace.ecommerce.kafka.OrderProducer;
import com.wallace.ecommerce.orderline.OrderLineRequest;
import com.wallace.ecommerce.orderline.OrderLineService;
import com.wallace.ecommerce.product.ProductClient;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderRepository orderRepository;
    private final OrderMapper mapper;
    private final OrderLineService orderLineService;
    private final OrderProducer orderProducer;


    public Long createOrder(@Valid OrderRequest orderRequest) {
        var customer = this.customerClient.findCustomerById(orderRequest.customerId())
                .orElseThrow(() -> new BusinessException(format("Customer not found :: "+orderRequest.customerId())));

        var purchasedProducts = this.productClient.purchaseProducts(orderRequest.products());

        var order = this.orderRepository.save(mapper.toOrder(orderRequest));

        for (var purchaseRequest : orderRequest.products()) {
            this.orderLineService.saveOrderLine(
                    new OrderLineRequest(null, order.getId(), purchaseRequest.productId(), purchaseRequest.quantity())
            );

        }

        this.orderProducer.sendOrderConfirmation(
                new OrderConfirmation(
                        orderRequest.reference(),
                        orderRequest.amount(),
                        orderRequest.paymentMethod(),
                        customer,
                        purchasedProducts)
        );



        return order.getId();
    }

    public List<OrderResponse> findAll() {
        return this.orderRepository.findAll().stream()
                .map(mapper::fromOrder)
                .toList();
    }

    public OrderResponse findById(Long orderId) {
        return this.orderRepository.findById(orderId)
                .map(mapper::fromOrder)
                .orElseThrow(() -> new EntityNotFoundException(format("Order not found :: "+orderId)));
    }
}
