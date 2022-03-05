package com.hansol.bloomingspring.hexagonal.account.adapter.in.web;

import com.hansol.bloomingspring.hexagonal.account.application.port.in.RegisterAccountCommand;
import com.hansol.bloomingspring.hexagonal.account.application.port.in.RegisterAccountUseCase;
import com.hansol.bloomingspring.hexagonal.common.WebAdapter;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class RegisterAccountController {

    private final RegisterAccountUseCase registerAccountUseCase;

    @PostMapping(params = "/accounts")
    Long registerAccount(
            @RequestBody RegisterAccountRequest registerAccountRequest
    ) {

        RegisterAccountCommand command = new RegisterAccountCommand(
                registerAccountRequest.getName()
        );

        return registerAccountUseCase.registerAccount(command);
    }

    @Value
    private static class RegisterAccountRequest {
        private String name;
    }
}
