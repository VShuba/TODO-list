package shpp.shuba.todo_list.exceptions;

import org.springframework.context.MessageSource;

public class RoleNotAssignedToUserException extends BaseLocalizedException {

    public RoleNotAssignedToUserException(MessageSource messageSource) {
        super(messageSource, "error.role.not.assigned");
    }
}