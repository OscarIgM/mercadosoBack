package com.example.MercadosoBack.controllers;

import com.example.MercadosoBack.models.order.PurchaseOrder;
import com.example.MercadosoBack.services.ProductService;
import com.example.MercadosoBack.services.PurchaseOrderService;
import com.example.MercadosoBack.services.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/purchase-orders")
public class PurchaseOrderController {

    @Autowired
    private PurchaseOrderService purchaseOrderService;
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

    @DeleteMapping("/{userId}/{orderId}")
    public ResponseEntity<String> deleteOrder(@PathVariable Integer userId, @PathVariable Integer orderId) {
        try {
            purchaseOrderService.deleteOrder(userId, orderId);
            return ResponseEntity.ok("orden borrada con exito");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }


}
