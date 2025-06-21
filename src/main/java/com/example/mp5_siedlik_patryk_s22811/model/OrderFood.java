package com.example.mp5_siedlik_patryk_s22811.model;

import lombok.*;
import jakarta.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@Table(name = "order_foods")
public class OrderFood {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    @NonNull
    private Order order;

    @ManyToOne
    @JoinColumn(name = "food_id", nullable = false)
    @NonNull
    private Food food;

    @Column(nullable = false)
    @NonNull
    private int quantity;
}
