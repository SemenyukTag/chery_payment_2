package org.example.chery_payment.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Entity
@Data
@Table(name = "chery")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Chery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    @NotNull(message = "Number cannot be null")
    private int number;

    @Column(name = "payment_date", nullable = false)
    @NotNull(message = "Payment date cannot be null")
    private LocalDate paymentDate;


    @Column(nullable = false)
    @NotNull(message = "Balance cannot be null")

    private float balance;

    @OneToOne
    @JoinColumn(name = "card_id", referencedColumnName = "id")
    private Card card;

}
