package com.sysManagemet.servientrega.service.decorator;

public class SeguroDecorator implements ServicioEncomienda {

    private final ServicioEncomienda wrappee;

    public SeguroDecorator(ServicioEncomienda wrappee) {
        this.wrappee = wrappee;
    }

    @Override
    public double calcularCosto() {
        return wrappee.calcularCosto() + 5000;
    }
}

