package com.sysManagemet.servientrega.service.decorator;

public class ServicioBase implements ServicioEncomienda {

    private final double costoBase;

    public ServicioBase(double costoBase) {
        this.costoBase = costoBase;
    }

    @Override
    public double calcularCosto() {
        return costoBase;
    }
}

