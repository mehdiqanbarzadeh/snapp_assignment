package com.assignment.walletmng.domain.account;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "WALLET_ACCOUNT", indexes = {
        @Index(name = "IDX_WALLET_ACCOUNT_USER_ID", columnList = "USER_ID")
})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WalletAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "UUID")
    private String uuid;

    @Column(name = "CREATE_AT")
    private Date createAt = new Date();

    @Column(name = "CREATE_BY")
    private String createBy;

    @Column(name = "USER_ID")
    private String userId;

    @Column(name = "BALANCE")
    private BigDecimal balance = BigDecimal.ZERO;
}
