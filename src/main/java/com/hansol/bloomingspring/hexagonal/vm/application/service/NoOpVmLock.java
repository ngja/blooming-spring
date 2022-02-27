package com.hansol.bloomingspring.hexagonal.vm.application.service;

import com.hansol.bloomingspring.hexagonal.vm.application.port.out.VmLock;
import com.hansol.bloomingspring.hexagonal.vm.domain.Vm;
import org.springframework.stereotype.Component;

@Component
public class NoOpVmLock implements VmLock {
    @Override
    public void lockVm(Vm.VmId vmId) {
        // do nothing
    }

    @Override
    public void releaseVm(Vm.VmId vmId) {
        // do nothing
    }
}
