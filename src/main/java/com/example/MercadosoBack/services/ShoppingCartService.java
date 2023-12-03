package com.example.MercadosoBack.services;

import com.example.MercadosoBack.models.product.ProductModel;
import com.example.MercadosoBack.models.shopping_cart.ShoppingCartKey;
import com.example.MercadosoBack.models.shopping_cart.ShoppingCartModel;

import com.example.MercadosoBack.models.user.User;
import com.example.MercadosoBack.repositories.ProductRepository;
import com.example.MercadosoBack.repositories.ShoppingCartRepository;
import com.example.MercadosoBack.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShoppingCartService {

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    public List<ShoppingCartModel> getUserShoppingCart(Integer userId) {
        return shoppingCartRepository.findAllByUserId(userId);
    }

public User findByUser(User user){
      return shoppingCartRepository.findUserByShoppingCartId(user.getId());
}

    public List<ShoppingCartModel> getUserShoppingCart(Integer userId) {
        return shoppingCartRepository.findAllByUserId(userId);
    }
    public void deleteShoppingCartItem(Integer userId, Integer productId) {
        ShoppingCartKey id = new ShoppingCartKey(productId, userId);
        shoppingCartRepository.deleteById(id);
    }

    public ShoppingCartModel saveShoppingCartItem(
            Integer userId,
            Integer productId,
            int quantity) {
        // Buscar instancias de User y ProductModel por sus id
        User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found"));
        ProductModel product = productRepository.findById(productId).orElseThrow(() -> new EntityNotFoundException("Product not found"));

        ShoppingCartKey id = new ShoppingCartKey(productId, userId);
        ShoppingCartModel shoppingCartItem = new ShoppingCartModel();
        shoppingCartItem.setId(id);
        shoppingCartItem.setUser(user);
        shoppingCartItem.setProduct(product);
        shoppingCartItem.setQuantity(quantity);
        return shoppingCartRepository.save(shoppingCartItem);
    }
}

