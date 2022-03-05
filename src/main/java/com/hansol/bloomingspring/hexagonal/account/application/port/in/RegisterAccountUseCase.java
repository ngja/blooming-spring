package com.hansol.bloomingspring.hexagonal.account.application.port.in;

public interface RegisterAccountUseCase {

    Long registerAccount(RegisterAccountCommand command);

}
