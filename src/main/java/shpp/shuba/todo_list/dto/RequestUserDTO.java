package shpp.shuba.todo_list.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RequestUserDTO(
        @NotBlank @Size(min = 3, max = 50)
        @Schema(example = "Mike", description = "User's unique username")
        String username,

        @NotBlank @Email @Size(max = 100)
        @Schema(example = "mike@example.com", description = "User's email address")
        String email
) {
}
