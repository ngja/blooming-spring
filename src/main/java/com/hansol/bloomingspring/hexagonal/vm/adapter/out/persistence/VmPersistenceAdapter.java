package com.hansol.bloomingspring.hexagonal.vm.adapter.out.persistence;

import com.hansol.bloomingspring.hexagonal.vm.application.port.out.LoadVmPort;
import com.hansol.bloomingspring.hexagonal.vm.application.port.out.UpdateVmStatePort;
import com.hansol.bloomingspring.hexagonal.vm.domain.Activity;
import com.hansol.bloomingspring.hexagonal.vm.domain.Vm;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
public class VmPersistenceAdapter implements LoadVmPort, UpdateVmStatePort {

    private final VmRepository vmRepository;
    private final ActivityRepository activityRepository;
    private final VmMapper vmMapper;

    @Override
    public Vm loadVm(Vm.VmId vmId, LocalDateTime baselineDate) {

        VmJpaEntity vm =
                vmRepository.findById(vmId.getVmId())
                        .orElseThrow(EntityNotFoundException::new);

        List<ActivityJpaEntity> activities =
                activityRepository.findByVmSince(vmId.getVmId(), baselineDate);

        return vmMapper.mapToDomainEntity(vm, activities);
    }

    @Override
    public void updateActivities(Vm vm) {
        for (Activity activity : vm.getActivityWindow().getActivities()) {
            if (activity.getId() == null) {
                activityRepository.save(vmMapper.mapToJpaEntity(activity));
            }
        }
    }
}
