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

        Vm.State state = Vm.State.valueOf(vm.getState());

        return Vm.withId(new Vm.VmId(vm.getId()), state, mapToActivityWindow(activities));
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

    ActivityJpaEntity mapToJpaEntity(Activity activity) {
        return new ActivityJpaEntity(
                activity.getId() == null ? null : activity.getId().getValue(),
                activity.getTimestamp(),
                activity.getVmId().getVmId(),
                activity.getAction().name()
        );
    }
}
