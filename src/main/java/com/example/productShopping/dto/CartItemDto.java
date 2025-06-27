package com.example.productShopping.dto;

import lombok.Data;
@Data

public class CartItemDto {
        private Long id;
        private Long ProductId;
        private String brand;
        private String model;
        private String category;
        private String description;
        private double price;
        private double rate;
        private String imageUrl;
        private Integer quantity;
}
