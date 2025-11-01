package com.assignment.walletmng.controller.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CashOutReqest {

    private String accountId;
    private BigDecimal amount;
    private String bankAccountId;

}
