package org.example.chery_payment.repository;

import org.example.chery_payment.entity.Chery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CheryRepository extends JpaRepository<Chery, Integer> {
}
