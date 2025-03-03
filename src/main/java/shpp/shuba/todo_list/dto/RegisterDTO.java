package shpp.shuba.todo_list.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import shpp.shuba.todo_list.models.RoleName;
import java.util.Set;

@Data
public class RegisterDTO {
    @NotBlank
    @Size(min = 3, max = 50)
    @Schema(example = "Mike", description = "User's chosen username")
    private String username;

    @NotBlank @Size(min = 8, max = 100)
    @Schema(example = "SecurePassword123", description = "User's password (at least 8 characters)")
    private String password;

    @NotBlank @Email @Size(max = 100)
    @Schema(example = "mike@example.com", description = "User's email for registration")
    private String email;

    @NotEmpty
    @Schema(example = "[\"USER\", \"MODERATOR\"]", description = "Roles assigned to the new user")
    private Set<RoleName> roles;
}

// ADMIN, USER, VLAD
// 1 - SAVE in ROLES ADMIN, USER
// 2 - ability to register
// 3 - USER, VLAD, KOSTYA