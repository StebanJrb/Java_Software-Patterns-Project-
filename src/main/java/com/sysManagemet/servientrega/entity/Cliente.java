package com.sysManagemet.servientrega.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "cliente", schema = "logistica")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCliente;

    private String nombre;
    private String apellido;
    private String telefono;
    private String email;
    private String direccion;
}
