package com.assignment.walletmng.controller.dto;

import com.assignment.walletmng.domain.transaction.TransactionType;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class WalletTransactionHistory {

    private String accountId;
    private String transactionIdentification;
    private BigDecimal amount;
    private Date createAt;
    private TransactionType transactionType;

}
