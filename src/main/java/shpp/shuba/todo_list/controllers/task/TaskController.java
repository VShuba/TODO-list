package shpp.shuba.todo_list.controllers.task;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shpp.shuba.todo_list.dto.RequestTaskDTO;
import shpp.shuba.todo_list.dto.ResponseTaskDTO;
import shpp.shuba.todo_list.models.TaskStatus;
import shpp.shuba.todo_list.service.task.ITaskService;

import java.util.List;

/**
 * TaskController (/tasks)
 * POST / – створити задачу
 * GET /{id} – отримати задачу
 * GET / – отримати список задач
 * PATCH /{id} – оновити опис, дедлайн
 * PATCH /{id}/status – змінити статус задачі
 * DELETE /{id} – видалити задачу
 */
@Tag(name = "Tasks", description = "Task management API")
@RestController
@RequestMapping("/api/v1/tasks")
@RequiredArgsConstructor
public class TaskController {
    private final ITaskService taskService;

    @Operation(summary = "Create a new task", description = "Creates a new task for the given user")
    @PostMapping("/")
    public ResponseEntity<ResponseTaskDTO> createTask(@RequestParam Long userId, @Valid @RequestBody RequestTaskDTO taskDTO) {
        return ResponseEntity.ok(taskService.createTask(userId, taskDTO));
    }

    @Operation(summary = "Get task by ID", description = "Fetches a specific task by its ID")
    @GetMapping("/{id}")
    public ResponseEntity<ResponseTaskDTO> getTask(@PathVariable Long id) {
        return ResponseEntity.ok(taskService.getTaskById(id));
    }

    @Operation(summary = "Get all tasks for a user", description = "Retrieves all tasks associated with a given user")
    @GetMapping("/")
    public ResponseEntity<List<ResponseTaskDTO>> getTasksByUser(@RequestParam Long userId) {
        return ResponseEntity.ok(taskService.getTasksByUser(userId));
    }

    @Operation(summary = "Update task status", description = "Changes task status with validation")
    @PatchMapping("/{id}/status")
    public ResponseEntity<ResponseTaskDTO> updateTaskStatus(@PathVariable Long id, @Valid @RequestParam TaskStatus newStatus) {
        return ResponseEntity.ok(taskService.updateTaskStatus(id, newStatus));
    }

    @Operation(summary = "Delete a task", description = "Removes a task by its ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }
}