package shpp.shuba.todo_list.dto;

import lombok.Data;
import shpp.shuba.todo_list.models.RoleName;

import java.util.Set;

@Data
public class RegisterDTO {
    private String username;
    private String password;
    private String email;
    private Set<RoleName> roles;
} // ADMIN, USER, VLAD
// 1 - SAVE in ROLES ADMIN, USER
// 2 - ability to register
// 3 - USER, VLAD, KOSTYA