package com.sysManagemet.servientrega.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "estado_encomienda", schema = "logistica")
public class EstadoEncomienda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEstado;

    private LocalDateTime fecha;

    private String descripcion;

    private String ubicacion;

    @ManyToOne
    @JoinColumn(name = "id_encomienda")
    private Encomienda encomienda;
}

