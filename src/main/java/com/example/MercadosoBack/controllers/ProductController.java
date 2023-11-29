package com.example.MercadosoBack.controllers;

import com.example.MercadosoBack.models.product.CategoryModel;
import com.example.MercadosoBack.models.product.ProductModel;
import com.example.MercadosoBack.services.ProductService;
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

    @GetMapping()
        public ArrayList<ProductModel> getProducts(){
        return productService.obtainProducts();
    }

    @PostMapping()
    public ProductModel saveProduct(@RequestBody ProductModel product){
       return this.productService.saveProduct(product);
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
        public String deleteProductById(@PathVariable("id") Integer id){
        boolean ok=this.productService.deleteProduct(id);
        if(ok){
            return "Se elemino el usuario con id"+id;
        }else {
            return "No se pudo eliminar el usuario con la id"+id;
        }
    }

    @GetMapping("/filterByCategory/{CategoryName}")
    public List<ProductModel> obtainProductByCategory(@PathVariable String CategoryName) {
        return productService.obtainProductsByCategory(CategoryName);
    }



}
