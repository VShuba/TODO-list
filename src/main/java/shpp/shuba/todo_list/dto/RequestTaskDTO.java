package shpp.shuba.todo_list.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

public record RequestTaskDTO(
        @NotBlank @Size(min = 3, max = 100)
        @Schema(example = "Buy groceries", description = "Title of the task to be created")
        String title,

        @Size(max = 500)
        @Schema(example = "Purchase milk, eggs, and bread from the store", description = "Optional task description")
        String description,

        @FutureOrPresent
        @Schema(example = "2025-05-05", description = "Planned date for the task")
        LocalDate date
) {}
