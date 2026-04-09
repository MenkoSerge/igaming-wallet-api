package com.igaming.wallet_api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wallet")
public class WalletController {

    @GetMapping("/{userId}/balance")
    public String getBalance(@PathVariable String userId) {
        return "Balance for " + userId + ": 1000";
    }
}