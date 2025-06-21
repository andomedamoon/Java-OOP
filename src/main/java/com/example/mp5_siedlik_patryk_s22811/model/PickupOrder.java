package com.example.mp5_siedlik_patryk_s22811.model;

import lombok.*;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class PickupOrder extends Order {

    public PickupOrder(int orderNumber,LocalDateTime orderDate, Customer customer, Address address, Set<OrderFood> orderFood,
                       String specialInstructions) {
        super(orderNumber, orderDate, customer, address, orderFood, specialInstructions);
    }

    // Gettery, settery i inne metody
}