package com.example.MercadosoBack.models.shoppingcart;

import java.util.List;

import com.example.MercadosoBack.models.product.ProductModel;
import com.example.MercadosoBack.models.user.User;
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

    @EmbeddedId
    private ShoppingCartKey id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product_id", nullable = false)
    private ProductModel product;

    @Column(nullable = false)
    private int quantity;
}