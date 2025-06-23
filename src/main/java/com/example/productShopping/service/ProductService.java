package com.example.productShopping.service;


import com.example.productShopping.dto.ProductDto;
import com.example.productShopping.entity.Product;
import com.example.productShopping.entity.User;
import com.example.productShopping.repository.ProductRepository;
import com.example.productShopping.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public List<ProductDto> getAllProducts() {
        productRepository.findAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public List<ProductDto> getUserProducts(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return user.getProducts().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public ProductDto addProduct(String username, ProductDto productDto) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Product newProduct = new Product();
        newProduct.setBrand(productDto.getBrand());
        newProduct.setModel(productDto.getModel());
        newProduct.setCategory(productDto.getCategory());
        newProduct.setDescription(productDto.getDescription());
        newProduct.setPrice(productDto.getPrice());
        newProduct.setRate(productDto.getRate());
        newProduct.setImageUrl(productDto.getImageUrl());
        newProduct.setUser(user);

        Product savedProduct = productRepository.save(newProduct);

        user.getProducts().add(savedProduct);
        userRepository.save(user);

        return mapToDto(savedProduct);
    }

    @Transactional

    public void deleteProduct(String username, Long productId, ProductDto productDto) {
        Product product = productRepository.findByIdAndUser_Username(productId, username)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        ProductRepository.delete(product);
    }

    @Transactional
    public ProductDto updateProduct(String username, Long productId, ProductDto productDto){
        Product product = productRepository.findByIdAndUser_Username(productId, username)
                .orElseThrow(() -> new RuntimeException("Product not found or access denied"));
        product.setBrand(productDto.getBrand());
        product.setModel(productDto.getModel());
        product.setCategory(productDto.getCategory());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setRate(productDto.getRate());
        product.setImageUrl(productDto.getImageUrl());

        Product updatedProduct = productRepository.save(product);

        return mapToDto(updatedProduct);
    }
    private ProductDto mapToDto(Product product){
        ProductDto dto = new ProductDto();
        dto.setId(product.getId());
        dto.setBrand(product.getBrand());
        dto.setModel(product.getModel());
        dto.setCategory(product.getCategory());
        dto.setDescription(product.getDescription());
        dto.setPrice(product.getPrice());
        dto.setRate(product.getRate());
        dto.setImageUrl(product.getImageUrl());
        return dto;
    }
}
