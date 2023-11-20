package com.example.MercadosoBack.services;

import com.example.MercadosoBack.models.user.UserModel;
import com.example.MercadosoBack.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String nombre) throws UsernameNotFoundException {
        UserModel usuario = userRepository.findByName(nombre);

        if (usuario == null) {
            throw new UsernameNotFoundException("Usuario no encontrado: " + nombre);
        }

        return org.springframework.security.core.userdetails.User
                .withUsername(usuario.getName())
                .password(usuario.getPassword())
                .roles("USER")
                .build();
    }
}
