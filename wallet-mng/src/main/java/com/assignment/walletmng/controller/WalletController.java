package com.assignment.walletmng.controller;

import com.assignment.walletmng.controller.dto.*;
import com.assignment.walletmng.service.account.WalletAccountService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wallet")
@AllArgsConstructor
public class WalletController {


    private final WalletAccountService walletAccountService;


    @PostMapping("/transfer")
    public ResponseEntity<?> transfer(WalletTransferRequest request) {
        return ResponseEntity.ok(walletAccountService.transfer(request));
    }


    @PostMapping("/cash-in")
    public ResponseEntity<?> cashIn(CashInRequest request) {
        return ResponseEntity.ok(walletAccountService.cashIn(request));
    }

    @PostMapping("/cash-out")
    public ResponseEntity<?> cashOut(CashOutReqest reqest) {
        return ResponseEntity.ok(walletAccountService.cashOut(reqest));
    }


    @GetMapping("/balance/{accountId}")
    public ResponseEntity<WalletBalanceReponse> getBalance(@PathVariable String accountId) {
        return ResponseEntity.ok(walletAccountService.getBalance(accountId));
    }


    @GetMapping("/history")
    public ResponseEntity<List<WalletTransactionHistory>> history(@RequestParam String accountId,
                                                                 @RequestParam Integer pageSize,
                                                                 @RequestParam Integer pageNumber) {
        return ResponseEntity.ok(walletAccountService.getHistory(accountId,pageSize,pageNumber));
    }

}
