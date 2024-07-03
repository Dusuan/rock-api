package com.example.rockapi.service;

import com.example.rockapi.api.model.Product;
import com.example.rockapi.api.model.artistas;
import com.example.rockapi.api.model.producto_artista_tipo;
import com.example.rockapi.api.model.tipo_producto;
import com.example.rockapi.api.repositories.IProductRepository;
import com.example.rockapi.api.repositories.IProducto_artista_tipo;
import com.example.rockapi.api.repositories.ITipo_productoRepository;
import jakarta.transaction.Transactional;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.rockapi.api.repositories.IArtistasRepository;


import java.util.ArrayList;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    IProductRepository productRepository;
    @Autowired
    IArtistasRepository artistasRepository;
    @Autowired
    ITipo_productoRepository tipoProductoRepository;
    @Autowired
    IProducto_artista_tipo producto_artista_tipoRepository;


    public ArrayList<Product> getAllProducts() {
        return (ArrayList<Product>) productRepository.findAll();
    }
    @Transactional
    public Product createProduct (@NotNull Product producto){ // i have absolutely no idea if im doing this correctly

        Optional<artistas> Artista = artistasRepository.findByName(
                producto.getProducto_artista_tipo().getId_artista().getName());

        Optional<tipo_producto> Tipo = tipoProductoRepository.findByName(
                producto.getProducto_artista_tipo().getId_tipo_producto().getName());

        Optional<producto_artista_tipo> union = producto_artista_tipoRepository.findByNames(
                producto.getProducto_artista_tipo().getId_artista().getName(),
                producto.getProducto_artista_tipo().getId_artista().getName()
        );

            if(Artista.isEmpty()){
                Artista = Optional.of(artistasRepository.save(producto.getProducto_artista_tipo().getId_artista()));
            }
            if(Tipo.isEmpty()){
                Tipo = Optional.of(tipoProductoRepository.save(producto.getProducto_artista_tipo().getId_tipo_producto()));
            }
            if (union.isEmpty()) {
                producto_artista_tipo nuevo = new producto_artista_tipo();
                nuevo.setId_artista(Artista.get());
                nuevo.setId_tipo_producto(Tipo.get());
                nuevo = producto_artista_tipoRepository.save(nuevo);
                producto.setProducto_artista_tipo(nuevo);
            }

        productRepository.save(producto);

      return producto;
    }



    public Boolean deleteProduct (long id) {
        try {
            productRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Optional<ArrayList<Product>> getAllProductUnderCertainPrice(double price) {
        return productRepository.findAllByPriceLessThan(price);
        }

    public  Optional<Product> getProductById(long id){
        return productRepository.findById(id);
    }

    public Optional<ArrayList<Product>> getAllProductByName(String name) {
        return productRepository.findAllByNameContainingIgnoreCase(name);
    }
    public Optional<ArrayList<Product>> getAllProductByDescription(String description) {
        return productRepository.findAllByDescriptionContainingIgnoreCase(description);
    }
    public Optional<ArrayList<Product>> getAllProductByPriceGreaterThan(double price) {
        return productRepository.findAllByPriceGreaterThan(price);
    }
    public Optional<ArrayList<Product>> getArtistProducts(String artistName) {
        return productRepository.findProductosByArtistName(artistName);
    }
    public Optional<ArrayList<Product>> getProductByProductType(String productType) {
        return productRepository.findProductByProductType(productType);
    }

}
