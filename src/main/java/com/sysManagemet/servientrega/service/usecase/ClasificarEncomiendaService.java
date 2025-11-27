package com.sysManagemet.servientrega.service.usecase;

import com.sysManagemet.servientrega.dto.ClasificarEncomiendaRequestDTO;
import com.sysManagemet.servientrega.dto.ClasificarEncomiendaResponseDTO;
import com.sysManagemet.servientrega.entity.Encomienda;
import com.sysManagemet.servientrega.entity.EstadoEncomiendaActual;
import com.sysManagemet.servientrega.repository.EncomiendaRepository;
import com.sysManagemet.servientrega.service.template.ProcesoClasificacionEstandar;
import com.sysManagemet.servientrega.service.template.ProcesoClasificacionManual;
import com.sysManagemet.servientrega.service.template.ProcesoClasificacionTemplate;
import org.springframework.stereotype.Service;

@Service
public class ClasificarEncomiendaService {

    private final EncomiendaRepository encomiendaRepository;

    public ClasificarEncomiendaService(EncomiendaRepository encomiendaRepository) {
        this.encomiendaRepository = encomiendaRepository;
    }

    public ClasificarEncomiendaResponseDTO clasificar(ClasificarEncomiendaRequestDTO request) {

        // 1️⃣ Obtener encomienda desde la BD
        Encomienda encomienda = encomiendaRepository.findById(request.encomiendaId())
                .orElseThrow(() -> new IllegalArgumentException("Encomienda no encontrada"));

        // 2️⃣ Seleccionar el tipo de proceso (manual o automático)
        ProcesoClasificacionTemplate template = request.clasificacionManual()
                ? new ProcesoClasificacionManual()
                : new ProcesoClasificacionEstandar();

        // 3️⃣ Ejecutar Template Method
        template.clasificarEncomienda(encomienda);

        // 4️⃣ Guardar cambios
        encomiendaRepository.save(encomienda);

        // 5️⃣ Respuesta
        return new ClasificarEncomiendaResponseDTO(
                encomienda.getId(),
                encomienda.getEstado().name(),
                request.clasificacionManual() ? "MANUAL" : "AUTOMÁTICA",
                "Encomienda clasificada correctamente"
        );
    }
}
