package com.example.MercadosoBack.controllers;


import com.example.MercadosoBack.models.shopping_cart.ShoppingCartModel;
import com.example.MercadosoBack.services.ShoppingCartService;
import com.example.MercadosoBack.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.MercadosoBack.models.shoppingcart.ShoppingCartModel;
import com.example.MercadosoBack.repositories.ProductRepository;
import com.example.MercadosoBack.services.ProductService;
import com.example.MercadosoBack.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.MercadosoBack.services.ShoppingCartService;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/shoppingCart")
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    @GetMapping("/{id}")
    public List<ShoppingCartModel> getUserShoppingCart(@PathVariable Integer id) {
        return shoppingCartService.getUserShoppingCart(id);
    }

    @PostMapping("/{id}/{productId}/{quantity}")
@Autowired
private UserService userService;
@Autowired
private ProductService productService;
@Autowired
private ProductRepository productRepository;



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
        return shoppingCartService.saveShoppingCartItem(id, productId, quantity);
    }

    @DeleteMapping("/{id}/{productId}")
    public void deleteShoppingCartItem(
            @PathVariable Integer id,
            @PathVariable Integer productId) {
        shoppingCartService.deleteShoppingCartItem(id, productId);
    }
}
