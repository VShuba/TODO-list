package shpp.shuba.todo_list.exceptions;

import org.springframework.context.MessageSource;

public class UserNotFoundException extends BaseLocalizedException {

    public UserNotFoundException(MessageSource messageSource) {
        super(messageSource, "error.user.not.found");
    }
}

