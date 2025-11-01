package com.assignment.walletmng.controller.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data //TODO fix validation
public class CashInRequest {
    private String accountId;
    private BigDecimal amount;

}
