package shpp.shuba.todo_list.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shpp.shuba.todo_list.models.Role;
import shpp.shuba.todo_list.models.RoleName;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName name);
}
