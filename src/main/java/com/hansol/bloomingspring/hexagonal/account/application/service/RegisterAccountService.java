package com.hansol.bloomingspring.hexagonal.account.application.service;

import com.hansol.bloomingspring.hexagonal.account.application.port.in.RegisterAccountCommand;
import com.hansol.bloomingspring.hexagonal.account.application.port.in.RegisterAccountUseCase;
import com.hansol.bloomingspring.hexagonal.account.application.port.out.RegisterAccountPort;
import com.hansol.bloomingspring.hexagonal.common.UseCase;
import lombok.RequiredArgsConstructor;

import javax.transaction.Transactional;

@UseCase
@RequiredArgsConstructor
@Transactional
public class RegisterAccountService implements RegisterAccountUseCase {

    private final RegisterAccountPort registerAccountPort;

    @Override
    public Long registerAccount(RegisterAccountCommand command) {
        return registerAccountPort.registerAccount(command.getName());
    }
}
