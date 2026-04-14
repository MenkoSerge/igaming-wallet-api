package com.igaming.wallet_api;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity, String> {

    // Spring сам реализует этот метод по названию!
    // findByUserId = найти все записи где userId = ?
    List<TransactionEntity> findByUserId(String userId);
}