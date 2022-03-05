package com.hansol.bloomingspring.hexagonal.account.application.port.out;

import com.hansol.bloomingspring.hexagonal.account.domain.Account;

public interface AccountLock {

    void lockAccount(Account.AccountId accountId);

    void releaseAccount(Account.AccountId accountId);
}
