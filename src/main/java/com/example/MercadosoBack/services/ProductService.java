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
import java.util.stream.Collectors;

@Getter
@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;
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



    public boolean deleteProduct(Integer id) {
        try {
            productRepository.deleteById(id);
            return true;
        } catch (Exception err) {
            return false;
        }
    }
    public CategoryModel createCategory(String categoryName){
        CategoryModel existingCategory = categoryRepository.findByName(categoryName);
        if (existingCategory != null){
            return null;
        }
        CategoryModel newCategory = new CategoryModel();
        newCategory.setName(categoryName);
        return categoryRepository.save(newCategory);
    }
    public List<CategoryModel> obtainCategories() {
        return categoryRepository.findAll();
    }
    public List<ProductModel> obtainProductsByCategory(String categoryName){
        CategoryModel category = categoryRepository.findByName(categoryName);
        if (category != null){
            return productRepository.findByCategory(category);
        }
        return null;
    }

}




