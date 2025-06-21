package com.example.mp5_siedlik_patryk_s22811.services;

import com.example.mp5_siedlik_patryk_s22811.model.*;
import com.example.mp5_siedlik_patryk_s22811.repository.OrderFoodRepository;
import com.example.mp5_siedlik_patryk_s22811.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderFoodRepository orderFoodRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, OrderFoodRepository orderFoodRepository) {
        this.orderRepository = orderRepository;
        this.orderFoodRepository = orderFoodRepository;
    }

    @Transactional(readOnly = true)
    public Customer findCustomerByOrderNumber(Integer orderNumber) {
        Optional<Order> orderOptional = orderRepository.findByOrderNumber(orderNumber);
        if (orderOptional.isPresent() && orderOptional.get().getCustomer() != null) {
            return cloneCustomer(orderOptional.get().getCustomer());
        }
        return null;
    }

    private Customer cloneCustomer(Customer customer) {
        // Tworzenie nowego obiektu Customer z istniejących danych
        Customer clonedCustomer = new Customer(customer.getId() ,customer.getLogin(), customer.getPassword(), customer.getEmail(), customer.getPhoneNumbers(), customer.getName(), customer.getSurname());
        clonedCustomer.setCustomerName(customer.getCustomerName());
        clonedCustomer.setPhoneNumbers(new ArrayList<>(customer.getPhoneNumbers())); // Głęboka kopia listy numerów telefonów
        // Adresy i zamówienia nie są kopiowane, ponieważ mogą zawierać złożone zależności
        return clonedCustomer;
    }

    public void addOrderFood(Order order, OrderFood orderFood) {
        order.getOrderFoods().add(orderFood);
        orderFood.setOrder(order);
        orderFoodRepository.save(orderFood);
    }

    public void removeOrderFood(Order order, OrderFood orderFood) {
        order.getOrderFoods().remove(orderFood);
        orderFood.setOrder(null);
        orderFoodRepository.delete(orderFood);
    }

    public DeliveryOrder convertToDeliveryOrder(PickupOrder pickupOrder /* Delivery delivery - usunięte na czas miniprojektu nr 5  */) {
        DeliveryOrder deliveryOrder = new DeliveryOrder(pickupOrder.getOrderNumber(), pickupOrder.getOrderDate(),
                pickupOrder.getCustomer(), pickupOrder.getOrderAddress(), pickupOrder.getOrderFoods(),
                pickupOrder.getSpecialInstructions());

        deliveryOrder.setOrderFoods(pickupOrder.getOrderFoods());
        deliveryOrder.setSpecialInstructions(pickupOrder.getSpecialInstructions());

        deleteOrder(pickupOrder);
        orderRepository.save(deliveryOrder);

        return deliveryOrder;
    }

    public PickupOrder convertToPickupOrder(DeliveryOrder deliveryOrder) {
        PickupOrder pickupOrder = new PickupOrder(deliveryOrder.getOrderNumber(), deliveryOrder.getOrderDate(),
                deliveryOrder.getCustomer(), deliveryOrder.getOrderAddress(), deliveryOrder.getOrderFoods(),
                deliveryOrder.getSpecialInstructions());

        pickupOrder.setOrderFoods(deliveryOrder.getOrderFoods());
        pickupOrder.setSpecialInstructions(deliveryOrder.getSpecialInstructions());

        deleteOrder(deliveryOrder);
        orderRepository.save(pickupOrder);

        return pickupOrder;
    }

    public void deleteOrder(Order order) {
        orderRepository.delete(order);
    }
}
