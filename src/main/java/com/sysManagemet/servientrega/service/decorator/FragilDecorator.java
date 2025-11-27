package com.sysManagemet.servientrega.service.decorator;

public class FragilDecorator implements ServicioEncomienda {

    private final ServicioEncomienda wrappee;

    public FragilDecorator(ServicioEncomienda wrappee) {
        this.wrappee = wrappee;
    }

    @Override
    public double calcularCosto() {
        return wrappee.calcularCosto() + 3000;
    }
}

