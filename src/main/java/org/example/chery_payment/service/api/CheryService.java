package org.example.chery_payment.service.api;

import lombok.RequiredArgsConstructor;
import org.example.chery_payment.entity.Card;
import org.example.chery_payment.entity.Chery;
import org.example.chery_payment.entity.CheryAudit;
import org.example.chery_payment.repository.CheryRepository;
import org.springframework.stereotype.Service;

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

    // Новый метод для поиска Chery по карте
    List<Chery> findAllByCard(Card card);

    // Альтернативный вариант - поиск по ID карты
    List<Chery> findAllByCardId(Integer cardId);
}