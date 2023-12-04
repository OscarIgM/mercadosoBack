package com.example.MercadosoBack.controllers;

import com.example.MercadosoBack.models.order.PurchaseOrder;
import com.example.MercadosoBack.models.shopping_cart.ShoppingCartModel;
import com.example.MercadosoBack.services.PurchaseOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/purchase-orders")
public class PurchaseOrderController {

    @Autowired
    private PurchaseOrderService purchaseOrderService;

    @GetMapping("/user/{userId}")
    public List<PurchaseOrder> getAllOrdersByUser(@PathVariable Integer userId) {
        return purchaseOrderService.getAllOrdersByUser(userId);
    }

    @PostMapping("/createOrder")
    public List<PurchaseOrder> createOrder(@RequestBody ShoppingCartModel shoppingCartModel){
        return purchaseOrderService.createOrder(shoppingCartModel);
    }

}
