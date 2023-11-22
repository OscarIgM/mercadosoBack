package com.example.MercadosoBack.controllers;

import com.example.MercadosoBack.repositories.ProductRepository;
import com.example.MercadosoBack.services.ProductService;
import com.example.MercadosoBack.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.MercadosoBack.models.product.ShoppingCartModel;
import com.example.MercadosoBack.services.ShoppingCartService;
import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/shopping-cart")
public class ShoppingCartController {
@Autowired
private UserService userService;
@Autowired
private ProductService productService;
@Autowired
private ProductRepository productRepository;

    @Autowired
    private ShoppingCartService shoppingCartService;

    @GetMapping
    public List<ShoppingCartModel> getAllShoppingCartItems() {
        return shoppingCartService.getAllShoppingCartItems();
    }



}