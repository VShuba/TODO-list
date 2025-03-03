package shpp.shuba.todo_list.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import shpp.shuba.todo_list.dto.ResponseUserDTO;
import shpp.shuba.todo_list.models.MyUser;

import java.util.Optional;

public interface UserRepository extends JpaRepository<MyUser, Long> {
    Optional<MyUser> findByUsername(String username);

    Page<ResponseUserDTO> findAllBy(Pageable pageable);

    boolean existsByUsername(String username);
}
// use projection
// use Pageable