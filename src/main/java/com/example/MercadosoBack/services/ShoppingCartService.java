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
private ProductService productService;
    @Autowired
    private ProductRepository productRepository;


    public void deleteShoppingCart(Integer userId) {
        List<ShoppingCartModel> shoppingCartItems = shoppingCartRepository.findAllByUserId(userId);

        for (ShoppingCartModel item : shoppingCartItems) {
            ShoppingCartKey shoppingCartKey= new ShoppingCartKey(item.getProduct().getId(), userId);
            System.out.println("item borrado es "+shoppingCartKey);
            shoppingCartRepository.deleteById(shoppingCartKey);
        }
    }

    public List<ShoppingCartModel> getUserShoppingCart(Integer userId) {
        return shoppingCartRepository.findAllByUserId(userId);
    }

public User findByUser(User user){
      return shoppingCartRepository.findUserByShoppingCartId(user.getId());
}


    public void deleteShoppingCartItem(Integer userId, Integer productId) {
        ShoppingCartKey id = new ShoppingCartKey(productId, userId);
        System.out.println(id);
        shoppingCartRepository.deleteById(id);
    }

    public ShoppingCartModel saveShoppingCartItem(
            Integer userId,
            Integer productId,
            int quantity) {
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

