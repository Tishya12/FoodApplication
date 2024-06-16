package com.example.clearFood.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    int userId;
    int pinCode;
    String userName;
    List<OrderDetails> orderDetailsList;
}
