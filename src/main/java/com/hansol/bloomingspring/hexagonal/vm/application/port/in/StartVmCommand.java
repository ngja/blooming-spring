package com.hansol.bloomingspring.hexagonal.vm.application.port.in;

import com.hansol.bloomingspring.hexagonal.common.SelfValidating;
import com.hansol.bloomingspring.hexagonal.vm.domain.Vm.VmId;
import lombok.EqualsAndHashCode;
import lombok.Value;

import javax.validation.constraints.NotNull;

@Value
@EqualsAndHashCode(callSuper = false)
public class StartVmCommand extends SelfValidating<StartVmCommand> {

    @NotNull
    private final VmId vmId;

    public StartVmCommand(VmId vmId) {
        this.vmId = vmId;
        this.validateSelf();
    }
}
