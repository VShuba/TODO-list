package shpp.shuba.todo_list.service.role;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shpp.shuba.todo_list.models.Role;
import shpp.shuba.todo_list.repository.RoleRepository;

import java.util.List;

/**
 * RoleService
 * Отримання ролей
 * Додавання ролей (якщо адміну дозволено створювати кастомні ролі)
 */
@Service
@RequiredArgsConstructor
public class RoleService implements IRoleService {

    private final RoleRepository roleRepository;

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Role createRole(Role role) {
        return roleRepository.save(role);
    }
}
