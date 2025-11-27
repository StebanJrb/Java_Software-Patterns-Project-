package com.sysManagemet.servientrega.service.template;

import com.sysManagemet.servientrega.dto.SolicitudRecoleccionRequestDTO;
import com.sysManagemet.servientrega.entity.Encomienda;

public abstract class ProcesoSolicitudRecoleccion {

    // Template method (final = no se modifica)
    public final double procesarSolicitud(SolicitudRecoleccionRequestDTO request, Encomienda encomienda) {

        validarDatos(request);

        double costoBase = calcularCostoBase(encomienda);

        double costoConExtras = aplicarServiciosAdicionales(costoBase, encomienda, request);

        registrarEncomienda(encomienda, costoConExtras);

        return costoConExtras;
    }

    // Pasos obligatorios que cada subclase implementará
    protected abstract void validarDatos(SolicitudRecoleccionRequestDTO request);
    protected abstract double calcularCostoBase(Encomienda encomienda);
    protected abstract double aplicarServiciosAdicionales(double costoBase, Encomienda encomienda, SolicitudRecoleccionRequestDTO request);
    protected abstract void registrarEncomienda(Encomienda encomienda, double costoFinal);
}

