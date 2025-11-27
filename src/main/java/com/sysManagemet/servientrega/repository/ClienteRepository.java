package com.sysManagemet.servientrega.repository;

import com.sysManagemet.servientrega.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}

