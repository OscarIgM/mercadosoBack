package com.example.MercadosoBack.models;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "product")
public class ProductModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private double rating;
    private double cant_rate;
    private String description;
    private String image;
    private double price;
    @ManyToOne
    @JoinColumn(name = "shopping_cart_id")
    private ShoppingCartModel shoppingCart;

}
