package com.example.clearFood.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Restaurant {
    int restaurantId;
    String restaurantName;
    List<FoodItem> foodItemList;
    List<Integer> ratings;
    int avgRating;
    Set<String> pinCodes;

}


