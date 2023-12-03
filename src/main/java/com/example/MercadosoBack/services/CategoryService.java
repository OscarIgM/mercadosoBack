package com.example.MercadosoBack.services;

import com.example.MercadosoBack.models.product.CategoryModel;
import com.example.MercadosoBack.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;
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
}
