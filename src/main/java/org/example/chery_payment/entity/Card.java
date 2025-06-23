package org.example.chery_payment.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity
@Data
@Table(name = "card")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    @NotNull(message = "Number cannot be null")
    private int number;

    // Добавляем связь с Cherry (или CheryPayment - уточните имя вашей сущности)
    @OneToMany(mappedBy = "card", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Chery> cheries; // или payments, если предпочитаете другое имя

    // Удаляем ненужное поле (оно конфликтует со связью)
    // @Column(name = "card_id", insertable = false, updatable = false)
    // private String someOtherProperty;
}