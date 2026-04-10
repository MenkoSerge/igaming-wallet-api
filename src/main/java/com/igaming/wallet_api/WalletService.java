package com.igaming.wallet_api;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class WalletService {

    private final WalletRepository walletRepository;

    public WalletService(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    public long getBalance(String userId) {
        return walletRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Wallet not found: " + userId))
                .getBalance();
    }

    @Transactional

    public long debit(String userId, long amount) {
        WalletEntity wallet = walletRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Wallet not found: " + userId));

        if (wallet.getBalance() < amount) {
            throw new InsufficientFundsException(amount, wallet.getBalance());
        }

        wallet.setBalance(wallet.getBalance() - amount);
        walletRepository.save(wallet);
        return wallet.getBalance();
    }

    @Transactional
    public long credit(String userId, long amount) {
        WalletEntity wallet = walletRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Wallet not found: " + userId));

        wallet.setBalance(wallet.getBalance() + amount);
        walletRepository.save(wallet);
        return wallet.getBalance();
    }

    @Transactional
    public long rollback(String userId, String txId, long amount) {
        return credit(userId, amount);
    }

    @Transactional
    public WalletEntity createWallet(String userId, long initialBalance) {
        WalletEntity wallet = new WalletEntity(userId, initialBalance);
        return walletRepository.save(wallet);
    }
}