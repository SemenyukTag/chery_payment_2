package org.example.chery_payment.controller;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.example.chery_payment.entity.Card;
import org.example.chery_payment.entity.Chery;
import org.example.chery_payment.repository.CardRepository;
import org.example.chery_payment.repository.CheryRepository;
import org.example.chery_payment.service.api.CheryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/chery") // Добавил базовый путь для всех endpoint'ов
public class CheryController {
    private final CheryService cheryService;

    @PostMapping("/new")
    public Chery save(@RequestBody Chery chery) {
        return cheryService.save(chery);
    }

    @GetMapping("/all")
    public List<Chery> getAll() {
        return cheryService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable int id) {
        try {
            Chery chery = cheryService.findById(id);
            return ResponseEntity.ok(chery);
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @PutMapping("/{id}") // Новый метод для обновления
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody Chery updatedChery) {
        try {
            Chery existingChery = cheryService.findById(id);

            // Обновляем поля существующей записи
            existingChery.setBalance(updatedChery.getBalance());
            existingChery.setNumber(updatedChery.getNumber());
            existingChery.setPaymentDate(updatedChery.getPaymentDate());
            // Добавь другие поля, которые нужно обновлять

            Chery savedChery = cheryService.save(existingChery);
            return ResponseEntity.ok(savedChery);
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        try {
            cheryService.deleteById(id);
            return ResponseEntity.noContent().build(); // 204 No Content
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.notFound().build(); // 404 Not Found
        }
    }


    @PostMapping("/by-card")
    public ResponseEntity<List<Chery>> getAllCheriesByCard(@RequestBody Card card) {
        System.out.println("Received Card ID: " + card.getId());
        List<Chery> cheries = cheryService.findAllByCard(card);
        System.out.println("Found cheries: " + cheries.size());
        return ResponseEntity.ok(cheries);
    }

    private final CheryRepository cheryRepository;


    @GetMapping("/by-card/{cardId}")
    public ResponseEntity<List<Chery>> getCheryByCardId(@PathVariable Integer cardId) {
        List<Chery> cheryList = cheryRepository.findByCard_Id(cardId);
        return ResponseEntity.ok(cheryList);
    }

    @Autowired
    private CardRepository cardRepository;

    @GetMapping("/card/{id}")
    public ResponseEntity<Card> getCardById(@PathVariable int id) {
        return cardRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/with-payments")
    public ResponseEntity<Card> getCardWithPayments(@PathVariable Integer id) {
        return cardRepository.findWithCheriesById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}

