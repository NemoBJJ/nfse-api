package com.gestex.nfse.repository;

import com.gestex.nfse.model.ConfigFiscal;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ConfigFiscalRepository extends JpaRepository<ConfigFiscal, Long> {
    Optional<ConfigFiscal> findByEmpresaId(Long empresaId);
}
