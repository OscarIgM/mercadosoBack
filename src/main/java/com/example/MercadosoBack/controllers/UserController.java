package com.example.MercadosoBack.controllers;


import com.example.MercadosoBack.models.product.ProductModel;
import com.example.MercadosoBack.models.shopping_cart.ShoppingCartModel;
import com.example.MercadosoBack.services.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.MercadosoBack.models.user.User;
import com.example.MercadosoBack.services.UserService;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Integer id) {
        return userService.getUserById(id);
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
       return this.userService.createUser(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
    }

    @GetMapping("/{id}/products")
    public List<ProductModel> getUserProducts(@PathVariable Integer id) {
        return userService.getUserProducts(id);
    }

    @GetMapping("/{id}/shoppingCart")
    public List<ShoppingCartModel> getUserShoppingCart(@PathVariable Integer id) {
        return userService.getUserShoppingCart(id);
    }

    @PostMapping("/{id}/shoppingCart/{productId}/{quantity}")
    public ShoppingCartModel saveShoppingCartItem(
            @PathVariable Integer id,
            @PathVariable Integer productId,
            @PathVariable int quantity
    ) {
        return userService.saveShoppingCartItem(id, productId, quantity);
    }

    @DeleteMapping("/{id}/shoppingCart/{productId}")
    public void deleteShoppingCartItem(
            @PathVariable Integer id,
            @PathVariable Integer productId) {
        userService.deleteShoppingCartItem(id, productId);
    }
}
