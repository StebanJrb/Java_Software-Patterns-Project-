package com.sysManagemet.servientrega.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "solicitud_recoleccion", schema = "logistica")
public class SolicitudRecoleccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // remitente_id → usuario
    @ManyToOne
    @JoinColumn(name = "remitente_id")
    private Usuario remitente;

    private String direccionOrigen;

    private String direccionDestino;

    private Double valorDeclarado;

    private Double tarifaCalculada;

    @Enumerated(EnumType.STRING)
    private EstadoSolicitud estado;

    private LocalDateTime fechaSolicitud;
}


