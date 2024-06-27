package com.example.rockapi.api.controller;
import com.example.rockapi.api.model.Product;
import com.example.rockapi.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ArrayList<Product> getProducts() {
        return this.productService.getAllProducts();
    }

    @PostMapping
    public Product addProduct(@RequestBody Product product) {
        return this.productService.createProduct(product);
    }

    @GetMapping(path = "/{id}")
    public Optional<Product> getUserById(@PathVariable("id") Long id){
        return this.productService.getProductById(id);
    }

    @DeleteMapping(path = "/{id}")
    public String deleteById(@PathVariable("id") Long id) {
        boolean delete = this.productService.deleteProduct(id);
        if (delete) {
            return "Product with id: " + id + "successfully deleted";
        } else {
            return "We couldn't find the product with id: " + id + " or something else happened";
        }
    }

    @GetMapping(path = "/over/{price}")
    public Optional<ArrayList<Product>> getProductsByPriceOver (@PathVariable("price") double price){
        return this.productService.getProductOverCertainPrice(price);
    }

}
