package org.example.chery_payment.service.api;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.example.chery_payment.entity.Card;
import org.example.chery_payment.entity.Chery;
import org.example.chery_payment.entity.CheryAudit;
import org.example.chery_payment.repository.CheryAuditRepository;
import org.example.chery_payment.repository.CheryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CheryServiceImpl implements CheryService {

    private final CheryRepository cheryRepository;
    private final CheryAuditRepository cheryAuditRepository;

    @Override
    public Chery save(Chery chery) {
        Chery savedChery = cheryRepository.save(chery);
        logAuditAction(savedChery.getId(), "CREATE", "Entity created");
        return savedChery;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Chery> findAll() {
        return cheryRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Chery findById(int id) {
        return cheryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Chery not found with id: " + id));
    }

    @Override
    public void deleteById(int id) {
        Chery chery = findById(id);
        cheryRepository.delete(chery);
        logAuditAction(id, "DELETE", "Entity deleted");
    }

    @Override
    public Chery update(int id, Chery chery) {
        Chery existingChery = cheryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Chery not found with id: " + id));

        // Обновляем только изменённые поля
        if (chery.getNumber() != 0) {
            existingChery.setNumber(chery.getNumber());
        }

        if (chery.getPaymentDate() != null) {
            existingChery.setPaymentDate(chery.getPaymentDate());
        }

        // Явное обновление времени (если не используется @PreUpdate)
        existingChery.setLastModified(LocalDateTime.now());

        // Сохраняем изменения
        return cheryRepository.save(existingChery);
    }

    @Override
    public Chery updateWithTracking(int id, Chery chery, String changeDescription) {
        Chery updated = update(id, chery);
        logAuditAction(id, "MANUAL_UPDATE", changeDescription);
        return updated;
    }

    @Override
    @Transactional(readOnly = true)
    public List<CheryAudit> getAuditLogsForEntity(int entityId) {
        return cheryAuditRepository.findByEntityIdOrderByChangedAtDesc(entityId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CheryAudit> getAuditLogsByAction(String action) {
        return cheryAuditRepository.findByActionOrderByChangedAtDesc(action);
    }

    private void logAuditAction(int entityId, String action, String description) {
        CheryAudit audit = new CheryAudit();
        audit.setEntityId(entityId);
        audit.setAction(action);
        audit.setChangeDescription(description);
        audit.setChangedAt(LocalDateTime.now());
        cheryAuditRepository.save(audit);
    }

    @Override
    public List<Chery> findAllByCard(Card card) {
        return cheryRepository.findByCard(card);
    }

    @Override
    public List<Chery> findAllByCardId(Integer cardId) {
        return cheryRepository.findByCardId(cardId);
    }
}