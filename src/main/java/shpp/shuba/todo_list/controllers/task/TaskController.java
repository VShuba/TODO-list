package shpp.shuba.todo_list.controllers.task;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import shpp.shuba.todo_list.dto.RequestTaskDTO;
import shpp.shuba.todo_list.dto.ResponseTaskDTO;
import shpp.shuba.todo_list.models.TaskStatus;
import shpp.shuba.todo_list.service.task.ITaskService;

import java.util.List;

@Tag(name = "Tasks", description = "Task management API")
@RestController
@RequestMapping("/api/v1/tasks")
@RequiredArgsConstructor
public class TaskController {
    private final ITaskService taskService;

    @Operation(summary = "Create a new task", description = "Creates a new task for the given user")
    @PreAuthorize("hasAnyRole('USER','ADMIN','MODERATOR')")
    @PostMapping("/")
    public ResponseEntity<ResponseTaskDTO> createTask(@RequestParam Long userId, @Valid @RequestBody RequestTaskDTO taskDTO) {
        return ResponseEntity.ok(taskService.createTask(userId, taskDTO));
    }

    @Operation(summary = "Get task by ID", description = "Fetches a specific task by its ID")
    @PreAuthorize("hasAnyRole('USER','ADMIN','MODERATOR')")
    @GetMapping("/{id}")
    public ResponseEntity<ResponseTaskDTO> getTask(@PathVariable Long id) {
        return ResponseEntity.ok(taskService.getTaskById(id));
    }

    @Operation(summary = "Get all tasks for a user", description = "Retrieves all tasks associated with a given user")
    @PreAuthorize("#id == authentication.principal.id or hasAnyRole('ADMIN','MODERATOR')")
    @GetMapping("/byUser/{id}")
    public ResponseEntity<List<ResponseTaskDTO>> getTasksByUser(@PathVariable Long id) {
        return ResponseEntity.ok(taskService.getTasksByUser(id));
    }

    @Operation(summary = "Update task status", description = "Changes task status with validation")
    @PreAuthorize("#id == authentication.principal.id or hasAnyRole('ADMIN','MODERATOR')")
    @PatchMapping("/{id}/status")
    public ResponseEntity<ResponseTaskDTO> updateTaskStatus(@PathVariable Long id, @Valid @RequestParam TaskStatus newStatus) {
        return ResponseEntity.ok(taskService.updateTaskStatus(id, newStatus));
    }

    @Operation(summary = "Delete a task", description = "Removes a task by its ID")
    @PreAuthorize("#id == authentication.principal.id or hasAnyRole('ADMIN','MODERATOR')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }
}