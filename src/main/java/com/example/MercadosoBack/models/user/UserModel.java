package com.example.MercadosoBack.models.user;

import com.example.MercadosoBack.models.ShoppingCartModel;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "users")
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String name;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
@Enumerated(EnumType.STRING)
private Role role;
@OneToOne
@JoinColumn(name = "shoppingCart_id")
    private ShoppingCartModel shoppingCart;
}
