package com.sysManagemet.servientrega.dto;

public record SolicitudRecoleccionRequestDTO(

        String nombreRemitente,
        String emailRemitente,
        String telefonoRemitente,

        String direccionOrigen,
        String direccionDestino,

        Double pesoKg,
        Double volumen,
        String tipoContenido,

        boolean esFragil,
        boolean esUrgente,
        boolean requiereSeguro,

        Double valorDeclarado

) {}

