package com.sysManagemet.servientrega.service.template;

import com.sysManagemet.servientrega.dto.SolicitudRecoleccionRequestDTO;
import com.sysManagemet.servientrega.entity.Encomienda;

public abstract class ProcesoSolicitudTemplate {

    public double procesarSolicitud(SolicitudRecoleccionRequestDTO request, Encomienda encomienda) {

        validarDatos(request); // paso 1
        double costoBase = calcularCostoBase(encomienda); // paso 2
        return ajustarCosto(costoBase); // paso 3
    }

    protected abstract void validarDatos(SolicitudRecoleccionRequestDTO request);

    protected abstract double calcularCostoBase(Encomienda encomienda);

    protected abstract double ajustarCosto(double costo);
}

