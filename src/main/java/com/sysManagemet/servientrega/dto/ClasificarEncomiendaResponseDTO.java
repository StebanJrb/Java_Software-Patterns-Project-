package com.sysManagemet.servientrega.dto;

public record ClasificarEncomiendaResponseDTO(
        Long encomiendaId,
        String estado,
        String tipoClasificacion,
        String mensaje
) {}

