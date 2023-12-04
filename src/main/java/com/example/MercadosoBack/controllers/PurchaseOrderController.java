package com.example.MercadosoBack.controllers;

import com.example.MercadosoBack.models.order.PurchaseOrder;
import com.example.MercadosoBack.models.product.ProductModel;
import com.example.MercadosoBack.models.shopping_cart.ShoppingCartModel;
import com.example.MercadosoBack.services.ProductService;
import com.example.MercadosoBack.services.PurchaseOrderService;
import com.example.MercadosoBack.services.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/purchase-orders")
public class PurchaseOrderController {

    @Autowired
    private PurchaseOrderService purchaseOrderService;
@Autowired
private ProductService productService;
@Autowired
private ShoppingCartService shoppingCartService;
    @GetMapping("/user/{userId}")
    public List<PurchaseOrder> getAllOrdersByUser(@PathVariable Integer userId) {
        return purchaseOrderService.getAllOrdersByUser(userId);
    }

    @PostMapping("/createOrder/{userId}")
    public PurchaseOrder createOrder(@PathVariable Integer userId) {
        System.out.println("Id recibida con éxito: " + userId);
        PurchaseOrder order= purchaseOrderService.createOrder(userId);
        shoppingCartService.deleteShoppingCart(userId);
        return order;
    }



}
