package com.assignment.walletmng.service.account;

import com.assignment.walletmng.controller.dto.*;

import java.util.List;

public interface WalletAccountService {
    Object transfer(WalletTransferRequest request);

    Object cashIn(CashInRequest request);

    Object cashOut(CashOutReqest reqest);

    WalletBalanceReponse getBalance(String accountId);

    List<WalletTransactionHistory> getHistory(String accountId, Integer pageSize, Integer pageNumber);
}
