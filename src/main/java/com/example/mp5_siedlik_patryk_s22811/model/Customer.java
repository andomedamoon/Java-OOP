package com.example.mp5_siedlik_patryk_s22811.model;

import lombok.*;
import jakarta.persistence.*;

import java.util.*;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "customers")
public class Customer extends User {

    @Column(nullable = false)
    private String customerName;


    /*
    CascadeType.ALL i orphanRemoval = true: Zapewniają, że gdy Customer jest usuwany, wszystkie powiązane Address
     są również usuwane z bazy danych. orphanRemoval zapewnia, że gdy adres zostanie odłączony od klienta, zostanie
      on automatycznie usunięty.
     */
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Address> addresses = new HashSet<>();

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    @MapKey(name = "orderNumber")
    private Map<Integer, Order> ordersById = new HashMap<>();

    public Customer(Long id, String login, String password, String email, List<String> phoneNumbers, String name, String surname) {
        super(id, login, password, email, phoneNumbers, name, surname);
    }

    // Wymagany przez Hibernate
    public Customer() {

    }
}
