package com.example.MercadosoBack.repositories;

import com.example.MercadosoBack.models.order.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Integer> {
    List<PurchaseOrder> findByUserId(Integer userId);
}
