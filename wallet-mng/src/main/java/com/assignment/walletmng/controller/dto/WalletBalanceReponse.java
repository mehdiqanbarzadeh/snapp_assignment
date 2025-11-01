package com.assignment.walletmng.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class WalletBalanceReponse {

    private BigDecimal balance;

}
