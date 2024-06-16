package com.example.clearFood.service;

import com.example.clearFood.model.FoodItem;
import com.example.clearFood.model.Restaurant;
import com.example.clearFood.model.User;
import com.example.clearFood.repository.RestaurantData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RestaurantManager {
    @Autowired
    RestaurantData restaurantData;

    @Autowired
    UserManager userManager;

    public boolean registerRestaurant(Restaurant restaurant) {
        int uniqueId = generateUniqueId();
        restaurant.setRestaurantId(uniqueId);
        return restaurantData.addRestaurant(restaurant);
    }

    public String updateQuantity(int restaurantId, int foodItemId, int quantity) {
        if (restaurantData.validRestaurant(restaurantId)) {
            if (restaurantData.validFoodItem(restaurantId, foodItemId)) {
                restaurantData.updateQuantity(restaurantId, foodItemId, quantity);
                return "Quantity updated Successfully";
            } else {
                return "Not a valid item";
            }
        } else return "Not a valid Restaurant";
    }

    public int generateUniqueId() {
        return (int) Math.random() * 1000;
    }

    public FoodItem getFoodItem(Restaurant restaurant, String foodItemName) {
        return restaurantData.getFoodItem(restaurant, foodItemName);
    }

    public List<Restaurant> filterRestaurantsForUser(List<Restaurant> restaurants, int userId) {
        User user = userManager.getUser(userId);
        int pinCode = user.getPinCode();
        List<Restaurant> filteredRestaurants = new ArrayList<>();
        for (Restaurant restaurant : restaurants) {
            if (restaurant.getPinCodes().contains(pinCode)) {
                filteredRestaurants.add(restaurant);
            }
        }
        return filteredRestaurants;
    }

    public List<Restaurant> returnRestaurantsForFoodItem(String foodItemName) {
        List<Restaurant> restaurants = restaurantData.getRestaurantsList();
        List<Restaurant> filteredRestaurants = new ArrayList<>();
        for (Restaurant restaurant : restaurants) {
            List<FoodItem> foodItems = restaurant.getFoodItemList();
            for (FoodItem foodItem : foodItems) {
                if (foodItem.getItemName().equals(foodItemName)) {
                    if (foodItem.getQuantity() != 0) {
                        filteredRestaurants.add(restaurant);
                    }
                }
            }
        }
        return filteredRestaurants;
    }
}



