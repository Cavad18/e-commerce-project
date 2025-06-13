package com.example.productShopping.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

@Data
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Product marka is required")
    private String marka;

    @NotBlank(message = "Product model is required")
    private String model;

    @NotBlank(message = "Product category is required")
    private String category;

    @Column(columnDefinition = "TEXT")
    @NotNull(message = "Description can't be full")
    @Size(min = 1, max = 5000, message = "Description must be between 1 and 5000 characters")
    private String description;

    @NotNull(message = "Price can't be full")
    @DecimalMin(value = "0", message = "Price must be at least 0")
    private Double price;

    @NotNull(message = "Rate can't be full")
    @DecimalMin(value = "0", message = "Rate must be at least 0")
    @DecimalMax(value = "5", message = "Rate must be less than or equal to 5")
    private int rate;

    @Column(columnDefinition = "TEXT")
    @NotNull(message = "Inage url can't be full")
    @Size(min = 1, max = 5000, message = "Image url must be between 1 and 5000 characters")
    private String imageUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "name_id")
    @JsonBackReference
    private User user;
}
