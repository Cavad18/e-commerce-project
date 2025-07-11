package com.example.productShopping.repository;

import com.example.productShopping.entity.CartItem;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<CartItem, Long> {
List<CartItem> FindByUser_Username(String username);
Optional<CartItem> findByUser_UsernameAndProduct_Id(String username, Long productId);

void deleteByUser(User user);
}
