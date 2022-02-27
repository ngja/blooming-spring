package com.hansol.bloomingspring.hexagonal.vm.application.port.out;

import com.hansol.bloomingspring.hexagonal.vm.domain.Vm;

public interface VmLock {

    void lockVm(Vm.VmId vmId);

    void releaseVm(Vm.VmId vmId);
}
