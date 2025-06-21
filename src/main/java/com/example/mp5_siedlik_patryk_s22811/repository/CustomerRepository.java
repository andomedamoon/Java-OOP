package com.example.mp5_siedlik_patryk_s22811.repository;

import com.example.mp5_siedlik_patryk_s22811.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findByCustomerName(String customerName);
}