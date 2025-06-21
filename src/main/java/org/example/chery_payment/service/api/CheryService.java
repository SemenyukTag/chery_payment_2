package org.example.chery_payment.service.api;

import org.example.chery_payment.entity.Chery;
import org.example.chery_payment.entity.CheryAudit;
import java.util.List;

public interface CheryService {
    // Основные CRUD операции
    Chery save(Chery chery);
    List<Chery> findAll();
    Chery findById(int id);
    void deleteById(int id);
    Chery update(int id, Chery chery);

    // Метод с отслеживанием изменений
    Chery updateWithTracking(int id, Chery chery, String changeDescription);

    // Методы для работы с аудитом
    List<CheryAudit> getAuditLogsForEntity(int entityId);
    List<CheryAudit> getAuditLogsByAction(String action);
}