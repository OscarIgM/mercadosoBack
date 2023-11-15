package com.example.MercadosoBack.services;

import com.example.MercadosoBack.models.ProductModel;
import com.example.MercadosoBack.repositories.ProductRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
@Getter
@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;
    
    public ArrayList<ProductModel> obtainProducts(){
        return (ArrayList<ProductModel>) productRepository.findAll();
    }

    public ProductModel saveProduct(ProductModel product){
        return productRepository.save(product);
    }

    public ProductModel obtainById(Integer id) {
        return productRepository.findById(id).orElse(null);
    }



    public ArrayList<ProductModel> obtainByRating(double rating){
        return productRepository.findByRating(rating);
    }
public boolean deleteProduct(Integer id){
        try {
            productRepository.deleteById(id);
            return true;
        }
        catch (Exception err){
            return false;
        }
}

}




