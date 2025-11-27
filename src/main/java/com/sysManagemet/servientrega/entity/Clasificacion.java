package com.sysManagemet.servientrega.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "clasificacion", schema = "logistica")
public class Clasificacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idClasificacion;

    private LocalDateTime fecha;

    @Enumerated(EnumType.STRING)
    private Prioridad prioridadAsignada;

    private String destinoGrupo;

    @ManyToOne
    @JoinColumn(name = "id_encomienda")
    private Encomienda encomienda;

    @ManyToOne
    @JoinColumn(name = "id_empleado")
    private EmpleadoHub empleado;
}

