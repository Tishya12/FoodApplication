package com.example.clearFood.repository;

import com.example.clearFood.model.OrderDetails;
import com.example.clearFood.model.Rating;
import com.example.clearFood.model.User;
import com.example.clearFood.service.RestaurantManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class UserData {

    @Autowired
    RestaurantManager restaurantManager;
    List<User> userList = new ArrayList<>();
    Map<Integer, User> userMap = new HashMap<>();

    List<Rating> ratings = new ArrayList<>();

    Map<Integer, Rating> ratingMap = new HashMap<>();

    public boolean addUser(User user) {
        if (userMap.containsKey(user.getUserId())) {
            return false;
        } else {
            userList.add(user);
            userMap.put(user.getUserId(), user);
            return true;
        }
    }

    public boolean saveRatings(Rating rating) {
        if (ratingMap.containsKey(rating.getRatingId())) {
            return false;
        } else {
            ratings.add(rating);
            ratingMap.put(rating.getRatingId(), rating);
            return true;
        }
    }

    public boolean validUser(int userId) {
        if (userMap.containsKey(userId)) {
            return true;
        } else return false;
    }

    public List<OrderDetails> getOrderHistory(int userId) {
        return userMap.get(userId).getOrderDetailsList();
    }

    public boolean placeOrder(OrderDetails orderDetails) {
        if (userMap.containsKey(orderDetails.getUserId())) {
            User user = userMap.get(orderDetails.getUserId());
            List<OrderDetails> orderDetailsList = user.getOrderDetailsList();
            orderDetailsList.add(orderDetails);
            user.setOrderDetailsList(orderDetailsList);
            restaurantManager.updateQuantity(orderDetails.getRestaurantId(), orderDetails.getFoodItem().getFoodItemId(),
                    orderDetails.getFoodItem().getQuantity());
            return true;
        } else {
            return false;
        }
    }

    public User getUser(int userId) {
        return userMap.get(userId);
    }
}
