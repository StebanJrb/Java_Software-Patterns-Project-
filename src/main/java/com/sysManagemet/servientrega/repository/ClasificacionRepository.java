package com.sysManagemet.servientrega.repository;

import com.sysManagemet.servientrega.entity.Clasificacion;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ClasificacionRepository extends JpaRepository<Clasificacion, Long> {

    List<Clasificacion> findByEncomiendaId(Long idEncomienda);
}


