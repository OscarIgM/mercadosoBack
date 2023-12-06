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
import java.util.Optional;
import java.util.stream.Collectors;

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
        List<ShoppingCartModel> shoppingCartModel = shoppingCartService.getUserShoppingCart(userId);

        List<ProductModel> products = shoppingCartModel.stream()
                .map(ShoppingCartModel::getProduct)
                .collect(Collectors.toList());

        // Obtener los usuarios dueños de los productos
        List<User> productOwners = products.stream()
                .map(ProductModel::getUser)
                .collect(Collectors.toList());

        // Setear la lista de productos y usuarios dueños en la orden
        order.setItems(new ArrayList<>(products));
        order.setUser(userService.getUserById(userId));
        order.setOrderStatus("solicitado");

        purchaseOrderRepository.save(order);

        shoppingCartService.deleteShoppingCart(userId);

        return order;
    }

    public void deleteOrder(Integer userId, Integer orderId) {
        PurchaseOrder order = purchaseOrderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        if (!order.getUser().getId().equals(userId)) {
            throw new RuntimeException("Order does not belong to the specified user");
        }
        order.getItems().clear();
        purchaseOrderRepository.delete(order);
    }

    //para los owners
    public List<PurchaseOrder> ownersOrders(Integer ownerId) {
        List<PurchaseOrder> ordersList = purchaseOrderRepository.findAll();
        List<PurchaseOrder> ownerOrders = new ArrayList<>();
User user = userService.getUserById(ownerId);

        for (PurchaseOrder purchaseOrder : ordersList) {
            List<ProductModel> filteredItems = purchaseOrder.getItems()
                    .stream()
                    .filter(item -> item.getUser().getId().equals(ownerId))
                    .collect(Collectors.toList());
            if (!filteredItems.isEmpty()) {
                PurchaseOrder ownerOrder = new PurchaseOrder();
                ownerOrder.setId(purchaseOrder.getId());
                ownerOrder.setUser(purchaseOrder.getUser());
                ownerOrder.setItems(filteredItems);
                ownerOrder.setOrderStatus(purchaseOrder.getOrderStatus());
                ownerOrders.add(ownerOrder);
            }
        }
        return ownerOrders;
    }
    public PurchaseOrder setStatus(String status, Integer orderId) {
        Optional<PurchaseOrder> optionalOrder = purchaseOrderRepository.findById(orderId);

        if (optionalOrder.isPresent()) {
            PurchaseOrder order = optionalOrder.get();
            order.setOrderStatus(status);
            return purchaseOrderRepository.save(order);
        } else {
            // Manejar el caso en el que no se encuentra la orden con el ID proporcionado
            // Puedes lanzar una excepción, devolver un valor predeterminado o realizar alguna otra acción.
            // En este ejemplo, lanzamos una excepción de IllegalStateException.
            throw new IllegalStateException("No se encuentra la orden con ID: " + orderId);
        }
    }
}
