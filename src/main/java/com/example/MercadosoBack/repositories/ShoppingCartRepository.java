package com.example.MercadosoBack.repositories;

import com.example.MercadosoBack.models.product.ShoppingCartModel;
import com.example.MercadosoBack.models.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCartModel, Integer> {

    @Query("SELECT s.user FROM ShoppingCartModel s WHERE s.id = :cartId")
    User findUserByShoppingCartId(@Param("cartId") Integer cartId);
}
