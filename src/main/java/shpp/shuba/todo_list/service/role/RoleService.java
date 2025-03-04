package shpp.shuba.todo_list.service.role;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shpp.shuba.todo_list.dto.ResponseUserDTO;
import shpp.shuba.todo_list.exceptions.RoleNotAssignedToUserException;
import shpp.shuba.todo_list.exceptions.ThereIsNoRoleInDB;
import shpp.shuba.todo_list.exceptions.TryingToTouchSuperAdmin;
import shpp.shuba.todo_list.exceptions.UserNotFoundException;
import shpp.shuba.todo_list.models.MyUser;
import shpp.shuba.todo_list.models.Role;
import shpp.shuba.todo_list.models.RoleName;
import shpp.shuba.todo_list.repository.RoleRepository;
import shpp.shuba.todo_list.repository.UserRepository;
import shpp.shuba.todo_list.service.user.UserService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class RoleService implements IRoleService {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final UserService userService;

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Role createRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public ResponseUserDTO addUserRoleById(Long id, RoleName roleName) {
        userService.throwIfSuperAdmin(id);

        MyUser user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);

        Set<Role> userRoles = new HashSet<>(user.getRoles());

        Role role = roleRepository.findByName(roleName)
                .orElseThrow(ThereIsNoRoleInDB::new);

        userRoles.add(role);
        user.setRoles(userRoles);

        return userService.userToDto(userRepository.save(user));
    }

    @Override
    public ResponseUserDTO removeUserRoleById(Long id, RoleName roleName) {

        userService.throwIfSuperAdmin(id);

        MyUser user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        Set<Role> userRoles = new HashSet<>(user.getRoles());

        Role roleToRemove = roleRepository.findByName(roleName)
                .orElseThrow(ThereIsNoRoleInDB::new);

        if (!userRoles.contains(roleToRemove)) {
            throw new RoleNotAssignedToUserException();
        }

        userRoles.remove(roleToRemove);
        user.setRoles(userRoles);

        return userService.userToDto(userRepository.save(user));
    }
}