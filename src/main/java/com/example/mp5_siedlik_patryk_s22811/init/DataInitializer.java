package com.example.mp5_siedlik_patryk_s22811.init;

import com.example.mp5_siedlik_patryk_s22811.model.*;
import com.example.mp5_siedlik_patryk_s22811.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Component
public class DataInitializer {

    private final RestaurantService restaurantService;
    private final CustomerService customerService;
    private final FoodService foodService;

    @Autowired
    public DataInitializer(RestaurantService restaurantService, CustomerService customerService, FoodService foodService) {
        this.restaurantService = restaurantService;
        this.customerService = customerService;
        this.foodService = foodService;
    }

    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent event) {
        // Przykładowe restauracje
        Restaurant restaurant1 = createRestaurant("La Bella Italia");
        Restaurant restaurant2 = createRestaurant("Sushi Corner");

        // Dodawanie jedzenia do restauracji
        addFoodToRestaurant(restaurant1, "Pizza Margherita", 25.00);
        addFoodToRestaurant(restaurant1, "Spaghetti Carbonara", 30.00);
        addFoodToRestaurant(restaurant2, "California Roll", 20.00);
        addFoodToRestaurant(restaurant2, "Nigiri Sushi", 22.00);

        // Przykładowi klienci i adresy
        Customer customer1 = createCustomer("jdoe", "password123", "jdoe@example.com", "John", "Doe");
        Address address1 = createAddress("New York", "NY 10001", "5th Avenue", 10);
        customerService.addAddressToCustomer(customer1, address1);

        Customer customer2 = createCustomer("msmith", "password321", "msmith@example.com", "Mary", "Smith");
        Address address2 = createAddress("San Francisco", "CA 94105", "Market Street", 20);
        customerService.addAddressToCustomer(customer2, address2);

        // Przykładowe zamówienia
        Order order1 = new PickupOrder();
        order1.setOrderDate(LocalDateTime.now());
        order1.setCustomer(customer1);
        order1.setOrderAddress(address1);
        order1.setSpecialInstructions("Extra napkins, please");
        customerService.addOrderToCustomer(customer1, order1);

        Order order2 = new DeliveryOrder();
        order2.setOrderDate(LocalDateTime.now());
        order2.setCustomer(customer2);
        order2.setOrderAddress(address2);
        order2.setSpecialInstructions("Ring the doorbell twice");
        customerService.addOrderToCustomer(customer2, order2);

        // Dodawanie jedzenia do zamówień
        Food pizza = restaurant1.getFoods().stream().filter(f -> f.getName().equals("Pizza Margherita")).findFirst().orElse(null);
        Food sushi = restaurant2.getFoods().stream().filter(f -> f.getName().equals("California Roll")).findFirst().orElse(null);
        foodService.addOrderFood(pizza, new OrderFood(order1, pizza, 2));
        foodService.addOrderFood(sushi, new OrderFood(order2, sushi, 3));
    }

    private Restaurant createRestaurant(String name) {
        Restaurant restaurant = new Restaurant();
        restaurant.setName(name);
        return restaurantService.saveRestaurant(restaurant);
    }

    private void addFoodToRestaurant(Restaurant restaurant, String name, double price) {
        Food food = new Food();
        food.setName(name);
        food.setPrice(price);
        food.setRestaurant(restaurant);
        foodService.addFoodToRestaurant(restaurant.getId(), food);
    }

    private Customer createCustomer(String login, String password, String email, String name, String surname) {
        Customer customer = new Customer();
        customer.setLogin(login);
        customer.setPassword(password);
        customer.setEmail(email);
        customer.setName(name);
        customer.setSurname(surname);
        customer.setCustomerName(name + " " + surname);
        return customerService.saveCustomer(customer);
    }

    private Address createAddress(String city, String postalCode, String street, int houseNumber) {
        Address address = new Address();
        address.setCity(city);
        address.setPostalCode(postalCode);
        address.setStreet(street);
        address.setHouseNumber(houseNumber);
        return address;
    }
}
