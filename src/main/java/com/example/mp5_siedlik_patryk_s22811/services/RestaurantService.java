package com.example.mp5_siedlik_patryk_s22811.services;

import com.example.mp5_siedlik_patryk_s22811.model.Food;
import com.example.mp5_siedlik_patryk_s22811.model.Restaurant;
import com.example.mp5_siedlik_patryk_s22811.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    @Autowired
    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @Transactional
    public void addFoodToRestaurant(Long restaurantId, Food food) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new RuntimeException("Restaurant not found"));
        restaurant.getFoods().add(food);
        food.setRestaurant(restaurant);
        restaurantRepository.save(restaurant);
    }

    @Transactional
    public void removeFoodFromRestaurant(Long restaurantId, Long foodId) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new RuntimeException("Restaurant not found"));
        restaurant.getFoods().removeIf(food -> food.getId().equals(foodId));
    }
}