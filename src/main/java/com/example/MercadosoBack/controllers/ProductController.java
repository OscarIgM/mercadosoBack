package com.example.MercadosoBack.controllers;

import com.example.MercadosoBack.models.product.CategoryModel;
import com.example.MercadosoBack.models.product.ProductModel;
import com.example.MercadosoBack.models.user.User;
import com.example.MercadosoBack.services.ProductService;
import com.example.MercadosoBack.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/products")
public class ProductController {
    @Autowired
    ProductService productService;
    @Autowired
    UserService userService;
    @GetMapping()
        public ArrayList<ProductModel> getProducts(){
        return productService.obtainProducts();
    }

    @PostMapping()
    public ProductModel saveProduct(@RequestBody ProductModel productData){
       try {
           User user = userService.getUserById(productData.getUser().getId());
           productData.setUser(user);
           return productService.saveProduct(productData);
       } catch (Exception e) {
           e.printStackTrace();
           return null;
       }
    }
    @GetMapping(path = "/{id}")
        public Optional<Optional<ProductModel>> getProductById(@PathVariable("id")Integer id){
        return Optional.of(Optional.ofNullable(this.productService.obtainById(id)));
    }
    @GetMapping(path = "/query")
        public ArrayList<ProductModel>obtainProductByRating(@RequestParam("rating") double rating){
        return this.productService.obtainByRating(rating);
    }

    @DeleteMapping(path = "/delete/{id}")
        public boolean deleteProductById(@PathVariable("id") Integer id){
   productService.deleteProduct(id);
        return true;
    }

    @GetMapping("/filterByCategory/{CategoryName}")
    public List<ProductModel> obtainProductByCategory(@PathVariable String CategoryName) {
        return productService.obtainProductsByCategory(CategoryName);
    }



}
