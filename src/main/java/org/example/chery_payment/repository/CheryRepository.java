package org.example.chery_payment.repository;

import org.example.chery_payment.entity.Card;
import org.example.chery_payment.entity.Chery;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CheryRepository extends JpaRepository<Chery, Integer> {
    List<Chery> findByCardId(Integer cardId);

    // Альтернативно: найти по объекту Card
    List<Chery> findByCard(Card card);

    List<Chery> findByCard_Id(Integer Id);

}



