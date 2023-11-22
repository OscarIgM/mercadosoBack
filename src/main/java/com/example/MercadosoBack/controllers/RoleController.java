package com.example.MercadosoBack.controllers;

import com.example.MercadosoBack.models.user.Role;
import com.example.MercadosoBack.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    RoleService roleService;

    @PostMapping
    public Role saveRole(@RequestParam Role role) {
        return this.roleService.saveRole(role);

    }



}
