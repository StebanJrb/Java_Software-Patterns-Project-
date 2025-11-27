package com.sysManagemet.servientrega.service.template;

import com.sysManagemet.servientrega.entity.Encomienda;
import com.sysManagemet.servientrega.entity.EstadoEncomiendaActual;

public class ProcesoClasificacionEstandar extends ProcesoClasificacionTemplate {

    @Override
    protected void validarEtiqueta(Encomienda encomienda) {
        if (encomienda.getId() == null) {
            throw new IllegalArgumentException("La encomienda no tiene etiqueta válida.");
        }
    }

    @Override
    protected void cargarInformacionDelSistema(Encomienda encomienda) {
        System.out.println("Cargando información desde sistema para encomienda " + encomienda.getId());
    }

    @Override
    protected void calcularPrioridad(Encomienda encomienda) {
        System.out.println("Calculando prioridad automática...");
    }

    @Override
    protected void agruparPorDestino(Encomienda encomienda) {
        System.out.println("Agrupando encomienda por destino...");
    }

    @Override
    protected void sugerirRuta(Encomienda encomienda) {
        System.out.println("Sugiriendo ruta logística óptima...");
    }

    @Override
    protected void registrarEnTanda(Encomienda encomienda) {
        System.out.println("Registrando encomienda en la tanda correspondiente...");
    }

    @Override
    protected void actualizarEstado(Encomienda encomienda) {
        encomienda.setEstado(EstadoEncomiendaActual.CLASIFICADA);
    }
}

