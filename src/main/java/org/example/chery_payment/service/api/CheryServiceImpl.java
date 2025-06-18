package org.example.chery_payment.service.api;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.example.chery_payment.entity.Chery;
import org.example.chery_payment.repository.CheryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CheryServiceImpl implements CheryService {

    private final CheryRepository cheryRepository;

    @Override
    public Chery save(Chery chery) {
        return cheryRepository.save(chery);
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
        if (!cheryRepository.existsById(id)) {
            throw new EntityNotFoundException("Chery not found with id: " + id);
        }
        cheryRepository.deleteById(id);
    }


    //public Chery update() {
    //    return update(0, null);
    //}


    @Override
    public Chery update(int id, Chery chery) {
        if (chery == null) {
            throw new IllegalArgumentException("Chery update data cannot be null");
        }

        Chery existingChery = cheryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Chery not found with id: " + id));

        existingChery.setNumber(chery.getNumber());
        existingChery.setBalance(chery.getBalance());

        if (chery.getPaymentDate() != null) {
            existingChery.setPaymentDate(chery.getPaymentDate());
        }

        return cheryRepository.save(existingChery);
    }
}