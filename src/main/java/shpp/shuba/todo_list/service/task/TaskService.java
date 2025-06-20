package shpp.shuba.todo_list.service.task;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import shpp.shuba.todo_list.dto.RequestTaskDTO;
import shpp.shuba.todo_list.dto.ResponseTaskDTO;
import shpp.shuba.todo_list.exceptions.TaskNotFoundException;
import shpp.shuba.todo_list.exceptions.UserNotFoundException;
import shpp.shuba.todo_list.exceptions.WrongStatusException;
import shpp.shuba.todo_list.models.MyUser;
import shpp.shuba.todo_list.models.Task;
import shpp.shuba.todo_list.models.TaskStatus;
import shpp.shuba.todo_list.repository.TaskRepository;
import shpp.shuba.todo_list.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService implements ITaskService {
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final MessageSource messageSource;

    @Override
    public ResponseTaskDTO createTask(Long userId, RequestTaskDTO dto) {
        MyUser user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(messageSource));

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
    public ResponseTaskDTO getTaskById(Long taskId) {
        return toTaskDto(taskRepository.findById(taskId).orElseThrow(() -> new TaskNotFoundException(messageSource)));
    }

    @Override
    public List<ResponseTaskDTO> getTasksByUser(Long userId) {
        return toDTOList(taskRepository.findByUserId(userId));
    }

    @Override
    public ResponseTaskDTO updateTaskStatus(Long taskId, TaskStatus newStatus) { // 1 -> 1 -> status ?

        Task task = taskRepository.findById(taskId).orElseThrow(() -> new TaskNotFoundException(messageSource));

        if (!task.getStatus().isAllowedStatus(newStatus)) {
            throw new WrongStatusException(messageSource);
        }

        task.setStatus(newStatus);
        taskRepository.save(task);

        return toTaskDto(task);
    }

    @Override
    public void deleteTask(Long taskId) {
        taskRepository.deleteById(taskId);
    }

    private List<ResponseTaskDTO> toDTOList(List<Task> tasks) {
        return tasks.stream().map(this::toTaskDto).toList();
    }

    private ResponseTaskDTO toTaskDto(Task task) {
        return new ResponseTaskDTO(task.getTitle(), task.getDescription(), task.getStatus(), task.getDueDate());
    }
}
