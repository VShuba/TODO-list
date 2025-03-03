package shpp.shuba.todo_list.exceptions;

public class TaskNotFoundException extends RuntimeException {

    private static final String TASK_NOT_FOUND = "TASK_NOT_FOUND";

    public TaskNotFoundException() {
        super(TASK_NOT_FOUND);
    }
}
