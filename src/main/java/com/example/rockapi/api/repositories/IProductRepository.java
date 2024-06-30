package com.example.rockapi.api.repositories;

import com.example.rockapi.api.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Optional;

@Repository
public interface IProductRepository
        extends JpaRepository<Product, Long> {

    Optional<Product> findByName(String name);

    Optional<ArrayList<Product>> findAllByPriceGreaterThan(double price);

    Optional<ArrayList<Product>> findAllByNameContainingIgnoreCase(String name);

    Optional<ArrayList<Product>> findAllByPriceLessThan(double price);

    Optional<ArrayList<Product>> findAllByDescriptionContainingIgnoreCase(String description);



    @Query("SELECT p FROM productos p " +
            "JOIN p.producto_artista_tipo pat " +
            "JOIN pat.id_artista a " +
            "WHERE a.name = :val")
    Optional<ArrayList<Product>> findProductosByArtistName(@Param("val") String artistName);

    @Query("SELECT p from productos p JOIN p.producto_artista_tipo pat JOIN pat.id_tipo_producto a WHERE a.name=:val")
    Optional<ArrayList<Product>> findProductByProductType(@Param("val") String productType);

}
