package com.example.MercadosoBack.models.user;

import com.example.MercadosoBack.models.ShoppingCartModel;
import jakarta.persistence.*;
import lombok.*;


import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username"),
                @UniqueConstraint(columnNames = "email")
        })
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String username;
    private String email;
    private String password;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(  name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();
    @OneToOne
    @JoinColumn(name = "shoppingCart_id")
    private ShoppingCartModel shoppingCart;


    public UserModel(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }



}
