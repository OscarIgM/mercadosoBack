package com.example.MercadosoBack.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.MercadosoBack.models.user.UserModel;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Integer> {
    Optional<UserModel> findByUsername(String username);
    UserModel findByEmail(String email);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}
