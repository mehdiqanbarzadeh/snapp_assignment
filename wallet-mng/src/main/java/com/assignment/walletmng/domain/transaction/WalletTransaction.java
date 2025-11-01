package com.assignment.walletmng.domain.transaction;


import com.assignment.walletmng.domain.account.WalletAccount;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "WALLET_TRANSACTIONS")
@NoArgsConstructor
@AllArgsConstructor

@Getter
@Setter
public class WalletTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "CREATE_AT")
    private Date createAt = new Date();

    @Column(name = "CREATE_BY")
    private String createBy;

    @Column(name = "TRANSACTION_IDENTIFICATION")
    private String transactionIdentification;

    @Column(name = "TYPE")
    @Enumerated(EnumType.STRING)
    private TransactionType type;

    @Column(name = "AMOUNT")
    private BigDecimal amount;

    @Column(name = "DESCRIPTION")
    private String description;

    @ManyToOne
    @JoinColumn(name = "ACCOUNT_ID", nullable = false)
    private WalletAccount account;

}
