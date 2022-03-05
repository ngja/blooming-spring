package com.hansol.bloomingspring.hexagonal.account.application.service;

import com.hansol.bloomingspring.hexagonal.account.application.port.in.GetAccountBalanceQuery;
import com.hansol.bloomingspring.hexagonal.account.application.port.out.LoadAccountPort;
import com.hansol.bloomingspring.hexagonal.account.domain.Account;
import com.hansol.bloomingspring.hexagonal.account.domain.Money;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@RequiredArgsConstructor
public class GetAccountBalanceService implements GetAccountBalanceQuery {

    private final LoadAccountPort loadAccountPort;

    @Override
    public Money getAccountBalance(Account.AccountId accountId) {
        return loadAccountPort.loadAccount(accountId, LocalDateTime.now())
                .calculateBalance();
    }
}
