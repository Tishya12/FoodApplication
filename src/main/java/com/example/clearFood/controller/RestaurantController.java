package com.example.clearFood.controller;

import com.example.clearFood.model.FoodItem;
import com.example.clearFood.model.Restaurant;
import com.example.clearFood.model.User;
import com.example.clearFood.service.RestaurantManager;
import com.example.clearFood.service.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestaurantController {
    @Autowired
    RestaurantManager restaurantManager;


    @PostMapping(value = "/registerRestaurant")
    public String registerRestaurant(Restaurant restaurant) {
        try {
            if (restaurantManager.registerRestaurant(restaurant)) {
                return "restaurant registered Successfully with ";
            }
            return "restaurant already exists";
        } catch (Exception e) {
            return "restaurant registration failed";
        }

    }

    @PostMapping(value = "/updateQuantity/{id}")
    public String updateQuantity(@PathVariable int restaurantId, int foodItemId, int quanity) {
        return restaurantManager.updateQuantity(restaurantId, foodItemId, quanity);
    }


}
