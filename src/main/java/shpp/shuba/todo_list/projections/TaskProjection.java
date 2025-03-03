package shpp.shuba.todo_list.projections;

import shpp.shuba.todo_list.models.TaskStatus;

public interface TaskProjection {
    Long getId();
    String getDescription();
    TaskStatus getStatus();
}
 // todo НЕ ЮЗАЮ