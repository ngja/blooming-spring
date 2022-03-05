package com.hansol.bloomingspring.hexagonal.account.application.port.out;

import com.hansol.bloomingspring.hexagonal.account.domain.Account;

public interface UpdateAccountStatePort {

    void updateActivities(Account account);

}
