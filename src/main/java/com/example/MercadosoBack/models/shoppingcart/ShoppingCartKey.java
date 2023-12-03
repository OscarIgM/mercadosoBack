package com.example.MercadosoBack.models.shoppingcart;


import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Embeddable
public class ShoppingCartKey {

    private Integer productId;
    private Integer userId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ShoppingCartKey that)) return false;
        return Objects.equals(productId, that.productId) && Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, userId);
    }
}