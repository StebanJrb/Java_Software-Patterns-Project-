package com.sysManagemet.servientrega.service.template;

import com.sysManagemet.servientrega.dto.SolicitudRecoleccionRequestDTO;
import com.sysManagemet.servientrega.entity.Encomienda;

public class ProcesoSolicitudEstandar extends ProcesoSolicitudTemplate {

    @Override
    protected void validarDatos(SolicitudRecoleccionRequestDTO request) {
        if (request.nombreRemitente() == null || request.nombreRemitente().isEmpty()) {
            throw new IllegalArgumentException("El nombre del remitente es obligatorio");
        }
        if (request.pesoKg() == null || request.pesoKg() <= 0) {
            throw new IllegalArgumentException("El peso debe ser mayor a cero");
        }
    }

    @Override
    protected double calcularCostoBase(Encomienda encomienda) {
        // costo base = peso * 1000 + volumen * 0.5
        double peso = encomienda.getPesoKg() != null ? encomienda.getPesoKg() : 0.0;
        double volumen = encomienda.getVolumen() != null ? encomienda.getVolumen() : 0.0;

        return peso * 1000 + volumen * 0.5;
    }

    @Override
    protected double ajustarCosto(double costo) {
        // envío estándar → sin ajustes adicionales
        return costo;
    }
}


