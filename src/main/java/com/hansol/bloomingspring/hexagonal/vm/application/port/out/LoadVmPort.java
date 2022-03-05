package com.hansol.bloomingspring.hexagonal.vm.application.port.out;

import com.hansol.bloomingspring.hexagonal.vm.domain.Vm;

import java.time.LocalDateTime;

public interface LoadVmPort {

    Vm loadVm(Vm.VmId vmId, LocalDateTime baselineDate);
}
