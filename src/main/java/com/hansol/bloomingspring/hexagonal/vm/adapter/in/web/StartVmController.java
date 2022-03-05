package com.hansol.bloomingspring.hexagonal.vm.adapter.in.web;

import com.hansol.bloomingspring.hexagonal.vm.application.port.in.StartVmCommand;
import com.hansol.bloomingspring.hexagonal.vm.application.port.in.StartVmUseCase;
import com.hansol.bloomingspring.hexagonal.vm.domain.Vm;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class StartVmController {

    private final StartVmUseCase startVmUseCase;

    @PostMapping(path = "/vms/start/{vmId}")
    void startVm(
            @PathVariable("vmId") Long vmId) {

        StartVmCommand command = new StartVmCommand(
                new Vm.VmId(vmId)
        );

        startVmUseCase.startVm(command);
    }
}
