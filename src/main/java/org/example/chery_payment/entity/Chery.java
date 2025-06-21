package org.example.chery_payment.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Entity
@Data
@Table(name = "chery")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
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

    @LastModifiedDate
    @Column(name = "lastmodified")
    private LocalDateTime lastModified;

    @PrePersist
    @PreUpdate
    public void updateTimestamps() {
        this.lastModified = LocalDateTime.now();
    }

    public LocalDateTime getLastModified() {
        return lastModified;
    }

    public void setLastModified(LocalDateTime lastModified) {
        this.lastModified = lastModified;
    }


}
