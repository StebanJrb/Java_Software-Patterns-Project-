package com.sysManagemet.servientrega.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "encomienda", schema = "logistica")
public class Encomienda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relación con solicitud de recolección
    @ManyToOne
    @JoinColumn(name = "solicitud_id")
    private SolicitudRecoleccion solicitud;

    // Estado actual de la encomienda
    @Enumerated(EnumType.STRING)
    @Column(name = "estado")
    private EstadoEncomiendaActual estado;

    // Peso en kilogramos
    @Column(name = "peso")
    private Double pesoKg;

    // Dimensiones como texto "40x20x15"
    private String dimensiones;

    // ✔ Volumen útil para reglas logísticas
    private Double volumen;

    // ✔ Tipo de contenido (documentos, ropa, frágil, etc.)
    @Column(name = "tipo_contenido")
    private String tipoContenido;
    //@ManyToOne
    //@JoinColumn(name = "tanda_salida_id")
    //private TandaSalida tandaSalida;
}



