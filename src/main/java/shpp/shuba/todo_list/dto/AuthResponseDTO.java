package shpp.shuba.todo_list.dto;

import lombok.Builder;
import lombok.Data;
import shpp.shuba.todo_list.models.RoleName;

import java.util.Set;

@Data
@Builder
public class AuthResponseDTO {
    private String username;
    private String accessToken;
    private Set<RoleName> roles;
}
