package com.family.be.repository;

import com.family.be.models.OrderInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderInfo, Long> {
    List<OrderInfo> findOrderInfoByCustomer_Id(Long id);
}
