package com.example.MercadosoBack.models;
import java.util.List;

import com.example.MercadosoBack.models.user.UserModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "shopping_cart")
public class ShoppingCartModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToOne
    @JoinColumn(name = "user_id")
    private UserModel user;
    @OneToMany(mappedBy = "shoppingCart", cascade = CascadeType.ALL)
    private List<ProductModel> products;
}

