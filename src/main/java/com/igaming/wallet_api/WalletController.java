package com.igaming.wallet_api;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/wallet")
public class WalletController {

    private final WalletService walletService;

    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @PostMapping("/create/{userId}/{initialBalance}")
    public WalletEntity createWallet(@PathVariable String userId,
                                     @PathVariable long initialBalance) {
        return walletService.createWallet(userId, initialBalance);
    }

    @GetMapping("/{userId}/balance")
    public long getBalance(@PathVariable String userId) {
        return walletService.getBalance(userId);
    }

    @PostMapping("/{userId}/debit/{amount}/{txId}")
    // txId теперь часть URL — провайдер передаёт свой уникальный ID
    public long debit(@PathVariable String userId,
                      @PathVariable long amount,
                      @PathVariable String txId) {
        return walletService.debit(userId, amount, txId);
    }

    @PostMapping("/{userId}/credit/{amount}/{txId}")
    public long credit(@PathVariable String userId,
                       @PathVariable long amount,
                       @PathVariable String txId) {
        return walletService.credit(userId, amount, txId);
    }

    @PostMapping("/{userId}/rollback/{txId}/{amount}")
    public long rollback(@PathVariable String userId,
                         @PathVariable String txId,
                         @PathVariable long amount) {
        return walletService.rollback(userId, txId, amount);
    }

    @GetMapping("/{userId}/history")
    // Новый эндпоинт — история всех транзакций пользователя
    public List<TransactionEntity> getHistory(@PathVariable String userId) {
        return walletService.getHistory(userId);
    }
}