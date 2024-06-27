package com.example.rockapi.service;

import com.example.rockapi.api.model.Product;
import com.example.rockapi.api.repositories.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    IProductRepository productRepository;

    public ArrayList<Product> getAllProducts() {
        return (ArrayList<Product>) productRepository.findAll();
    }

    public Product createProduct (Product producto){
        return productRepository.save(producto);
    }

    public  Optional<Product> getProductById(long id){
        return productRepository.findById(id);
    }

    public Boolean deleteProduct (long id) {
        try {
            productRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Optional<ArrayList<Product>> getProductOverCertainPrice(double price) {
        return productRepository.findByPriceGreaterThan(price);
        }


}
