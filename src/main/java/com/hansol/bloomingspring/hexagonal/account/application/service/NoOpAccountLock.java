package com.hansol.bloomingspring.hexagonal.account.application.service;

import com.hansol.bloomingspring.hexagonal.account.application.port.out.AccountLock;
import com.hansol.bloomingspring.hexagonal.account.domain.Account;
import org.springframework.stereotype.Component;

@Component
public class NoOpAccountLock implements AccountLock {

    @Override
    public void lockAccount(Account.AccountId accountId) {
        // do nothing
    }

    @Override
    public void releaseAccount(Account.AccountId accountId) {
        // do nothing
    }
}
