package com.hansol.bloomingspring.hexagonal.account.application.port.in;

import com.hansol.bloomingspring.hexagonal.account.domain.Account;
import com.hansol.bloomingspring.hexagonal.account.domain.Money;

public interface GetAccountBalanceQuery {

    Money getAccountBalance(Account.AccountId accountId);

}
