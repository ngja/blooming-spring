package com.hansol.bloomingspring.hexagonal.vm.domain;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Value
@RequiredArgsConstructor
public class Activity {

    @Getter
    private ActivityId id;

    @Getter
    @NonNull
    private final Vm.VmId vmId;

    @Getter
    @NotNull
    private final Vm.Action action;

    @Getter
    @NonNull
    private final LocalDateTime timestamp;

    public Activity(
            @NotNull Vm.VmId vmId,
            @NotNull Vm.Action action,
            @NotNull LocalDateTime timestamp) {
        this.id = null;
        this.vmId = vmId;
        this.action = action;
        this.timestamp = timestamp;
    }

    @Value
    public static class ActivityId {
        private final Long value;
    }
}
