package com.sysManagemet.servientrega.service.template;

import com.sysManagemet.servientrega.entity.Encomienda;

public abstract class ProcesoClasificacionTemplate {

    // Método principal del Template Method
    public final void clasificarEncomienda(Encomienda encomienda) {
        validarEtiqueta(encomienda);
        cargarInformacionDelSistema(encomienda);
        calcularPrioridad(encomienda);
        agruparPorDestino(encomienda);
        sugerirRuta(encomienda);
        registrarEnTanda(encomienda);
        actualizarEstado(encomienda);
    }

    // --- Pasos obligatorios pero personalizables ---

    protected abstract void validarEtiqueta(Encomienda encomienda);

    protected abstract void cargarInformacionDelSistema(Encomienda encomienda);

    protected abstract void calcularPrioridad(Encomienda encomienda);

    protected abstract void agruparPorDestino(Encomienda encomienda);

    protected abstract void sugerirRuta(Encomienda encomienda);

    protected abstract void registrarEnTanda(Encomienda encomienda);

    protected abstract void actualizarEstado(Encomienda encomienda);
}

