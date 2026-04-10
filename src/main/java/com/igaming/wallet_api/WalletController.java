package com.igaming.wallet_api;

import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/{userId}/debit/{amount}")
    public long debit(@PathVariable String userId,
                      @PathVariable long amount) {
        return walletService.debit(userId, amount);
    }

    @PostMapping("/{userId}/credit/{amount}")
    public long credit(@PathVariable String userId,
                       @PathVariable long amount) {
        return walletService.credit(userId, amount);
    }

    @PostMapping("/{userId}/rollback/{txId}/{amount}")
    public long rollback(@PathVariable String userId,
                         @PathVariable String txId,
                         @PathVariable long amount) {
        return walletService.rollback(userId, txId, amount);
    }
}