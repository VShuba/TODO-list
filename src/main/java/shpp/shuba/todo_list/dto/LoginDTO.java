package shpp.shuba.todo_list.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LoginDTO {
    @NotBlank
    @Size(min = 3, max = 50)
    @Schema(example = "Mike", description = "User's username")
    private String username;

    @NotBlank @Size(min = 8, max = 100)
    @Schema(example = "SecurePassword123", description = "User's password")
    private String password;
}
