package com.example.MercadosoBack.repositories;
import com.example.MercadosoBack.models.product.CategoryModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.example.MercadosoBack.models.product.ProductModel;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<ProductModel,Integer>{
    ArrayList<ProductModel> findByRating(double rating);

    List<ProductModel> findAllByUserId(Integer id);
    List<ProductModel> findByCategory(CategoryModel category);
}
