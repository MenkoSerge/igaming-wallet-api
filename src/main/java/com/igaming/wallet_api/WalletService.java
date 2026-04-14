package com.igaming.wallet_api;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.UUID;

@Service
public class WalletService {

    private final WalletRepository walletRepository;
    private final TransactionRepository transactionRepository;

    // Spring передаёт оба репозитория сюда
    public WalletService(WalletRepository walletRepository,
                         TransactionRepository transactionRepository) {
        this.walletRepository = walletRepository;
        this.transactionRepository = transactionRepository;
    }

    public long getBalance(String userId) {
        return walletRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Wallet not found: " + userId))
                .getBalance();
    }

    @Transactional
    public long debit(String userId, long amount, String txId) {
        // Идемпотентность — если txId уже есть, не списываем повторно
        if (transactionRepository.existsById(txId)) {
            return getBalance(userId);
        }

        WalletEntity wallet = walletRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Wallet not found: " + userId));

        if (wallet.getBalance() < amount) {
            throw new InsufficientFundsException(amount, wallet.getBalance());
        }

        wallet.setBalance(wallet.getBalance() - amount);
        walletRepository.save(wallet);

        // Сохраняем транзакцию в историю
        transactionRepository.save(
                new TransactionEntity(txId, userId, amount, "DEBIT")
        );

        return wallet.getBalance();
    }

    @Transactional
    public long credit(String userId, long amount, String txId) {
        // Идемпотентность
        if (transactionRepository.existsById(txId)) {
            return getBalance(userId);
        }

        WalletEntity wallet = walletRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Wallet not found: " + userId));

        wallet.setBalance(wallet.getBalance() + amount);
        walletRepository.save(wallet);

        transactionRepository.save(
                new TransactionEntity(txId, userId, amount, "CREDIT")
        );

        return wallet.getBalance();
    }

    @Transactional
    public long rollback(String userId, String txId, long amount) {
        // Идемпотентность
        if (transactionRepository.existsById(txId)) {
            return getBalance(userId);
        }

        WalletEntity wallet = walletRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Wallet not found: " + userId));

        wallet.setBalance(wallet.getBalance() + amount);
        walletRepository.save(wallet);

        transactionRepository.save(
                new TransactionEntity(txId, userId, amount, "ROLLBACK")
        );

        return wallet.getBalance();
    }

    @Transactional
    public WalletEntity createWallet(String userId, long initialBalance) {
        WalletEntity wallet = new WalletEntity(userId, initialBalance);
        return walletRepository.save(wallet);
    }

    // История транзакций пользователя
    public List<TransactionEntity> getHistory(String userId) {
        return transactionRepository.findByUserId(userId);
    }
}