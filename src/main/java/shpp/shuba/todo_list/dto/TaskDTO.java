package shpp.shuba.todo_list.dto;

import shpp.shuba.todo_list.models.TaskStatus;

import java.time.LocalDate;

public record TaskDTO(
        String description,
        TaskStatus status,
        LocalDate date
) {
}
