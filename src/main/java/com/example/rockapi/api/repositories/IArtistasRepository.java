package com.example.rockapi.api.repositories;

import com.example.rockapi.api.model.Product;
import com.example.rockapi.api.model.artistas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Optional;

@Repository
public interface IArtistasRepository extends JpaRepository<artistas, Long>{

    Optional<artistas> findByName(String name);

}
