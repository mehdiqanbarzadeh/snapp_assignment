package com.assignment.walletmng.controller.dto;

import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;

import java.math.BigDecimal;

@Data
public class WalletTransferRequest {

    @NotNull /// TODO fix validation with correct message
    private String fromAccount;
    @NotNull
    private String toAccount;

    @NotNull
    private BigDecimal amount;
}
