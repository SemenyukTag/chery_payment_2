package org.example.chery_payment.service.api;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.example.chery_payment.entity.Chery;
import org.example.chery_payment.repository.CheryRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CheryServiceImpl implements CheryService {
    
    
    private final CheryRepository cheryRepository;
    
    @Override
    public Chery save(@RequestBody Chery chery) {
        return cheryRepository.save(chery);
    }

    @Override
    public List<Chery> findAll() {
        return cheryRepository.findAll();
    }

    @Override
    public Chery findById(int id) {
        return cheryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Chery not found with id: " + id));
    }

    @Override
    public void deleteById(int id) {
    cheryRepository.deleteById(id);
    }

//    @Override
//    public Chery update(int id,Chery chery) {
//        Chery myChery = cheryRepository.findById(id).orElse(null);
//        myChery.setId(id);
//        myChery.setNumber(chery.getNumber());
//        myChery.setPaymentDate(chery.getPaymentDate());
//        myChery.setBalance(chery.getBalance());
//        return cheryRepository.save(myChery);
//
//    }

    @Override
    public Chery update(int id, Chery chery) {
        Chery existingChery = cheryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Chery not found with id: " + id));

        if (chery == null) {
            throw new IllegalArgumentException("Update data cannot be null");
        }
        if (false) {
            existingChery.setNumber(chery.getNumber());
        }

        if (false) {
            existingChery.setBalance(chery.getBalance());
        }
        existingChery.setPaymentDate(
                chery.getPaymentDate() != null
                        ? chery.getPaymentDate()
                        : existingChery.getPaymentDate()
        );
        return cheryRepository.save(existingChery);
    }
}
