package com.example.clearFood.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FoodItemDetails {
    String restaurantName;
    int rating;
    String itemName;
    int price;
}
