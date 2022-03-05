package com.hansol.bloomingspring.hexagonal.account.application.service;

import com.hansol.bloomingspring.hexagonal.account.application.port.in.SendMoneyCommand;
import com.hansol.bloomingspring.hexagonal.account.application.port.in.SendMoneyUseCase;
import com.hansol.bloomingspring.hexagonal.account.application.port.out.AccountLock;
import com.hansol.bloomingspring.hexagonal.account.application.port.out.LoadAccountPort;
import com.hansol.bloomingspring.hexagonal.account.application.port.out.UpdateAccountStatePort;
import com.hansol.bloomingspring.hexagonal.account.domain.Account;
import com.hansol.bloomingspring.hexagonal.common.UseCase;
import lombok.RequiredArgsConstructor;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@UseCase
@RequiredArgsConstructor
@Transactional
public class SendMoneyService implements SendMoneyUseCase {

    private final LoadAccountPort loadAccountPort;
    private final AccountLock accountLock;
    private final UpdateAccountStatePort updateAccountStatePort;
    private final MoneyTransferProperties moneyTransferProperties;

    @Override
    public boolean sendMoney(SendMoneyCommand command) {

        checkThreshold(command);

        LocalDateTime baselineDate = LocalDateTime.now().minusDays(10);

        Account sourceAccount = loadAccountPort.loadAccount(
                command.getSourceAccountId(),
                baselineDate
        );

        Account targetAccount = loadAccountPort.loadAccount(
                command.getTargetAccountId(),
                baselineDate
        );

        Account.AccountId sourceAccountId = sourceAccount.getId()
                .orElseThrow(() -> new IllegalStateException("expected source account ID not to be empty"));
        Account.AccountId targetAccountId = targetAccount.getId()
                .orElseThrow(() -> new IllegalStateException("expected target account ID not to be empty"));

        accountLock.lockAccount(sourceAccountId);
        if (!sourceAccount.withdraw(command.getMoney(), targetAccountId)) {
            accountLock.releaseAccount(sourceAccountId);
            return false;
        }

        accountLock.lockAccount(targetAccountId);
        if (!targetAccount.deposit(command.getMoney(), sourceAccountId)) {
            accountLock.releaseAccount(sourceAccountId);
            accountLock.releaseAccount(targetAccountId);
            return false;
        }

        updateAccountStatePort.updateActivities(sourceAccount);
        updateAccountStatePort.updateActivities(targetAccount);

        accountLock.releaseAccount(sourceAccountId);
        accountLock.releaseAccount(targetAccountId);

        return true;
    }

    private void checkThreshold(SendMoneyCommand command) {
        if(command.getMoney().isGreaterThan(moneyTransferProperties.getMaximumTransferThreshold())){
            throw new ThresholdExceededException(moneyTransferProperties.getMaximumTransferThreshold(), command.getMoney());
        }
    }

}
