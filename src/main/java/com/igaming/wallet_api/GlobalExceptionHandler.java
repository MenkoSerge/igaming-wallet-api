package com.igaming.wallet_api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InsufficientFundsException.class)
    public ResponseEntity<Map<String, Object>> handleInsufficientFunds(
            InsufficientFundsException e) {

        Map<String, Object> error = Map.of(
                "error", "Insufficient funds",
                "requested", e.getRequested(),
                "available", e.getAvailable()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}