package shpp.shuba.todo_list.exceptions;

import shpp.shuba.todo_list.models.TaskStatus;

public class WrongStatusException extends RuntimeException {
    private static final String WRONG_STATUS_MESSAGE = "You can't go this status: ";

    public WrongStatusException(TaskStatus status) {
        super(WRONG_STATUS_MESSAGE + status);
    }
}
