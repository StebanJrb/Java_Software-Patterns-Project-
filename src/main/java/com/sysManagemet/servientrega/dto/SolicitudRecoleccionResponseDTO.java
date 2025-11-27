package com.sysManagemet.servientrega.dto;

public record SolicitudRecoleccionResponseDTO(
        Long idSolicitud,
        Long idEncomienda,
        double costoTotal,
        String comprobante
) { }

