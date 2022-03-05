package com.hansol.bloomingspring.hexagonal.account.adapter.in.web;

import com.hansol.bloomingspring.hexagonal.account.application.port.in.SendMoneyCommand;
import com.hansol.bloomingspring.hexagonal.account.application.port.in.SendMoneyUseCase;
import com.hansol.bloomingspring.hexagonal.account.domain.Account;
import com.hansol.bloomingspring.hexagonal.account.domain.Money;
import com.hansol.bloomingspring.hexagonal.common.WebAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class SendMoneyController {

    private final SendMoneyUseCase sendMoneyUseCase;

    @PostMapping(params = "/accounts/send/{sourceAccountId}/{targetAccountId}/{amount}")
    void sendMoney(
            @PathVariable("sourceAccountId") Long sourceAccountId,
            @PathVariable("targetAccountId") Long targetAccountId,
            @PathVariable("amount") Long amount
    ) {

        SendMoneyCommand command = new SendMoneyCommand(
                new Account.AccountId(sourceAccountId),
                new Account.AccountId(targetAccountId),
                Money.of(amount)
        );

        sendMoneyUseCase.sendMoney(command);
    }
}
