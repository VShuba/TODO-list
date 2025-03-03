package shpp.shuba.todo_list.models;

import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Map;
import java.util.Set;

public enum TaskStatus {
    PLANNED,
    WORK_IN_PROGRESS,
    POSTPONED,
    NOTIFIED,
    SIGNED,
    DONE,
    CANCELLED;

    private static final Map<TaskStatus, Set<TaskStatus>> allowedTransitions;

    static {
        allowedTransitions = new EnumMap<>(TaskStatus.class);

        allowedTransitions.put(PLANNED, EnumSet.of(WORK_IN_PROGRESS, POSTPONED, CANCELLED));
        allowedTransitions.put(WORK_IN_PROGRESS, EnumSet.of(NOTIFIED, SIGNED, CANCELLED));
        allowedTransitions.put(POSTPONED, EnumSet.of(NOTIFIED, SIGNED, CANCELLED));
        allowedTransitions.put(NOTIFIED, EnumSet.of(DONE, CANCELLED));
        allowedTransitions.put(SIGNED, EnumSet.of(DONE, CANCELLED));

        allowedTransitions.put(DONE, EnumSet.noneOf(TaskStatus.class)); // final status
        allowedTransitions.put(CANCELLED, EnumSet.noneOf(TaskStatus.class)); // final status
    }

    public boolean isAllowedStatus(TaskStatus status) {
        Set<TaskStatus> statuses = allowedTransitions.get(this);
        return statuses != null && statuses.contains(status);
    }
}