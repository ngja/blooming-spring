package com.hansol.bloomingspring.hexagonal.vm.application.service;

import com.hansol.bloomingspring.hexagonal.vm.application.port.in.StartVmCommand;
import com.hansol.bloomingspring.hexagonal.vm.application.port.in.StartVmUseCase;
import com.hansol.bloomingspring.hexagonal.vm.application.port.out.LoadVmPort;
import com.hansol.bloomingspring.hexagonal.vm.application.port.out.VmLock;
import com.hansol.bloomingspring.hexagonal.vm.domain.Vm;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@RequiredArgsConstructor
public class StartVmService implements StartVmUseCase {

    private final LoadVmPort loadVmPort;
    private final VmLock vmLock;

    @Override
    public boolean startVm(StartVmCommand command) {

        LocalDateTime baselineDate = LocalDateTime.now().minusDays(10);

        Vm vm = loadVmPort.loadVm(command.getVmId(), baselineDate);

        Vm.VmId vmId = vm.getId()
                .orElseThrow(() -> new IllegalStateException("expected source account ID not to be empty"));

        vmLock.lockVm(vmId);


        return false;
    }
}
