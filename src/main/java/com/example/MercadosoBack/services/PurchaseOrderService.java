package com.example.MercadosoBack.services;

import com.example.MercadosoBack.models.order.PurchaseOrder;
import com.example.MercadosoBack.models.product.ProductModel;
import com.example.MercadosoBack.models.shopping_cart.ShoppingCartModel;
import com.example.MercadosoBack.repositories.PurchaseOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseOrderService {
    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;
    @Autowired
    private ShoppingCartService shoppingCartService;

    public List<PurchaseOrder> getAllOrdersByUser(Integer userId) {
        return purchaseOrderRepository.findByUserId(userId);
    }
    public List<PurchaseOrder> createOrder(ShoppingCartModel shoppingCartModel) {
        PurchaseOrder newOrder = new PurchaseOrder();
        newOrder.setUser(shoppingCartModel.getUser());
        newOrder.setOrderStatus("PENDING"); // Puedes establecer el estado como "PENDIENTE" por defecto, ajusta según tu lógica.

        // Asignar la lista de productos a la orden de compra
        List<ProductModel> products = shoppingCartModel.getUser().getProducts(); // Ajusta esto según tu modelo de datos
        newOrder.setItems(products);

        // Guardar la orden en purchaseOrderRepository
        purchaseOrderRepository.save(newOrder);

        // Luego, elimina el carrito de compras
        shoppingCartService.deleteShoppingCart(shoppingCartModel.getUser().getId());

        // Puedes devolver la lista actualizada de órdenes del usuario
        return getAllOrdersByUser(shoppingCartModel.getUser().getId());
    }
}
