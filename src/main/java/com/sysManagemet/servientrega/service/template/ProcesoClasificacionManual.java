package com.sysManagemet.servientrega.service.template;

import com.sysManagemet.servientrega.entity.Encomienda;
import com.sysManagemet.servientrega.entity.EstadoEncomiendaActual;

public class ProcesoClasificacionManual extends ProcesoClasificacionTemplate {

    @Override
    protected void validarEtiqueta(Encomienda encomienda) {
        System.out.println("Verificando etiqueta físicamente...");
    }

    @Override
    protected void cargarInformacionDelSistema(Encomienda encomienda) {
        System.out.println("Consultando datos en sistema por el operador...");
    }

    @Override
    protected void calcularPrioridad(Encomienda encomienda) {
        System.out.println("Operador asigna prioridad manualmente...");
    }

    @Override
    protected void agruparPorDestino(Encomienda encomienda) {
        System.out.println("Operador asigna grupo por destino...");
    }

    @Override
    protected void sugerirRuta(Encomienda encomienda) {
        System.out.println("Ruta sugerida manualmente o desde sistema...");
    }

    @Override
    protected void registrarEnTanda(Encomienda encomienda) {
        System.out.println("Registrando encomienda en tanda manualmente...");
    }

    @Override
    protected void actualizarEstado(Encomienda encomienda) {
        encomienda.setEstado(EstadoEncomiendaActual.CLASIFICADA);
    }
}

