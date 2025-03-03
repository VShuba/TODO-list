package shpp.shuba.todo_list.service.task;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shpp.shuba.todo_list.dto.InitTaskDTO;
import shpp.shuba.todo_list.dto.TaskDTO;
import shpp.shuba.todo_list.exceptions.TaskNotFoundException;
import shpp.shuba.todo_list.exceptions.UserNotFoundException;
import shpp.shuba.todo_list.exceptions.WrongStatusException;
import shpp.shuba.todo_list.models.MyUser;
import shpp.shuba.todo_list.models.Task;
import shpp.shuba.todo_list.models.TaskStatus;
import shpp.shuba.todo_list.repository.TaskRepository;
import shpp.shuba.todo_list.repository.UserRepository;

import java.util.List;

/**
 * TaskService
 * Створення задачі
 * Отримання задачі за ID
 * Отримання списку задач користувача
 * Оновлення задачі (опис, дедлайн, статус тощо)
 * Зміна статусу задачі (з дотриманням правил переходу між станами)
 * Видалення задачі
 */
@Service
@RequiredArgsConstructor
public class TaskService implements ITaskService {
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    @Override
    public TaskDTO createTask(Long userId, InitTaskDTO dto) {
        MyUser user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);

        Task task = Task.builder()
                .title(dto.title())
                .description(dto.description())
                .dueDate(dto.date())
                .status(TaskStatus.PLANNED) // first status
                .user(user)
                .build();

        return toTaskDto(taskRepository.save(task));
    }

    @Override
    public TaskDTO getTaskById(Long taskId) {
        return toTaskDto(taskRepository.findById(taskId).orElseThrow(TaskNotFoundException::new));
    }

    @Override
    public List<TaskDTO> getTasksByUser(Long userId) {
        return toDTOList(taskRepository.findByUserId(userId));
    }

    @Override
    public TaskDTO updateTaskStatus(Long taskId, TaskStatus newStatus) { // 1 -> 1 -> status ?

        Task task = taskRepository.findById(taskId).orElseThrow(TaskNotFoundException::new);

        if (!task.getStatus().isAllowedStatus(newStatus)) {
            throw new WrongStatusException(newStatus);
        }

//        TaskStatus currentStatus = task.getStatus();
//        task.setStatus(currentStatus.isAllowedStatus(newStatus) ? newStatus : task.getStatus());

        task.setStatus(newStatus);
        taskRepository.save(task);

        return toTaskDto(task);
    }

    @Override
    public void deleteTask(Long taskId) {
        taskRepository.deleteById(taskId);
    }

    private List<TaskDTO> toDTOList(List<Task> tasks) {
        return tasks.stream().map(this::toTaskDto).toList();
    }

    private TaskDTO toTaskDto(Task task) {
        return new TaskDTO(task.getTitle(), task.getDescription(), task.getStatus(), task.getDueDate());
    }
}
