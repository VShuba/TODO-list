package shpp.shuba.todo_list.service.role;

import shpp.shuba.todo_list.dto.ResponseUserDTO;
import shpp.shuba.todo_list.models.Role;
import shpp.shuba.todo_list.models.RoleName;

import java.util.List;

public interface IRoleService {
    List<Role> getAllRoles();

    Role createRole(Role roleDTO);

    ResponseUserDTO addUserRoleById(Long id, RoleName roleName);

    ResponseUserDTO removeUserRoleById(Long id, RoleName roleName);
}
