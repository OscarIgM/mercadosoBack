package com.example.MercadosoBack.repositories;

import com.example.MercadosoBack.models.shopping_cart.ShoppingCartKey;
import com.example.MercadosoBack.models.shopping_cart.ShoppingCartModel;

import com.example.MercadosoBack.models.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCartModel, ShoppingCartKey> {

    @Query("SELECT s.user FROM ShoppingCartModel s WHERE s.id = :cartId")
    User findUserByShoppingCartId(@Param("cartId") Integer cartId);


    List<ShoppingCartModel> findAllByUserId(Integer id);
}
