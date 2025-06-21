package com.example.mp5_siedlik_patryk_s22811.repository;

import com.example.mp5_siedlik_patryk_s22811.model.Customer;
import com.example.mp5_siedlik_patryk_s22811.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findByOrderDate(LocalDateTime orderDate);
    List<Order> findByCustomer(Customer customer);
    Optional<Order> findByOrderNumber(int orderNumber);
    List<Order> findBySpecialInstructions(String specialInstructions);
}
