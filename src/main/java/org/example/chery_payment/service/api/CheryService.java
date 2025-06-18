package org.example.chery_payment.service.api;

import org.example.chery_payment.entity.Chery;

import java.util.List;

public interface CheryService {
    Chery save(Chery chery);
    List<Chery> findAll();

    Chery findById(int id);
    void deleteById(int id);

    Chery update(int id, Chery chery);

}
