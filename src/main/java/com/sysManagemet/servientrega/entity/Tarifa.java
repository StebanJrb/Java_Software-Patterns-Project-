package com.sysManagemet.servientrega.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "tarifa", schema = "logistica")
public class Tarifa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTarifa;

    @Enumerated(EnumType.STRING)
    private TipoServicio tipoServicio;

    private Double costoBase;

    private Double costoPorKg;

    private Double recargoFragil;

    private Double recargoAltoValor;
}

