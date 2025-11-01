package com.assignment.walletmng.service.account;

import com.assignment.walletmng.controller.dto.*;
import com.assignment.walletmng.domain.account.WalletAccount;
import com.assignment.walletmng.domain.account.dao.WalletAccountRepository;
import com.assignment.walletmng.domain.transaction.TransactionType;
import com.assignment.walletmng.domain.transaction.WalletTransaction;
import com.assignment.walletmng.domain.transaction.dao.WalletTransactionRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class WalletAccountServiceImpl implements WalletAccountService {

    private final WalletAccountRepository walletRepo;
    private final WalletTransactionRepository walletTransactionRepository;

    @Override
    @Transactional
    public WalletTransferResponse transfer(WalletTransferRequest request) {
        WalletAccount from = walletRepo.findByUserId(request.getFromAccount())
                .orElseThrow(() -> new RuntimeException("from account not found")); //TODO : fix exception handling


        WalletAccount to = walletRepo.findByUserId(request.getToAccount())
                .orElseThrow(() -> new RuntimeException("receiver account not found"));


        if (from.getBalance().compareTo(request.getAmount()) < 0)
            throw new RuntimeException("from account amount not enough");


        from.setBalance(from.getBalance().subtract(request.getAmount()));
        to.setBalance(to.getBalance().add(request.getAmount()));

        walletRepo.save(from);
        walletRepo.save(to);

        List<WalletTransaction> transferTransactions = getTransferTransactions(from, to, request);

        String transactionIdentification = transferTransactions
                .stream()
                .filter(t -> t.getAccount() == from)
                .findFirst()
                .get()
                .getTransactionIdentification();

        return new WalletTransferResponse(transactionIdentification);
    }

    private List<WalletTransaction> getTransferTransactions(WalletAccount from, WalletAccount to, WalletTransferRequest request) {

        //TODO fix it with mapstruct
        WalletTransaction t1 = new WalletTransaction();
        t1.setAmount(request.getAmount());
        t1.setAccount(from);
        t1.setType(TransactionType.WITHDRAW);
        t1.setTransactionIdentification(UUID.randomUUID().toString());
        WalletTransaction t2 = new WalletTransaction();
        t2.setAmount(request.getAmount());
        t2.setAccount(to);
        t2.setType(TransactionType.DEPOSIT);
        t2.setTransactionIdentification(UUID.randomUUID().toString());
        return walletTransactionRepository.saveAll(List.of(t1, t2));
    }


    @Override
    @Transactional
    public WalletCashInResponse cashIn(CashInRequest request) {

        WalletAccount account = walletRepo.findByUuid(request.getAccountId())
                .orElseThrow(() -> new RuntimeException("account not found"));

        account.setBalance(account.getBalance().add(request.getAmount()));
        walletRepo.save(account);

        WalletTransaction t1 = new WalletTransaction();
        t1.setAmount(request.getAmount());
        t1.setAccount(account);
        t1.setType(TransactionType.DEPOSIT);
        t1.setTransactionIdentification(UUID.randomUUID().toString());
        walletTransactionRepository.save(t1);

        //TODO call payment client service

        return new WalletCashInResponse(t1.getTransactionIdentification());
    }


    @Override
    public WalletCashInResponse cashOut(CashOutReqest reqest) {
        WalletAccount account = walletRepo.findByUuid(reqest.getAccountId())
                .orElseThrow(() -> new RuntimeException("account not found"));


        if (account.getBalance().compareTo(reqest.getAmount()) < 0)
            throw new RuntimeException("account amount not enough");
        account.setBalance(account.getBalance().subtract(reqest.getAmount()));
        walletRepo.save(account);

        WalletTransaction t1 = new WalletTransaction();
        t1.setAmount(reqest.getAmount());
        t1.setAccount(account);
        t1.setType(TransactionType.WITHDRAW);
        t1.setTransactionIdentification(UUID.randomUUID().toString());
        walletTransactionRepository.save(t1);

        //TODO call payment client service

        return new WalletCashInResponse(t1.getTransactionIdentification());
    }


    @Override
    @Transactional(readOnly = true)
    public WalletBalanceReponse getBalance(String accountId) {
        BigDecimal balance = walletRepo.findByUserId(accountId).map(WalletAccount::getBalance).orElseThrow(
                () -> new RuntimeException("account not found")
        );
        return new WalletBalanceReponse(balance);
    }


    @Override
    @Transactional(readOnly = true)
    public List<WalletTransactionHistory> getHistory(String accountId, Integer pageSize, Integer pageNumber) {

        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        List<WalletTransaction> byWalletAccountUuid = walletTransactionRepository.findByWalletAccountUuid(accountId, pageRequest);
        return new ArrayList<>();//TODO return with mapstruct
    }
}
