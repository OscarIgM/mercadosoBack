package com.example.MercadosoBack.services;

import com.example.MercadosoBack.models.order.PurchaseOrder;
import com.example.MercadosoBack.models.product.ProductModel;
import com.example.MercadosoBack.models.shopping_cart.ShoppingCartModel;
import com.example.MercadosoBack.models.user.User;
import com.example.MercadosoBack.repositories.PurchaseOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class PurchaseOrderService {
    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;
    @Autowired
    private ShoppingCartService shoppingCartService;
    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;

    public List<PurchaseOrder> getAllOrdersByUser(Integer userId) {
        return purchaseOrderRepository.findByUserId(userId);
    }


    public PurchaseOrder createOrder(Integer userId) {
PurchaseOrder order = new PurchaseOrder();
        List<ShoppingCartModel> shoppingCartModel= shoppingCartService.getUserShoppingCart(userId);
        System.out.println("datos a obtener"+ shoppingCartModel.stream());
        order.setUser(userService.getUserById(userId));
        order.setItems(shoppingCartModel);
        order.setOrderStatus("Pending");
        purchaseOrderRepository.save(order);
        return order;
    }
}
