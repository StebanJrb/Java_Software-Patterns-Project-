package com.sysManagemet.servientrega.repository;

import com.sysManagemet.servientrega.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // Opcional: para búsquedas por email si luego lo necesitas
    boolean existsByEmail(String email);
}

