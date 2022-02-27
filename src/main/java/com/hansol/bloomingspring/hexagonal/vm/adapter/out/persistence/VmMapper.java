package com.hansol.bloomingspring.hexagonal.vm.adapter.out.persistence;

import com.hansol.bloomingspring.hexagonal.vm.domain.Activity;
import com.hansol.bloomingspring.hexagonal.vm.domain.ActivityWindow;
import com.hansol.bloomingspring.hexagonal.vm.domain.Vm;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class VmMapper {

    Vm mapToDomainEntity(VmJpaEntity vm, List<ActivityJpaEntity> activities) {
        return Vm.withId(new Vm.VmId(vm.getId()), mapToActivityWindow(activities));
    }

    ActivityWindow mapToActivityWindow(List<ActivityJpaEntity> activities) {
        List<Activity> mappedActivities = new ArrayList<>();

        for (ActivityJpaEntity activity : activities) {
            mappedActivities.add(new Activity(
                    new Vm.VmId(activity.getVmId()),
                    Vm.Action.valueOf(activity.getAction()),
                    activity.getTimestamp()));
        }

        return new ActivityWindow(mappedActivities);
    }
}
