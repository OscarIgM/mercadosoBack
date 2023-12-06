package com.example.MercadosoBack.services;

import com.example.MercadosoBack.models.product.CategoryModel;
import com.example.MercadosoBack.models.product.ProductModel;
import com.example.MercadosoBack.repositories.CategoryRepository;
import com.example.MercadosoBack.repositories.ProductRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Getter
@Service
public class ProductService {

    @Autowired
 private ProductRepository productRepository;
    @Autowired
    CategoryRepository categoryRepository;
    
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



    public void deleteProduct(Integer id) {
    productRepository.deleteById(id);
    }

    public List<ProductModel> obtainProductsByCategory(String categoryName){
        CategoryModel category = categoryRepository.findByName(categoryName);
        if (category != null){
            return productRepository.findByCategory(category);
        }
        return null;
    }
    public List<ProductModel> searchProductsByName(String query){
        String formattedQuery = query.trim().toLowerCase();
        return productRepository.findByNameContainingIgnoreCase(formattedQuery);
    }

}




