package com.hansol.bloomingspring.hexagonal.vm.domain;

import lombok.NonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ActivityWindow {

    private List<Activity> activities;

    public ActivityWindow(@NonNull List<Activity> activities) {
        this.activities = activities;
    }

    public ActivityWindow(@NonNull Activity... activities) {
        this.activities = new ArrayList<>(Arrays.asList(activities));
    }

    public List<Activity> getActivities() {
        return Collections.unmodifiableList(this.activities);
    }

    public void addActivity(Activity activity) {
        this.activities.add(activity);
    }
}
