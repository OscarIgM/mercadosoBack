package com.example.MercadosoBack.repositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.example.MercadosoBack.models.product.ProductModel;

import java.util.ArrayList;

@Repository
public interface ProductRepository extends CrudRepository<ProductModel,Integer>{
public abstract ArrayList<ProductModel> findByRating(double rating);

}
