package com.example.clearFood.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FoodItem {
    String itemName;
    int foodItemId;
    int quantity;
    int price;
    List<Integer> ratings;
}
