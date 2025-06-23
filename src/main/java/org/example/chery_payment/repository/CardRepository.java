package org.example.chery_payment.repository;

import org.example.chery_payment.entity.Card;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CardRepository extends JpaRepository<Card, Integer> {
    @EntityGraph(attributePaths = {"cheries"})
    Optional<Card> findWithCheriesById(Integer id);
}