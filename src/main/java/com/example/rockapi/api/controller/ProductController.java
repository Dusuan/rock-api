package com.example.rockapi.api.controller;
import com.example.rockapi.api.model.Product;
import com.example.rockapi.service.ProductService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;
    @Transactional
    @GetMapping(path = "/")
    public Optional<List<Product>> getProducts(@RequestParam(required = false) String name, @RequestParam(required = false) String tipo) {
        if(name.isEmpty() && tipo.isEmpty()) {
            return this.productService.getAllProducts();
        }
        else if(!name.isEmpty() && tipo.isEmpty()){
            return this.productService.getAllProductByName(name);
        }
        else if(name.isEmpty()){
            return this.productService.getProductByProductType(tipo);
        }
        else{
            return this.productService.getProductByNameAndType(name,tipo);
        }
    }

    @GetMapping(path = "/{id}")
    public Optional<Product> getUserById(@PathVariable("id") Long id){
        return this.productService.getProductById(id);
    }

    @GetMapping(path = "/price/over/{price}")
    public Optional<List<Product>> getProductsByPriceOver (@PathVariable("price") double price){
        return this.productService.getAllProductByPriceGreaterThan(price);
    }

    @GetMapping(path = "/name/{name}")
    public Optional<List<Product>> getProductsByName(@PathVariable("name") String name){
        if(name.isEmpty()) {
        return getProducts(null, null);
        }
        return this.productService.getAllProductByName(name);
    }
    @GetMapping(path = "/descripcion/{descripcion}")
    public Optional<List<Product>> getProductsByDescription(@PathVariable("descripcion") String description){
        return this.productService.getAllProductByDescription(description);
    }
    @GetMapping(path = "/price/under/{price}")
    public Optional<List<Product>> getProductsByPriceUnder (@PathVariable("price") double price){
        return this.productService.getAllProductUnderCertainPrice(price);
    }

    @GetMapping(path = "/artista/{nombre}")
    public Optional<List<Product>> getProductsByArtista(@PathVariable("nombre") String nombre){
        return this.productService.getArtistProducts(nombre);
    }
    @GetMapping(path = "/productType/{type}")
    public Optional<List<Product>> getProductsByType (@PathVariable("type") String type){
        return this.productService.getProductByProductType(type);
    }

    @PostMapping(path = "/newProduct")
    public Optional<Product> addProduct(@RequestBody Product product) {
        try{
         this.productService.createProduct(product);
         return Optional.ofNullable(this.productService.createProduct(product));
    }catch(Exception e){
        return Optional.empty();
        }
    }

    @DeleteMapping(path = "/{id}") // este se usa en la app
    public String deleteById(@PathVariable("id") Long id) {
        boolean delete = this.productService.deleteProductById(id);
        if (delete) {
            return "Product with id: " + id + " successfully deleted";
        } else {
            return "We couldn't find the product with id: " + id + " or something else happened";
        }
    }
    @DeleteMapping(path = "/delete/{name}") // no se usa
    public String deleteByName(@PathVariable("name") String name) {
        boolean delete = this.productService.deleteProductByName(name);
        if(delete){
            return "Product with name: " + name + " successfully deleted";
        }
        else{
            return "We couldn't find the product with name: " + name;
        }
    }
}
