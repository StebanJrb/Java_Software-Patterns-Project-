package com.sysManagemet.servientrega.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "usuario", schema = "logistica")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String email;

    private String telefono;

    @Enumerated(EnumType.STRING)
    private TipoUsuario tipo;  // REMITENTE o EMPLEADO
}

