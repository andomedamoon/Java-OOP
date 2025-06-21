package com.example.mp5_siedlik_patryk_s22811.repository;

import com.example.mp5_siedlik_patryk_s22811.model.Address;
import com.example.mp5_siedlik_patryk_s22811.model.PickupOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PickupOrderRepository extends JpaRepository<PickupOrder, Long> {

}
