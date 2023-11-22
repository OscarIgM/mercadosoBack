package com.example.MercadosoBack.services;

import com.example.MercadosoBack.models.product.ProductModel;
import com.example.MercadosoBack.models.product.ShoppingCartModel;
import com.example.MercadosoBack.models.user.User;
import com.example.MercadosoBack.repositories.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShoppingCartService {

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    public List<ShoppingCartModel> getAllShoppingCartItems() {
        return shoppingCartRepository.findAll();
    }
public User findByUser(User user){
      return shoppingCartRepository.findUserByShoppingCartId(user.getId());
}
    public ShoppingCartModel addProduct(ShoppingCartModel shoppingCart, ProductModel product) {
        List<ProductModel> products = shoppingCart.getProducts();
        if (products == null) {
            products = new ArrayList<>();
        }
        products.add(product);
        shoppingCart.setProducts(products);
        return shoppingCartRepository.save(shoppingCart);
    }
}

