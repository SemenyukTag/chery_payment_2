package org.example.chery_payment.controller;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.example.chery_payment.entity.Chery;
import org.example.chery_payment.service.api.CheryService;
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
}