package com.hansol.bloomingspring.hexagonal.account.application.port.out;

import com.hansol.bloomingspring.hexagonal.account.domain.Account;

import java.time.LocalDateTime;

public interface LoadAccountPort {

    Account loadAccount(Account.AccountId accountId, LocalDateTime baselineDate);
}
