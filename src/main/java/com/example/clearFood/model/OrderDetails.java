package com.example.clearFood.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetails {
    int orderId;
    int restaurantId;
    int userId;
    FoodItem foodItem;

}
