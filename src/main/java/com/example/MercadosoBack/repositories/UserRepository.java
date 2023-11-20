package com.example.MercadosoBack.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.MercadosoBack.models.user.UserModel;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Integer> {
    UserModel findByEmail(String email);
    UserModel findByName(String name);
}
