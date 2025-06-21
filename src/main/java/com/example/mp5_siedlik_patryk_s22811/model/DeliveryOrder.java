package com.example.mp5_siedlik_patryk_s22811.model;

import lombok.*;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
public class DeliveryOrder extends Order {


    public DeliveryOrder(int orderNumber,LocalDateTime orderDate, Customer customer, Address address, Set<OrderFood> orderFood,
                         String specialInstructions) {
        super(orderNumber, orderDate, customer, address, orderFood, specialInstructions);
    }
}
