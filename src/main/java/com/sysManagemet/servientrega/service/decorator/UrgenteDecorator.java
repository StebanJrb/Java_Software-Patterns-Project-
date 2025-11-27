package com.sysManagemet.servientrega.service.decorator;

public class UrgenteDecorator implements ServicioEncomienda {

    private final ServicioEncomienda wrappee;

    public UrgenteDecorator(ServicioEncomienda wrappee) {
        this.wrappee = wrappee;
    }

    @Override
    public double calcularCosto() {
        return wrappee.calcularCosto() * 1.2; // 20% extra
    }
}

