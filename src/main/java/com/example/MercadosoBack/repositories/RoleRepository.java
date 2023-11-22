package com.example.MercadosoBack.repositories;

import java.util.Optional;

import com.example.MercadosoBack.models.user.ERole;
import com.example.MercadosoBack.models.user.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(ERole name);
}