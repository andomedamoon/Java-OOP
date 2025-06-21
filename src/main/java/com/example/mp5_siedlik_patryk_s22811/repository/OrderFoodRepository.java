package com.example.mp5_siedlik_patryk_s22811.repository;

import com.example.mp5_siedlik_patryk_s22811.model.Order;
import com.example.mp5_siedlik_patryk_s22811.model.OrderFood;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderFoodRepository extends JpaRepository<OrderFood, Integer> {

}