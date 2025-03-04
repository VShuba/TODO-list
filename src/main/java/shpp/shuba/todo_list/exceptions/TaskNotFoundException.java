package shpp.shuba.todo_list.exceptions;

import org.springframework.context.MessageSource;

public class TaskNotFoundException extends BaseLocalizedException {

    public TaskNotFoundException(MessageSource messageSource) {
        super(messageSource, "error.task.not.found");
    }
}

