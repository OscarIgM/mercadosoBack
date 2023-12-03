package com.example.MercadosoBack.controllers;

import com.example.MercadosoBack.models.product.CategoryModel;
import com.example.MercadosoBack.services.CategoryService;
import com.example.MercadosoBack.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;
    @PostMapping
    public ResponseEntity<String> createCategory(@RequestBody String categoryName) {
        CategoryModel newCategory = categoryService.createCategory(categoryName);
        if (newCategory != null) {
            return new ResponseEntity<>("Creación de categoría exitoso", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("La categoría ya existe", HttpStatus.CONFLICT);
        }
    }
    @GetMapping
    public List<CategoryModel> obtainCategories() {
        return categoryService.obtainCategories();
    }
}
