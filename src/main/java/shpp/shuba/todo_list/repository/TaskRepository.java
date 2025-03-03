package shpp.shuba.todo_list.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shpp.shuba.todo_list.models.Task;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByUserId(Long userId);
}
// use projection
// use Pageable