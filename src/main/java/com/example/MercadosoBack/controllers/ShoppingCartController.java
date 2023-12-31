package com.example.MercadosoBack.controllers;


import com.example.MercadosoBack.models.shopping_cart.ShoppingCartModel;
import com.example.MercadosoBack.services.ShoppingCartService;
import com.example.MercadosoBack.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.MercadosoBack.repositories.ProductRepository;
import com.example.MercadosoBack.services.ProductService;
import java.util.List;
@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/shopping-cart")
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;
    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/{id}")
    public List<ShoppingCartModel> getUserShoppingCart(@PathVariable Integer id) {
        return shoppingCartService.getUserShoppingCart(id);
    }
    @PostMapping("/{id}/{productId}/{quantity}")
    public ShoppingCartModel saveShoppingCartItem(@PathVariable Integer id, @PathVariable Integer productId, @PathVariable int quantity
    ) {
        return shoppingCartService.saveShoppingCartItem(id, productId, quantity);
    }


    @DeleteMapping("/delete-product")
    public void deleteShoppingCartItem(
            @RequestParam Integer id,
            @RequestParam Integer productId) {
        shoppingCartService.deleteShoppingCartItem(id, productId);
    }
}
