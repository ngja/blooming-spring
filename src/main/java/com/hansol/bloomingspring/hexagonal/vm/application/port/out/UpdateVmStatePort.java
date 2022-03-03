package com.hansol.bloomingspring.hexagonal.vm.application.port.out;

import com.hansol.bloomingspring.hexagonal.vm.domain.Vm;

public interface UpdateVmStatePort {

    void updateActivities(Vm vm);

}
