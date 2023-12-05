package com.example.MercadosoBack.services;


import com.example.MercadosoBack.models.product.ProductModel;
import com.example.MercadosoBack.models.shopping_cart.ShoppingCartModel;
import com.example.MercadosoBack.repositories.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.MercadosoBack.models.user.User;
import com.example.MercadosoBack.repositories.UserRepository;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ShoppingCartService shoppingCartService;


    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }


    public List<ProductModel> getUserProducts(Integer id) {
        return  productRepository.findAllByUserId(id);
    }
    public List<ShoppingCartModel> getUserShoppingCart(Integer id) {
        return shoppingCartService.getUserShoppingCart(id);
    }

    public ShoppingCartModel saveShoppingCartItem(
            Integer id,
            Integer productId,
            int quantity
    ) {
        return shoppingCartService.saveShoppingCartItem(id, productId, quantity);
    }

    public void deleteShoppingCartItem(Integer id, Integer productId) {
        shoppingCartService.deleteShoppingCartItem(id, productId);
    }

    public User updateUser(Integer id, User updateUser) {
        // Buscar el usuario existente por ID
        User existingUser = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found"));

        // Actualizar solo los campos proporcionados
        existingUser.setUsername(updateUser.getUsername());
        existingUser.setPassword(updateUser.getPassword());

        // Guardar los cambios en la base de datos
        return userRepository.save(existingUser);
    }
}
