package shpp.shuba.todo_list.dto;

import shpp.shuba.todo_list.models.Role;
import java.util.Set;

public record UserDTO(
        String username,
        String email,
        Set<Role> roles
){}
