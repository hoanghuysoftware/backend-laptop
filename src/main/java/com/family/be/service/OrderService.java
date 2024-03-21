package com.family.be.service;

import com.family.be.dto.request.OrderRequest;
import com.family.be.models.OrderInfo;

import java.util.List;

public interface OrderService {
    OrderInfo createNewOrder(OrderRequest orderRequest);
    List<OrderInfo> getAllOrder();

    List<OrderInfo> getOrderInfoByCustomer(Long id);
}
