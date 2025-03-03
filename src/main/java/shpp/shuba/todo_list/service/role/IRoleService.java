package shpp.shuba.todo_list.service.role;

import shpp.shuba.todo_list.models.Role;

import java.util.List;

public interface IRoleService {
    List<Role> getAllRoles();

    Role createRole(Role roleDTO);
}
