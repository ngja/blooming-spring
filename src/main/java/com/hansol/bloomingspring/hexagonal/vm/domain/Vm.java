package com.hansol.bloomingspring.hexagonal.vm.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;

import java.util.Optional;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Vm {

    @Getter
    private final VmId id;

    @Getter
    private final ActivityWindow activityWindow;

    @Value
    public static class VmId {
        private Long vmId;
    }

    public Optional<VmId> getId() {
        return Optional.ofNullable(this.id);
    }

    public static Vm withId(
            VmId vmId,
            ActivityWindow activityWindow
    ) {
        return new Vm(vmId, activityWindow);
    }

    public enum Action {
        START, STOP
    }
}
