package shpp.shuba.todo_list.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import shpp.shuba.todo_list.models.RoleName;

import java.util.Set;

public record ResponseUserDTO(
        @NotBlank @Size(min = 3, max = 50)
        @Schema(example = "Mike", description = "User's unique username")
        String username,

        @NotBlank @Email @Size(max = 100)
        @Schema(example = "mike@example.com", description = "User's email address")
        String email,

        @NotEmpty
        @Schema(example = "[\"ROLE_USER\", \"ROLE_MODERATOR\"]", description = "Roles assigned to the user")
        Set<RoleName> roles
) {}
