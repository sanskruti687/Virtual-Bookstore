package com.virtualbookstore.controller;

import com.virtualbookstore.model.*;
import com.virtualbookstore.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired private CartRepository cartRepo;
    @Autowired private BookRepository bookRepo;
    @Autowired private UserRepository userRepo;

    @PostMapping("/add")
    public CartItem addToCart(@RequestBody Map<String, String> body) {
        User user = userRepo.findById(Long.parseLong(body.get("userId"))).orElse(null);
        Book book = bookRepo.findById(Long.parseLong(body.get("bookId"))).orElse(null);
        int quantity = Integer.parseInt(body.get("quantity"));
        CartItem item = new CartItem(null, user, book, quantity);
        return cartRepo.save(item);
    }

    @GetMapping("/{userId}")
    public List<CartItem> viewCart(@PathVariable Long userId) {
        return cartRepo.findByUserId(userId);
    }
}
