package com.example.MercadosoBack.repositories;

import com.example.MercadosoBack.models.product.CategoryModel;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<CategoryModel, Integer> {
    CategoryModel findByName(String name);
}
