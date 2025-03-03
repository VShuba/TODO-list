package shpp.shuba.todo_list.service.task;

import shpp.shuba.todo_list.dto.InitTaskDTO;
import shpp.shuba.todo_list.dto.TaskDTO;
import shpp.shuba.todo_list.models.TaskStatus;

import java.util.List;

public interface ITaskService {
    TaskDTO createTask(Long userId, InitTaskDTO description);

    TaskDTO getTaskById(Long taskId);

    List<TaskDTO> getTasksByUser(Long userId);

    TaskDTO updateTaskStatus(Long taskId, TaskStatus newStatus);

    void deleteTask(Long taskId);
}
