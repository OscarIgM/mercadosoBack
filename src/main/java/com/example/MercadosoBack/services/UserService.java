package com.example.MercadosoBack.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.MercadosoBack.models.user.UserModel;
import com.example.MercadosoBack.repositories.UserRepository;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<UserModel> getAllUsers() {
        return userRepository.findAll();
    }

    public UserModel getUserById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    public UserModel createUser(UserModel user) {
        return userRepository.save(user);
    }

    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }

    public UserModel getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
