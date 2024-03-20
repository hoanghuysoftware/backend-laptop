package com.family.be.repository;

import com.family.be.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findCustomerById(Long id);
    boolean existsByNameCustomer(String name);
    boolean existsByEmailCus(String email);
}
