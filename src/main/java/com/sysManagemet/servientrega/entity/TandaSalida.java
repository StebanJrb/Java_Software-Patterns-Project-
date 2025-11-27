package com.sysManagemet.servientrega.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;


@Entity
@Data
@Table(name = "tanda_salida", schema = "logistica")
public class TandaSalida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTanda;

    private LocalDateTime fechaCreacion;

    private String rutaAsignada;

    private String vehiculo;

    //@OneToMany(mappedBy = "tandaSalida")
    //private List<Encomienda> encomiendas;
}

