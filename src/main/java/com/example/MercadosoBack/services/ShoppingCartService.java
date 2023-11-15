package com.example.MercadosoBack.services;

import com.example.MercadosoBack.models.ProductModel;
import com.example.MercadosoBack.models.ShoppingCartModel;
import com.example.MercadosoBack.models.user.UserModel;
import com.example.MercadosoBack.repositories.ProductRepository;
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
public UserModel findByUser(UserModel user){
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

