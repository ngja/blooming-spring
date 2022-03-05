package com.hansol.bloomingspring.hexagonal.account.application.port.in;

import com.hansol.bloomingspring.hexagonal.common.SelfValidating;
import lombok.EqualsAndHashCode;
import lombok.Value;

import javax.validation.constraints.NotNull;

@Value
@EqualsAndHashCode(callSuper = false)
public class RegisterAccountCommand extends SelfValidating<RegisterAccountCommand> {

    @NotNull
    private final String name;

    public RegisterAccountCommand(
            String name
    ) {
        this.name = name;
        this.validateSelf();
    }
}
