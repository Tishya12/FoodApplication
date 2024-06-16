package com.example.clearFood.service;

import com.example.clearFood.model.*;
import com.example.clearFood.repository.UserData;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@Component
public class UserManager {

    @Autowired
    UserData userData;

    @Autowired
    RestaurantManager restaurantManager;

    public boolean registerUser(User user) {
        int uniqueId = generateUniqueId();
        user.setUserId(uniqueId);
        return userData.addUser(user);
    }

    public int generateUniqueId() {
        return new Random().nextInt(100);
    }

    public boolean saveRatings(Rating rating) {
        return userData.saveRatings(rating);
    }

    public List<OrderDetails> getOrderHistory(int userId) {
        if (userData.validUser(userId)) {
            return userData.getOrderHistory(userId);
        } else {
            return null;
        }
    }

    public String placeOrder(OrderDetails orderDetails) {
        if (userData.placeOrder(orderDetails)) {
            return "Order placed successfully";
        } else return "Facing some issues while placing order";
    }

    public List<FoodItemDetails> getFoodItems(String foodItemName, String sortBasedOn, int userId) {
        if (sortBasedOn.equals("price")) {
            return getFoodItemsByPrice(foodItemName, userId);
        } else if (sortBasedOn.equals("rating")) {
            return getFoodItemsByRating(foodItemName, userId);
        }
        return null;
    }

    public List<FoodItemDetails> getFoodItemsByPrice(String foodItemName, int userId) {
        List<FoodItemDetails> foodItemDetailsList = new ArrayList<>();
        foodItemDetailsList = getFoodItemsForUser(foodItemDetailsList, userId, foodItemName);
        Collections.sort(foodItemDetailsList, (o1, o2) -> o2.getPrice() - o1.getPrice());
        return foodItemDetailsList;
    }

    public List<FoodItemDetails> getFoodItemsByRating(String foodItemName, int userId) {
        List<FoodItemDetails> foodItemDetailsList = new ArrayList<>();
        foodItemDetailsList = getFoodItemsForUser(foodItemDetailsList, userId, foodItemName);
        Collections.sort(foodItemDetailsList, (o1, o2) -> o2.getRating() - o1.getRating());
        return foodItemDetailsList;
    }

    List<FoodItemDetails> getFoodItemsForUser(List<FoodItemDetails> foodItemDetailsList, int userId, String foodItemName) {
        List<Restaurant> restaurants = restaurantManager.returnRestaurantsForFoodItem(foodItemName);
        restaurants = restaurantManager.filterRestaurantsForUser(restaurants, userId);
        for (Restaurant restaurant : restaurants) {
            FoodItem foodItem = restaurantManager.getFoodItem(restaurant, foodItemName);
            FoodItemDetails foodItemDetails = new FoodItemDetails();
            foodItemDetails.setItemName(foodItemName);
            foodItemDetails.setPrice(foodItem.getPrice());
            foodItemDetails.setRating(restaurant.getAvgRating());
            foodItemDetails.setRestaurantName(restaurant.getRestaurantName());
            foodItemDetailsList.add(foodItemDetails);
        }

        return foodItemDetailsList;
    }

    public User getUser(int userId) {
        return userData.getUser(userId);
    }


}
