package com.example.clearFood.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Rating {
    int ratingId;
    int userId;
    int restuarantId;
    int rating;
}
