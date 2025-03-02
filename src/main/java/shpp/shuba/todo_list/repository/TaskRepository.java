package shpp.shuba.todo_list.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shpp.shuba.todo_list.models.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
// use projection
// use Pageable