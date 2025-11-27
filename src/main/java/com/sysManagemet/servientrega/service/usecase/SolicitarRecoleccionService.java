package com.sysManagemet.servientrega.service.usecase;

import com.sysManagemet.servientrega.dto.SolicitudRecoleccionRequestDTO;
import com.sysManagemet.servientrega.dto.SolicitudRecoleccionResponseDTO;
import com.sysManagemet.servientrega.entity.*;
import com.sysManagemet.servientrega.repository.EncomiendaRepository;
import com.sysManagemet.servientrega.repository.SolicitudRecoleccionRepository;
import com.sysManagemet.servientrega.repository.UsuarioRepository;
import com.sysManagemet.servientrega.service.decorator.*;
import com.sysManagemet.servientrega.service.template.ProcesoSolicitudEstandar;
import org.springframework.stereotype.Service;

@Service
public class SolicitarRecoleccionService {

    private final UsuarioRepository usuarioRepo;
    private final SolicitudRecoleccionRepository solicitudRepo;
    private final EncomiendaRepository encomiendaRepo;

    public SolicitarRecoleccionService(
            UsuarioRepository usuarioRepo,
            SolicitudRecoleccionRepository solicitudRepo,
            EncomiendaRepository encomiendaRepo
    ) {
        this.usuarioRepo = usuarioRepo;
        this.solicitudRepo = solicitudRepo;
        this.encomiendaRepo = encomiendaRepo;
    }

    public SolicitudRecoleccionResponseDTO solicitar(SolicitudRecoleccionRequestDTO request) {

        // 1️⃣ Crear remitente (Usuario)
        Usuario remitente = new Usuario();
        remitente.setNombre(request.nombreRemitente());
        remitente.setEmail(request.emailRemitente());
        remitente.setTelefono(request.telefonoRemitente());
        remitente.setTipo(TipoUsuario.REMITENTE);

        remitente = usuarioRepo.save(remitente);

        // 2️⃣ Crear la solicitud de recolección
        SolicitudRecoleccion solicitud = new SolicitudRecoleccion();
        solicitud.setRemitente(remitente);
        solicitud.setDireccionOrigen(request.direccionOrigen());
        solicitud.setDireccionDestino(request.direccionDestino());
        solicitud.setValorDeclarado(request.valorDeclarado());
        solicitud.setTarifaCalculada(0.0); // temporal
        solicitud.setEstado(EstadoSolicitud.PENDIENTE);

        solicitud = solicitudRepo.save(solicitud);

        // 3️⃣ Crear la encomienda asociada
        Encomienda encomienda = new Encomienda();
        encomienda.setSolicitud(solicitud);
        encomienda.setPesoKg(request.pesoKg());
        encomienda.setVolumen(request.volumen());
        encomienda.setTipoContenido(request.tipoContenido());
        encomienda.setEstado(EstadoEncomiendaActual.EN_HUB);

        encomienda = encomiendaRepo.save(encomienda);

        // 4️⃣ TEMPLATE METHOD → calcular costo base
        ProcesoSolicitudEstandar proceso = new ProcesoSolicitudEstandar();
        double costoBase = proceso.procesarSolicitud(request, encomienda);

        // 5️⃣ DECORATOR → aplicar extras
        ServicioEncomienda servicio = new ServicioBase(costoBase);

        if (request.esFragil()) servicio = new FragilDecorator(servicio);
        if (request.esUrgente()) servicio = new UrgenteDecorator(servicio);
        if (request.requiereSeguro()) servicio = new SeguroDecorator(servicio);

        double costoFinal = servicio.calcularCosto();

        // 6️⃣ Ajustar costos finales
        solicitud.setTarifaCalculada(costoFinal);
        solicitudRepo.save(solicitud);

        // 7️⃣ retornar DTO
        return new SolicitudRecoleccionResponseDTO(
                solicitud.getId(),
                encomienda.getId(),
                costoFinal,
                "SOL-" + solicitud.getId()
        );
    }
}


