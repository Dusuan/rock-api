package com.example.rockapi.api.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity(name = "producto_artista_tipo")
public class producto_artista_tipo {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private long id;

    @ManyToOne
    @JoinColumn(name = "id_artista", referencedColumnName = "id")
    private artistas id_artista;

    @ManyToOne
    @JoinColumn(name = "id_tipo_producto", referencedColumnName = "id")
    private tipo_producto id_tipo_producto;


}
