package com.example.clearFood.repository;

import com.example.clearFood.model.FoodItem;
import com.example.clearFood.model.Restaurant;
import com.example.clearFood.model.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class RestaurantData {
    List<Restaurant> restaurantsList = new ArrayList<>();
    Map<Integer, Restaurant> restaurants = new HashMap<>();

    public boolean addRestaurant(Restaurant restaurant) {
        if (restaurants.containsKey(restaurant.getRestaurantId())) {
            return false;
        } else {
            restaurantsList.add(restaurant);
            restaurants.put(restaurant.getRestaurantId(), restaurant);
            return true;
        }
    }

    public boolean validRestaurant(int restaurantId) {
        if (restaurants.containsKey(restaurantId)) {
            return true;
        } else return false;
    }

    public boolean validFoodItem(int restaurantId, int foodItem) {
        if (restaurants.containsKey(restaurantId)){
            Restaurant restaurant = restaurants.get(restaurantId);
            List<FoodItem> foodItemList = restaurant.getFoodItemList();
            if (foodItemList.contains(foodItem)) {
                return true;
            }
        }
        return false;
    }

    public void updateQuantity(int restaurantId, int foodItemId, int quantity) {
        if (restaurants.containsKey(restaurantId)) {
            Restaurant restaurant = restaurants.get(restaurantId);
            List<FoodItem> foodItemList = restaurant.getFoodItemList();

            if (foodItemList.contains(foodItemId)) {
                FoodItem foodItem = foodItemList.get(foodItemId);
                foodItem.setQuantity(quantity);
            }
        }
    }

    public FoodItem getFoodItem(Restaurant restaurant, String foodItemName) {
        List<FoodItem> foodItemList = restaurant.getFoodItemList();
        for (FoodItem foodItem : foodItemList) {
            if (foodItem.getItemName().equals(foodItemName)) {
                return foodItem;
            }
        }
        return null;
    }

    public List<Restaurant> getRestaurantsList() {
        return restaurantsList;
    }
}
