package com.igaming.wallet_api;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/wallet")
public class WalletController {

    private final WalletService walletService;

    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @GetMapping("/{userId}/balance")
    public long getBalance(@PathVariable String userId) {
        return walletService.getBalance(userId);
    }

    @PostMapping("/{userId}/debit/{amount}")
    public long debit(@PathVariable String userId,
                      @PathVariable long amount) {
        walletService.debit(userId, amount);
        return walletService.getBalance(userId);
    }

    @PostMapping("/{userId}/credit/{amount}")
    public long credit(@PathVariable String userId,
                       @PathVariable long amount) {
        walletService.credit(userId, amount);
        return walletService.getBalance(userId);
    }
}