package com.igaming.wallet_api;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
public class TransactionEntity {

    @Id
    private String txId;
    // Уникальный ID транзакции от провайдера

    private String userId;
    // Кому принадлежит транзакция

    private long amount;
    // Сумма

    private String type;
    // DEBIT / CREDIT / ROLLBACK

    private LocalDateTime createdAt;
    // Когда произошла транзакция

    // Пустой конструктор — обязателен для JPA
    public TransactionEntity() {}

    public TransactionEntity(String txId, String userId, long amount, String type) {
        this.txId = txId;
        this.userId = userId;
        this.amount = amount;
        this.type = type;
        this.createdAt = LocalDateTime.now();
        // Автоматически ставим текущее время
    }

    public String getTxId()             { return txId; }
    public String getUserId()           { return userId; }
    public long getAmount()             { return amount; }
    public String getType()             { return type; }
    public LocalDateTime getCreatedAt() { return createdAt; }
}