package com.family.be.service.serviceIMPL;

import com.family.be.dto.request.OrderRequest;
import com.family.be.models.Customer;
import com.family.be.models.DetailsOrder;
import com.family.be.models.OrderInfo;
import com.family.be.models.Product;
import com.family.be.repository.CustomerRepository;
import com.family.be.repository.OrderRepository;
import com.family.be.repository.ProductRepository;
import com.family.be.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceIMPL implements OrderService {
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;
    @Override
    public OrderInfo createNewOrder(OrderRequest orderRequest) {
        Customer customer = customerRepository.findCustomerById(orderRequest.getIdCustomer());
        OrderInfo order = OrderInfo.builder()
                .customer(customer)
                .totalPrice(0L)
                .noteOrder(orderRequest.getNoteOrder())
                .statusPay(orderRequest.isStatusPay())
                .dateOrder(new Date())
                .shippingAddress(orderRequest.getShippingAddress())
                .build();

        List<DetailsOrder> detailsOrders = new ArrayList<>();
        orderRequest.getOrderRequests().forEach(item -> {
            Product product = productRepository.getProductById(item.getIdProduct()).orElseThrow(() ->
                    new RuntimeException("Not found Product"));
            DetailsOrder detailsOrder = DetailsOrder.builder()
                    .priceDetailsOrder(product.getPriceProduct())
                    .quantityDetailsOrder(item.getQuantityOrder())
                    .order(order)
                    .product(product)
                    .build();
            order.setTotalPrice(order.getTotalPrice() + product.getPriceProduct());
            detailsOrders.add(detailsOrder);
        });
        order.setDetailsOrders(detailsOrders);
        return orderRepository.save(order);
    }

    @Override
    public List<OrderInfo> getAllOrder() {
        return orderRepository.findAll();
    }

    @Override
    public List<OrderInfo> getOrderInfoByCustomer(Long id) {
        return orderRepository.findOrderInfoByCustomer_Id(id);
    }
}
