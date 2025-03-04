package shpp.shuba.todo_list.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import shpp.shuba.todo_list.models.TaskStatus;
import java.time.LocalDate;

public record ResponseTaskDTO(
        @NotBlank @Size(min = 3, max = 100)
        @Schema(example = "Complete project report", description = "Title of the task")
        String title,

        @Size(max = 500)
        @Schema(example = "Prepare the final project report for submission", description = "Detailed description of the task")
        String description,

        @NotNull
        @Schema(example = "IN_PROGRESS", description = "Current status of the task")
        TaskStatus status,

        @FutureOrPresent
        @Schema(example = "2025-05-05", description = "Due date for the task (must be today or in the future)")
        LocalDate date
) {}

// нєма айди
// нєма юзера
