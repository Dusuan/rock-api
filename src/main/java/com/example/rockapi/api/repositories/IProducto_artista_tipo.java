package com.example.rockapi.api.repositories;

import com.example.rockapi.api.model.Product;
import com.example.rockapi.api.model.artistas;
import com.example.rockapi.api.model.producto_artista_tipo;
import com.example.rockapi.api.model.tipo_producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Optional;

@Repository
public interface IProducto_artista_tipo extends JpaRepository<producto_artista_tipo, Long> {

    @Query("SELECT pat FROM producto_artista_tipo pat JOIN pat.id_artista a WHERE a.name = :artista AND pat.id_tipo_producto.name= :tipoProducto")
    Optional<producto_artista_tipo> findByNames(@Param("artista") String artista, @Param("tipoProducto") String tipoProducto);
}
