package com.example.rockapi.service;

import com.example.rockapi.api.model.*;
import com.example.rockapi.api.repositories.*;
import jakarta.transaction.Transactional;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;
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
    @Autowired
    IVentas ventasRepository;



    public Optional<List<Product>> getAllProducts() {
        return Optional.of(productRepository.findAll());
    }
    @Transactional
    public Product createProduct (@NotNull Product producto){ // I have absolutely no idea if im doing this correctly

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
    public Boolean deleteProductById (long id) {

        try {
            Optional <Product> producto = productRepository.findById(id);
            if(producto.isPresent()) {

                ventas nuevaVenta = new ventas();
                Date fecha = new Date();
                String nombre = producto.get().getName();
                double precio = producto.get().getPrice();
                String tipo = producto.get().getProducto_artista_tipo().getId_tipo_producto().getName();

                nuevaVenta.setDate(fecha);
                nuevaVenta.setName(nombre);
                nuevaVenta.setTipo_producto(tipo);
                nuevaVenta.setPrice(precio);

                ventasRepository.save(nuevaVenta);
                productRepository.delete(producto.get());

            }
            else{
                throw new Exception();
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Boolean deleteProductByName(String name){
        try{
            Optional<Product> producto = productRepository.findByName(name);
            productRepository.delete(producto.get());
            return true;
        }catch(Exception e){
            return false;
        }
    }

    public Optional<List<Product>> getAllProductUnderCertainPrice(double price) {
        return productRepository.findAllByPriceLessThan(price);
        }

    public  Optional<Product> getProductById(long id){
        return productRepository.findById(id);
    }

    public Optional<List<Product>> getAllProductByName(String name) {
        return productRepository.findAllByNameContainingIgnoreCase(name);
    }
    public Optional<List<Product>> getAllProductByDescription(String description) {
        return productRepository.findAllByDescriptionContainingIgnoreCase(description);
    }
    public Optional<List<Product>> getAllProductByPriceGreaterThan(double price) {
        return productRepository.findAllByPriceGreaterThan(price);
    }
    public Optional<List<Product>> getArtistProducts(String artistName) {
        return productRepository.findProductosByArtistName(artistName);
    }
    public Optional<List<Product>> getProductByProductType(String productType) {
        return productRepository.findProductByProductType(productType);
    }
    public Optional<List<Product>> getProductByNameAndType(String name, String tipo){
        return productRepository.findAllByNameAndType(name, tipo);
    }

}
