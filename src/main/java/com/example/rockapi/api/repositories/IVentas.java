package com.example.rockapi.api.repositories;

import com.example.rockapi.api.model.ventas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IVentas extends JpaRepository<ventas, Long> {
}
