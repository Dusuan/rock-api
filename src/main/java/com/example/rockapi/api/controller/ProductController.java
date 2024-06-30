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

    @GetMapping(path = "/")
    public ArrayList<Product> getProducts() {
        return this.productService.getAllProducts();
    }

    @GetMapping(path = "/{id}")
    public Optional<Product> getUserById(@PathVariable("id") Long id){
        return this.productService.getProductById(id);
    }

    @GetMapping(path = "/price/over/{price}")
    public Optional<ArrayList<Product>> getProductsByPriceOver (@PathVariable("price") double price){
        return this.productService.getAllProductByPriceGreaterThan(price);
    }

    @GetMapping(path = "/name/{name}")
    public Optional<ArrayList<Product>> getProductsByName(@PathVariable("name") String name){
        return this.productService.getAllProductByName(name);
    }
    @GetMapping(path = "/descripcion/{descripcion}")
    public Optional<ArrayList<Product>> getProductsByDescription(@PathVariable("descripcion") String description){
        return this.productService.getAllProductByDescription(description);
    }
    @GetMapping(path = "/price/under/{price}")
    public Optional<ArrayList<Product>> getProductsByPriceUnder (@PathVariable("price") double price){
        return this.productService.getAllProductUnderCertainPrice(price);
    }

    @GetMapping(path = "/artista/{nombre}")
    public Optional<ArrayList<Product>> getProductsByArtista(@PathVariable("nombre") String nombre){
        return this.productService.getArtistProducts(nombre);
    }
    @GetMapping(path = "/productType/{type}")
    public Optional<ArrayList<Product>> getProductsByType (@PathVariable("type") String type){
        return this.productService.getProductByProductType(type);
    }

    @PostMapping
    public Product addProduct(@RequestBody Product product) {
        return this.productService.createProduct(product);
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



}
