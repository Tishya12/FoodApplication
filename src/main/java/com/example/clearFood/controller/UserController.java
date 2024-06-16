package com.example.clearFood.controller;

import com.example.clearFood.model.FoodItemDetails;
import com.example.clearFood.model.OrderDetails;
import com.example.clearFood.model.Rating;
import com.example.clearFood.model.User;
import com.example.clearFood.service.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserManager userManager;


    @PostMapping(value = "/registerUser")
    public String registerUser(User user){
        if (userManager.registerUser(user)){
            return "User registered Successfully with user id";
        } else {
            return "User already exist";
        }
    }


    @PostMapping(value = "/getFoodItems")
    public List<FoodItemDetails> getFoodItems(String foodItemName, String sortBasedOn,int userId){
        return userManager.getFoodItems(foodItemName,sortBasedOn,userId);
    }

    @PostMapping(value = "/rate")
    public String giveRating(Rating rating){
        if (userManager.saveRatings(rating)){
            return "rating successfully saved";
        } else {
            return "facing some issues while saving rating";
        }
    }

    @PostMapping(value = "/getAllOrders")
    public List<OrderDetails> getOrderHistory(int userId){
        return userManager.getOrderHistory(userId);
    }

    @PostMapping(value = "/rate")
    public String placeOrder(OrderDetails orderDetails){
        return userManager.placeOrder(orderDetails);
    }








}
