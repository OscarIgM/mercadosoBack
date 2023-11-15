package com.example.MercadosoBack.repositories;

import com.example.MercadosoBack.models.ShoppingCartModel;
import com.example.MercadosoBack.models.user.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCartModel, Integer> {

    @Query("SELECT s.user FROM ShoppingCartModel s WHERE s.id = :cartId")
    UserModel findUserByShoppingCartId(@Param("cartId") Integer cartId);
}
