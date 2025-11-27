package com.sysManagemet.servientrega.entity;

import jakarta.persistence.*;
import lombok.Data;
@Entity
@Data
@Table(name = "empleado_hub", schema = "logistica")
public class EmpleadoHub {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEmpleado;

    private String nombre;

    private String rol;

    private String turno;
}

