package org.example.chery_payment.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {
    private int status;
    private String message;
    private List<String> errors; // для валидационных ошибок

    // Конструктор для простых ошибок (без списка ошибок)
    public ErrorResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }
}