package com.gestex.nfse.repository;

import com.gestex.nfse.model.HistoricoNotas;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface HistoricoNotasRepository extends JpaRepository<HistoricoNotas, Long> {
    List<HistoricoNotas> findByEmpresaIdOrderByCreatedAtDesc(Long empresaId);
}
