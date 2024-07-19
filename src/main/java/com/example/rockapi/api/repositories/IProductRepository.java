package com.example.rockapi.api.repositories;

import com.example.rockapi.api.model.Product;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public interface IProductRepository
        extends JpaRepository<Product, Long> {

    Optional<Product> findByName(String name);

    Optional<List<Product>> findAllByPriceGreaterThan(double price);

    Optional<List<Product>> findAllByNameContainingIgnoreCase(String name);

    Optional<List<Product>> findAllByPriceLessThan(double price);

    Optional<List<Product>> findAllByDescriptionContainingIgnoreCase(String description);

    @Query("SELECT p FROM productos p JOIN p.producto_artista_tipo pat JOIN pat.id_tipo_producto tp WHERE LOWER(tp.name) = LOWER(:type) AND LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%')) OR LOWER(p.name) = :name")
    Optional<List<Product>> findAllByNameAndType(String name, String type);

    void deleteByName(String name);

    void deleteById(@NotNull Long id);


    @Query("SELECT p FROM productos p " +
            "JOIN p.producto_artista_tipo pat " +
            "JOIN pat.id_artista a " +
            "WHERE LOWER(a.name) LIKE LOWER(CONCAT('%', :val,'%'))")
    Optional<List<Product>> findProductosByArtistName(@Param("val") String artistName);

    @Query("SELECT p from productos p JOIN p.producto_artista_tipo pat JOIN pat.id_tipo_producto a WHERE a.name=:val")
    Optional<List<Product>> findProductByProductType(@Param("val") String productType);

}
