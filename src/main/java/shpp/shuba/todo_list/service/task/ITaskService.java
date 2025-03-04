package shpp.shuba.todo_list.service.task;

import shpp.shuba.todo_list.dto.RequestTaskDTO;
import shpp.shuba.todo_list.dto.ResponseTaskDTO;
import shpp.shuba.todo_list.models.TaskStatus;

import java.util.List;

public interface ITaskService {

    ResponseTaskDTO createTask(Long userId, RequestTaskDTO description);

    ResponseTaskDTO getTaskById(Long taskId);

    List<ResponseTaskDTO> getTasksByUser(Long userId);

    ResponseTaskDTO updateTaskStatus(Long taskId, TaskStatus newStatus);

    void deleteTask(Long taskId);
}


