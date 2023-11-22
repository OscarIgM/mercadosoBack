package com.example.MercadosoBack.services;

import com.example.MercadosoBack.models.ProductModel;
import com.example.MercadosoBack.models.user.Role;
import com.example.MercadosoBack.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    @Autowired
    RoleRepository roleRepository;

    public Role saveRole(Role role){
        return roleRepository.save(role);
    }

}
