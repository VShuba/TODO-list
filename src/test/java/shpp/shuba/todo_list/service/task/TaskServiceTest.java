package shpp.shuba.todo_list.service.task;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import shpp.shuba.todo_list.dto.ResponseTaskDTO;
import shpp.shuba.todo_list.exceptions.TaskNotFoundException;
import shpp.shuba.todo_list.exceptions.WrongStatusException;
import shpp.shuba.todo_list.models.MyUser;
import shpp.shuba.todo_list.models.Task;
import shpp.shuba.todo_list.models.TaskStatus;
import shpp.shuba.todo_list.repository.TaskRepository;


import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskService taskService;

    private Task task;

    @BeforeEach
    void setUp() {
        MyUser user = new MyUser();
        user.setId(1L);

        task = Task.builder()
                .id(1L)
                .title("Test Task")
                .description("Test Description")
                .status(TaskStatus.PLANNED)
                .dueDate(LocalDate.now().plusDays(5))
                .user(user)
                .build();
    }

    @Test
    @DisplayName("Successful status update: PLANNED → WORK_IN_PROGRESS")
    void updateTaskStatus_Successful() {

        TaskStatus newStatus = TaskStatus.WORK_IN_PROGRESS;
        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));
        when(taskRepository.save(any(Task.class))).thenReturn(task);

        ResponseTaskDTO updatedTask = taskService.updateTaskStatus(1L, newStatus);

        assertNotNull(updatedTask);
        assertEquals(newStatus, updatedTask.status());
        verify(taskRepository).save(task);
    }

    @Test
    @DisplayName("Error: Change to prohibited status (PLANNED → DONE)")
    void updateTaskStatus_ThrowsWrongStatusException() {

        TaskStatus invalidStatus = TaskStatus.DONE;
        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));

        assertThrows(WrongStatusException.class, () -> taskService.updateTaskStatus(1L, invalidStatus));
        verify(taskRepository, never()).save(any(Task.class));
    }

    @Test
    @DisplayName("Error: Task not found")
    void updateTaskStatus_ThrowsTaskNotFoundException() {
        when(taskRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(TaskNotFoundException.class, () -> taskService.updateTaskStatus(1L, TaskStatus.WORK_IN_PROGRESS));
        verify(taskRepository, never()).save(any(Task.class));
    }

}
