package org.example.chery_payment.repository;

import org.example.chery_payment.entity.CheryAudit;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CheryAuditRepository extends JpaRepository<CheryAudit, Long> {
    List<CheryAudit> findByEntityIdOrderByChangedAtDesc(int entityId);
    List<CheryAudit> findByActionOrderByChangedAtDesc(String action);
}