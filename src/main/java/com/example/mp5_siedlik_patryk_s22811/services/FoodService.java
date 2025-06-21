package com.example.mp5_siedlik_patryk_s22811.services;

import com.example.mp5_siedlik_patryk_s22811.model.Food;
import com.example.mp5_siedlik_patryk_s22811.model.OrderFood;
import com.example.mp5_siedlik_patryk_s22811.repository.FoodRepository;
import com.example.mp5_siedlik_patryk_s22811.repository.OrderFoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FoodService {

    private final FoodRepository foodRepository;
    private final OrderFoodRepository orderFoodRepository;

    @Autowired
    public FoodService(FoodRepository foodRepository, OrderFoodRepository orderFoodRepository) {
        this.foodRepository = foodRepository;
        this.orderFoodRepository = orderFoodRepository;
    }

    public void addOrderFood(Food food, OrderFood orderFood) {
        food.getOrderFoods().add(orderFood);
        orderFood.setFood(food);
        orderFoodRepository.save(orderFood);
    }

    public void removeOrderFood(Food food, OrderFood orderFood) {
        food.getOrderFoods().remove(orderFood);
        orderFood.setFood(null);
        orderFoodRepository.delete(orderFood);
    }
}
