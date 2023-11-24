package com.example.MercadosoBack.services;


import com.example.MercadosoBack.models.product.ProductModel;
import com.example.MercadosoBack.repositories.ProductRepository;
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
}
