package com.hansol.bloomingspring.hexagonal.vm.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.Optional;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Vm {

    @Getter
    private final VmId id;

    @Getter
    private final State state;

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
            State state,
            ActivityWindow activityWindow
    ) {
        return new Vm(vmId, state, activityWindow);
    }

    public boolean start() {

        if (!canStart()) {
            return false;
        }

        Activity activity = new Activity(
                this.id,
                Action.START,
                LocalDateTime.now()
        );
        this.activityWindow.addActivity(activity);
        return true;
    }

    private boolean canStart() {
        return State.STOPPED.equals(this.state);
    }

    public enum State {
        RUNNING, STOPPED, TERMINATED
    }

    public enum Action {
        START, STOP
    }
}
