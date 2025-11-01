package com.assignment.walletmng.domain.transaction.dao;

import com.assignment.walletmng.domain.transaction.WalletTransaction;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WalletTransactionRepository extends JpaRepository<WalletTransaction, Long> {


    @Query(value = "SELECT wt FROM WalletTransaction wt JOIN wt.walletAccount wa WHERE wa.uuid = :uuid",nativeQuery = true)
    List<WalletTransaction> findByWalletAccountUuid(@Param("uuid") String uuid, Pageable pageable);

}
