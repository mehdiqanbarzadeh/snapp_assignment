package com.assignment.walletmng.domain.account.dao;

import com.assignment.walletmng.domain.account.WalletAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WalletAccountRepository extends JpaRepository<WalletAccount, Long> {

    Optional<WalletAccount> findByUserId(String userId);

    Optional<WalletAccount> findByUuid(String uuid);

}
