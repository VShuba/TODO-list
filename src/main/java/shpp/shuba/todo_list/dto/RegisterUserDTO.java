package shpp.shuba.todo_list.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterUserDTO(
        @NotBlank
        @Size(min = 3, max = 50)
        @Schema(example = "Mike", description = "User's chosen username")
        String username,

        @NotBlank @Size(min = 8, max = 100)
        @Schema(example = "SecurePassword123", description = "User's password (at least 8 characters)")
        String password,

        @NotBlank @Email @Size(max = 100)
        @Schema(example = "mike@example.com", description = "User's email for registration")
        String email
) {
}
